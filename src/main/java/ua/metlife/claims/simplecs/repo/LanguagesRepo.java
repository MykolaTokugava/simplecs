package ua.metlife.claims.simplecs.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.metlife.claims.simplecs.entity.crl.Languages;

public interface LanguagesRepo extends JpaRepository<Languages, Long> {

    Languages findByCode(String code);

}



