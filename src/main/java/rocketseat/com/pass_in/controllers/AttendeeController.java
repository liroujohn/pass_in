package rocketseat.com.pass_in.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/attendees")
public class AttendeeController {
    @GetMapping
    public ResponseEntity<String> getTeste(){
        return ResponseEntity.ok("Success!");
    }
}
