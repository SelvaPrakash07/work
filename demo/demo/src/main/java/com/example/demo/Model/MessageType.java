package com.example.demo.Model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;


@Entity
    @Data
    @Getter
    @Setter
    public class MessageType {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private int id;

        @Column(name = "type")
        private String name;

        @CreationTimestamp
        @Column(name = "createdat")
        private Timestamp createdAt;

    }
