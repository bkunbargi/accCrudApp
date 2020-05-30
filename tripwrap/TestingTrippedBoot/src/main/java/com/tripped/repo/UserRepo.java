package com.tripped.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tripped.model.UserImpl;

public interface UserRepo extends JpaRepository<UserImpl, String> {
	@Query(value = "select * from userlogin u where u.email= :email and u.password = :password", nativeQuery = true)
	UserImpl findByEmailAddress(@Param("email") String email, @Param("password") String password);
}
