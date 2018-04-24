/**
 * 
 */
package com.halialab.demo.domain;

import org.springframework.data.repository.CrudRepository;

/**
 * @author Janath
 *
 */
public interface ETRRepository extends CrudRepository<ETRDetail, Long> {
	
	ETRDetail findByAssetID(String assetID);

}
