package de.maxhenkel.voicechat.mixin;

import de.maxhenkel.voicechat.Voicechat;
import net.minecraft.client.resources.IResourcePack;
import net.minecraft.client.resources.ResourcePackRepository;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import de.maxhenkel.voicechat.resourcepacks.VoiceChatResourcePack;
import net.minecraft.util.text.TextComponentTranslation;

import java.lang.reflect.Constructor;
import java.util.List;

@Mixin(ResourcePackRepository.class)
public class ResourcePackRepositoryMixin {

    @Shadow
    private List<ResourcePackRepository.Entry> repositoryEntriesAll;

    /*
    @Inject(method = "updateRepositoryEntriesAll", at = @At("HEAD"))
    public void updateRepositoryEntriesAllHead(CallbackInfo info) {
        addResourcePack(VoicechatClient.CLASSIC_ICONS);
        addResourcePack(VoicechatClient.WHITE_ICONS);
        addResourcePack(VoicechatClient.BLACK_ICONS);
    }
    */
    
    private static VoiceChatResourcePack CLASSIC_ICONS = new VoiceChatResourcePack("classic_icons", new TextComponentTranslation("resourcepack.voicechat.classic_icons"));
    private static VoiceChatResourcePack WHITE_ICONS = new VoiceChatResourcePack("white_icons", new TextComponentTranslation("resourcepack.voicechat.white_icons"));
    private static VoiceChatResourcePack BLACK_ICONS = new VoiceChatResourcePack("black_icons", new TextComponentTranslation("resourcepack.voicechat.black_icons"));

    @Inject(method = "updateRepositoryEntriesAll", at = @At("RETURN"))
    public void updateRepositoryEntriesAllReturn(CallbackInfo info) {
        addResourcePack(CLASSIC_ICONS);
        addResourcePack(WHITE_ICONS);
        addResourcePack(BLACK_ICONS);
    }

    private void addResourcePack(IResourcePack resourcePack) {
        ResourcePackRepository.Entry entry = createEntry(resourcePack);
        if (entry == null) {
            return;
        }
        try {
            entry.updateResourcePack();
            repositoryEntriesAll.add(entry);
        } catch (Exception e) {
            Voicechat.LOGGER.error("Failed to update resource pack", e);
        }
    }

    private ResourcePackRepository.Entry createEntry(IResourcePack resourcePack) {
        try {
            Constructor<ResourcePackRepository.Entry> constructor = ResourcePackRepository.Entry.class.getDeclaredConstructor(IResourcePack.class);
            constructor.setAccessible(true);
            return constructor.newInstance(resourcePack);
        } catch (Exception e) {
            Voicechat.LOGGER.error("Failed to create resource pack entry", e);
            return null;
        }
    }

}
