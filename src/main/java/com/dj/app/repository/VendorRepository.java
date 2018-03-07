package com.dj.app.repository;

import com.dj.app.domain.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface VendorRepository extends JpaRepository<Vendor, String>{

	Vendor findByPhoneAndPassword(String phone, String password);
	Vendor findByEmailAndPassword(String email,String password);
	Long countByEmailIgnoreCase(String email);
	Long countByPhoneIgnoreCase(String phone);
	Vendor findByVendorId(String id);
	Vendor findByEmail(String email);

	@Modifying
	@Query("update Vendor v SET v.isEmailVerified = true WHERE v.vendorId =:vendorId ")
	@Transactional
	void updateEmailVerificationStatus(@Param("vendorId") Long vendorId);

}
