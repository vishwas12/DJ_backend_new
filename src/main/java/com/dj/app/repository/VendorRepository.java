package com.dj.app.repository;

import com.dj.app.domain.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorRepository extends JpaRepository<Vendor, String>{

	Vendor findByPhoneAndPassword(String phone, String password);
	Vendor findByEmailAndPassword(String email,String password);
	Long countByEmail(String email);
	Long countByPhone(String phone);
	Vendor findByVendorId(String id);

}
