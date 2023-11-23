package com.example.ymeqpvuunrfubnfukxtd.service.implementation.model_service_impl;

import com.example.ymeqpvuunrfubnfukxtd.exception.EntityNotFoundException;
import com.example.ymeqpvuunrfubnfukxtd.exception.IncorrectCallObject;
import com.example.ymeqpvuunrfubnfukxtd.exception.ObjectCannotBeAddedException;
import com.example.ymeqpvuunrfubnfukxtd.model.entity.CallOneMongo;
import com.example.ymeqpvuunrfubnfukxtd.model.service_dto.CallOneMongoDTO;
import com.example.ymeqpvuunrfubnfukxtd.model.service_dto.Filter;
import com.example.ymeqpvuunrfubnfukxtd.repository.CallOneMongoRepository;
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

    // Конвертация объекта CallOneMongoDTO в CallOneMongo
    public CallOneMongo callOneMongoDTOToCallOneMongo(CallOneMongoDTO callOneMongoDTO) {
        if (checkCallOneMongoDTO(callOneMongoDTO)) {
            CallOneMongo callOneMongo = new CallOneMongo();
            callOneMongo.setName(callOneMongoDTO.getName());
            callOneMongo.setYear(callOneMongoDTO.getYear());
            callOneMongo.setPhoneOne(callOneMongoDTO.getPhoneOne());
            callOneMongo.setPhoneTwo(callOneMongoDTO.getPhoneTwo());
            callOneMongo.setCreationDate(callOneMongoDTO.getCreationDate());
            return callOneMongo;
        }
        else {
            throw new IncorrectCallObject("The data in this object is incorrect!");
        }
    }

    // Конвертация объекта CallOneMongo в CallOneMongoDTO
    public CallOneMongoDTO callOneMongoToCallOneMongoDTO(CallOneMongo callOneMongo) {
        if (checkCallOneMongo(callOneMongo)) {
            CallOneMongoDTO callOneMongoDTO = new CallOneMongoDTO();
            callOneMongoDTO.setName(callOneMongo.getName());
            callOneMongoDTO.setYear(callOneMongo.getYear());
            callOneMongoDTO.setPhoneOne(callOneMongo.getPhoneOne());
            callOneMongoDTO.setPhoneTwo(callOneMongo.getPhoneTwo());
            callOneMongoDTO.setCreationDate(callOneMongo.getCreationDate());
            return callOneMongoDTO;
        }
        else {
            throw new IncorrectCallObject("The data in this object is incorrect!");
        }
    }

    // Проверка данных объекта CallOneMongo
    public Boolean checkCallOneMongo(CallOneMongo callOneMongo) {
        return checkPhoneNumber(callOneMongo.getPhoneOne()) &&
                checkPhoneNumber(callOneMongo.getPhoneTwo()) &&
                checkName(callOneMongo.getName()) &&
                checkYear(callOneMongo.getYear()) &&
                checkDate(callOneMongo.getCreationDate());
    }

    // Проверка данных объекта CallOneMongoDTO
    public Boolean checkCallOneMongoDTO(CallOneMongoDTO callOneMongoDTO) {
        return checkPhoneNumber(callOneMongoDTO.getPhoneOne()) &&
                checkPhoneNumber(callOneMongoDTO.getPhoneTwo()) &&
                checkName(callOneMongoDTO.getName()) &&
                checkYear(callOneMongoDTO.getYear()) &&
                checkDate(callOneMongoDTO.getCreationDate());
    }

    // Проверка номера телефона. Длина номера телефона 11-12 символов (может '+' быть)
    public Boolean checkPhoneNumber(String number) {
        String regex = "(^[78]\\d{10}$)|(^\\+[78]\\d{10}$)";
        return number.matches(regex);
    }

    // Проверка на имя. Любые пробельные символы допускаются, (\\s)
    // Не алфавитные буквы не допускаются
    public Boolean checkName(String name) {
        String nameRegex = "^[A-Za-z\\s]+$";
        return name.matches(nameRegex);
    }

    // Проверка на год (от 1900 до 2023)
    public Boolean checkYear(Integer year) {
        return year >= 1900 && year <= 2023;
    }

    // Проверка даты в формате Day-Month-Year с дня 01.01.1900 до сегодня
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

    // Добавление объекта в репозиторий, MongoDB
    public void addPhoneObject(CallOneMongo callOneMongo) {
        boolean successfulData = checkCallOneMongo(callOneMongo);
        if (successfulData) {
            callOneMongoRepository.save(callOneMongo);
        }
        else {
            throw new ObjectCannotBeAddedException("Some input data is wrong!");
        }
    }

    // Получить объект CallOneMongo по первому номеру телефона
    public CallOneMongo getPhoneObject(String phoneOne) {
        CallOneMongo callOneMongo = callOneMongoRepository.getCallOneMongoByPhoneOne(phoneOne);
        if (callOneMongo != null) {
            return callOneMongo;
        }
        throw new EntityNotFoundException("Incorrect phone number, " +
                "object with first phone number " + phoneOne + " does not exist!");
    }

    // Удалить объект CallOneMongo по первому номеру телефона
    public String deleteCallOneMongoObjectByPhoneOne(String phoneOne) {
        CallOneMongo callOneMongo = callOneMongoRepository.getCallOneMongoByPhoneOne(phoneOne);
        if (callOneMongo != null) {
            callOneMongoRepository.delete(callOneMongo);
            return "Call Object with phone number " + phoneOne + " was successfully deleted!";
        }
        throw new EntityNotFoundException("Incorrect phone number, " +
                "object with first phone number " + phoneOne + " does not exist!");
    }

    // Обновить объект CallOneMongo по первому номеру телефона
    public String updateCallObjectMongoByPhoneNumber(String phoneOne, CallOneMongoDTO callOneMongoDTO) {
        CallOneMongo callOneMongo = callOneMongoRepository.getCallOneMongoByPhoneOne(phoneOne);
        if (callOneMongo == null) {
            throw new EntityNotFoundException("Call Object with phone " + phoneOne + " does not exist!");
        }
        if (checkCallOneMongoDTO(callOneMongoDTO)) {
            callOneMongo.setName(callOneMongoDTO.getName());
            callOneMongo.setYear(callOneMongoDTO.getYear());
            callOneMongo.setPhoneOne(callOneMongoDTO.getPhoneOne());
            callOneMongo.setPhoneTwo(callOneMongoDTO.getPhoneTwo());
            callOneMongo.setCreationDate(callOneMongoDTO.getCreationDate());
            callOneMongoRepository.save(callOneMongo);
            return "Call Object with phone number " + phoneOne + " was successfully updated!";
        }
        throw new IncorrectCallObject("Call object is not correctly filled!");
    }

    // Получить все объекты CallOneMongo
    public List<CallOneMongoDTO> getAllCallMongoObjects() {
        return callOneMongoRepository.findAll().stream()
                .map(this::callOneMongoToCallOneMongoDTO)
                .collect(Collectors.toList());
    }

    // Получить объекты CallOneMongo по Filter
    public List<CallOneMongoDTO> getFilteredCallOnesMongo(Filter filter) {
        List<CallOneMongoDTO> callOneDTOS = getAllCallMongoObjects();
        int limitValue = filter.getLimit() * (filter.getOffset() + 1);
        // Если limit, offset перейдут за грань размеров БД
        if (limitValue > callOneDTOS.size()) {
            throw new IncorrectCallObject("'limit' and 'offset' are incorrect!" +
                    " The size of our database is " + callOneDTOS.size());
        }
        int startIndex = filter.getLimit() * filter.getOffset();
        List<CallOneMongoDTO> filteredCallMongoOnes = new ArrayList<>();
        for (int i = startIndex; i < startIndex + filter.getLimit(); ++i) {
            filteredCallMongoOnes.add(callOneDTOS.get(i));
        }
        return filteredCallMongoOnes;
    }
}
