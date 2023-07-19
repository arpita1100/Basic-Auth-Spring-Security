package umunchat.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyRestController {
    @GetMapping("")
    public String welcome()
    {
        return "welcome";
    }
    @GetMapping("/unsecure")
    public String unsecure(){
        return "unsecure";
    }
    @GetMapping("/secure")
    public String secure()
    {
        return "secure";
    }


}
