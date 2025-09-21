package com.Waste.ManagementOrg.controller;


import com.Waste.ManagementOrg.entity.User;
import com.Waste.ManagementOrg.entity.WasteSubmission;
import com.Waste.ManagementOrg.repos.UserRepo;
import com.Waste.ManagementOrg.repos.WasteSubmissionRepository;
import com.Waste.ManagementOrg.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    private WasteSubmissionRepository wasteRepo;
    @Autowired
    private UserRepo userRepo;

    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user) {
        User created = service.create(user);
        return ResponseEntity.status(201).body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> get(@PathVariable Long id) {
        User u = service.get(id);
        return u == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(u);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    // token management
//    @PostMapping("/register")
//    public User register(@RequestBody User user){
//        user.setRole("user");
//        return userRepo.save(user);
//    }

    // POST http://localhost:8080/user/submitWaste?userId=1&weight=20
    @PostMapping("/submitWaste")
    public WasteSubmission submitWaste(@RequestParam Long userId, @RequestParam double weight){
        WasteSubmission submission = new WasteSubmission();
        submission.setUserId(userId);
        submission.setWeight(weight);
        return wasteRepo.save(submission);
    }

    // GET http://localhost:8080/user/wallet/1 → returns updated wallet balance.
    @GetMapping("/wallet/{userId}")
    public int walletBalance(@PathVariable Long userId){
        //this>>>
        return Math.toIntExact(userRepo.findById(userId).get().getWalletBalance());
    }

    @GetMapping("/allSubmissions")
    public List<WasteSubmission> allSubmissions(){
        return wasteRepo.findAll();
    }
}
