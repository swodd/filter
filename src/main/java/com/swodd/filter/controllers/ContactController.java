package com.swodd.filter.controllers;

import com.swodd.filter.entities.Contact;
import com.swodd.filter.repositories.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/hello")
public class ContactController {
    @Autowired
    ContactRepository contactRepository;

    @GetMapping("/contacts")
    public ResponseEntity<List<Contact>> getContacts(@RequestParam String nameFilter) {
        List<Contact> result = contactRepository.findAll().stream()
                .filter(c -> !c.getName().matches(nameFilter))
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
