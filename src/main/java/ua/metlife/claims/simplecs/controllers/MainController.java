package ua.metlife.claims.simplecs.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.metlife.claims.simplecs.ClaimAction.CrlClaimOpen;
import ua.metlife.claims.simplecs.entity.csr.CsSystem;
import ua.metlife.claims.simplecs.repo.CrsfClmRepository;
import ua.metlife.claims.simplecs.repo.CsSystemRepository;
import ua.metlife.claims.simplecs.utils.DbEnvData;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
//@RestController // This means that this class is a Controller
//@RequestMapping(path="/cs") // This means URL's start with /demo (after Application path)
@RequestMapping
public class MainController {
    @Autowired // This means to get the bean called userRepository
    private CsSystemRepository csSystemRepository;

    @Autowired // This means to get the bean called userRepository
    private CrsfClmRepository crsfClmRepository;

    @Autowired // This means to get the bean called userRepository
    private DbEnvData dbEnvData;

    @Autowired
    CrlClaimOpen crlClaimOpen;

    @GetMapping(path="/addlast/")
    public @ResponseBody String addLastClaim ()
    {
        crlClaimOpen.claimOpen(dbEnvData.getEntityManager(), null);
        return "Saved last...";
    }


    @GetMapping(path="/addclaim/{id}")
    public @ResponseBody String addNewClaim (@PathVariable("id") Integer id, Model model)
    {
        crlClaimOpen.claimOpen(dbEnvData.getEntityManager(), id);
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


    @GetMapping("/login")
    public String login(Map<String, Object> model) {
        return "login";
    }

    @PostMapping("/login")
    public String userLogin(
            @RequestParam String username,
            @RequestParam String password
    ) {
        return "redirect:/";
    }

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @RequestMapping(value="/logout", method=RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/";
    }


}
