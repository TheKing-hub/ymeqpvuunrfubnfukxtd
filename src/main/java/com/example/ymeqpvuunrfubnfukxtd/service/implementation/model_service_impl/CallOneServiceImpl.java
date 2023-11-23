package com.example.ymeqpvuunrfubnfukxtd.service.implementation.model_service_impl;

import com.example.ymeqpvuunrfubnfukxtd.exception.EntityNotFoundException;
import com.example.ymeqpvuunrfubnfukxtd.exception.IncorrectCallObject;
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

    // Конвертация объекта CallOneDTO в CallOne
    public CallOne callOneDTOToCallOne(CallOneDTO callOneDTO) {
        if (checkCallOneDTO(callOneDTO)) {
            CallOne callOne = new CallOne();
            callOne.setName(callOneDTO.getName());
            callOne.setYear(callOneDTO.getYear());
            callOne.setPhoneOne(callOneDTO.getPhoneOne());
            callOne.setPhoneTwo(callOneDTO.getPhoneTwo());
            callOne.setCreationDate(callOneDTO.getCreationDate());
            return callOne;
        }
        else {
            throw new IncorrectCallObject("The data in this object is incorrect!");
        }
    }

    // Конвертация объекта CallOne в CallOneDTO
    public CallOneDTO callOneToCallOneDTO(CallOne callOne) {
        if (checkCallOne(callOne)) {
            CallOneDTO callOneDTO = new CallOneDTO();
            callOneDTO.setName(callOne.getName());
            callOneDTO.setYear(callOne.getYear());
            callOneDTO.setPhoneOne(callOne.getPhoneOne());
            callOneDTO.setPhoneTwo(callOne.getPhoneTwo());
            callOneDTO.setCreationDate(callOne.getCreationDate());
            return callOneDTO;
        }
        else {
            throw new IncorrectCallObject("The data in this object is incorrect!");
        }
    }

    // Проверка данных объекта CallOne
    public Boolean checkCallOne(CallOne callOne) {
        return checkPhoneNumber(callOne.getPhoneOne()) &&
                checkPhoneNumber(callOne.getPhoneTwo()) &&
                checkName(callOne.getName()) &&
                checkYear(callOne.getYear()) &&
                checkDate(callOne.getCreationDate());
    }

    // Проверка данных объекта CallOneDTO
    public Boolean checkCallOneDTO(CallOneDTO callOneDTO) {
        return checkPhoneNumber(callOneDTO.getPhoneOne()) &&
                checkPhoneNumber(callOneDTO.getPhoneTwo()) &&
                checkName(callOneDTO.getName()) &&
                checkYear(callOneDTO.getYear()) &&
                checkDate(callOneDTO.getCreationDate());
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

    // Добавление объекта в репозиторий, БД PostgreSQL
    public void addPhoneObject(CallOne callOne) {
        boolean successfulData = checkCallOne(callOne);

        if (successfulData) {
            callOneRepository.save(callOne);
        }
        else {
            throw new ObjectCannotBeAddedException("Some input data is wrong!");
        }
    }

    // Получить объект CallOne по id
    public CallOne getCallObjectById(Long id) {
        return callOneRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Call Object with id " + id + " is not found!"));
    }

    // Удалить объект CallOne по id
    public String deleteCallOneObject(Long id) {
        if (callOneRepository.findById(id).isEmpty()) {
            throw new EntityNotFoundException("Object with id " + id + " does not exist!");
        }
        callOneRepository.deleteById(id);
        return "Object with id " + id + " was successfully deleted!";
    }

    // Обновить объект CallOne по id
    public String updateCallObjectById(Long id, CallOneDTO callOneDTO) {
        if (callOneRepository.findById(id).isEmpty()) {
            throw new EntityNotFoundException("Object with id " + id + " does not exist!");
        }
        // Проверка на корректность данных в callOneDTO
        if (checkCallOneDTO(callOneDTO)) {
            CallOne callOne = callOneRepository.findById(id).get();
            callOne.setName(callOneDTO.getName());
            callOne.setYear(callOneDTO.getYear());
            callOne.setPhoneOne(callOneDTO.getPhoneOne());
            callOne.setPhoneTwo(callOneDTO.getPhoneTwo());
            callOne.setCreationDate(callOneDTO.getCreationDate());
            callOneRepository.save(callOne);
            return "Call object with id " + id + " was successfully updated!";
        }
        throw new IncorrectCallObject("The data in this Call Object is incorrect!");
    }

    // Получить все объекты CallOne
    public List<CallOneDTO> getAllCallObjects() {
        return callOneRepository.findAll().stream().map(this::callOneToCallOneDTO).collect(Collectors.toList());
    }

    // Получить объекты CallOne по Filter
    public List<CallOneDTO> getFilteredCallOnes(Filter filter) {
        List<CallOneDTO> callOneDTOS = getAllCallObjects();
        int limitValue = filter.getLimit() * (filter.getOffset() + 1);
        // Если limit, offset перейдут за грань размеров БД
        if (limitValue > callOneDTOS.size()) {
            throw new IncorrectCallObject("'limit' and 'offset' are incorrect!" +
                    " The size of our database is " + callOneDTOS.size());
        }
        int startIndex = filter.getLimit() * filter.getOffset();
        List<CallOneDTO> filteredCallOnes = new ArrayList<>();
        for (int i = startIndex; i < startIndex + filter.getLimit(); ++i) {
            filteredCallOnes.add(callOneDTOS.get(i));
        }
        return filteredCallOnes;
    }
}
