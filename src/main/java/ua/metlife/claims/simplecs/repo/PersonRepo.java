package ua.metlife.claims.simplecs.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.metlife.claims.simplecs.entity.crl.Person;


public interface PersonRepo extends JpaRepository<Person, Long> {
    Person findByUsername(String username);

}



