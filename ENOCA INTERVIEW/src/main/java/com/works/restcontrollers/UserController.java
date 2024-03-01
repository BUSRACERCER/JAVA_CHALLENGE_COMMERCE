package com.works.restcontrollers;

import com.works.dto.UserDto;
import com.works.dto.UserService;
import com.works.services.UserManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserManagementService userService;

    @PostMapping("/create")
    public ResponseEntity<UserDto> createCustomer(@RequestBody UserDto user ) {
        UserDto resultCustomer = userService.createUser(user);
        return ResponseEntity.ok(resultCustomer);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<UserDto>> getCustomers() {
        List<UserDto> customers = userService.getUsers();
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<UserDto> getCustomer(@PathVariable("id") Long id) {
        UserDto customer = userService.getUser();
        return ResponseEntity.ok(customer);

    }

}
