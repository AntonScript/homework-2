package com.example.controller;

import com.example.model.MyModel;
import com.example.service.UserCollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("{id}/lists")
public class UserCollectionController {
    private final UserCollectionService userCollectionService;

    @Autowired
    public UserCollectionController(UserCollectionService userCollectionService) {
        this.userCollectionService = userCollectionService;
    }

    @PostMapping
    public ResponseEntity<?> create(
            @PathVariable Long id,
            @RequestParam("name") String name
    ){
        return userCollectionService.createCollection(id,name);
    }

    @GetMapping
    public ResponseEntity<?> getList(
            @PathVariable Long id
    ){
        return userCollectionService.getList(id);
    }

    @PostMapping("/{list-id}/elements")
    public ResponseEntity<?> addElement(
            @PathVariable("id") Long id,
            @PathVariable("list-id") Long listId,
            @RequestBody MyModel myModel
            ){
        return userCollectionService.addElement(id, listId,myModel);
    }

    @DeleteMapping("/{list-id}/elements/{id_element}")
    public ResponseEntity<?> deleteElement(
            @PathVariable("list-id") Long listId,
            @PathVariable("id_element") Long elementId
    ){
        return userCollectionService.deleteElement(elementId);
    }
}
