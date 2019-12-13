package me.vinceh121.socialbladeapi.youtube;

import java.util.Date;

import me.vinceh121.socialbladeapi.DataPoint;

public class YTDataPoint implements DataPoint {
	private Date date;
	private int subs, views;

	public YTDataPoint(Date date, int subs, int views) {
		this.date = date;
		this.subs = subs;
		this.views = views;
	}

	public Date getDate() {
		return date;
	}

	public int getSubs() {
		return subs;
	}

	public int getViews() {
		return views;
	}
}
