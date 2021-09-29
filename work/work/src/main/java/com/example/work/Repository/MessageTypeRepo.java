package com.example.work.Repository;

import com.example.work.Model.MessageType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MessageTypeRepo extends JpaRepository<MessageType,Integer> {
}
