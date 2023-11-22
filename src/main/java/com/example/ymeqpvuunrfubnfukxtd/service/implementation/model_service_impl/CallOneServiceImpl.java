package com.example.ymeqpvuunrfubnfukxtd.service.implementation.model_service_impl;

import com.example.ymeqpvuunrfubnfukxtd.exception.ObjectCannotBeAddedException;
import com.example.ymeqpvuunrfubnfukxtd.model.entity.CallOne;
import com.example.ymeqpvuunrfubnfukxtd.model.service_dto.CallOneDTO;
import com.example.ymeqpvuunrfubnfukxtd.model.service_dto.Filter;
import com.example.ymeqpvuunrfubnfukxtd.repository.CallOneRepository;
import com.example.ymeqpvuunrfubnfukxtd.service.model_service.CallOneService;
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
public class CallOneServiceImpl implements CallOneService {
    private final CallOneRepository callOneRepository;
    public CallOne callOneDTOToCallOne(CallOneDTO callOneDTO) {
        CallOne callOne = new CallOne();
        callOne.setName(callOneDTO.getName());
        callOne.setYear(callOneDTO.getYear());
        callOne.setPhoneOne(callOneDTO.getPhoneOne());
        callOne.setPhoneTwo(callOneDTO.getPhoneTwo());
        callOne.setCreationDate(callOneDTO.getCreationDate());
        return callOne;
    }

    public CallOneDTO callOneToCallOneDTO(CallOne callOne) {
        CallOneDTO callOneDTO = new CallOneDTO();
        callOneDTO.setName(callOne.getName());
        callOneDTO.setYear(callOne.getYear());
        callOneDTO.setPhoneOne(callOne.getPhoneOne());
        callOneDTO.setPhoneTwo(callOne.getPhoneTwo());
        callOneDTO.setCreationDate(callOne.getCreationDate());
        return callOneDTO;
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

    public void addPhoneObject(CallOne callOne) {
        boolean successfulData = checkPhoneNumber(callOne.getPhoneOne()) &&
                checkPhoneNumber(callOne.getPhoneTwo()) &&
                checkName(callOne.getName()) &&
                checkYear(callOne.getYear()) &&
                checkDate(callOne.getCreationDate());

        if (successfulData) {
            callOneRepository.save(callOne);
        }
        else {
            throw new ObjectCannotBeAddedException("Some input data is wrong");
        }
    }

    public CallOne getCallObjectById(Long id) {
        return callOneRepository.findById(id).orElse(null);
    }

    public String deleteCallOneObject(Long id) {
        if (callOneRepository.findById(id).isEmpty()) {
            return "Id " + id + " does not exist";
        }
        callOneRepository.deleteById(id);
        return "Object with " + id + " was successfully deleted";
    }

    public String updateCallObjectById(Long id, CallOneDTO callOneDTO) {
        if (callOneRepository.findById(id).isEmpty()) {
            return "Id " + id + " does not exist";
        }
        CallOne callOne = callOneRepository.findById(id).get();
        callOne.setName(callOneDTO.getName());
        callOne.setYear(callOneDTO.getYear());
        callOne.setPhoneOne(callOneDTO.getPhoneOne());
        callOne.setPhoneTwo(callOneDTO.getPhoneTwo());
        callOne.setCreationDate(callOneDTO.getCreationDate());
        callOneRepository.save(callOne);
        return "Call object with " + id + " was successfully updated";
    }

    public List<CallOneDTO> getAllCallObjects() {
        return callOneRepository.findAll().stream().map(this::callOneToCallOneDTO).collect(Collectors.toList());
    }

    public List<CallOneDTO> getFilteredCallOnes(Filter filter) {
        List<CallOneDTO> callOneDTOS = getAllCallObjects();
        int startIndex = filter.getLimit() * filter.getOffset();
        List<CallOneDTO> filteredCallOnes = new ArrayList<>();
        for (int i = startIndex; i < startIndex + filter.getLimit(); ++i) {
            filteredCallOnes.add(callOneDTOS.get(i));
        }
        return filteredCallOnes;
    }
}
