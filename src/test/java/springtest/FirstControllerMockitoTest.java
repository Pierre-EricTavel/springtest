/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package springtest;

import net.itta.springtest.*;
import net.itta.springtest.config.WebAppContext;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.HttpClientErrorException;


public class FirstControllerMockitoTest {
    
    MockMvc mockmvc;
    
    @Rule
    public MockitoRule rule = MockitoJUnit.rule();
    
    
    @Before
    public void setup(){
        WebAppContext wac = new WebAppContext();
        mockmvc=MockMvcBuilders
                .standaloneSetup( new FirstController(new LeServiceExpress()))
                .setHandlerExceptionResolvers(wac.exceptionResolver())
                .setViewResolvers(wac.viewResolver())
                .setValidator(wac.getValidator())
                .build();
        
    }
    
    @Test
    public void mockitoTestsNominauxdeFirstController() throws Exception{
        mockmvc.perform(MockMvcRequestBuilders.get("/premiere.html")
             .accept(MediaType.TEXT_HTML))   
             .andDo(MockMvcResultHandlers.print())
             .andExpect(MockMvcResultMatchers.status().isOk())
             .andExpect(MockMvcResultMatchers.view().name("premiere"))
             .andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/jsp/premiere.jsp"));
    }
    
    @Test
    public void MockitotestsLimitedeFirstController() throws Exception{
        mockmvc.perform(MockMvcRequestBuilders.get("/premiere.html?id=20")
             .accept(MediaType.TEXT_HTML))
             .andDo(MockMvcResultHandlers.print())  
             .andExpect(MockMvcResultMatchers.model().attribute("exception",Matchers.isA(RuntimeException.class)))
             .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
             .andExpect(forwardedUrl("/WEB-INF/jsp/error/error.jsp"));
        
        mockmvc.perform(MockMvcRequestBuilders.get("/premiere.html?id=150")
             .accept(MediaType.TEXT_HTML))
             .andDo(MockMvcResultHandlers.print())
             .andExpect(MockMvcResultMatchers.model().attribute("exception",Matchers.isA(MyException.class)))
             .andExpect(MockMvcResultMatchers.status().is4xxClientError())
             .andExpect(forwardedUrl("/WEB-INF/jsp/error/404.jsp"));
        
        mockmvc.perform(MockMvcRequestBuilders.get("/premiere.html?id=501")
             .accept(MediaType.TEXT_HTML))
             .andDo(MockMvcResultHandlers.print())
             .andExpect(MockMvcResultMatchers.model().attribute("exception",Matchers.isA(NullPointerException.class)))
             .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
             .andExpect(forwardedUrl("/WEB-INF/jsp/error/error.jsp"));
        
          mockmvc.perform(MockMvcRequestBuilders.get("/premiere.html?id=1001")
             .accept(MediaType.TEXT_HTML))
             .andDo(MockMvcResultHandlers.print())
             .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
             .andExpect(MockMvcResultMatchers.model().attribute("exception",Matchers.isA(HttpClientErrorException.class)))
             .andExpect(forwardedUrl("/WEB-INF/jsp/error/error.jsp"));
          
         mockmvc.perform(MockMvcRequestBuilders.get("/premiere.html?id=751")
             .accept(MediaType.TEXT_HTML))
             .andDo(MockMvcResultHandlers.print())
             .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
             .andExpect(forwardedUrl("/WEB-INF/jsp/toto.jsp"));//WTF!!!
         
            mockmvc.perform(MockMvcRequestBuilders.get("/maman.html")
             .accept(MediaType.TEXT_HTML))
             .andDo(MockMvcResultHandlers.print())
             .andExpect(MockMvcResultMatchers.status().is4xxClientError())
             .andExpect(forwardedUrl(null));//WTF!!!
            
            
            
    }
       
}
