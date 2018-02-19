package com.dj.app.repository;

import com.dj.app.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String>{

	User findByPhoneAndPassword(String phone, String password);
	User findByEmailAndPassword(String email,String password);
	Long countByEmail(String email);
	Long countByPhone(String phone);
	User findByUserId(String id);

}
