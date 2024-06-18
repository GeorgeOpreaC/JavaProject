package com.javaproject.JavaProject.api.Controller;

import com.javaproject.JavaProject.Exception.BadRequestException;
import com.javaproject.JavaProject.api.Dto.InventoryDtoAdd;
import com.javaproject.JavaProject.api.Dto.InventoryDtoUpdate;
import com.javaproject.JavaProject.domain.Inventory.Inventory;
import com.javaproject.JavaProject.domain.Inventory.InventoryRepository;
import com.javaproject.JavaProject.domain.User.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")

public class InventoryController {

    final InventoryRepository inventoryRepository;

    public InventoryController(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @GetMapping("/inventory")
    public List<Inventory> getAllInventories(){
        return inventoryRepository.findAll();
    }
    @GetMapping("/inventory/{id}")
    Inventory getAllInventories(@PathVariable Integer id){
        return inventoryRepository.findById(Long.valueOf(id)).get(); }

        @PostMapping("/add")
        Inventory add(@RequestBody InventoryDtoAdd commandDto ){
        Inventory inventoryToBeSaved = new Inventory();

        inventoryToBeSaved.setId(commandDto.getId());
        inventoryToBeSaved.setUser(commandDto.getUser());
        inventoryToBeSaved.setItem(commandDto.getItem());
        inventoryToBeSaved.setQuantity(commandDto.getQuantity());

        return inventoryRepository.save(inventoryToBeSaved);

        }
        @PostMapping("/update/{id}")
    Inventory update(
                @PathVariable Integer id,
                @RequestBody InventoryDtoUpdate dtoUpdate
                ) {
            Inventory InventoryToBeUpdated = inventoryRepository.findById(Long.valueOf(id))
                    .orElseThrow(() -> new BadRequestException("Doesn't exist:" + id));
            InventoryToBeUpdated.setId(dtoUpdate.getId());
            InventoryToBeUpdated.setUser(dtoUpdate.getUser());
            InventoryToBeUpdated.setItem(dtoUpdate.getItem());
            InventoryToBeUpdated.setQuantity(dtoUpdate.getQuantity());

            return inventoryRepository.save(InventoryToBeUpdated);

        }
    @DeleteMapping("/delete/{id}")
    ResponseEntity<String> delete(@PathVariable Integer id){
        Inventory inventoryToBeDeleted = inventoryRepository.findById(Long.valueOf(id))
                .orElseThrow(()-> new BadRequestException("Doesn't exist:" + id));

        inventoryRepository.delete(inventoryToBeDeleted);
        return ResponseEntity.ok("Deleted!");
    }

    }


