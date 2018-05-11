///**
// * 
// */
//package com.halialab.demo.domain;
//
//import java.util.UUID;
//
//import org.springframework.data.cassandra.repository.CassandraRepository;
//import org.springframework.data.cassandra.repository.Query;
//import org.springframework.stereotype.Repository;
//
///**
// * @author Janath
// *
// */
//@Repository
//public interface UsrRepository extends CassandraRepository<Usr, UUID> {
//	
//	@Query(allowFiltering = true)
//	Usr findByUsername(final String username);
//
//}
