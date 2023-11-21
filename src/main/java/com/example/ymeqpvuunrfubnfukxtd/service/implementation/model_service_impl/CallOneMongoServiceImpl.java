package com.example.ymeqpvuunrfubnfukxtd.service.implementation.model_service_impl;

import com.example.ymeqpvuunrfubnfukxtd.exception.ObjectCannotBeAddedException;
import com.example.ymeqpvuunrfubnfukxtd.model.entity.CallOne;
import com.example.ymeqpvuunrfubnfukxtd.model.entity.CallOneMongo;
import com.example.ymeqpvuunrfubnfukxtd.repository.CallOneMongoRepository;
import com.example.ymeqpvuunrfubnfukxtd.repository.CallOneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CallOneMongoServiceImpl {
    private final CallOneMongoRepository callOneMongoRepository;

    public void addPhoneObject(CallOneMongo callOneMongo) {
        callOneMongoRepository.save(callOneMongo);
    }
}
