package com.example.model;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user_collections")
public class UserCollection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String collectionName;

    @OneToMany(mappedBy = "userCollection", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MyModel> myModelList;

    @JsonProperty("userId")
    @JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
    @JsonIdentityReference(alwaysAsId=true)
    @ManyToOne
    private User user;

    public UserCollection() {
    }

    public UserCollection(String collectionName) {
        this.collectionName = collectionName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public List<MyModel> getMyModelList() {
        return myModelList;
    }

    public void setMyModelList(List<MyModel> myModel) {
        this.myModelList = myModel;
    }

}
