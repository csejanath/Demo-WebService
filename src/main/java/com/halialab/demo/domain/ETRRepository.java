/**
 * 
 */
package com.halialab.demo.domain;

import org.springframework.data.repository.CrudRepository;

/**
 * @author Janath
 *
 */
public interface ETRRepository extends CrudRepository<ETRDetail, String> {
	
	ETRDetail findByAssetID(String assetID);

}
