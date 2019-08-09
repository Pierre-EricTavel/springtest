/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package springtest;

import net.itta.springtest.LeServiceExpress;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class SpringTestConfig {
    
    @Bean
    LeServiceExpress leServiceExpress(){
        return new LeServiceExpress();
    }
    
}
