package peaksoft.springboot_rest_api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import peaksoft.springboot_rest_api.dto.Login;
import peaksoft.springboot_rest_api.dto.LoginResponse;
import peaksoft.springboot_rest_api.dto.UserRequest;
import peaksoft.springboot_rest_api.dto.UserResponse;
import peaksoft.springboot_rest_api.entity.User;
import peaksoft.springboot_rest_api.jwt.JwtTokenUtil;
import peaksoft.springboot_rest_api.repository.UserRepository;
import peaksoft.springboot_rest_api.service.UserService;

@RestController
@RequestMapping("api/jwt")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final UserRepository userRepo;
    private final JwtTokenUtil jwtTokenUtil;
    private final Login login;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> getLogin(@RequestBody UserRequest request) {
        try {
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(request.getEmail(),
                    request.getPassword());
            authenticationManager.authenticate(token);
            User user = userRepo.findByEmail(token.getName()).get();
            return ResponseEntity.ok().body(login.
                    toLoginView(jwtTokenUtil.generateToken(user),"SUCCESSFULLY",user));
        }catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).
                    body(login.toLoginView("","Login_failed",null));

        }
    }

    @PostMapping("/registration")
    public UserResponse create(@RequestBody UserRequest request) {
        return userService.create(request);
    }
}
