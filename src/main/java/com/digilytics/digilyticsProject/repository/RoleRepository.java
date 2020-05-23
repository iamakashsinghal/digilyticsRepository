package com.digilytics.digilyticsProject.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.digilytics.digilyticsProject.entity.Role;
/**
 * @author akash
 *
 */
@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
	
	Role findByRoleId(String id);

	Role findByRoleName(String role);
}
