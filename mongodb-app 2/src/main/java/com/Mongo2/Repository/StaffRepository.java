package com.Mongo2.Repository;

import com.Mongo2.Entity.Staff;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface StaffRepository extends MongoRepository<Staff,Long> {

}
