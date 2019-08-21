package me.vinceh121.socialbladeapi;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import me.vinceh121.socialbladeapi.twitter.TWStats;
import me.vinceh121.socialbladeapi.youtube.YTStats;
import org.json.JSONObject;

public class SocialBlade {
	public final static String VERSION = "0.2.2";
	private String userAgent = "vinceh121.socialblade-api (" + VERSION + ")";

	private String token, email;
	private JSONObject userJson;
	private boolean ignoreLoginCheck;

	public SocialBlade() {

	}

	public TWStats statsTwitter(String name) throws Exception {
		return TWStats.fromJson(getJson("https://api.socialblade.com/v2/twitter/statistics?query=statistics&username="
				+ name + "&email=" + email + "&token=" + token));
	}

	public JSONObject getUserJson() {
		return userJson;
	}

	public YTStats statsYoutube(String name) throws Exception {
		return YTStats.fromJson(getJson("https://api.socialblade.com/v2/youtube/statistics?query=statistics&username="
				+ name + "&email=" + email + "&token=" + token));
	}

	public void login(String email, String password) throws Exception {
		JSONObject l = getJson(
				"https://api.socialblade.com/v2/bridge?email=" + email + "&password=" + getMD5(password));

		if (l.getJSONObject("status").getInt("response") != 200)
			throw new Exception("API returned HTTP code " + l.getJSONObject("status").getInt("response"));

		token = l.getJSONObject("id").getString("token");
		this.email = email;

		
		//userJson = c; // Moved in checkLogin()
	}
	
	public void loginToken(String email, String token) throws Exception {
		this.token = token;
		this.email = email;
		if (!checkLogin())
			throw new Exception("Failed to check token validity");
	}
	
	/**
	 * Checks if our token is still up to date
	 * @return {@code true} if check succeeded, {@code false} otherwise
	 * @throws Exception
	 */
	public boolean checkLogin() throws Exception {
		if (ignoreLoginCheck)
			return true;
		JSONObject c = getJson("https://api.socialblade.com/v2/bridge?email=" + email + "&token=" + token);
		userJson = c;
		return c.getJSONObject("status").getInt("response") == 200;
	}

	private String getMD5(String s) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] encode = s.getBytes();
			byte[] encoded = md.digest(encode);

			StringBuilder sb = new StringBuilder(2 * encoded.length);
			for (byte b : encoded) {
				sb.append("0123456789ABCDEF".charAt((b & 0xF0) >> 4));
				sb.append("0123456789ABCDEF".charAt((b & 0x0F)));
			}
			return sb.toString().toLowerCase();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}

	private String getUrl(String url) throws Exception {
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		con.setRequestMethod("GET");

		con.setRequestProperty("User-Agent", userAgent);

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		return response.toString();

	}

	private JSONObject getJson(String url) throws Exception {
		return new JSONObject(getUrl(url));
	}

	public String getUserAgent() {
		return this.userAgent;
	}

	public void setUserAgent(String ua) {
		this.userAgent = ua;
	}

	public boolean isIgnoreLoginCheck() {
		return ignoreLoginCheck;
	}

	public void setIgnoreLoginCheck(boolean ignoreLoginCheck) {
		this.ignoreLoginCheck = ignoreLoginCheck;
	}

	public enum PLATFORM {
		YOUTUBE("youtube"), TWITTER("twitter"), INSTAGRAM("instagram"), TWITCH("twitch"), FACEBOOK("facebook"),
		DAILYMOTION("dailymotion"), MIXER("mixer");

		private String endPoint;

		PLATFORM(String endPoint) {
			this.endPoint = endPoint;
		}

		public String getEndPoint() {
			return endPoint;
		}
	}
}
