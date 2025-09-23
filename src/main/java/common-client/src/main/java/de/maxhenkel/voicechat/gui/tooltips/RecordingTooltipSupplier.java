package de.maxhenkel.voicechat.gui.tooltips;

import de.maxhenkel.voicechat.gui.ScreenBase;
import de.maxhenkel.voicechat.gui.widgets.ImageButton;
import de.maxhenkel.voicechat.voice.client.ClientManager;
import de.maxhenkel.voicechat.voice.client.ClientVoicechat;
import net.minecraft.util.text.TextComponentTranslation;

import java.util.ArrayList;
import java.util.List;

public class RecordingTooltipSupplier implements ImageButton.TooltipSupplier {

    public static final TextComponentTranslation RECORDING_ENABLED = new TextComponentTranslation("message.voicechat.recording.enabled");
    public static final TextComponentTranslation RECORDING_DISABLED = new TextComponentTranslation("message.voicechat.recording.disabled");

    private final ScreenBase screen;

    public RecordingTooltipSupplier(ScreenBase screen) {
        this.screen = screen;
    }

    @Override
    public void onTooltip(ImageButton button, int mouseX, int mouseY) {
        ClientVoicechat client = ClientManager.getClient();
        if (client == null) {
            return;
        }

        List<String> tooltip = new ArrayList<>();

        if (client.getRecorder() == null) {
            tooltip.add(RECORDING_DISABLED.getUnformattedComponentText());
        } else {
            tooltip.add(RECORDING_ENABLED.getUnformattedComponentText());
        }

        screen.drawTooltip(tooltip, mouseX, mouseY);
    }

}
