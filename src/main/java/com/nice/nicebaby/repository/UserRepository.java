package com.nice.nicebaby.repository;
//import com.nice.nicebaby.model.User;

import com.nice.nicebaby.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
//@Transactional
public class UserRepository {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final EntityManager entityManager;

    public UserRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<User> findAllUsers() {
        return entityManager.createQuery("SELECT e FROM User e", User.class).getResultList();
    }

    public User findById(Long id) {
        User user = entityManager.find(User.class, id);

        logger.info("User -> {}", user);

        return user;
    }

    public void insert() {
        User user = new User();

        entityManager.persist(user);

        logger.info("New user -> {}", user);
    }

    public void update() {
        User user = findById(1L);
        user.setFirst_name("Jane");

        entityManager.merge(user);

        logger.info("Updated user -> {}", user);
    }

    public void delete() {
        User user = findById(1L);

        entityManager.remove(user);
    }

}
