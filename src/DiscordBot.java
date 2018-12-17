
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.exceptions.RateLimitedException;
import net.dv8tion.jda.core.managers.GuildController;

public class DiscordBot {

	public static JDA jda;

	public static String token;

	public static void main(String[] args) {
		try {
			Scanner sc = new Scanner(new File("token.txt"));
			token = sc.nextLine();
			sc.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		try {
			jda = new JDABuilder(AccountType.BOT).addEventListener(new BotListener()).setToken(token).buildBlocking();
		} catch (LoginException | IllegalArgumentException | InterruptedException | RateLimitedException e) {
			e.printStackTrace();
		}
		while(true){
			tornUpdate();
			try {
			    Thread.sleep(1000*60*60*24);
			} catch(InterruptedException ex) {
			    Thread.currentThread().interrupt();
			}
		}
	}
	
	private static void tornUpdate(){
		Guild g = jda.getGuildById("247490958374076416");
		g.getTextChannelById("493489353046097926").sendMessage("Updating roles...").queue();

		String doc = Web.getWebInfo("https://torn.space/leaderboard");
		System.out.println(doc);
		String[] spl = doc.split("</tr>");
		List<Member> members = g.getMembers();
		Role[] roles = {g.getRoleById(453678967996678145l), g.getRoleById(453678938275708960l), g.getRoleById(453678890628546560l), g.getRoleById(453678855534804992l), g.getRoleById(453612904365948929l), g.getRoleById(453620521632923660l), g.getRoleById(453620581041045555l), g.getRoleById(453620631116709888l), g.getRoleById(453620675526000674l), g.getRoleById(453620720581214208l)};
		List<Role> remove = Arrays.asList(roles);
		GuildController cont = g.getController();
		
		
		
		//delete everyones' roles
		for(Member m : members){
			if(m.getUser().isBot()) continue;
			for(Role r : remove)
				if(m.getRoles().contains(r))
					cont.removeSingleRoleFromMember(m, r).queue();
		}
		
		
		
		//wait 5s
		try {
		    Thread.sleep(1000*5);
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
		
		
		
		//give roles back
		for(int i = 1; i < spl.length - 1; i++){
			
			//Initialize place and name
			spl[i] = spl[i].substring(28, spl[i].length()-5).replaceAll("\\.", "").replaceAll("<", " <");
			System.out.println(spl[i]);
			Scanner sc = new Scanner(spl[i]);
			int place = sc.nextInt();
			spl[i] = spl[i].replace(place+"", "").substring(11);
			String name = spl[i].split(" ")[0];
			System.out.println(place+" "+name);
			sc.close();
			
			for(Member m : members){
				if(m.getUser().isBot()) continue;
				if(m.getEffectiveName().replaceAll(" ", "").equalsIgnoreCase(name.replaceAll(" ", ""))){
					if(place <= 5)
						cont.addSingleRoleToMember(m,roles[9]).queue();
					else if(place <= 10)
						cont.addSingleRoleToMember(m,roles[8]).queue();
					else if(place <= 25)
						cont.addSingleRoleToMember(m,roles[7]).queue();
					else if(place <= 50)
						cont.addSingleRoleToMember(m,roles[6]).queue();
					else if(place <= 75)
						cont.addSingleRoleToMember(m,roles[5]).queue();
					else if(place <= 100)
						cont.addSingleRoleToMember(m, roles[4]).queue();
					else if(place <= 250)
						cont.addSingleRoleToMember(m, roles[3]).queue();
					else if(place <= 500)
						cont.addSingleRoleToMember(m, roles[2]).queue();
					else if(place <= 750)
						cont.addSingleRoleToMember(m, roles[1]).queue();
					else if(place <= 1000)
						cont.addSingleRoleToMember(m, roles[0]).queue();
				}
			}
		}
		
		g.getTextChannelById("493489353046097926").sendMessage("Roles updated.").queue();
	}
}