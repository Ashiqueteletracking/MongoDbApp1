package com.Mongo2.Controller;

import com.Mongo2.Entity.Staff;
import com.Mongo2.Service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:8080")
public class StaffController {

    private final StaffService staffService;
    @Autowired
    public StaffController( StaffService staffService) {
        this.staffService = staffService;

    }
    @GetMapping("/get")
    public List<Staff> getAllStaff(){

        return staffService.getAllStaff();
    }
    @PostMapping("/post")
    public ResponseEntity<Staff> createStaff(@RequestBody Staff staff){
        Staff savedStaff = staffService.saveStaff(staff);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedStaff);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Staff> getStaff(@PathVariable Long id){
        Optional<Staff> staff=staffService.getStaffById(id);
        return staff.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PutMapping("/{id}")
    public ResponseEntity<Staff> updateStaff(@PathVariable Long id, @RequestBody Staff updatedStaff){
        Optional<Staff> staff = staffService.getStaffById(id);
        if (staff.isPresent()){
            updatedStaff.setId(updatedStaff.getId());
            updatedStaff.setEmail(updatedStaff.getEmail());
            updatedStaff.setName(updatedStaff.getName());
            updatedStaff.setPosition(updatedStaff.getPosition());
            Staff savedStaff =staffService.saveStaff(updatedStaff);
            return ResponseEntity.ok(savedStaff);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStaff(@PathVariable Long id){
        Optional<Staff> staff =staffService.getStaffById(id);
        if(staff.isPresent()){
            staffService.deleteStaff(id);
            return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
