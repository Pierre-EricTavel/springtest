/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.itta.springtest;

import org.springframework.stereotype.Service;


@Service
public class LeServiceExpress {
    
    public int getResult(int id){
        System.out.println("LeServiceExpress return "+id);
        return 20+id;
    }
    
}
