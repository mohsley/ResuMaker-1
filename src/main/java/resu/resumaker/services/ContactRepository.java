package resu.resumaker.services;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.data.jpa.repository.JpaRepository;
import resu.resumaker.userData.ContactData;

public interface ContactRepository extends JpaRepository<ContactData, Integer> {
}
