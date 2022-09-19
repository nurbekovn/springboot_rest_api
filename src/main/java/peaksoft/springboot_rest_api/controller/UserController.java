package peaksoft.springboot_rest_api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import peaksoft.springboot_rest_api.dto.UserRequest;
import peaksoft.springboot_rest_api.dto.UserResponse;
import peaksoft.springboot_rest_api.service.UserService;

@RestController
@RequestMapping("api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;


    @PostMapping
    public UserResponse create(@RequestBody UserRequest request){
        return service.create(request);

    }
}
