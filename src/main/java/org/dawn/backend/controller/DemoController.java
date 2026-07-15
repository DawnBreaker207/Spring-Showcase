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


    @GetMapping
    public ResponseObject<ResponsePage<DemoResponse>> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size){
        return ResponseObject.success(demoService.getAll(page, size));
    }

    @GetMapping("/{id}")
    public ResponseObject<DemoResponse> findById(@PathVariable Long id){
        return ResponseObject.success(demoService.getById(id));
    }

    @PostMapping
    public ResponseObject<DemoResponse> create(@RequestBody @Valid DemoRequest req) {
        return ResponseObject.created(demoService.create(req));
    }

    @PutMapping("/{id}")
    public ResponseObject<DemoResponse> update(@PathVariable Long id, @RequestBody @Valid DemoRequest req) {
        return ResponseObject.success(demoService.update(id, req));
    }

    @DeleteMapping("/{id}")
    public ResponseObject<Void> delete(@PathVariable Long id) {
        demoService.delete(id);
        return ResponseObject.deleted();
    }
}
