package com.example.work.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "contact")
public class Contact {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private int id;

        @Column(name = "username")
        private String username;

        @Column(name = "mobile_no")
        private String mobileNo;

        @Column(name = "mail_id")
        private String mail_id;

        @CreationTimestamp
        @Column(name = "createdat")
        private Timestamp createdAt;

        public Contact(Contact user) {

        }
}
