package com.dj.app.repository;

import com.dj.app.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long>{

	Role findByRole(String role);
}
