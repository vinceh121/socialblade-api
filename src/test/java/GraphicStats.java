import java.awt.HeadlessException;
import java.text.ParseException;

import javax.swing.JOptionPane;

import org.json.JSONException;
import org.junit.Test;

import me.vinceh121.socialbladeapi.SocialBlade;
import me.vinceh121.socialbladeapi.twitter.TWStats;
import me.vinceh121.socialbladeapi.youtube.YTStats;

public class GraphicStats {

	private static SocialBlade sb;

	public GraphicStats() throws HeadlessException, JSONException, Exception {
		if (sb != null)
			return;
		sb = new SocialBlade();
		sb.login(JOptionPane.showInputDialog("SB Email"), JOptionPane.showInputDialog("SB Password (visisble)"));
	}

	@Test
	public void YTStats() throws HeadlessException, JSONException, Exception {
		YTStats s = sb.statsYoutube(JOptionPane.showInputDialog("Youtuber to lookup"));
		System.out.println(s.getUsername() + "'s current total number of views is " + s.getViews());
	}

	@Test
	public void TWStats() throws HeadlessException, JSONException, ParseException, Exception {
		TWStats s = sb.statsTwitter(JOptionPane.showInputDialog("Twitter username to lookup"));
		System.out.println("@" + s.getFullName() + " has " + s.getFollowers() + " followers.");
	}

}
