package com.dev0kch.mybook.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table( name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)

    @Column(name = "id", nullable = false)
    private Long id;
    private String username;
    private String password;
    private String email;

    public Long getId() {
        return id;
    }

    public void setD(long id) {
        this.id = id;
    }

}
