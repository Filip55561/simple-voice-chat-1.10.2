package de.maxhenkel.voicechat;

import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.Mixins;
import org.spongepowered.asm.mixin.MixinEnvironment.Option;

import de.maxhenkel.voicechat.intercompatibility.ClientCompatibilityManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ForgeVoicechatClientMod extends VoicechatClient {

    public ForgeVoicechatClientMod(FMLPreInitializationEvent event) {}
    
    public void preInit(FMLPreInitializationEvent event) {
    	/*
    	MixinBootstrap.init();
    	MixinEnvironment.getCurrentEnvironment().setOption(Option.IGNORE_REQUIRED, true);
        MixinEnvironment.getCurrentEnvironment().setObfuscationContext("searge");
        Mixins.addConfiguration("voicechat.mixins.json");
        System.out.println("Sucessfully init Mixin");
        System.out.println("Client Configs: " + Mixins.getConfigs());
        */
    }

    public void clientSetup(FMLInitializationEvent event) {
        initializeClient();
        MinecraftForge.EVENT_BUS.register(ClientCompatibilityManager.INSTANCE);
    }
}
