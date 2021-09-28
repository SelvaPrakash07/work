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
    public class Message {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private int msgid;

        @Column(name = "content")
        private String content;

        @OneToOne(cascade = CascadeType.ALL)
        @JoinColumn(name = "message_type_id")
        private MessageType messageTypeid;

        @CreationTimestamp
        private Timestamp createdAt;
}
