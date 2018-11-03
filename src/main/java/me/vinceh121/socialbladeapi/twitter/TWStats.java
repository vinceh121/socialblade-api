package me.vinceh121.socialbladeapi.twitter;

import java.awt.Color;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TWStats {
	private String twitterId, fullName, username, picture, banner, website, recentTweetUrl, recentTweetId, grade;
	private long followers, following, tweets, favorites, averageRetweets, averageFavourites, dailyFollowers,
			dailyFollowing, sbRank, rank, weekFollowers, monthFollowers, yearFollowers, weekFollowing, monthFollowing,
			yearFollowing;
	private boolean isVerified, isStaff;
	private Color color;
	private Date createdAt;
	private ArrayList<TWDataPoint> statistics;

	/**
	 * @return the twitterId
	 */
	public String getTwitterId() {
		return twitterId;
	}

	/**
	 * @return the fullName
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @return the picture
	 */
	public String getPicture() {
		return picture;
	}

	/**
	 * @return the banner
	 */
	public String getBanner() {
		return banner;
	}

	/**
	 * @return the website
	 */
	public String getWebsite() {
		return website;
	}

	/**
	 * @return the recentTweetUrl
	 */
	public String getRecentTweetUrl() {
		return recentTweetUrl;
	}

	/**
	 * @return the recentTweetId
	 */
	public String getRecentTweetId() {
		return recentTweetId;
	}

	/**
	 * @return the grade
	 */
	public String getGrade() {
		return grade;
	}

	/**
	 * @return the followers
	 */
	public long getFollowers() {
		return followers;
	}

	/**
	 * @return the following
	 */
	public long getFollowing() {
		return following;
	}

	/**
	 * @return the tweets
	 */
	public long getTweets() {
		return tweets;
	}

	/**
	 * @return the favorites
	 */
	public long getFavorites() {
		return favorites;
	}

	/**
	 * @return the averageRetweets
	 */
	public long getAverageRetweets() {
		return averageRetweets;
	}

	/**
	 * @return the averageFavourites
	 */
	public long getAverageFavourites() {
		return averageFavourites;
	}

	/**
	 * @return the dailyFollowers
	 */
	public long getDailyFollowers() {
		return dailyFollowers;
	}

	/**
	 * @return the dailyFollowing
	 */
	public long getDailyFollowing() {
		return dailyFollowing;
	}

	/**
	 * @return the sbRank
	 */
	public long getSbRank() {
		return sbRank;
	}

	/**
	 * @return the rank
	 */
	public long getRank() {
		return rank;
	}

	/**
	 * @return the weekFollowers
	 */
	public long getWeekFollowers() {
		return weekFollowers;
	}

	/**
	 * @return the monthFollowers
	 */
	public long getMonthFollowers() {
		return monthFollowers;
	}

	/**
	 * @return the yearFollowers
	 */
	public long getYearFollowers() {
		return yearFollowers;
	}

	/**
	 * @return the weekFollowing
	 */
	public long getWeekFollowing() {
		return weekFollowing;
	}

	/**
	 * @return the monthFollowing
	 */
	public long getMonthFollowing() {
		return monthFollowing;
	}

	/**
	 * @return the yearFollowing
	 */
	public long getYearFollowing() {
		return yearFollowing;
	}

	/**
	 * @return the isVerified
	 */
	public boolean isVerified() {
		return isVerified;
	}

	/**
	 * @return the isStaff
	 */
	public boolean isStaff() {
		return isStaff;
	}

	/**
	 * @return the color
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * @return the createdAt
	 */
	public Date getCreatedAt() {
		return createdAt;
	}

	public static TWStats fromJson(JSONObject o) throws JSONException, ParseException {
		DateFormat creationDf = new SimpleDateFormat("E M d H:m:s X y");
		TWStats s = new TWStats();

		JSONObject user = o.getJSONObject("user");
		s.twitterId = user.getString("twitter_id");
		s.fullName = user.getString("full_name");
		s.username = user.getString("username");
		try {
			s.createdAt = creationDf.parse(user.getString("created_at"));
		} catch (ParseException e) {
			System.err.println("Failed to parse account creation date for @" + s.getUsername());
		}
		try {
			s.website = user.getString("website"); // website could be null
		} catch (JSONException e) {
			System.err.println("JSONException: " + e.getLocalizedMessage());
		}
		s.followers = user.getLong("followers");
		s.following = user.getLong("following");
		s.tweets = user.getLong("tweets");
		s.favorites = user.getLong("favorites");
		s.isVerified = user.getBoolean("isVerified");
		s.isStaff = user.getBoolean("isStaff");

		JSONObject userDesign = user.getJSONObject("design");
		s.picture = userDesign.getString("picture");
		s.banner = userDesign.getString("banner");
		s.color = new Color(Integer.parseInt(userDesign.getString("color").replaceAll("#", ""), 16));

		try {
			JSONObject userRecentTweet = user.getJSONObject("recent_tweet"); // recent_tweet could be null
			s.recentTweetId = userRecentTweet.getString("id");
			s.recentTweetUrl = userRecentTweet.getString("url");
		} catch (JSONException e) {
			System.err.println("JSONException: " + e.getLocalizedMessage());
		}

		JSONObject average = o.getJSONObject("average");
		s.averageRetweets = average.getLong("retweets");
		s.averageFavourites = average.getLong("favourites");

		JSONObject averageDaily = average.getJSONObject("daily");
		s.dailyFollowers = averageDaily.getLong("followers");
		s.dailyFollowing = averageDaily.getLong("following");

		JSONObject rankRaw = o.getJSONObject("rank").getJSONObject("raw");
		s.grade = rankRaw.getString("grade");
		s.sbRank = rankRaw.getLong("sbrank");
		s.rank = rankRaw.getLong("rank");
		try {
			JSONObject chartsFollowers = o.getJSONObject("charts").getJSONObject("followers");
			s.weekFollowers = chartsFollowers.getLong("week");
			s.monthFollowers = chartsFollowers.getLong("month");
			s.yearFollowers = chartsFollowers.getLong("year");

			JSONObject chartsFollowing = o.getJSONObject("charts").getJSONObject("following");
			s.weekFollowing = chartsFollowing.getLong("week");
			s.monthFollowing = chartsFollowing.getLong("month");
			s.yearFollowing = chartsFollowing.getLong("year");
		} catch (JSONException e) {
			System.err.println("JSONException: " + e.getLocalizedMessage()); // all of charts' childs could be all set
																				// to null
		}
		final DateFormat df = new SimpleDateFormat("y-M-d");

		final JSONArray stats = o.getJSONArray("statistics");
		s.statistics = new ArrayList<TWDataPoint>();
		for (int i = 0; i < stats.length(); i++) {
			final JSONObject e = stats.getJSONObject(i);
			s.statistics.add(i, new TWDataPoint() {

				public long getTweets() {
					return e.getLong("tweets");
				}

				public long getFollowing() {
					return e.getLong("following");
				}

				public long getFollowers() {
					return e.getLong("followers");
				}

				public long getFavorites() {
					return e.getLong("favorites");
				}

				public Date getDate() {
					try {
						return df.parse(e.getString("date"));
					} catch (JSONException e) {
						e.printStackTrace();
						return null;
					} catch (ParseException e) {
						e.printStackTrace();
						return null;
					}
				}
			});
		}

		return s;
	}

}
