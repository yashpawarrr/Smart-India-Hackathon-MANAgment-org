package com.Waste.ManagementOrg.repos;

import com.Waste.ManagementOrg.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffRepo extends JpaRepository<Staff, Long> {
}
