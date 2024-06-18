package com.javaproject.JavaProject.api.Controller;

import com.javaproject.JavaProject.Exception.BadRequestException;
import com.javaproject.JavaProject.api.Dto.UserDtoAdd;
import com.javaproject.JavaProject.api.Dto.UserDtoUpdate;
import com.javaproject.JavaProject.domain.Item.ItemRepository;
import com.javaproject.JavaProject.domain.User.User;
import com.javaproject.JavaProject.domain.User.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")

public class UserController {
    final UserRepository userRepository;
    final ItemRepository itemRepository;

    public UserController(UserRepository userRepository, ItemRepository itemRepository) {
        this.userRepository = userRepository;
        this.itemRepository = itemRepository;
    }

    @GetMapping("/users")
    public List<User> getAllUsers(){
        return userRepository.findAll();

    }
    @GetMapping("/users/{id}")
    User getAllUsers(@PathVariable Integer id){
        return userRepository.findById(Long.valueOf(id)).get(); }

        @PostMapping("/add")
        User add(@RequestBody UserDtoAdd commandDto){
        User userToBeSaved = new User();

        userToBeSaved.setId(commandDto.getId());
        userToBeSaved.setName(commandDto.getName());
        userToBeSaved.setEmail(commandDto.getEmail());
        userToBeSaved.setPassword(commandDto.getPassword());

        return userRepository.save(userToBeSaved);

        }

        @PostMapping("/update/{id}")
    User update(
                @PathVariable Integer id,
                @RequestBody UserDtoUpdate dtoUpdate
                ){
        User UserToBeUpdated = userRepository.findById(Long.valueOf(id))
                .orElseThrow(()-> new BadRequestException("The user with this id doesn't exist:" + id));

            UserToBeUpdated.setId(dtoUpdate.getId());
            UserToBeUpdated.setName(dtoUpdate.getName());
            UserToBeUpdated.setEmail(dtoUpdate.getEmail());
            UserToBeUpdated.setPassword(dtoUpdate.getPassword());

            return userRepository.save(UserToBeUpdated);

        }
        @DeleteMapping("/delete/{id}")
    ResponseEntity<String> delete(@PathVariable Integer id){
        User userToBeDeleted = userRepository.findById(Long.valueOf(id))
                .orElseThrow(()-> new BadRequestException("The user with this id doesn't exist:" + id));

        userRepository.delete(userToBeDeleted);
        return ResponseEntity.ok("The user was deleted!");
        }

    }

