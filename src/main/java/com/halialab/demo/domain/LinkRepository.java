/**
 * 
 */
package com.halialab.demo.domain;

import org.springframework.data.repository.CrudRepository;

/**
 * @author Janath
 *
 */
public interface LinkRepository extends CrudRepository<LinkDetail, Long> {
	
	LinkDetail findByLid(Long id);

}
