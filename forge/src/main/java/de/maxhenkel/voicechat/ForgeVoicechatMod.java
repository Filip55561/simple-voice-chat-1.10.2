package de.maxhenkel.voicechat;

import de.maxhenkel.voicechat.command.VoicechatCommand;
import de.maxhenkel.voicechat.intercompatibility.CommonCompatibilityManager;
import de.maxhenkel.voicechat.intercompatibility.ForgeCommonCompatibilityManager;
import de.maxhenkel.voicechat.permission.ForgePermissionManager;
import de.maxhenkel.voicechat.permission.PermissionManager;
import de.maxhenkel.voicechat.voice.client.ClientForgeEvents;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.*;

import javax.annotation.Nullable;

import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.MixinEnvironment.Option;
import org.spongepowered.asm.mixin.Mixins;


@Mod(modid = ForgeVoicechatMod.MODID, acceptedMinecraftVersions = "[1.10.2]", acceptableRemoteVersions = "*", updateJSON = "https://maxhenkel.de/update/voicechat.json", guiFactory = "de.maxhenkel.voicechat.VoicechatGuiFactory")
public class ForgeVoicechatMod extends Voicechat {

    public static ForgeVoicechatMod INSTANCE;
    @Nullable
    private static ForgeVoicechatClientMod CLIENT_MOD;

    private final ForgeCommonCompatibilityManager compatibilityManager;

    public ForgeVoicechatMod() {
        INSTANCE = this;
        compatibilityManager = ((ForgeCommonCompatibilityManager) CommonCompatibilityManager.INSTANCE);
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        if (event.getSide().isClient()) {
            CLIENT_MOD = new ForgeVoicechatClientMod(event);
            CLIENT_MOD.preInit(event);
        }
        
        /*
        MixinBootstrap.init();
        MixinEnvironment.getCurrentEnvironment().setOption(Option.IGNORE_REQUIRED, true);
        MixinEnvironment.getCurrentEnvironment().setObfuscationContext("searge");
        Mixins.addConfiguration("voicechat.mixins.json");
        System.out.println("Server Configs: " + Mixins.getConfigs());
         */
        compatibilityManager.preInit(event);
        
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        initialize();
        MinecraftForge.EVENT_BUS.register(compatibilityManager);
        //MinecraftForge.EVENT_BUS.register(new ClientForgeEvents());
        ((ForgePermissionManager) PermissionManager.INSTANCE).registerPermissions();
        if (CLIENT_MOD != null) {
            CLIENT_MOD.clientSetup(event);
        }
    }

    @Mod.EventHandler
    public void serverStarting(FMLServerStartingEvent event) {
        event.registerServerCommand(new VoicechatCommand());
    }

    @Mod.EventHandler
    public void serverStarting(FMLServerStartedEvent event) {
        compatibilityManager.serverStarted(event);
    }

    @Mod.EventHandler
    public void serverStopping(FMLServerStoppingEvent event) {
        compatibilityManager.serverStopping(event);
    }

}