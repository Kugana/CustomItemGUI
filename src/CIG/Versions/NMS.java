package CIG.Versions;

import org.bukkit.entity.Player;

public interface NMS {
	
	public void sendTitle(Player p, String titleMessage,int fadIn,int onScreen, int fedeOut) ;
	
	public void sendActionbar(Player p, String message);
	

}
