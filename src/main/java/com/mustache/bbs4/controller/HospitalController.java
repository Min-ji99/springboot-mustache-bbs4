package com.mustache.bbs4.controller;

import com.mustache.bbs4.domain.entity.Article;
import com.mustache.bbs4.domain.entity.Hospital;
import com.mustache.bbs4.repository.HospitalRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/hospitals")
@Slf4j
public class HospitalController {
    private final HospitalRepository hospitalRepository;

    public HospitalController(HospitalRepository hospitalRepository) {
        this.hospitalRepository = hospitalRepository;
    }


    @GetMapping("")
    public String list(Model model, Pageable pageable) {
        Page<Hospital> hospitals = hospitalRepository.findAll(pageable);
        model.addAttribute("hospitals", hospitals);
        model.addAttribute("checkNext", hospitals.hasNext());
        model.addAttribute("checkPrevious", hospitals.hasPrevious());
        model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next", pageable.next().getPageNumber());
        return "hospitals/list";
    }
    @GetMapping("/{id}")
    public String selectHospital(@PathVariable Integer id, Model model){
        return "hospitals/show";
    }
}