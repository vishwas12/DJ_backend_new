package com.dj.app.domain;

import javax.persistence.*;

@Entity
@Table(name = "ROLES",uniqueConstraints = { @UniqueConstraint(columnNames = {"role"}) })
public class Role {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROLE_ID")
    private Long roleId;

	@Column(name = "ROLE")
    private String role;

    @PrePersist
    void prePersist() {
        if (!this.role.isEmpty()) {
            this.role = this.role.toUpperCase();
        }
    }

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
    
    
}