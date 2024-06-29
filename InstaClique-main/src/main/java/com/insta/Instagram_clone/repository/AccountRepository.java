
package com.insta.Instagram_clone.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import com.insta.Instagram_clone.model.*;
import java.util.List;

public interface AccountRepository extends MongoRepository<Account, String> {
    List<Account> findByUsernameContaining(String username);

    Account findByUsername(String username);

    Account save(Account account);

    long countByUsername(String username);

    boolean existsById(String id);

    Account findByIdAndUsernameAndPassword(String userId, String username, String password);

    List<Account> findByUsernameContainingIgnoreCaseOrEmailContainingIgnoreCase(String username, String email);

    @Query(value = "{}", fields = "{ '_id': 0, 'id': 1 }")
    List<String> findAllUserIds();
}