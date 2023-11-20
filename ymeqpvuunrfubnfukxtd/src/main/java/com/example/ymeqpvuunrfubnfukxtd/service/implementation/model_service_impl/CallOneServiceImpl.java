package com.example.ymeqpvuunrfubnfukxtd.service.implementation.model_service_impl;

import com.example.ymeqpvuunrfubnfukxtd.model.entity.CallOne;
import com.example.ymeqpvuunrfubnfukxtd.model.service_dto.CallOneDTO;
import com.example.ymeqpvuunrfubnfukxtd.service.model_service.CallOneService;

public class CallOneServiceImpl implements CallOneService {
    public CallOne callOneDTOToCallOne(CallOneDTO callOneDTO) {
        CallOne callOne = new CallOne();
        callOne.setName(callOneDTO.getName());
        callOne.setYear(callOneDTO.getYear());
        callOne.setPhoneOne(callOneDTO.getPhoneOne());
        callOne.setPhoneTwo(callOneDTO.getPhoneTwo());
        callOne.setCreation(callOneDTO.getCreation());
        return callOne;
    }

    public CallOneDTO callOneToCallOneDTO(CallOne callOne) {
        CallOneDTO callOneDTO = new CallOneDTO();
        callOneDTO.setName(callOne.getName());
        callOneDTO.setYear(callOne.getYear());
        callOneDTO.setPhoneOne(callOne.getPhoneOne());
        callOneDTO.setPhoneTwo(callOne.getPhoneTwo());
        callOneDTO.setCreation(callOne.getCreation());
        return callOneDTO;
    }

    public Boolean checkPhoneNumber() {
        return true;
    }

    public void addPhoneNumber() {

    }

    public String getPhoneNumber() {
        return "";
    }

    public String deletePhoneNumber() {
        return "";
    }
}
