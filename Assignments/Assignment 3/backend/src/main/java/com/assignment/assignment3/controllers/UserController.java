package com.assignment.assignment3.controllers;

import com.assignment.assignment3.user.UserService;
import com.assignment.assignment3.user.dto.UserDTO;
import com.assignment.assignment3.user.model.ERole;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.assignment.assignment3.UrlMapping.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(USERS)
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping(FIND_ALL)
    @Secured({"ADMIN"})
    public List<UserDTO> allUsers() {
        return userService.findAll();
    }

    @GetMapping(FIND_ALL_DOCTORS)
    @Secured({"ADMIN"})
    public List<UserDTO> allDoctors() {
        return userService.findByRole(ERole.DOCTOR);
    }

    @DeleteMapping(DELETE)
    @Secured({"ADMIN"})
    public void delete(@PathVariable Long id) {
        userService.deleteByID(id);
    }

    @PostMapping(SAVE)
    @Secured({"ADMIN"})
    public void save(@Valid @RequestBody UserDTO userDTO) {
        userService.save(userDTO);
    }

    @PatchMapping(UPDATE)
    @Secured({"ADMIN"})
    public void update(@Valid @RequestBody UserDTO userDTO) {
        userService.update(userDTO);
    }

    @GetMapping(FIND_BY_ID)
    @Secured({"ADMIN"})
    public UserDTO findById(@PathVariable Long id) {
        return userService.findById(id);
    }
}