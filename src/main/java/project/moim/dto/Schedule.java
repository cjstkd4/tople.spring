package project.moim.dto;

public class Schedule {
	private int schnum;
	private int moimcode;
	private String year;
	private String month;
	private String day;
	private String time;
	private String title;
	private String sub;
	private String join;
	private int lat;
	private int lot;
	
	public Schedule() {
		super();
	}

	public int getSchnum() {
		return schnum;
	}

	public void setSchnum(int schnum) {
		this.schnum = schnum;
	}

	public int getMoimcode() {
		return moimcode;
	}

	public void setMoimcode(int moimcode) {
		this.moimcode = moimcode;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSub() {
		return sub;
	}

	public void setSub(String sub) {
		this.sub = sub;
	}

	public String getJoin() {
		return join;
	}

	public void setJoin(String join) {
		this.join = join;
	}

	public int getLat() {
		return lat;
	}

	public void setLat(int lat) {
		this.lat = lat;
	}

	public int getLot() {
		return lot;
	}

	public void setLot(int lot) {
		this.lot = lot;
	}
}
