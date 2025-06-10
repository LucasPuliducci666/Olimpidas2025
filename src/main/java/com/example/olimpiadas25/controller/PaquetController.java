package com.example.olimpiadas25.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trivagotrucho")
public class PaquetController {

    @GetMapping("/paquetes")
    public String mostrarPaqg(){
        return "Hola, estos son los paquetes disponibles" +
                "1" +
                "2" +
                "3";
    }

    @GetMapping("paquetesesp/{idpaq}")
    public String mostrarpaquete(@PathVariable int idpaq){
        return "Hola! el paquete que seleccionaste es el "+ idpaq;
    }

}
