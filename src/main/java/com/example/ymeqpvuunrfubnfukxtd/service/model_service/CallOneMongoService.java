package com.example.ymeqpvuunrfubnfukxtd.service.model_service;

import com.example.ymeqpvuunrfubnfukxtd.model.entity.CallOneMongo;
import com.example.ymeqpvuunrfubnfukxtd.model.service_dto.CallOneMongoDTO;

public interface CallOneMongoService {
    CallOneMongo callOneMongoDTOToCallOneMongo(CallOneMongoDTO callOneMongoDTO);
    CallOneMongoDTO callOneMongoToCallOneMongoDTO(CallOneMongo callOneMongo);
    void addPhoneObject(CallOneMongo callOneMongo);
    CallOneMongo getPhoneObject(String phoneOne);
    String deleteCallOneMongoObjectByPhoneOne(String phoneOne);
    String updateCallObjectMongoByPhoneNumber(String phoneOne, CallOneMongoDTO callOneMongoDTO);
}
