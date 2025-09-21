package com.Waste.ManagementOrg.repos;


import com.Waste.ManagementOrg.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {

}
