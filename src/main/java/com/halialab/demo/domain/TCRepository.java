/**
 * 
 */
package com.halialab.demo.domain;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

/**
 * @author Janath
 *
 */
public interface TCRepository extends CrudRepository<TCDetail, String> {
	
	TCDetail findByCid(String id);

}
