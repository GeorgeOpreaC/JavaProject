package com.javaproject.JavaProject.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api/inventory")

public class InventoryController {

    @GetMapping("/test")

    public String itemTest() {
        return "test";
    }
    @GetMapping
    public String itemString(){
        return "Inventory String";
    }
}

