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
    public class Contact {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private int id;

        @Column(name = "name")
        private String name;

        @Column(name = "mobile_no")
        private String mobileNo;

        @JoinColumn(name = "mail_id")
        private String mail_id;

        @CreationTimestamp
        private Timestamp createdAt;
}
