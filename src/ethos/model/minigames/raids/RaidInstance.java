package ethos.model.minigames.raids;

import ethos.model.content.instances.SingleInstancedArea;
import ethos.model.minigames.rfd.DisposeTypes;
import ethos.model.npcs.NPCHandler;
import ethos.model.players.Boundary;
import ethos.model.players.Player;

public class RaidInstance extends SingleInstancedArea {

	public RaidInstance(Player player, Boundary boundary, int height) {
		super(player, boundary, height);
	}
	
	@Override
	public void onDispose() {
		end(DisposeTypes.INCOMPLETE);
	}
	
	public final void end(DisposeTypes dispose) {
		if (player == null) {
			return;
		}
		
		NPCHandler.kill(7604, height);
		NPCHandler.kill(7605, height);
		
		if (dispose == DisposeTypes.COMPLETE) {
			
		} else if (dispose == DisposeTypes.INCOMPLETE) {
			
		}
	}
	
	public int getHeight() {
		return height;
	}

}
