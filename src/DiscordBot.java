
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.exceptions.RateLimitedException;

public class DiscordBot {
	
	public static void main(String[] args) {
		try {
			Scanner sc = new Scanner(new File("token.txt"));
			token = sc.nextLine();
			sc.close();
			jda = new JDABuilder(AccountType.BOT).addEventListener(new BotListener()).setToken(token).buildBlocking();
		}
		catch (LoginException | IllegalArgumentException | InterruptedException | RateLimitedException | FileNotFoundException e) { e.printStackTrace(); }
		
		while(true){
			Manager.tornUpdate();
			try { Thread.sleep(1000*60*60); }
			catch(InterruptedException ex) { Thread.currentThread().interrupt(); }
		}
	}

	public static JDA jda;
	public static String token;
	
}