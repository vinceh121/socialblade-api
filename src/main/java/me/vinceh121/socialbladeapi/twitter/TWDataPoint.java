package me.vinceh121.socialbladeapi.twitter;

import java.util.Date;

public interface TWDataPoint {
	public Date getDate();

	public long getFollowers();

	public long getFollowing();

	public long getTweets();

	public long getFavorites();
}
