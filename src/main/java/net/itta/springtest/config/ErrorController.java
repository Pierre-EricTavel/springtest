/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.itta.springtest.config;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController {
      

    @GetMapping("/error/404.html" )
    public String get404(){
        return "error/404";
    }
      @GetMapping("/error/500.html" )
    public String get500(){
        return "error/500";
    }
}
