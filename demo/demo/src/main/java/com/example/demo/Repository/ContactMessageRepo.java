package com.example.demo.Repository;

import com.example.demo.Model.ContactMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
    public interface ContactMessageRepo extends JpaRepository<ContactMessage,Integer> {
    }

