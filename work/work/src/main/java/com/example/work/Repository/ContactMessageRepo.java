package com.example.work.Repository;

import com.example.work.Model.ContactMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
    public interface ContactMessageRepo extends JpaRepository<ContactMessage,Integer> {
    }

