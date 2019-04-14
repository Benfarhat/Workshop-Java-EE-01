package model;

import java.io.Serializable;
import javax.persistence.*;

@Entity

public class CD extends Item implements Serializable {

	@Transient
	private static final long serialVersionUID = 1L;

	private String musicCompany;
	private Integer numberOfCDs;
	private Float totalDuration;
	private String gender;
	
	public CD() {
		super();
	}

	public CD(String musicCompany, Integer numberOfCDs, Float totalDuration, String gender) {
		super();
		this.musicCompany = musicCompany;
		this.numberOfCDs = numberOfCDs;
		this.totalDuration = totalDuration;
		this.gender = gender;
	}

	public String getMusicCompany() {
		return musicCompany;
	}

	public void setMusicCompany(String musicCompany) {
		this.musicCompany = musicCompany;
	}

	public Integer getNumberOfCDs() {
		return numberOfCDs;
	}

	public void setNumberOfCDs(Integer numberOfCDs) {
		this.numberOfCDs = numberOfCDs;
	}

	public Float getTotalDuration() {
		return totalDuration;
	}

	public void setTotalDuration(Float totalDuration) {
		this.totalDuration = totalDuration;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
   
}
