/**
 * 
 */
package lc.alg.controller;

import org.springframework.data.mongodb.repository.MongoRepository;

import lc.alg.entity.User;

/**
 * @author �
 *
 */
public interface UserRepository extends MongoRepository<User, String>{

}
