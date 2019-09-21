import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
public class Web {

	public static TrustManager[] trustAllCerts = new TrustManager[]{//ssl stuff
	        new X509TrustManager() {
	            public java.security.cert.X509Certificate[] getAcceptedIssuers(){return null;}
	            public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType){}
	            public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType){}
	        }
	};
	
	// Gets raw HTML of in
	public static String getWebInfo(String in) {
		String web = "";
		in = in.split(" ")[0];
		try {
		    SSLContext sc = SSLContext.getInstance("SSL");
		    sc.init(null, trustAllCerts, new java.security.SecureRandom());
		    HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
			URLConnection connection = new URL(in).openConnection();
			connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
			connection.connect();
			BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String str;
			while ((str = br.readLine()) != null)
				web += str;
			br.close();
		} catch (IOException | KeyManagementException | NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return web;
	}
}