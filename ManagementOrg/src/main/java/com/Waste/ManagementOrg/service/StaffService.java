package com.Waste.ManagementOrg.service;


import com.Waste.ManagementOrg.entity.Staff;
import com.Waste.ManagementOrg.repos.StaffRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffService {

    @Autowired
    private StaffRepo repo;

    public Staff create(Staff staff) {
        Staff staff1 = new Staff();
        staff1.setId(staff.getId());
        staff1.setAssignedArea(staff.getAssignedArea());
        staff1.setPassword(staff.getPassword());
        staff1.setRole(staff.getRole());
        staff1.setName(staff.getName());

        return repo.save(staff1);
    }


    public List<Staff> listAll() { return repo.findAll(); }


    public Staff get(Long id) { return repo.findById(id).orElse(null); }


//    public Staff update(Long id, Staff updated) {
//        return repo.findById(id).map(s -> {
//            s.setName(updated.getName());
//            s.setPhone(updated.getPhone());
//            s.setAssignedArea(updated.getAssignedArea());
//            return repo.save(s);
//        }).orElse(null);
//    }


    public void delete(Long id) { repo.deleteById(id); }
}
