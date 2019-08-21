import javax.swing.JOptionPane;

import org.junit.Test;

import me.vinceh121.socialbladeapi.SocialBlade;
import me.vinceh121.socialbladeapi.youtube.YTStats;

public class ReadmeCode {

	@Test
	public void readmeCode() throws Exception {
		SocialBlade sb = new SocialBlade();
		sb.login(JOptionPane.showInputDialog("Email"), JOptionPane.showInputDialog("Password"));

		YTStats stats = sb.statsYoutube("pewdiepie");
		System.out.println(stats.getSubs());
	}

}
