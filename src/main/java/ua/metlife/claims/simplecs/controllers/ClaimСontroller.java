package ua.metlife.claims.simplecs.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.view.RedirectView;
import ua.metlife.claims.simplecs.ClaimAction.CrlClaimOpen;
import ua.metlife.claims.simplecs.entity.crl.CrlGeneral1c;
import ua.metlife.claims.simplecs.entity.crl.CrlGeneralBank;
import ua.metlife.claims.simplecs.entity.crl.CrlPayment;
import ua.metlife.claims.simplecs.entity.crl.Languages;
import ua.metlife.claims.simplecs.processing.Convert;
import ua.metlife.claims.simplecs.repo.CrlGeneral1cRepository;
import ua.metlife.claims.simplecs.repo.CrlPaymentRepository;
import ua.metlife.claims.simplecs.utils.DbEnvData;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private CrlGeneral1cRepository crlGeneral1cRepository;

    @GetMapping
    public String getList(Model model) {
        model.addAttribute("clients", new ArrayList<>());//crlPaymentRepository.findTop20ByOrderByIdDesc()
        model.addAttribute("isClientList", true);
        model.addAttribute("isAddLang", false);
        model.addAttribute("action", "add");
        model.addAttribute("isStatus", true);
        model.addAttribute("statusAction", request.getSession().getAttribute("statusAction")==null ? "" : request.getSession().getAttribute("statusAction") );
        model.addAttribute("searchName", "");
        model.addAttribute("taxcode", "");
        request.getSession().setAttribute("statusAction", null);
        return "/clients";
    }

    @GetMapping("{id}")
    public String getOne(@PathVariable("id") Languages languages, Model model) {
        System.out.println("code: " + languages.getCode());
        model.addAttribute("code", languages.getCode());
        // languagesRepo.findAll().forEach(n -> System.out.println("language: " + n.getName()));
        return "/clients";
    }

//    @GetMapping("add")
//    public String languagesAdd(
//            Model model
//    ) {
//        model.addAttribute("isAddLang", true);
//        model.addAttribute("isClientList", false);
//        model.addAttribute("action", "add");
//        return "/clients";
//    }

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
            model.addAttribute("isClientList", false);
            model.addAttribute("action", "edit");


        } else {
            System.err.println("unknouwn Language ID");
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
        model.addAttribute("isClientList", true);

        return "redirect:/clients";
    }


    @GetMapping("addclaim/{id}")
    public String addclaim(
            @PathVariable("id") CrlPayment crlPayment, Model model
    ) {
        log.info("Prepared for add claim for id!: " + crlPayment.getId());
        claimOpen(dbEnvData.getEntityManager(), crlPayment.getId());
        log.info("Claim added for id: " + crlPayment.getId());

        model.addAttribute("searchName", "");
        model.addAttribute("taxcode", "");
        model.addAttribute("isStatus", true);
        request.getSession().setAttribute("statusAction", "Claim added for: "+crlPayment.getGeneralId().getCustomerFullName());

        return "redirect:/clients";
    }

    private void claimOpen(EntityManager e, Integer i) {
        crlClaimOpen.claimOpen(e, i);
    }


    @PostMapping
    public String srch(Model model, @RequestParam(defaultValue = "") String searchName, @RequestParam(defaultValue = "") String taxcode) {
        log.info("searchName: " +searchName);
        taxcode = taxcode.trim();
        searchName = searchName.toLowerCase().trim();
        List<CrlPayment> list = new ArrayList<>();
        List<CrlGeneral1c> list1c =  new ArrayList<>();
        if (taxcode.length()>4 && searchName.length()==0) {
            list = crlPaymentRepository.findByTaxcode(taxcode);
            list1c = crlGeneral1cRepository.findByTaxcode(taxcode);
            log.info("list1c: " + list1c.size());
        } else if (taxcode.length()==0 && searchName.length()>5) {
            list = crlPaymentRepository.findByGName(searchName);
        } else if (taxcode.length()>4 && searchName.length()>5) {
            list = crlPaymentRepository.findByGNameTaxcode(searchName, taxcode);
        } else {
            //list = crlPaymentRepository.findTop20ByOrderByIdDesc();
            //list = crlPaymentRepository.findTop20WithStatusClaim();
        }

        List<CrlPayment> listResult = new ArrayList<>();

        list.stream().limit(20).forEach(x->listResult.add(x));

        for (CrlPayment item: listResult) {
            item.getGeneralId().setClaim(item.getGeneralId().getClaim()==null ? 0 : item.getGeneralId().getClaim());
        }

        log.info("listResult.size(): " + listResult.size());

        //findByGNameTaxcode
        model.addAttribute("clients", listResult);
        model.addAttribute("isClientList", true);
        model.addAttribute("isAddLang", false);
        model.addAttribute("action", "add");
        model.addAttribute("searchName", searchName);
        model.addAttribute("taxcode", taxcode);
        request.getSession().setAttribute("searchName", searchName);
        model.addAttribute("isStatus", false);
        //request.getSession().setAttribute("statusAction","Added");
        return "/clients";
    }

    @GetMapping("clientview/{id}")
    public String viewclaim(
            @PathVariable("id") CrlPayment crlPayment, Model model
    ) {
        if (crlPayment==null) {
            model.addAttribute("isData", false);
            model.addAttribute("isClient", true);
            model.addAttribute("isStatus", false);
            return "/clientview";
        }
        log.info("View claim for id: " + crlPayment.getId());
        CrlGeneral1c crlGeneral1c = crlPayment.getGeneralId();
        CrlGeneralBank crlGeneralBank = crlPayment.getCrlGeneralBank();
        String address = crlGeneralBank.getCustomerAddress()==null ? "" : crlGeneralBank.getCustomerAddress();
        if (address.length()>30) address = address.substring(0,29);

        model.addAttribute("isData", true);
        model.addAttribute("isClient", false);
        model.addAttribute("isStatus", false);

        model.addAttribute("id", crlPayment.getId());
        model.addAttribute("fullName", getDataNotNull(crlGeneral1c.getCustomerFullName()));
        model.addAttribute("dob", getDataNotNull(Convert.stringToDateFormatString(Convert.DATE_FORMAT_BASE, Convert.DATE_FORMAT_UA, crlGeneral1c.getCustomerDateOfBirth())));
        model.addAttribute("taxcode", getDataNotNull(crlGeneralBank.getTaxcode()));
        model.addAttribute("passport", getDataNotNull(crlGeneralBank.getInsurantPassport()));
        model.addAttribute("address", getDataNotNull(address));
        model.addAttribute("bank", "["+crlGeneral1c.getCurrentTpId().getBpId().getBpBankId().getBankCode() +"] " +crlGeneral1c.getCurrentTpId().getBpId().getBpBankId().getBankName());
        model.addAttribute("bank_tp", getDataNotNull(crlGeneral1c.getCurrentTpId().getBpId().getBpName()));
        model.addAttribute("paidfrom", getDataNotNull(Convert.stringToDateFormatString(Convert.DATE_FORMAT_BASE, Convert.DATE_FORMAT_UA, crlPayment.getPaidFrom())));
        model.addAttribute("paidto", getDataNotNull(Convert.stringToDateFormatString(Convert.DATE_FORMAT_BASE, Convert.DATE_FORMAT_UA, crlPayment.getPaidTo())));
        model.addAttribute("paiddate", getDataNotNull(Convert.stringToDateFormatString(Convert.DATE_FORMAT_BASE, Convert.DATE_FORMAT_UA, crlPayment.getDate1c())));
        model.addAttribute("amount", getDataNotNull((crlPayment.getAmount()==null ? BigDecimal.ZERO : crlPayment.getAmount()).toString()));
        model.addAttribute("premium", getDataNotNull((crlPayment.getPremiumCalculated()!=null ? crlPayment.getPremiumCalculated() : BigDecimal.ZERO).toString()));
        model.addAttribute("titleInfo", "Это мой заголовок");
        model.addAttribute("tplan", getDataNotNull(crlGeneral1c.getCurrentTpId().getTpName()));
        model.addAttribute("tpCode", getDataNotNull(crlGeneral1c.getCurrentTpId().getTpCode()));
        model.addAttribute("tarrifs", crlGeneral1c.getCurrentTpId().getCrlTpCoefList());
        return "/clientview";
    }

    public String getDataNotNull(String item) {
        return item==null ? "" : item;
    }

}
