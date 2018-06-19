package ethos.discord.api;

import ethos.model.players.Player;
import ethos.model.players.PlayerHandler;
import ethos.model.players.skills.Skill;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IEmoji;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.util.EmbedBuilder;

public class CommandProcessor {
	
	public static void processCommand(IMessage message, String prefix) {
		
		IUser sender = message.getAuthor();
		IChannel channel = message.getChannel();
		IGuild guild = message.getGuild();
		IChannel pm = sender.getOrCreatePMChannel();
		
		String[] command = message.getContent().toLowerCase().replaceFirst(prefix, "").split(" ");
		
		IChannel feedChannel = message.getClient().getChannelByID(458715499556110344L);
		
		
		/**
		 * Usage: ping
		 * Description: Sends a pong if the bot is online
		 */
		if(command[0].equals("ping")) {
			channel.sendMessage("pong!");
		}
		
		/**
		 * Usage: avatar <@user>
		 * Description: Displays a users avatar
		 */
		else if(command[0].equals("avatar")) {
			if(command.length == 2) {
				command[1] = command[1].replaceAll("[<>@!]", "");
				
				/**
				 * Check if the user specified is a real user
				 */
				for(char alphabet = 'A'; alphabet <= 'Z'; alphabet++) {
					
					if(command[1].toUpperCase().indexOf(alphabet) >= 0) {
						channel.sendMessage(command[1] + " is not a valid user");
						return;
					}
					
				}
				
				IUser user = guild.getUserByID(Long.parseLong(command[1]));
				channel.sendMessage(user.mention() + "'s Avatar: " + user.getAvatarURL());
				message.delete();
				return;
			}
		}
		
		/**
		 * Usage: loc
		 * Description: Test command for "feed" channel
		 */
		else if(command[0].equals("loc")) {
			EmbedBuilder builder = new EmbedBuilder();
			String skillName = Skill.forId(1).name().toLowerCase();
			skillName = skillName.substring(0, 1).toUpperCase() + skillName.substring(1);
			IEmoji emoji = message.getClient().getGuildByID(418458996941258752L).getEmojiByName(skillName);
			builder.withAuthorName(message.getAuthor().getName());
			builder.appendField(emoji + " test", "hello", false);
			feedChannel.sendMessage(builder.build());
			message.delete();
		}
		
		/**
		 * 
		 */
		else if(command[0].equals("link")) {
			if(!channel.isPrivate()) {
				message.delete();
				pm.sendMessage("Please only do that in PM!");
				return;
			} else {
				String username = String.valueOf(command[1]);
				String password = String.valueOf(command[2]);
				Player c = PlayerHandler.getPlayer(username);
				
				if(c == null) {
					pm.sendMessage("Could not find the user " + username + "! Please make sure this account is logged in.");
					return;
				}
				
				if(c.playerPass.equals(password)) {
					
					if(c.discord != null) {
						pm.sendMessage("That account is already linked to a Discord Account!");
						return;
					}
					
					c.setDiscord(String.valueOf(sender.getLongID()));
					if(c.discord != null) {
						pm.sendMessage("You have successfully linked your Discord account to the account " + c.getName());
						return;
					}
					
				} else {
					pm.sendMessage("This password is incorrect. Please try again.");
					return;
				}
			}
		}
	}
	
}
