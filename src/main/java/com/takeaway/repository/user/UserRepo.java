package com.takeaway.repository.user;

import com.takeaway.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface UserRepo extends JpaRepository<User, Long> {

    User findByUserNameAndPassword(String userName, String password);

    Integer countByUserName(String userName);

    User findById(Long id);

}
