package com.dev0kch.mybook.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
public class Review {
    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column
    private int value;

    @OneToOne
    @JoinColumn(name="book_id", nullable=false)
    private Book book;

    @OneToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;


    public Review(String id,int value, Book book, User user) {
        this.id = id;
        this.value = value;
        this.book = book;
        this.user = user;
    }
}
