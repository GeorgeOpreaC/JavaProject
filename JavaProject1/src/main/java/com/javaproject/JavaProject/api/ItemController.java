package com.javaproject.JavaProject.api;
//BussinesLogic
import com.javaproject.JavaProject.api.Dto.ItemDtoAdd;
import com.javaproject.JavaProject.api.Dto.ItemDtoUpdate;
import com.javaproject.JavaProject.domain.Item.Item;
import com.javaproject.JavaProject.domain.Item.ItemRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//request-urile care vin pe calea adaugata, vor ajunge in zona aceasta de cod
//prima parte a denumirii endpoint-ului
@RequestMapping("/api/item")
public class ItemController {

    final ItemRepository itemRepository;

    public ItemController(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
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

        itemToBeSaved.setName(commandDto.getName());
        itemToBeSaved.setCategory(commandDto.getCategory());
        itemToBeSaved.setDescription(commandDto.getDescription());

        return itemRepository.save(itemToBeSaved);


    }
    /**Update*/
    @PostMapping("/update/{id}")

    Item update(
            @PathVariable Integer id,
            @RequestBody ItemDtoUpdate updateDto
    ){

        Item ItemToBeUpdated = itemRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("The item with this id doesn't exist:" + id));

        ItemToBeUpdated.setName(updateDto.getName());
        ItemToBeUpdated.setCategory(updateDto.getCategory());
        ItemToBeUpdated.setDescription(updateDto.getDescription());

        return itemRepository.save(ItemToBeUpdated);

    }




    @GetMapping
    public String itemString(){
        return "Item String";
    }

}
