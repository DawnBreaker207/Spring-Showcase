package org.dawn.backend.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.dawn.backend.config.response.ResponseObject;
import org.dawn.backend.config.response.ResponsePage;
import org.dawn.backend.dto.request.DemoRequest;
import org.dawn.backend.dto.response.DemoResponse;
import org.dawn.backend.service.DemoService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/demo")
@RequiredArgsConstructor
public class DemoController {

    private final DemoService  demoService;


    public ResponseObject<ResponsePage<DemoResponse>> findAll(@RequestParam int page, @RequestParam int size){
        return ResponseObject.success(demoService.getAll(page, size));
    }


    @PostMapping("")
    public ResponseObject<DemoResponse> create(@RequestBody @Valid DemoRequest req) {
        return ResponseObject.success(demoService.create(req));
    }
}
