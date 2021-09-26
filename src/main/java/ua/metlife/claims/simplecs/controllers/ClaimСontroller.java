package ua.metlife.claims.simplecs.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.metlife.claims.simplecs.ClaimAction.CrlClaimOpen;
import ua.metlife.claims.simplecs.entity.crl.CrlPayment;
import ua.metlife.claims.simplecs.entity.crl.Languages;
import ua.metlife.claims.simplecs.repo.CrlPaymentRepository;
import ua.metlife.claims.simplecs.repo.LanguagesRepo;
import ua.metlife.claims.simplecs.utils.DbEnvData;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Slf4j
@RequestMapping("/clients")
@Controller
public class ClaimСontroller {

    @Autowired
    private CrlPaymentRepository crlPaymentRepository;

    @Autowired
    CrlClaimOpen crlClaimOpen;

    @Autowired // This means to get the bean called userRepository
    private DbEnvData dbEnvData;

    @GetMapping
    public String getList(Model model) {
        model.addAttribute("clients", crlPaymentRepository.findTop20ByOrderByIdDesc());
        List<CrlPayment> list = crlPaymentRepository.findTop20ByOrderByIdDesc();
        model.addAttribute("isLangList", true);
        model.addAttribute("isAddLang", false);
        model.addAttribute("action", "add");
        return "/clients";
    }

    @GetMapping("{id}")
    public String getOne(@PathVariable("id") Languages languages, Model model) {
        System.out.println("code: " + languages.getCode());
        model.addAttribute("code", languages.getCode());
       // languagesRepo.findAll().forEach(n -> System.out.println("language: " + n.getName()));
        return "/clients";
    }

    @GetMapping("add")
    public String languagesAdd(
            Model model
    ) {
        model.addAttribute("isAddLang", true);
        model.addAttribute("isLangList", false);
        model.addAttribute("action", "add");
        return "/clients";
    }

    @PostMapping("add")
    public String addLang(
            @RequestParam String name,
            @RequestParam String code
            ) {

        Languages langFromDb = null;//languagesRepo.findByCode(code.trim().toUpperCase());

        System.out.println("code = " + code);
        System.out.println("name = " + name);

        if (langFromDb == null) {
            Languages item = new Languages();
            item.setCode(code.trim().toUpperCase());
            item.setName(name);

            //languagesRepo.save(item);
        }

        return "redirect:/clients";
    }


    @GetMapping("delete/{id}")
    public String unsubscribe(
            @PathVariable("id") Languages languages, Model model
    ) {
        //languagesRepo.delete(languages);

        return "redirect:/clients";
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

        return "/clients";
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
            //languagesRepo.save(languages);
        }
            model.addAttribute("isAddLang", false);
            model.addAttribute("isLangList", true);

        return "redirect:/clients";
    }


    @GetMapping("addclaim/{id}")
    public String addclaim(
            @PathVariable("id") CrlPayment crlPayment, Model model
    ) {
        log.info("Prepared for add claim for id: " + crlPayment.getId());
        crlClaimOpen.claimOpen(dbEnvData.getEntityManager(), crlPayment.getId());
        log.info("Claim added for id: " + crlPayment.getId());
        return "redirect:/clients";
    }


}
