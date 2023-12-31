package com.Mongo2.Service;

import com.Mongo2.Entity.Staff;
import com.Mongo2.Repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StaffService {

    private final StaffRepository staffRepository;

    @Autowired
    public StaffService(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }


    public List<Staff> getAllStaff(){
        return staffRepository.findAll();
    }

    public Optional<Staff> getStaffById(Long id){
        return  staffRepository.findById(id);
    }
    public Staff saveStaff (Staff staff){
        return staffRepository.save(staff);
    }

    public void deleteStaff(Long id){
        staffRepository.deleteById(id);
    }

}
