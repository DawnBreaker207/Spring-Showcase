package org.dawn.backend.service;

import lombok.RequiredArgsConstructor;
import org.dawn.backend.config.response.ResponsePage;
import org.dawn.backend.dto.request.DemoRequest;
import org.dawn.backend.dto.response.DemoResponse;
import org.dawn.backend.entity.Demo;
import org.dawn.backend.exception.wrapper.ResourceNotFoundException;
import org.dawn.backend.repository.DemoRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DemoService {
    private final DemoRepository demoRepository;


    public ResponsePage<DemoResponse> getAll(int page, int size){
        Pageable pageable =  PageRequest.of(page, size);
        return ResponsePage.of(demoRepository.findAll(pageable).map(this::toDto));
    }

    public DemoResponse getById(Long id){
        return toDto(demoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Demo not found with id: " + id)));
    }

    public DemoResponse create(DemoRequest req){
        return toDto(demoRepository.save(toEntity(req)));
    }

    public DemoResponse update(Long id, DemoRequest req){
        Demo demo = demoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Demo not found with id: " + id));
        demo.setName(req.getName());
        return toDto(demoRepository.save(demo));
    }

    public void delete(Long id){
        if (!demoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Demo not found with id: " + id);
        }
        demoRepository.deleteById(id);
    }

    private Demo toEntity(DemoRequest req){
        return Demo.builder()
                .name(req.getName())
                .build();
    }

    private DemoResponse toDto(Demo req) {
        return DemoResponse.builder()
                .id(req.getId())
                .name(req.getName())
                .createdAt(req.getCreatedAt())
                .updatedAt(req.getUpdatedAt())
                .build();
    }
}
