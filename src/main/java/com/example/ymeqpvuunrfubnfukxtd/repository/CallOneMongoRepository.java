package com.example.ymeqpvuunrfubnfukxtd.repository;

import com.example.ymeqpvuunrfubnfukxtd.model.entity.CallOneMongo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CallOneMongoRepository extends MongoRepository<CallOneMongo, String> {
    CallOneMongo getCallOneMongoByPhoneOne(String phoneOne);
}
