package com.example.ymeqpvuunrfubnfukxtd.service.implementation.model_service_impl;

import com.example.ymeqpvuunrfubnfukxtd.exception.ObjectCannotBeAddedException;
import com.example.ymeqpvuunrfubnfukxtd.model.entity.CallOne;
import com.example.ymeqpvuunrfubnfukxtd.model.entity.CallOneMongo;
import com.example.ymeqpvuunrfubnfukxtd.model.service_dto.CallOneMongoDTO;
import com.example.ymeqpvuunrfubnfukxtd.repository.CallOneMongoRepository;
import com.example.ymeqpvuunrfubnfukxtd.repository.CallOneRepository;
import com.example.ymeqpvuunrfubnfukxtd.service.model_service.CallOneMongoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Service
@RequiredArgsConstructor
public class CallOneMongoServiceImpl implements CallOneMongoService {
    private final CallOneMongoRepository callOneMongoRepository;

    public CallOneMongo callOneMongoDTOToCallOneMongo(CallOneMongoDTO callOneMongoDTO) {
        CallOneMongo callOneMongo = new CallOneMongo();
        callOneMongo.setName(callOneMongoDTO.getName());
        callOneMongo.setYear(callOneMongoDTO.getYear());
        callOneMongo.setPhoneOne(callOneMongoDTO.getPhoneOne());
        callOneMongo.setPhoneTwo(callOneMongoDTO.getPhoneTwo());
        callOneMongo.setCreationDate(callOneMongoDTO.getCreationDate());
        return callOneMongo;
    }

    public CallOneMongoDTO callOneMongoToCallOneMongoDTO(CallOneMongo callOneMongo) {
        CallOneMongoDTO callOneMongoDTO = new CallOneMongoDTO();
        callOneMongoDTO.setName(callOneMongo.getName());
        callOneMongoDTO.setYear(callOneMongo.getYear());
        callOneMongoDTO.setPhoneOne(callOneMongo.getPhoneOne());
        callOneMongoDTO.setPhoneTwo(callOneMongo.getPhoneTwo());
        callOneMongoDTO.setCreationDate(callOneMongo.getCreationDate());
        return callOneMongoDTO;
    }

    // Длина номера телефона 11-12 символов (может + быть)
    public Boolean checkPhoneNumber(String number) {
        String regex = "(^[78]\\d{10}$)|(^\\+[78]\\d{10}$)";
        return number.matches(regex);
    }

    public Boolean checkName(String name) {
        String nameRegex = "^[A-Za-z\\s'-]+$";
        return name.matches(nameRegex);
    }

    public Boolean checkYear(Integer year) {
        return year >= 1900 && year <= 2023;
    }

    // Day/Month/Year from 01.01.1900 to now
    public Boolean checkDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        try {
            LocalDate parsedDate = LocalDate.parse(date, formatter);
            LocalDate startDate = LocalDate.of(1900, 1, 1);
            LocalDate currentDate = LocalDate.now();
            return (parsedDate.isEqual(startDate) || parsedDate.isAfter(startDate)) &&
                    (parsedDate.isEqual(currentDate) || parsedDate.isBefore(currentDate));
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public void addPhoneObject(CallOneMongo callOneMongo) {
        boolean successfulData = checkPhoneNumber(callOneMongo.getPhoneOne()) &&
                checkPhoneNumber(callOneMongo.getPhoneTwo()) &&
                checkName(callOneMongo.getName()) &&
                checkYear(callOneMongo.getYear()) &&
                checkDate(callOneMongo.getCreationDate());
        if (successfulData) {
            callOneMongoRepository.save(callOneMongo);
        }
        else {
            throw new ObjectCannotBeAddedException("Some input data is wrong");
        }
    }

    public CallOneMongo getPhoneObject(String phoneOne) {
        CallOneMongo callOneMongo = callOneMongoRepository.getCallOneMongoByPhoneOne(phoneOne);
        if (callOneMongo != null) {
            return callOneMongo;
        }
        throw new IllegalStateException("Incorrect phone number, " + phoneOne + " does not exist");
    }
}
