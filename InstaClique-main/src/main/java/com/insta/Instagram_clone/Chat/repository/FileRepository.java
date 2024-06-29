package com.insta.Instagram_clone.Chat.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.insta.Instagram_clone.Chat.model.*;

public interface FileRepository extends MongoRepository<FileEntity, String> {
}
