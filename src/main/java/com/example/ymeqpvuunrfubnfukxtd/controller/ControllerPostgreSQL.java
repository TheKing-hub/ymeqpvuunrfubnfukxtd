package com.example.ymeqpvuunrfubnfukxtd.controller;

import com.example.ymeqpvuunrfubnfukxtd.model.entity.CallOne;
import com.example.ymeqpvuunrfubnfukxtd.model.service_dto.CallOneDTO;
import com.example.ymeqpvuunrfubnfukxtd.service.implementation.model_service_impl.CallOneServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("call_one/object")
@RequiredArgsConstructor
public class ControllerPostgreSQL {
    private final CallOneServiceImpl callOneService;

    @PostMapping("add")
    public ResponseEntity<String> addCallOne(@RequestBody CallOneDTO callOneDTO) {
        CallOne callOne = callOneService.callOneDTOToCallOne(callOneDTO);
        callOneService.addPhoneObject(callOne);
        return ResponseEntity.ok("Call Object was successfully added!");
    }

    @GetMapping("{id}")
    public ResponseEntity<CallOne> getCallOne(@PathVariable Long id) {
        return ResponseEntity.ok(callOneService.getCallObjectById(id));
    }

    @GetMapping("all")
    public ResponseEntity<List<CallOneDTO>> getAllCallObjects() {
        return ResponseEntity.ok(callOneService.getAllCallObjects());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCallObjectById(@PathVariable Long id) {
        return ResponseEntity.ok(callOneService.deleteCallOneObject(id));
    }
}
