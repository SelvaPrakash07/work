package com.example.work.Model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;


@Entity
@Getter
@Setter
@Table(name = "message")
    public class Message {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private int msgid;

        @Column(name = "content")
        private String content;

        @OneToOne(cascade = CascadeType.ALL)
        @JoinColumn(name = "message_type_fkid")
        private MessageType messageTypeid;

        @CreationTimestamp
        @Column(name = "createdat")
        private Timestamp createdAt;
}
