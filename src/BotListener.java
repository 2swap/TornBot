
import net.dv8tion.jda.core.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class BotListener extends ListenerAdapter {

	public static int timeout = 0;

	@Override
	public void onMessageReceived(MessageReceivedEvent e) {
	}

	@Override
	public void onGuildMemberJoin(GuildMemberJoinEvent e) {
		Manager.sendMessage(e.getMember().getAsMention() + " Welcome to the torn.space discord!",Manager.gen);
	}
}