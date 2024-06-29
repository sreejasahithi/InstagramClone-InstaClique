
package com.insta.Instagram_clone.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import com.insta.Instagram_clone.model.*;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface Account1Repository extends MongoRepository<Account1, String> {

    @Query("{ 'UserId' : ?0 }")
    Account1 findByUserId(String UserId);

    Account1 findFirstByOrderByUserIdDesc();
}
