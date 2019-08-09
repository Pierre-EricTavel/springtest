/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.itta.springtest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException;

@Controller
public class FirstController {

    public void setLeServiceExpress(LeServiceExpress leServiceExpress) {
        this.leServiceExpress = leServiceExpress;
    }
    
    private LeServiceExpress leServiceExpress;
    
    @Autowired
    public FirstController(LeServiceExpress leServiceExpress){
        this.leServiceExpress=leServiceExpress;
    }

    public LeServiceExpress getLeServiceExpress() {
        return leServiceExpress;
    }
    
    @GetMapping("/premiere.html")
    public String getPremiere(@RequestParam(name="id",defaultValue = "0",required = false) int id){
        System.out.println("id="+id);
        if(id>1000) throw new HttpClientErrorException(HttpStatus.NOT_FOUND);//404.html
        if(id>750) return "toto";//404.html web.xml obligatoire
        if(id>500) throw new NullPointerException("t'es nulle ou quoi?");//404.html
        if(id>100) throw new MyException("Tu t'es planté grave!");//404.html
        if(id>10) throw new RuntimeException("Tu t'es planté grave!");//error.html
               
       return "premiere";

    }
   
   
}
