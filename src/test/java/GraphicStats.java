import java.awt.HeadlessException;

import javax.swing.JOptionPane;

import org.json.JSONException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import me.vinceh121.socialbladeapi.SocialBlade;
import me.vinceh121.socialbladeapi.YTStats;

public class GraphicStats {

	private SocialBlade sb;

	@BeforeAll
	public void setUp() throws Exception {
		sb = new SocialBlade();
	}

	@Test
	public void login() throws HeadlessException, JSONException, Exception {
		sb.login(JOptionPane.showInputDialog("SB Email"), JOptionPane.showInputDialog("SB Password (visisble)"));

	}

	@Test
	public void stats() throws HeadlessException, JSONException, Exception {
		YTStats s = sb.statsYoutube(JOptionPane.showInputDialog("Youtuber to lookup"));
		System.out.println(s.getCusername() + "'s current total number of views is " + s.getViews());
	}

}
