package com.example.work.Model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "contactmessage")
public class ContactMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contact_id")
    private Contact contactid;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "message_id")
    private Message messageid;
}
