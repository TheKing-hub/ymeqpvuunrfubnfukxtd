package com.example.ymeqpvuunrfubnfukxtd.service.model_service;

import com.example.ymeqpvuunrfubnfukxtd.model.entity.CallOne;
import com.example.ymeqpvuunrfubnfukxtd.model.service_dto.CallOneDTO;

import java.util.List;

public interface CallOneService {
    CallOne callOneDTOToCallOne(CallOneDTO callOneDTO);

    CallOneDTO callOneToCallOneDTO(CallOne callOne);

    Boolean checkPhoneNumber(String number);

    void addPhoneObject(CallOne callOne);

    Boolean checkName(String name);

    Boolean checkYear(Integer year);

    Boolean checkDate(String date);

    CallOne getCallObjectById(Long id);

    String deleteCallOneObject(Long id);

    List<CallOneDTO> getAllCallObjects();
}
