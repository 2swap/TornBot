

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Web {// TODO optimize, not just first match

	public String in;
	public String webWhole;
	public static int webCount = 0;

	public Web() {
	}

	// Gets raw HTML of in
	public static String getWebInfo(String in) {
		String web = "";
		in = in.split(" ")[0];
		try {
			URLConnection connection = new URL(in).openConnection();
			connection.connect();
			BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String str;
			while ((str = br.readLine()) != null)
				web += str;
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return web;
	}
	
}
