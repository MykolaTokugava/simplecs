package ua.metlife.claims.simplecs.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ua.metlife.claims.simplecs.ClaimAction.CrlClaimOpen;
import ua.metlife.claims.simplecs.entity.csr.CsSystem;
import ua.metlife.claims.simplecs.repo.CrsfClmRepository;
import ua.metlife.claims.simplecs.repo.CsSystemRepository;
import ua.metlife.claims.simplecs.repo.CsSystemTransaction;
import ua.metlife.claims.simplecs.utils.DbEnvData;

@Controller // This means that this class is a Controller
@RequestMapping(path="/cs") // This means URL's start with /demo (after Application path)
public class MainController {
    @Autowired // This means to get the bean called userRepository
    private CsSystemRepository csSystemRepository;

    @Autowired // This means to get the bean called userRepository
    private CrsfClmRepository crsfClmRepository;

    @Autowired // This means to get the bean called userRepository
    private DbEnvData dbEnvData;

    @Autowired
    CrlClaimOpen crlClaimOpen;

    @GetMapping(path="/addclaim")
    public @ResponseBody String addNewClaim () {
        crlClaimOpen.claimOpen(dbEnvData.getEntityManager(), null);
        //new CsSystemTransaction().doTransaction(csSystemRepository);
        return "Saved";
    }

    @PostMapping(path="/add")
    public @ResponseBody String addNewUser (@RequestParam String name
            , @RequestParam String email) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request
        return "SavedPost";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<CsSystem> getAllUsers() {

        // This returns a JSON or XML with the users

//        for (CsSystem item : csSystemRepository.findAll() ) {
//            System.out.println("name:" + item.getSystemName());
//        }

        crsfClmRepository.getNextClaimNumber(2021);

        //dbEnvData.getEntityManager();

        return csSystemRepository.findAll();
    }

    @GetMapping(path="/hello")
    public @ResponseBody String getHello() {
        return "Hello!";
    }

}
