
import java.util.ArrayList;

import net.dv8tion.jda.core.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class BotListener extends ListenerAdapter {

	public static int timeout = 0;
	public static ArrayList<String> words = new ArrayList<>();
	public static ArrayList<Integer> freq = new ArrayList<>();


	@Override
	public void onMessageReceived(MessageReceivedEvent e) {
	}

	@Override
	public void onGuildMemberJoin(GuildMemberJoinEvent e) {
		if (e.getGuild().getId().equals("247490958374076416"))
			e.getGuild().getTextChannelById("247490958374076416").sendMessage(e.getMember().getAsMention() + " Welcome to the torn.space discord!").queue();
	}
}