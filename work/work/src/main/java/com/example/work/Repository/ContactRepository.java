package com.example.work.Repository;

import com.example.work.Model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ContactRepository extends JpaRepository<Contact,Integer> {
    Optional<Contact> findByUsername(String username);
}
