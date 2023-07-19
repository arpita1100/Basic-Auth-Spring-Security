package umunchat.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/create")
    public CustomUser create(@RequestBody CustomUser customUser)
    {
        customUser.setPassword(passwordEncoder.encode(customUser.getPassword()));
        return userRepo.save(customUser);
    }
    @GetMapping("/read/{id}")
    public CustomUser read(@PathVariable long id)
    {
        return userRepo.findById(id).get();
    }
    @GetMapping("/readAll")
    public List<CustomUser> read()
    {
        List<CustomUser> list=new ArrayList<>();
        userRepo.findAll().forEach(item->list.add(item));
        return list;
    }
    @PutMapping("/update")
    public CustomUser update(@RequestBody CustomUser customUser)
    {
        CustomUser savedUser=userRepo.findByUsername(customUser.getUsername());
        savedUser.setPassword(passwordEncoder.encode(customUser.getPassword()));
        return userRepo.save(savedUser);
    }

    @DeleteMapping("/delete")
    public CustomUser delete(@RequestBody String username)
    {
        CustomUser customUser=userRepo.findByUsername(username);
        userRepo.delete(customUser);
        return customUser;
    }
}
