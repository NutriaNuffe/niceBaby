package com.nice.nicebaby.service;

import com.nice.nicebaby.dao.UserDao;
import com.nice.nicebaby.dto.user.ReqUserLogin;
import com.nice.nicebaby.dto.user.ReqUserRegister;
import com.nice.nicebaby.dto.user.RespUserLogin;
import com.nice.nicebaby.entity.User;
import com.nice.nicebaby.repository.UserRepository;
import com.nice.nicebaby.util.HttpResult;
import com.nice.nicebaby.util.JwtUtil;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.util.List;

import static com.nice.nicebaby.define.ErrorId.*;

@Service
public class UserService {

    @Autowired
    private UserRepository user;

    @Autowired
    private UserDao userDao;

    @Autowired
    private JwtUtil jwtUtil;

    @Transactional
    public List<User> findByAllUser() {
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

        // 將密碼加密
        String hashPassword = BCrypt.hashpw(reqUserRegister.getPassword(), BCrypt.gensalt());
        newUser.setPassword(hashPassword);

        user = userDao.saveAndFlush(newUser);

        return new HttpResult<>(Success, user);

    }

    public HttpResult<RespUserLogin> login(ReqUserLogin reqUserLogin) {

        User user = userDao.findByAccount(reqUserLogin.getAccount());

        // 檢查 User 是否存在
        if (user == null) {
            return new HttpResult<>(Email_Not_Registered, null);
        }

        boolean passwordMatches = BCrypt.checkpw(reqUserLogin.getPassword(), user.getPassword());

        if (passwordMatches) {
            String token = jwtUtil.generateToken(user);
            RespUserLogin respUserLogin = new RespUserLogin();
            BeanUtils.copyProperties(user, respUserLogin);
            respUserLogin.setToken(token);
            return new HttpResult<>(Success, respUserLogin);
        } else {
            return new HttpResult<>(Password_Incorrect, null);
        }
    }

}
