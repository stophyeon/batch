package com.example.batch;

import com.example.batch.winter.WinterShelterDto;
import com.example.batch.winter.repository.WinterShelterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/shelter")
public class TestController {
    private final WinterShelterRepository repository;
    @PostMapping("/test")
    public String test(@RequestBody WinterShelterDto dto){
        repository.save(dto);
        return "SUCCESS";
    }
}
