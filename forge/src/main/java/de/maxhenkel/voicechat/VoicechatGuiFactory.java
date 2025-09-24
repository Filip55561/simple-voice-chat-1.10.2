package de.maxhenkel.voicechat;

import de.maxhenkel.voicechat.gui.VoiceChatSettingsScreen;
import de.maxhenkel.voicechat.gui.onboarding.OnboardingManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.client.IModGuiFactory;

import java.util.Set;

public class VoicechatGuiFactory implements IModGuiFactory {

    @Override
    public void initialize(Minecraft minecraftInstance) {

    }

    @Override
    public Class<? extends GuiScreen> mainConfigGuiClass() {
        return VoiceChatSettingsScreen.class;
    }

    @Override
    public Set<RuntimeOptionCategoryElement> runtimeGuiCategories() {
        return null;
    }

	@SuppressWarnings("deprecation")
	@Override
	public RuntimeOptionGuiHandler getHandlerFor(RuntimeOptionCategoryElement element) {
		return null;
	}
	
	public static class wrapper extends GuiScreen {

        private final GuiScreen parent;

        public wrapper(GuiScreen parent) {
            this.parent = parent;
        }

        @Override
        public void initGui() {
            GuiScreen nextScreen;
            if (OnboardingManager.isOnboarding()) {
                nextScreen = OnboardingManager.getOnboardingScreen(parent);
            } else {
                nextScreen = new VoiceChatSettingsScreen(parent);
            }
            Minecraft.getMinecraft().displayGuiScreen(nextScreen);
        }
    }
	
	/*
    @Override
    public GuiScreen createConfigGui(GuiScreen parent) {
        if (OnboardingManager.isOnboarding()) {
            return OnboardingManager.getOnboardingScreen(parent);
        }
        return new VoiceChatSettingsScreen(parent);
    }
    */
}
