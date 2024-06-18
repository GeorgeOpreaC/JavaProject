package com.javaproject.JavaProject.api.Controller;
//BussinesLogic
import com.javaproject.JavaProject.Exception.BadRequestException;
import com.javaproject.JavaProject.api.Dto.ItemDtoAdd;
import com.javaproject.JavaProject.api.Dto.ItemDtoUpdate;
import com.javaproject.JavaProject.domain.Inventory.InventoryRepository;
import com.javaproject.JavaProject.domain.Item.Item;
import com.javaproject.JavaProject.domain.Item.ItemRepository;
import com.javaproject.JavaProject.domain.User.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//request-urile care vin pe calea adaugata, vor ajunge in zona aceasta de cod
//prima parte a denumirii endpoint-ului
@RequestMapping("/api/item")
public class ItemController {

    final ItemRepository itemRepository;
    final InventoryRepository inventoryRepository;

    final UserRepository userRepository;

    public ItemController(ItemRepository itemRepository, InventoryRepository inventoryRepository, InventoryRepository inventoryRepository1, UserRepository userRepository) {
        this.itemRepository = itemRepository;
        this.inventoryRepository = inventoryRepository1;
        this.userRepository = userRepository;
    }

    @GetMapping("/items")
    public List<Item> getAllItems(){
        return itemRepository.findAll();

    }
    @GetMapping("/items/{id}")
    Item getAllProducts(@PathVariable Integer id){
        return itemRepository.findById(id).get();
    }

    @PostMapping("/add")
    Item add(@RequestBody ItemDtoAdd commandDto){

        Item itemToBeSaved= new Item();

        if (commandDto.getName() == null || commandDto.getName()== " "){
            throw new BadRequestException("Should be completed an item's name!");
        }
        /**Verificam daca exista categoria salvata in DB*/
        inventoryRepository.findById((long) commandDto.getInventoryId())
                        .orElseThrow(()-> new BadRequestException("The inventory doesn't exist!"));

        itemToBeSaved.setName(commandDto.getName());
        itemToBeSaved.setDescription(commandDto.getDescription());
        itemToBeSaved.setUserId(commandDto.getUserId());
        itemToBeSaved.setInventoryId(commandDto.getInventoryId());

        return itemRepository.save(itemToBeSaved);


    }
    /**Update*/
    @PostMapping("/update/{id}")

    Item update(
            @PathVariable Integer id,
            @RequestBody ItemDtoUpdate updateDto
    ){

        Item ItemToBeUpdated = itemRepository.findById(id)
                .orElseThrow(()-> new BadRequestException("The item with this id doesn't exist:" + id));

        ItemToBeUpdated.setName(updateDto.getName());
        ItemToBeUpdated.setDescription(updateDto.getDescription());
        ItemToBeUpdated.setUserId(updateDto.getUserId());
        ItemToBeUpdated.setInventoryId(updateDto.getInventoryId());

        return itemRepository.save(ItemToBeUpdated);

    }

    /**Delete*/
    @DeleteMapping("/delete/{id}")
    ResponseEntity<String>  delete(@PathVariable Integer id){
        Item itemToBeDeleted = itemRepository.findById(id)
                .orElseThrow(()-> new BadRequestException("The item with this id doesn't exist:" + id));

        itemRepository.delete(itemToBeDeleted);
        return ResponseEntity.ok("The item was deleted!");
    }

    @GetMapping("/filteritem/{userId}")
    List<Item> gellAllItemsByuserId(
            @PathVariable Integer userId
    ) {
        userRepository.findById(Long.valueOf(userId))
                .orElseThrow(()->new BadRequestException("The selected user does not exist"));

        return itemRepository.findAllByUserId(userId);
    }


    @GetMapping
    public String itemString(){
        return "Item String";
    }

}
