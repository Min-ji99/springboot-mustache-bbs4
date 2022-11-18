package com.mustache.bbs4.controller;

import com.mustache.bbs4.domain.entity.Article;
import com.mustache.bbs4.domain.entity.Hospital;
import com.mustache.bbs4.repository.HospitalRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/hospitals")
@Slf4j
public class HospitalController {
    private final HospitalRepository hospitalRepository;

    public HospitalController(HospitalRepository hospitalRepository) {
        this.hospitalRepository = hospitalRepository;
    }


    @GetMapping("")
    public String list(@RequestParam(required = false) String keyword, Model model, @PageableDefault(size=10) Pageable pageable) {
        log.info(keyword);
        Page<Hospital> hospitals;
        if(keyword!=null){
            hospitals=hospitalRepository.findByRoadNameAddressContaining(keyword, pageable);
        }else{
            hospitals = hospitalRepository.findAll(pageable);
        }
        model.addAttribute("keyword", keyword);
        model.addAttribute("hospitals", hospitals);
        model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next", pageable.next().getPageNumber());
        return "hospitals/list";
    }
    @GetMapping("/{id}")
    public String selectHospital(@PathVariable Integer id, Model model){
        Optional<Hospital> hospital=hospitalRepository.findById(id);
        if(!hospital.isEmpty()){
            model.addAttribute("hospital", hospital.get());
            return "hospitals/show";
        }
        return "error";
    }
}