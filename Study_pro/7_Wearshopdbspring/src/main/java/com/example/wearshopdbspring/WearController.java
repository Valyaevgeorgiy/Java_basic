package com.example.wearshopdbspring;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class WearController {
    @Autowired
    private WearRepository wearRepository;

    @Autowired
    private WearService wearService;

    public WearController(WearRepository wearRepository) {
        this.wearRepository = wearRepository;
    }
    
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("wears", wearRepository.findAll());
        return "index";
    }

    @PostMapping("/")
    public String addWear(@ModelAttribute("wear") Wear wear) {
        wearRepository.save(wear);
        return "redirect:/";
    }

    @PostMapping("/deleteWear")
    public String deleteWear(@RequestParam Long wearId) {
        wearService.deleteWear(wearId);
        return "redirect:/";
    }
}