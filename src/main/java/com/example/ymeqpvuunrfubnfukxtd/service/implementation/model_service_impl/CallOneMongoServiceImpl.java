package com.example.ymeqpvuunrfubnfukxtd.service.implementation.model_service_impl;

import com.example.ymeqpvuunrfubnfukxtd.exception.ObjectCannotBeAddedException;
import com.example.ymeqpvuunrfubnfukxtd.model.entity.CallOne;
import com.example.ymeqpvuunrfubnfukxtd.model.entity.CallOneMongo;
import com.example.ymeqpvuunrfubnfukxtd.model.service_dto.CallOneDTO;
import com.example.ymeqpvuunrfubnfukxtd.model.service_dto.CallOneMongoDTO;
import com.example.ymeqpvuunrfubnfukxtd.model.service_dto.Filter;
import com.example.ymeqpvuunrfubnfukxtd.repository.CallOneMongoRepository;
import com.example.ymeqpvuunrfubnfukxtd.repository.CallOneRepository;
import com.example.ymeqpvuunrfubnfukxtd.service.model_service.CallOneMongoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public String deleteCallOneMongoObjectByPhoneOne(String phoneOne) {
        CallOneMongo callOneMongo = callOneMongoRepository.getCallOneMongoByPhoneOne(phoneOne);
        if (callOneMongo != null) {
            callOneMongoRepository.delete(callOneMongo);
            return "Call Object with phone number " + phoneOne + " was successfully deleted!";
        }
        throw new IllegalStateException("There is no object with " + phoneOne + " phone number");
    }

    public String updateCallObjectMongoByPhoneNumber(String phoneOne, CallOneMongoDTO callOneMongoDTO) {
        CallOneMongo callOneMongo = callOneMongoRepository.getCallOneMongoByPhoneOne(phoneOne);
        if (callOneMongo == null) {
            return "Call Object with phone " + phoneOne + " does not exist";
        }
        callOneMongo.setName(callOneMongoDTO.getName());
        callOneMongo.setYear(callOneMongoDTO.getYear());
        callOneMongo.setPhoneOne(callOneMongoDTO.getPhoneOne());
        callOneMongo.setPhoneTwo(callOneMongoDTO.getPhoneTwo());
        callOneMongo.setCreationDate(callOneMongoDTO.getCreationDate());
        callOneMongoRepository.save(callOneMongo);
        return "Call Object with " + phoneOne + " was successfully updated!";
    }

    public List<CallOneMongoDTO> getAllCallMongoObjects() {
        return callOneMongoRepository.findAll().stream()
                .map(this::callOneMongoToCallOneMongoDTO)
                .collect(Collectors.toList());
    }

    public List<CallOneMongoDTO> getFilteredCallOnesMongo(Filter filter) {
        List<CallOneMongoDTO> callOneDTOS = getAllCallMongoObjects();
        int startIndex = filter.getLimit() * filter.getOffset();
        List<CallOneMongoDTO> filteredCallMongoOnes = new ArrayList<>();
        for (int i = startIndex; i < startIndex + filter.getLimit(); ++i) {
            filteredCallMongoOnes.add(callOneDTOS.get(i));
        }
        return filteredCallMongoOnes;
    }
}
