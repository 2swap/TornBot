
import java.io.File;
import java.util.Scanner;

import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;


public class DiscordBot {
	
	public static void main(String[] args) {
		try {
			Scanner sc = new Scanner(new File("token.txt"));
			token = sc.nextLine();
			sc.close();
			jda = new JDABuilder(AccountType.BOT).addEventListeners(new BotListener()).setToken(token).build().awaitReady();
		}
		catch (Throwable e) { e.printStackTrace(); }
		
		while(true){
			Manager.tornUpdate();
			try { Thread.sleep(1000*60*60); }
			catch(InterruptedException ex) { Thread.currentThread().interrupt(); }
		}
	}

	public static JDA jda;
	public static String token;
	
}