package veilleux.harry;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.util.Session;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import veilleux.harry.gui.SessionGui;

import java.awt.*;

@Mod(modid = "NEHS", name = "NotEnoughHypixelSkoiblock", version = "1.1.0")
public class SUSAuth {
    public static Minecraft mc = Minecraft.getMinecraft();
    public static Session originalSession = mc.session;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent e) {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onInitGuiPost(GuiScreenEvent.InitGuiEvent.Post e) {
        if (e.gui instanceof GuiMultiplayer) {
            e.buttonList.add(new GuiButton(999, 5, 5, 100, 20, "Sus-Auth"));
        }
    }

    @SubscribeEvent
    public void onDrawScreenPost(GuiScreenEvent.DrawScreenEvent.Post e) {
        if (e.gui instanceof GuiMultiplayer) {
            String status = String.format("User: §a%s §rUUID: §a%s", mc.session.getUsername(), mc.session.getPlayerID());
            Minecraft.getMinecraft().fontRendererObj.drawString(status, 115, 10, Color.GREEN.getRGB());
        }
    }

    @SubscribeEvent
    public void onActionPerformedPre(GuiScreenEvent.ActionPerformedEvent.Pre e) {
        if (e.gui instanceof GuiMultiplayer) {
            if (e.button.id == 999) {
                Minecraft.getMinecraft().displayGuiScreen(new SessionGui(e.gui));
            }
        }
    }
}
