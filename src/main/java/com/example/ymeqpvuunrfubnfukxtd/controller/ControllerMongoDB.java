package com.example.ymeqpvuunrfubnfukxtd.controller;

import com.example.ymeqpvuunrfubnfukxtd.model.entity.CallOne;
import com.example.ymeqpvuunrfubnfukxtd.model.entity.CallOneMongo;
import com.example.ymeqpvuunrfubnfukxtd.model.service_dto.CallOneDTO;
import com.example.ymeqpvuunrfubnfukxtd.service.implementation.model_service_impl.CallOneMongoServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("call_one/mongo")
@RequiredArgsConstructor
public class ControllerMongoDB {
    private final CallOneMongoServiceImpl callOneMongoService;

    @PostMapping("add")
    public ResponseEntity<String> addCallOneMongo(@RequestBody CallOneMongo callOneMongo) {
        callOneMongoService.addPhoneObject(callOneMongo);
        return ResponseEntity.ok("CallMongo Object was successfully added!");
    }
}
