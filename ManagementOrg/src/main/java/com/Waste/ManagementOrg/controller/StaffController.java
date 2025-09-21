package com.Waste.ManagementOrg.controller;


import com.Waste.ManagementOrg.entity.Staff;
import com.Waste.ManagementOrg.entity.User;
import com.Waste.ManagementOrg.entity.WasteSubmission;
import com.Waste.ManagementOrg.repos.UserRepo;
import com.Waste.ManagementOrg.repos.WasteSubmissionRepository;
import com.Waste.ManagementOrg.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/staff")
public class StaffController {

    @Autowired
    private StaffService service;
    @Autowired
    private WasteSubmissionRepository wasteRepo;
    @Autowired
    private UserRepo userRepo;

        @PostMapping
        public ResponseEntity<Staff> create(@RequestBody Staff staff) {
            Staff created = service.create(staff);
            return ResponseEntity.status(201).body(created);
        }


        @GetMapping
        public List<Staff> list() { return service.listAll(); }


        @GetMapping("/{id}")
        public ResponseEntity<Staff> get(@PathVariable Long id) {
            Staff s = service.get(id);
            return s == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(s);
        }


//        @PutMapping("/{id}")
//        public ResponseEntity<Staff> update(@PathVariable Long id, @RequestBody Staff staff) {
//            Staff s = service.update(id, staff);
//            return s == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(s);
//        }


        @DeleteMapping("/{id}")
        public ResponseEntity<Void> delete(@PathVariable Long id) {
            service.delete(id);
            return ResponseEntity.noContent().build();
        }

        // POST http://localhost:8080/staff/issueToken/1?tokenAmount=5
    @PostMapping("/issueToken/{submissionId}")
    public ResponseEntity<String> issueToken(@PathVariable Long submissionId,
                                             @RequestParam int tokenAmount,
                                             @RequestHeader String role){

        if(!role.equals("staff")){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Only staff can issue tokens");
        }

        WasteSubmission submission = wasteRepo.findById(submissionId).orElse(null);
        if(submission == null) return ResponseEntity.badRequest().body("Submission not found");
        if(submission.isTokenIssued()) return ResponseEntity.badRequest().body("Token already issued");

        User user = userRepo.findById(submission.getUserId()).get();
        user.setWalletBalance(user.getWalletBalance() + tokenAmount);
        submission.setTokenIssued(true);

        userRepo.save(user);
        wasteRepo.save(submission);

        return ResponseEntity.ok("Token issued successfully");
    }
}
