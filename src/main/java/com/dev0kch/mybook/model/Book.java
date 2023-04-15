package com.dev0kch.mybook.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.*;

@Entity
@Table(name = "books")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, allowGetters = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Book {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "La valeur titre ne peut pas etre vide")
    private String title;



    private Double review;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "book_categories",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns =  @JoinColumn(name =  "category_id" )
    )
    private List<Category> categories  = new ArrayList<>();

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "sold_books",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns =  @JoinColumn(name =  "user_id" )
    )
    private List<User> users  = new ArrayList<>();



    @NotBlank(message = "La valeur description ne peut pas etre vide")
    @Lob
    private String description;

    @Column(name = "id_author")
    private String id_author;

    @Column(name = "price")
    private Double price;

    @Column(name = "language")
    private String language;
    @NotBlank(message = "La valeur image  ne peut pas etre vide")
    private String image;

    @NotBlank(message = "La valeur file ne peut pas etre vide")
    private String file;



    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createdAt;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private Date updatedAt;

    public void addCategory(Category category) {
        categories.add(category);
    }
    public void buyBook(User user) {
        users.add(user);
    }


}
