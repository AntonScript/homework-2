package com.example.service;

import com.example.model.MyModel;
import com.example.model.User;
import com.example.model.UserCollection;
import com.example.repo.MyModelRepo;
import com.example.repo.UserCollectionRepo;
import com.example.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserCollectionService {
    private final UserRepo userRepo;
    private final UserCollectionRepo userCollectionRepo;
    private final MyModelRepo myModelRepo;

    @Autowired
    public UserCollectionService(UserRepo userRepo, UserCollectionRepo userCollectionRepo, MyModelRepo myModelRepo) {
        this.userRepo = userRepo;
        this.userCollectionRepo = userCollectionRepo;
        this.myModelRepo = myModelRepo;
    }

    public ResponseEntity<?> createCollection(Long id, String name){
        Optional<User> user = userRepo.findById(id);
        if(!user.isPresent())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        try {
            UserCollection userCollection = new UserCollection(name);
            user.get().getUserCollections().add(userCollection);
            userCollection.setUser(user.get());
            userCollectionRepo.save(userCollection);
            userRepo.save(user.get());
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> getList(Long id){
        Optional<User> user = userRepo.findById(id);
        if(!user.isPresent())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        try {
            return new ResponseEntity<>(user.get().getUserCollections(),HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> addElement(Long id, Long listId, MyModel myModel){
        Optional<User> user = userRepo.findById(id);
        if(!user.isPresent())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        try {
            Optional<UserCollection> userCollection = userCollectionRepo.findById(listId);
            userCollection.get().getMyModelList().add(myModel);
            myModel.setUserCollection(userCollection.get());
            myModelRepo.save(myModel);
            userCollectionRepo.save(userCollection.get());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    public ResponseEntity<?> deleteElement(Long ElementId){
        myModelRepo.deleteById(ElementId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
