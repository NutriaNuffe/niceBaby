package com.nice.nicebaby.service;

//import com.nice.nicebaby.dao.UserDao;
//import com.nice.nicebaby.dao.daoInterface.UserDaoInterface;
//import com.nice.nicebaby.model.User;

import com.nice.nicebaby.dao.UserDao;
import com.nice.nicebaby.dto.user.ReqUserRegister;
import com.nice.nicebaby.entity.User;
import com.nice.nicebaby.repository.UserRepository;
import com.nice.nicebaby.util.httpResultUtil.HttpResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static com.nice.nicebaby.define.ErrorId.Account_Already_Registered;
import static com.nice.nicebaby.define.ErrorId.Success;

@Service
public class UserService {

    @Autowired
    private UserRepository user;

    @Autowired
    private UserDao userDao;

//    @Autowired
//    private UserDaoInterface userDaoInterface;


    @Transactional
    public List<User> findByAllUser() {
//        return userDao.getAllUser();
//        return userDaoInterface.findAll();
        return user.findAllUsers();
    }

    @Transactional
    public HttpResult<User> register(ReqUserRegister reqUserRegister) {

        // 檢查是否註冊
        User user = userDao.findByAccount(reqUserRegister.getAccount());

        if (user != null) {
            return new HttpResult<>(Account_Already_Registered, null);
        }

        // 創建帳號
        User newUser = new User();
        BeanUtils.copyProperties(reqUserRegister, newUser);
        user = userDao.saveAndFlush(newUser);

        // TODO** 將密碼加密

        return new HttpResult<>(Success, user);

    }

}
