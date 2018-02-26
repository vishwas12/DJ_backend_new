package com.dj.app.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Profile {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer profileId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "vendor_id")
	private Vendor vendor;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id")
	private Category category;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy="profile")
	private Set<SubProfile> subProfile;
	
	@Column(columnDefinition = "tinyint default 0")
	private Boolean isPrimary;
	
	private String createdBy;
	
	private String updatedBy;
	
	private Date createdOn;
	
	private Date updatedOn;
	
	private String status; //need to be replaced with enum
	
	public Profile() {
		this.subProfile = new HashSet<>();
	}
}
