package com.vasu.dao;

import com.vasu.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;


@Transactional
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByUserNameAndPassword(String userName, String password);
}
