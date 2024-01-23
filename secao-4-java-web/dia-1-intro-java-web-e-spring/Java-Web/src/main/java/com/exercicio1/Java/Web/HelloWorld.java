package com.exercicio1.Java.Web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/javaweb")
public class HelloWorld {

  @GetMapping
  public String aprendendo(){
    return "Estou aprendendo a criar aplicações Java para Web!";
  }
}