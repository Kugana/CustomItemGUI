package CIG.Versions;


import org.bukkit.craftbukkit.v1_14_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_14_R1.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_14_R1.PacketPlayOutChat;
import net.minecraft.server.v1_14_R1.PacketPlayOutTitle;
import net.minecraft.server.v1_14_R1.PacketPlayOutTitle.EnumTitleAction;



public class Version_v1_14_R1 implements NMS {

	@Override
	public void sendTitle(Player p, String titleMessage, int fadIn, int onScreen, int fedeOut) {
		PacketPlayOutTitle title = new PacketPlayOutTitle(EnumTitleAction.TITLE,
				ChatSerializer.a("{\"text\": \"" + titleMessage + "\"}"), fadIn, onScreen, fedeOut);

		((CraftPlayer) p).getHandle().playerConnection.sendPacket(title);

	}

	@Override
	public void sendActionbar(Player p, String message) {
		PacketPlayOutChat packet = new PacketPlayOutChat(ChatSerializer.a("{\"text\": \"" + message + "\"}"));
		((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet);

	}


}
