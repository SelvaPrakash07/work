package com.example.demo.Repository;

import com.example.demo.Model.MessageType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MessageTypeRepo extends JpaRepository<MessageType,Integer> {
}
