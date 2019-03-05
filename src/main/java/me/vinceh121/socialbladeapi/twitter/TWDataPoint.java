package me.vinceh121.socialbladeapi.twitter;

import java.util.Date;

public interface TWDataPoint {
	Date getDate();

	long getFollowers();

	long getFollowing();

	long getTweets();

	long getFavorites();
}
