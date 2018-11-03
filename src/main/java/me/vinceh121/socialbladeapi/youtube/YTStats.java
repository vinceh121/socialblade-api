package me.vinceh121.socialbladeapi.youtube;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class YTStats {
	private String channelId, username, cusername, mod, subs, countryCode, country, channelType, avatar, banner,
			partner, grade, networkName, networkDisplayName, googleplus, facebook, twitter, instagram;
	int averageDailyViews, averageDailySubs, uploads;
	long views;
	private boolean isVerfied;
	private int sbRank, rank, viewsRank, countryRank, channelTypeRank, subsGrowth, viewsGrowth;

	private HashMap<Integer, Long> subsChart, viewsChart;
	private HashMap<Integer, YTDataPoint> dataDaily;

	private Date createdAt;

	/**
	 * @return the channelId
	 */
	public String getChannelId() {
		return channelId;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @return the cusername
	 */
	public String getCusername() {
		return cusername;
	}

	/**
	 * @return the mod
	 */
	public String getMod() {
		return mod;
	}

	/**
	 * @return the uploads
	 */
	public int getUploads() {
		return uploads;
	}

	/**
	 * @return the subs
	 */
	public String getSubs() {
		return subs;
	}

	/**
	 * @return the views
	 */
	public long getViews() {
		return views;
	}

	/**
	 * @return the countryCode
	 */
	public String getCountryCode() {
		return countryCode;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @return the channelType
	 */
	public String getChannelType() {
		return channelType;
	}

	/**
	 * @return the avatar
	 */
	public String getAvatar() {
		return avatar;
	}

	/**
	 * @return the banner
	 */
	public String getBanner() {
		return banner;
	}

	/**
	 * @return the averageDailySubs
	 */
	public int getAverageDailySubs() {
		return averageDailySubs;
	}

	/**
	 * @return the averageDailyViews
	 */
	public int getAverageDailyViews() {
		return averageDailyViews;
	}

	/**
	 * @return the partner
	 */
	public String getPartner() {
		return partner;
	}

	/**
	 * @return the grade
	 */
	public String getGrade() {
		return grade;
	}

	/**
	 * @return the networkName
	 */
	public String getNetworkName() {
		return networkName;
	}

	/**
	 * @return the networkDisplayName
	 */
	public String getNetworkDisplayName() {
		return networkDisplayName;
	}

	/**
	 * @return the googleplus
	 */
	public String getGoogleplus() {
		return googleplus;
	}

	/**
	 * @return the facebook
	 */
	public String getFacebook() {
		return facebook;
	}

	/**
	 * @return the twitter
	 */
	public String getTwitter() {
		return twitter;
	}

	/**
	 * @return the instagram
	 */
	public String getInstagram() {
		return instagram;
	}

	/**
	 * @return the isVerfied
	 */
	public boolean isVerfied() {
		return isVerfied;
	}

	/**
	 * @return the sbRank
	 */
	public int getSbRank() {
		return sbRank;
	}

	/**
	 * @return the rank
	 */
	public int getRank() {
		return rank;
	}

	/**
	 * @return the viewsRank
	 */
	public int getViewsRank() {
		return viewsRank;
	}

	/**
	 * @return the countryRank
	 */
	public int getCountryRank() {
		return countryRank;
	}

	/**
	 * @return the channelTypeRank
	 */
	public int getChannelTypeRank() {
		return channelTypeRank;
	}

	/**
	 * @return the subsGrowth
	 */
	public int getSubsGrowth() {
		return subsGrowth;
	}

	/**
	 * @return the viewsGrowth
	 */
	public int getViewsGrowth() {
		return viewsGrowth;
	}

	/**
	 * @return the subsChart
	 */
	public HashMap<Integer, Long> getSubsChart() {
		return subsChart;
	}

	/**
	 * @return the viewsChart
	 */
	public HashMap<Integer, Long> getViewsChart() {
		return viewsChart;
	}

	/**
	 * @return the dataDaily
	 */
	public HashMap<Integer, YTDataPoint> getDataDaily() {
		return dataDaily;
	}

	public static YTStats fromJson(JSONObject o) throws JSONException, ParseException {
		final DateFormat df = new SimpleDateFormat("y-M-d");

		YTStats s = new YTStats();

		JSONObject id = o.getJSONObject("id");
		s.channelId = id.getString("channelid");
		s.username = id.getString("username");
		s.cusername = id.getString("cusername");
		s.mod = id.getString("mod");

		JSONObject data = o.getJSONObject("data");
		s.createdAt = df.parse(data.getString("created_at"));
		s.uploads = data.getInt("uploads");
		s.views = data.getLong("views");
		s.countryCode = data.getString("country_code");
		s.country = data.getString("country");
		s.channelType = data.getString("channeltype");
		s.avatar = data.getString("avatar");
		s.banner = data.getString("banner");
		s.averageDailySubs = data.getInt("avgdailysubs");
		s.averageDailyViews = data.getInt("avgdailyviews");
		s.partner = data.getString("partner"); // Check value type
		s.isVerfied = "1".equals(data.getString("isVerified"));

		JSONObject rankRaw = o.getJSONObject("rank").getJSONObject("raw");
		s.sbRank = rankRaw.getInt("sbrank");
		s.rank = rankRaw.getInt("rank");
		s.viewsRank = rankRaw.getInt("viewsrank");
		s.countryRank = rankRaw.getInt("countryrank");
		s.channelTypeRank = rankRaw.getInt("channeltyperank");

		s.grade = o.getJSONObject("rank").getString("grade_raw");

		JSONObject network = o.getJSONObject("network");
		try {
			s.networkName = network.getString("networkname"); // Sometimes networkname = null
		} catch (JSONException e) {
		}
		
		try {
			s.networkDisplayName = network.getString("networkname_display"); // Sometime networkname_display = null
		} catch (JSONException e) {
		}

		JSONObject social = o.getJSONObject("social");
		s.googleplus = social.getString("googleplus");
		s.facebook = social.getString("facebook");
		s.twitter = social.getString("twitter");
		s.instagram = social.getString("instagram");

		JSONObject chartsSubs = o.getJSONObject("charts").getJSONObject("subs");
		s.subsChart = new HashMap<Integer, Long>();
		int n;
		for (String k : chartsSubs.keySet()) {
			n = Integer.parseInt(k.replaceAll("subs", ""));

			s.subsChart.put(n, chartsSubs.getLong(k));
		}

		JSONObject chartsViews = o.getJSONObject("charts").getJSONObject("views");
		s.viewsChart = new HashMap<Integer, Long>();
		for (String k : chartsViews.keySet()) {
			n = Integer.parseInt(k.replaceAll("views", ""));

			s.subsChart.put(n, chartsViews.getLong(k));
		}

		final JSONArray daily = o.getJSONArray("data_daily");
		s.dataDaily = new HashMap<Integer, YTDataPoint>();
		for (int i = 0; i < daily.length(); i++) {
			final JSONObject tmp = daily.getJSONObject(i);
			s.dataDaily.put(i, new YTDataPoint() {

				public int getViews() {
					return tmp.getInt("views");
				}

				public int getSubs() {
					return tmp.getInt("subs");
				}

				public Date getDate() {
					try {
						return df.parse(tmp.getString("date"));
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

	/**
	 * @return the createdAt
	 */
	public Date getCreatedAt() {
		return createdAt;
	}
}
