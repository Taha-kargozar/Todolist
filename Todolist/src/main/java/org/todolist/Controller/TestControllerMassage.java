package org.todolist.Controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.todolist.dto.TodolistDTO;

@RestController
public class TestControllerMassage {
//استاد اینجا چیز مهمی نیست برای تست کردن اینه که message.propertis جواب میده یا نه
        @Autowired
        private MessageSourceAccessor messageSourceAccessor;

        @GetMapping("/test-message")
        public String testMessage() {
            return messageSourceAccessor.getMessage("title.todolist.blank");
        }
    @PostMapping("/test-validate")
    public ResponseEntity<String> testValidate(@RequestBody @Valid TodolistDTO dto) {
        return ResponseEntity.ok("داده معتبر بود!");
    }
    }

