package com.example.ymeqpvuunrfubnfukxtd.config;

import com.example.ymeqpvuunrfubnfukxtd.model.entity.CallOne;
import com.example.ymeqpvuunrfubnfukxtd.model.entity.CallOneMongo;
import com.example.ymeqpvuunrfubnfukxtd.repository.CallOneMongoRepository;
import com.example.ymeqpvuunrfubnfukxtd.repository.CallOneRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Call;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DatabaseLoader {
    private final CallOneRepository callOneRepository;
    private final CallOneMongoRepository callOneMongoRepository;

    @PostConstruct
    public void initDatabase() {
        // Если PostgreSQL БД пустая, то заполнить ее
        if (callOneRepository.count() == 0) {
            callOneRepository.save(saveCallOneObject("Askhat", 1999, "+77073066446", "87073066446", "01-09-2020"));
            callOneRepository.save(saveCallOneObject("Abai", 2003, "+77002064447", "87023163421", "02-09-2020"));
            callOneRepository.save(saveCallOneObject("Aidar", 2001, "+77711024467", "87083264471", "03-09-2020"));
            callOneRepository.save(saveCallOneObject("Bekzat", 2000, "+77771721267", "87013224331", "04-09-2020"));
            callOneRepository.save(saveCallOneObject("Yerzhan", 2001, "+77751024467", "87073264471", "05-09-2020"));
            callOneRepository.save(saveCallOneObject("Altynbek", 1998, "+77771924007", "87273264471", "06-09-2020"));
            callOneRepository.save(saveCallOneObject("Anuar", 1995, "+77781023417", "87083264471", "07-09-2020"));
            callOneRepository.save(saveCallOneObject("Eldos", 1997, "+77781012417", "87083264471", "08-09-2020"));
            callOneRepository.save(saveCallOneObject("Zhanibek", 2005, "+77762554467", "87023264471", "09-09-2020"));
            callOneRepository.save(saveCallOneObject("Zhambyl", 2002, "+77071023117", "87003264471", "10-09-2020"));
        }
        // Если MongoDB пустая, то заполнить ее данными
        if (callOneMongoRepository.count() == 0) {
            callOneMongoRepository.save(saveCallOneMongoObject("Askhat", 1999, "+77073066446", "87073066446", "01-09-2020"));
            callOneMongoRepository.save(saveCallOneMongoObject("Abai", 2003, "+77002064447", "87023163421", "02-09-2020"));
            callOneMongoRepository.save(saveCallOneMongoObject("Aidar", 2001, "+77711024467", "87083264471", "03-09-2020"));
            callOneMongoRepository.save(saveCallOneMongoObject("Bekzat", 2000, "+77771721267", "87013224331", "04-09-2020"));
            callOneMongoRepository.save(saveCallOneMongoObject("Yerzhan", 2001, "+77751024467", "87073264471", "05-09-2020"));
            callOneMongoRepository.save(saveCallOneMongoObject("Altynbek", 1998, "+77771924007", "87273264471", "06-09-2020"));
            callOneMongoRepository.save(saveCallOneMongoObject("Anuar", 1995, "+77781023417", "87083264471", "07-09-2020"));
            callOneMongoRepository.save(saveCallOneMongoObject("Eldos", 1997, "+77781012417", "87083264471", "08-09-2020"));
            callOneMongoRepository.save(saveCallOneMongoObject("Zhanibek", 2005, "+77762554467", "87023264471", "09-09-2020"));
            callOneMongoRepository.save(saveCallOneMongoObject("Zhambyl", 2002, "+77071023117", "87003264471", "10-09-2020"));
        }
    }

    private CallOne saveCallOneObject(String name, Integer year, String phoneOne, String phoneTwo, String creationDate) {
        CallOne callOne = new CallOne();
        callOne.setName(name);
        callOne.setYear(year);
        callOne.setPhoneOne(phoneOne);
        callOne.setPhoneTwo(phoneTwo);
        callOne.setCreationDate(creationDate);
        return callOne;
    }

    private CallOneMongo saveCallOneMongoObject(String name, Integer year, String phoneOne, String phoneTwo, String creationDate) {
        CallOneMongo callOneMongo = new CallOneMongo();
        callOneMongo.setName(name);
        callOneMongo.setYear(year);
        callOneMongo.setPhoneOne(phoneOne);
        callOneMongo.setPhoneTwo(phoneTwo);
        callOneMongo.setCreationDate(creationDate);
        return callOneMongo;
    }
}
