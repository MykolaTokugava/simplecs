package ua.metlife.claims.simplecs.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.metlife.claims.simplecs.entity.crl.Languages;
import ua.metlife.claims.simplecs.repo.LanguagesRepo;

import java.util.ArrayList;

@RequestMapping("/languages")
@Controller
public class LanguagesСontroller {

    @Autowired
    private LanguagesRepo languagesRepo;


    @GetMapping
    public String getList(Model model) {
        //model.addAttribute("languages", languagesRepo.findAll());
        model.addAttribute("languages", new ArrayList());
        model.addAttribute("isLangList", true);
        model.addAttribute("isAddLang", false);
        model.addAttribute("action", "add");
        return "/languages";
    }

    @GetMapping("{id}")
    public String getOne(@PathVariable("id") Languages languages, Model model) {
        System.out.println("code: " + languages.getCode());
        model.addAttribute("code", languages.getCode());
        languagesRepo.findAll().forEach(n -> System.out.println("language: " + n.getName()));
        return "/languages";
    }

    @GetMapping("add")
    public String languagesAdd(
            Model model
    ) {
        model.addAttribute("isAddLang", true);
        model.addAttribute("isLangList", false);
        model.addAttribute("action", "add");
        return "/languages";
    }

    @PostMapping("add")
    public String addLang(
            @RequestParam String name,
            @RequestParam String code
            ) {

        Languages langFromDb = languagesRepo.findByCode(code.trim().toUpperCase());

        System.out.println("code = " + code);
        System.out.println("name = " + name);

        if (langFromDb == null) {
            Languages item = new Languages();
            item.setCode(code.trim().toUpperCase());
            item.setName(name);

            languagesRepo.save(item);
        }

        return "redirect:/languages";
    }


    @GetMapping("delete/{id}")
    public String unsubscribe(
            @PathVariable("id") Languages languages, Model model
    ) {
        languagesRepo.delete(languages);

        return "redirect:/languages";
    }

    @GetMapping("edit/{id}")
    public String editLanguage(
            @PathVariable("id") Languages languages,
            Model model
    ) {


        if (languages != null) {

            model.addAttribute("name", languages.getName());
            model.addAttribute("code", languages.getCode());
            model.addAttribute("id", languages.getId());
            model.addAttribute("isAddLang", true);
            model.addAttribute("isLangList", false);
            model.addAttribute("action", "edit");

        } else {
            System.err.println("unknouwn Language ID" );
        }

        return "/languages";
    }


    @PostMapping("edit")
    public String updateLanguage(
            @RequestParam("id") Languages languages,
            @RequestParam("name") String name,
            @RequestParam("code") String code,
            Model model
    ) {
        if (languages != null) {
            languages.setName(name.trim());
            languages.setCode(code.trim().toUpperCase());
            languagesRepo.save(languages);
        }
            model.addAttribute("isAddLang", false);
            model.addAttribute("isLangList", true);

        return "redirect:/languages";
    }





}
