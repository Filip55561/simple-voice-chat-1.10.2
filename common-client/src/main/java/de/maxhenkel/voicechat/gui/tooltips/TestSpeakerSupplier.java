package de.maxhenkel.voicechat.gui.tooltips;

import java.util.Collections;

import de.maxhenkel.voicechat.gui.ScreenBase;
import de.maxhenkel.voicechat.gui.widgets.ImageButton;
import net.minecraft.client.Minecraft;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fml.client.FMLClientHandler;

public class TestSpeakerSupplier implements ImageButton.TooltipSupplier {

    public static final ITextComponent TEST_SPEAKER = new TextComponentTranslation("message.voicechat.test_speaker");

    @Override
    public void onTooltip(ImageButton button, int mouseX, int mouseY) {
    	ScreenBase screen = (ScreenBase) Minecraft.getMinecraft().currentScreen;
        if (screen == null) {
            return;
        }
        screen.drawTooltip(Collections.singletonList(TEST_SPEAKER.getUnformattedComponentText()), mouseX, mouseY);
    }
}
