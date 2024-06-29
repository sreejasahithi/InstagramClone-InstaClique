package com.insta.Instagram_clone.restcontroller;

import com.insta.Instagram_clone.model.*;
import com.insta.Instagram_clone.repository.*;
import com.insta.Instagram_clone.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class FormController {

    @Autowired
    private Account1Repository accountRepository;

    @PostMapping("/submit-form")
    public ResponseEntity<String> submitForm(@RequestBody Account1 formData) {

        // Save the Account object to the MongoDB collection
        Account1 savedAccount = accountRepository.save(formData);

        // Display the saved Account details (optional)
        formData.display();
        savedAccount.display();

        // Return a success response
        return ResponseEntity.ok("Form submitted successfully");
    }
}
