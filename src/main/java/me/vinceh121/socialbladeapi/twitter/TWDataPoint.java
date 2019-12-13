package me.vinceh121.socialbladeapi.twitter;

import java.util.Date;

import me.vinceh121.socialbladeapi.DataPoint;

public class TWDataPoint implements DataPoint {
	private Date date;
	private long followers, following, tweets, favorites;

	public TWDataPoint(Date date, long followers, long following, long tweets, long favorites) {
		this.date = date;
		this.followers = followers;
		this.following = following;
		this.tweets = tweets;
		this.favorites = favorites;
	}

	public Date getDate() {
		return date;
	}

	public long getFollowers() {
		return followers;
	}

	public long getFollowing() {
		return following;
	}

	public long getTweets() {
		return tweets;
	}

	public long getFavorites() {
		return favorites;
	}
}
