package ua.metlife.claims.simplecs.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ua.metlife.claims.simplecs.entity.crl.Person;
import ua.metlife.claims.simplecs.repo.PersonRepo;

import java.util.List;

@Slf4j
@Service
public class PersonSevice implements UserDetailsService {
    @Autowired
    private PersonRepo personRepo;


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${hostname}")
    private String hostname;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person person = personRepo.findByUsername(username);

        System.out.println("username = " + username);
        //$2a$08$pCPq97GhdFTtcxOySmdKEONI0ERQTmKTqJsWRp7uNm5G6NA0ksHWO
//        for (Person p : personRepo.findAll()) {
//            p.setPassword(passwordEncoder.encode("pass"));
//            p.setEmail("zop11a@mail.sru");
//            personRepo.save(p);
//
//        }
//System.exit(0);
        if (person == null) {
            log.error("User not found");
            throw new UsernameNotFoundException("User not found");
        } else {
            log.info("User found: " + person.toString());
        }

        return person;
    }


    public void personProfile(Person user, String password, String email) {
        String userEmail = user.getEmail();

        boolean isEmailChanged = (email != null && !email.equals(userEmail)) ||
                (userEmail != null && !userEmail.equals(email));

        if (isEmailChanged) {
            user.setEmail(email);
        }

        if (!StringUtils.isEmpty(password)) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }

        personRepo.save(user);


    }


    public List<Person> findAll() {
        return personRepo.findAll();
    }


}
