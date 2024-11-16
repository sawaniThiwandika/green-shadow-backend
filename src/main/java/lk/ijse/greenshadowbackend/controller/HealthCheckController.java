package lk.ijse.greenshadowbackend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/health1")
public class HealthCheckController {
    @GetMapping
    public String healthText(){
        return "Health Controller is working";
    }
}
