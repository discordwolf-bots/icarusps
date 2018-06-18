package ethos.discord.api;

import ethos.model.players.skills.Skill;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IEmoji;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.util.EmbedBuilder;

public class CommandProcessor {
	
	public static void processCommand(IMessage message, String prefix) {
		
		@SuppressWarnings("unused")
		IUser sender = message.getAuthor();
		IChannel channel = message.getChannel();
		IGuild guild = message.getGuild();
		
		String[] command = message.getContent().toLowerCase().replaceFirst(prefix, "").split(" ");
		
		IChannel feedChannel = message.getClient().getChannelByID(458391954594463764L);
		
		if(command[0].equals("ping")) {
			channel.sendMessage("pong!");
		} else if(command[0].equals("avatar")) {
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
		} else if(command[0].equals("loc")) {
			EmbedBuilder builder = new EmbedBuilder();
			String skillName = Skill.forId(1).name().toLowerCase();
			skillName = skillName.substring(0, 1).toUpperCase() + skillName.substring(1);
			IEmoji emoji = message.getClient().getGuildByID(418458996941258752L).getEmojiByName(skillName);
			builder.withAuthorName(message.getAuthor().getName());
			builder.appendField(emoji + " test", "hello", false);
			feedChannel.sendMessage(builder.build());
			message.delete();
		}
	}
	
}
