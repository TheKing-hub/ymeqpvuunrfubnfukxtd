package com.example.ymeqpvuunrfubnfukxtd.controller;

import com.example.ymeqpvuunrfubnfukxtd.model.entity.CallOneMongo;
import com.example.ymeqpvuunrfubnfukxtd.model.service_dto.CallOneMongoDTO;
import com.example.ymeqpvuunrfubnfukxtd.model.service_dto.Filter;
import com.example.ymeqpvuunrfubnfukxtd.service.implementation.model_service_impl.CallOneMongoServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("call_one/mongo")
@RequiredArgsConstructor
public class ControllerMongoDB {
    private final CallOneMongoServiceImpl callOneMongoService;
    @PostMapping("add")
    public ResponseEntity<String> addCallOneMongo(@RequestBody CallOneMongoDTO callOneMongoDTO) {
        CallOneMongo callOneMongo = callOneMongoService.callOneMongoDTOToCallOneMongo(callOneMongoDTO);
        callOneMongoService.addPhoneObject(callOneMongo);
        return ResponseEntity.ok("CallMongo Object was successfully added!");
    }

    @GetMapping("get/{phoneNum}")
    public ResponseEntity<CallOneMongoDTO> getCallOneMongo(@PathVariable String phoneNum) {
        CallOneMongo callOneMongo = callOneMongoService.getPhoneObject(phoneNum);
        CallOneMongoDTO callOneMongoDTO = callOneMongoService.callOneMongoToCallOneMongoDTO(callOneMongo);
        return ResponseEntity.ok(callOneMongoDTO);
    }

    @GetMapping("all")
    public ResponseEntity<List<CallOneMongoDTO>> getAllCallMongoObjects() {
        return ResponseEntity.ok(callOneMongoService.getAllCallMongoObjects());
    }

    @PutMapping("update/{phoneNum}")
    public ResponseEntity<String> updateCallMongoObjectById(@PathVariable String phoneNum, @RequestBody CallOneMongoDTO callOneMongoDTO) {
        return ResponseEntity.ok(callOneMongoService.updateCallObjectMongoByPhoneNumber(phoneNum, callOneMongoDTO));
    }

    @DeleteMapping("/delete/{phoneNum}")
    public ResponseEntity<String> deleteCallMongoObjectById(@PathVariable String phoneNum) {
        return ResponseEntity.ok(callOneMongoService.deleteCallOneMongoObjectByPhoneOne(phoneNum));
    }

    @PostMapping
    public ResponseEntity<List<CallOneMongoDTO>> getCallMongoObjects(@RequestBody Filter filter) {
        return ResponseEntity.ok(callOneMongoService.getFilteredCallOnesMongo(filter));
    }
}
