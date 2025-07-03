package ch.wiss.m223.Football_Training.Check_In.App.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;
import java.util.ArrayList;
 
 
import java.util.ArrayList;
import java.util.List;
 
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
 
@RestController
public class DemoController {
 
  private final List<String> list = new ArrayList<>();
 
  @CrossOrigin
  @GetMapping("/public/")
  public ResponseEntity<List<String>> publicEndpoint() {
    return ResponseEntity.ok(list);
  }
 
  @CrossOrigin
  @PostMapping("/public/")
  public ResponseEntity<List<String>> postPublicEndpoint(@RequestBody String message) {
    list.add(message);
    return ResponseEntity.ok(list);
  }
 
  @GetMapping("/private")
  public ResponseEntity<String> privateEndpoint() {
    return ResponseEntity.ok("This is a private endpoint.");
  }
}