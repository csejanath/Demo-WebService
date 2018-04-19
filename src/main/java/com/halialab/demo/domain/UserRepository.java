/**
 * 
 */
package com.halialab.demo.domain;

import org.springframework.data.repository.CrudRepository;

/**
 * @author Janath
 *
 */
public interface UserRepository extends CrudRepository<User, Long> {
	User findByUsername(String username);
}
