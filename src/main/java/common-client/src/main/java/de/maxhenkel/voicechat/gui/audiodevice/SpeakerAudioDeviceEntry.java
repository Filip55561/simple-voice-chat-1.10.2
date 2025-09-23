package de.maxhenkel.voicechat.gui.audiodevice;

import de.maxhenkel.voicechat.Voicechat;
import de.maxhenkel.voicechat.gui.tooltips.TestSpeakerSupplier;
import de.maxhenkel.voicechat.gui.widgets.ImageButton;
import de.maxhenkel.voicechat.voice.client.TestSoundPlayer;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.fml.client.FMLClientHandler;

import javax.annotation.Nullable;
import java.util.function.Supplier;

public class SpeakerAudioDeviceEntry extends AudioDeviceEntry {

    public static final ResourceLocation SPEAKER_ICON = new ResourceLocation(Voicechat.MODID, "textures/icons/test_speaker.png");

    private ImageButton testButton;

    public SpeakerAudioDeviceEntry(String device, ITextComponent name, @Nullable ResourceLocation icon, Supplier<Boolean> isSelected) {
        super(device, name, icon, isSelected);

        testButton = new ImageButton(10000, 0, 0, SPEAKER_ICON, button -> {
            testButton.enabled = false;
            TestSoundPlayer.playTestSound(() -> {
                testButton.enabled = true;
            });
        }, new TestSpeakerSupplier());
    }

    @Override
    public void drawEntry(int slotIndex, int left, int top, int width, int height, int mouseX, int mouseY, boolean hovered) {
        super.drawEntry(slotIndex, left, top, width, height, mouseX, mouseY, hovered);
        boolean selected = isSelected.get();
        if (selected && hovered) {
            testButton.visible = true;
            testButton.xPosition = left + (width - testButton.width - PADDING);
            testButton.yPosition = top + (height - testButton.height) / 2;
            testButton.drawButton(Minecraft.getMinecraft(), mouseX, mouseY);
        } else {
            testButton.visible = false;
        }
    }

    @Override
    public boolean mousePressed(int slotIndex, int mouseX, int mouseY, int mouseEvent, int relativeX, int relativeY) {
        if (testButton.mousePressed(minecraft, mouseX, mouseY)) {
            testButton.onPress();
            return true;
        }
        return super.mousePressed(slotIndex, mouseX, mouseY, mouseEvent, relativeX, relativeY);
    }
}
