/**
 * 
 */
package com.halialab.demo.domain;

import java.util.List;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import com.halialab.demo.domain.dynamodb.Customer;

/**
 * @author Janath
 *
 */
@EnableScan
public interface UserRepository extends CrudRepository<User, String> {
	List<User> findByUsername(String username);
}
