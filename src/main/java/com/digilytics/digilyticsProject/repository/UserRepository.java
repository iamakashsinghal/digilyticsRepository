/**
 * 
 */
package com.digilytics.digilyticsProject.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.digilytics.digilyticsProject.entity.User;

/**
 * @author akash
 *
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long>{

	User findByUserId(String id);
	List<User> findAll();
	User findAllByEmailId(String email);
	List<User> findByErrorFlag(boolean b);

}
