package com.nice.nicebaby.service;

//import com.nice.nicebaby.dao.UserDao;
//import com.nice.nicebaby.dao.daoInterface.UserDaoInterface;
//import com.nice.nicebaby.model.User;
import com.nice.nicebaby.entity.User;
import com.nice.nicebaby.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository user;

//    @Autowired
//    private UserDaoInterface userDaoInterface;



@Transactional
    public List<User> findByAllUser(){
//        return userDao.getAllUser();
//        return userDaoInterface.findAll();
        return user.findAllUsers();
    }
}
