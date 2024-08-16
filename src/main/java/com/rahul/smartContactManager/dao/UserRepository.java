package com.rahul.smartContactManager.dao;

import com.rahul.smartContactManager.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {


}
