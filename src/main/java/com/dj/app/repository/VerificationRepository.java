package com.dj.app.repository;

import com.dj.app.domain.Verification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VerificationRepository extends JpaRepository<Verification,Long>{

	@Query("select v from Verification v where v.vendor.vendorId = :vendorId")
	Verification findByVendorId(@Param("vendorId") Long vendorId);
}
