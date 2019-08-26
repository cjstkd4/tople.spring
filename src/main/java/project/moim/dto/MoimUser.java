package project.moim.dto;

public class MoimUser {
	private String id;
	private String tel;
	private String moim;
	private String fav;
	private String name;
	private String gender;
	private String birth;
	private String loca;
	private String prof;
	private String thumb;
	private String isShowDial;	// 개인정보 부재시 다이얼로그 보여준 여부
	
	public MoimUser() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMoim() {
		return moim;
	}

	public void setMoim(String moim) {
		this.moim = moim;
	}

	public String getFav() {
		return fav;
	}

	public void setFav(String fav) {
		this.fav = fav;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getLoca() {
		return loca;
	}

	public void setLoca(String loca) {
		this.loca = loca;
	}

	public String getProf() {
		return prof;
	}

	public void setProf(String prof) {
		this.prof = prof;
	}

	public String getThumb() {
		return thumb;
	}

	public void setThumb(String thumb) {
		this.thumb = thumb;
	}

	public String getIsShowDial() {
		return isShowDial;
	}

	public void setIsShowDial(String isShowDial) {
		this.isShowDial = isShowDial;
	}
}
