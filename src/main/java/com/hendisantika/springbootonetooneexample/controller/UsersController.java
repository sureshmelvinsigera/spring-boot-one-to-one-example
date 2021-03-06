package com.hendisantika.springbootonetooneexample.controller;

import com.hendisantika.springbootonetooneexample.entity.Users;
import com.hendisantika.springbootonetooneexample.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-one-to-one-example
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 22/08/18
 * Time: 18.40
 * To change this template use File | Settings | File Templates.
 */

@RestController
@RequestMapping("/rest/users")
public class UsersController {
    @Autowired
    UsersRepository usersRepository;

    @GetMapping("/all")
    public List<Users> getAll() {
        return usersRepository.findAll();
    }

    @PostMapping("/add")
    public Users addUser(@RequestBody @Valid Users users) {
        return usersRepository.save(users);
    }

    @GetMapping("/{name}")
    public List<Users> getUser(@PathVariable("name") final String name) {
        return usersRepository.findByName(name);

    }

    @GetMapping("/id/{id}")
    public Optional<Users> getId(@PathVariable("id") final Integer id) {
        return usersRepository.findById(id);
    }

    @GetMapping("/update/{id}/{name}")
    public Users update(@PathVariable("id") final Integer id, @PathVariable("name") final String name) {
        Optional<Users> users = getId(id);
        users.ifPresent(users1 -> {
            users1.setName(name);
        });
        Users result = users.orElse(null);
        return usersRepository.save(result);
    }
}
