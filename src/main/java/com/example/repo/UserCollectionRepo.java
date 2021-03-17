package com.example.repo;

import com.example.model.MyModel;
import com.example.model.User;
import com.example.model.UserCollection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserCollectionRepo extends JpaRepository<UserCollection,Long> {
    List<MyModel> findAllByUser(Long id);
}
