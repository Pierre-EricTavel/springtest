/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package springtest;

import net.itta.springtest.LeServiceExpress;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;



@Profile("mockitotest")
@Configuration
class SpringMockitoTestConfig {
     
    @Bean
    @Primary
    public LeServiceExpress LeServiceExpress() {
        System.out.println("----------------------------------");
        return Mockito.mock(LeServiceExpress.class);
    }
}
