package com.example.ymeqpvuunrfubnfukxtd.controller;

import com.example.ymeqpvuunrfubnfukxtd.model.entity.CallOne;
import com.example.ymeqpvuunrfubnfukxtd.model.service_dto.CallOneDTO;
import com.example.ymeqpvuunrfubnfukxtd.model.service_dto.Filter;
import com.example.ymeqpvuunrfubnfukxtd.service.implementation.model_service_impl.CallOneServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("call_one")
@RequiredArgsConstructor
public class ControllerPostgreSQL {
    private final CallOneServiceImpl callOneService;

    @PostMapping("add")
    public ResponseEntity<String> addCallOne(@RequestBody CallOneDTO callOneDTO) {
        CallOne callOne = callOneService.callOneDTOToCallOne(callOneDTO);
        callOneService.addPhoneObject(callOne);
        return ResponseEntity.ok("Call Object was successfully added!");
    }

    @GetMapping("get/{id}")
    public ResponseEntity<CallOne> getCallOne(@PathVariable Long id) {
        return ResponseEntity.ok(callOneService.getCallObjectById(id));
    }

    @GetMapping("all")
    public ResponseEntity<List<CallOneDTO>> getAllCallObjects() {
        return ResponseEntity.ok(callOneService.getAllCallObjects());
    }

    @PutMapping("update/{id}")
    public ResponseEntity<String> updateCallObjectById(@PathVariable Long id, @RequestBody CallOneDTO callOneDTO) {
        return ResponseEntity.ok(callOneService.updateCallObjectById(id, callOneDTO));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCallObjectById(@PathVariable Long id) {
        return ResponseEntity.ok(callOneService.deleteCallOneObject(id));
    }

    @PostMapping
    public ResponseEntity<List<CallOneDTO>> getCallObjects(@RequestBody Filter filter) {
        return ResponseEntity.ok(callOneService.getFilteredCallOnes(filter));
    }
}
