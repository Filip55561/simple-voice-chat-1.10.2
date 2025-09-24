package de.maxhenkel.voicechat.mixin;

import java.util.Map;

import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.Mixins;

import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;

@IFMLLoadingPlugin.MCVersion("1.10.2")
@IFMLLoadingPlugin.Name("MixinCoreMod")
public class MixinCoreMod implements IFMLLoadingPlugin {
	static {
		MixinBootstrap.init();
		Mixins.addConfiguration("voicechat.mixins.json");
	}

	@Override
	public String[] getASMTransformerClass() {
		// TODO Auto-generated method stub
		return new String[0];
	}

	@Override
	public String getModContainerClass() {
		return null;
	}

	@Override
	public String getSetupClass() {
		return null;
	}

	@Override
	public void injectData(Map<String, Object> data) {
		
	}

	@Override
	public String getAccessTransformerClass() {
		return null;
	}
}
