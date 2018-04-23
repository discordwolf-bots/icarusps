package ethos.model.players.packets.commands.all;

import ethos.model.players.Player;
import ethos.model.players.packets.commands.Command;

public class Claim extends Command {

	@Override
	public void execute(Player player, String input) {
		String[] args = input.split("-");
//		if (args.length == 0) {
//			player.sendMessage("Please use [::claim], [::claim-amount], or [::claim-all].");
//			return;
//		}
		final String playerName = player.playerName;
		//final String id = args[0];
		final String id = "1";
		final String amount = args.length == 2 ? args[0] : "1";
		if(amount != "all") {
			player.sendMessage("Did you know? You can do <col=ff0000>[::reward-all]</col> to redeem all votes at once");
		}

		com.everythingrs.vote.Vote.service.execute(new Runnable() {
			@Override
			public void run() {
				try {
					com.everythingrs.vote.Vote[] reward = com.everythingrs.vote.Vote.reward(
							"y0kpf77retzl3bajq2949rudi1mofrkr7d0ndh6zuycmh7iudiliswg86puplolel3hjrio1or",
							playerName, id, amount);
					if (reward[0].message != null) {
						player.sendMessage(reward[0].message);
						return;
					}
					player.getItems().addItem(reward[0].reward_id, reward[0].give_amount);
					if(reward[0].vote_points == 0) {
						player.sendMessage(
								"Thank you for voting! You have <col=ff0000><shad=000000>" + reward[0].vote_points + "</shad></col> vote points remaining");
					} else {
						player.sendMessage(
								"Thank you for voting! You have <col=0000ff><shad=000000>" + reward[0].vote_points + "</shad></col> vote points remaining");						
					}
					
				} catch (Exception e) {
					player.sendMessage("Api Services are currently offline. Please check back shortly");
					e.printStackTrace();
				}
			}

		});
	}

}
