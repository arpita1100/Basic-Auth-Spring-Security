package umunchat.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import umunchat.config.CustomUserDetailService;
import umunchat.config.TokenProvider;

import java.security.Principal;

@RestController
public class AuthController {

    @Autowired
    CustomUserDetailService customUserDetailService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepo userRepo;
    @Autowired
    TokenProvider tokenProvider;
    @PostMapping("/login")
    public ResponseEntity<?> login(Principal principal)
    {
//        String token=tokenProvider.createBasicAuthToken(customUser.getUsername(), customUser.getPassword());
        System.out.println(principal);
        return ResponseEntity.status(HttpStatus.OK).body(principal);
//        return ResponseEntity.status(HttpStatus.OK).body(new LoginResponse(customUser.getUsername(), token));
    }
    @PostMapping("/register")
    public ResponseEntity<CustomUser> register(@RequestBody CustomUser customUser)
    {
        String token=tokenProvider.createBasicAuthToken(customUser.getUsername(), customUser.getPassword());
        customUser.setToken(token);
        customUser.setPassword(passwordEncoder.encode(customUser.getPassword()));
        return ResponseEntity.status(HttpStatus.OK).body(userRepo.save(customUser));
    }
}
