/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package springtest;


import net.itta.springtest.FirstController;
import net.itta.springtest.LeServiceExpress;
import net.itta.springtest.MyException;
import org.junit.*;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.web.client.HttpClientErrorException;

@TestExecutionListeners(DependencyInjectionTestExecutionListener.class)
public class FirstControllerTest {
   
    FirstController firstController;

    @Before
    public void Init(){
        firstController = new FirstController(new LeServiceExpress());
    }
    @After
    public void End(){
        firstController = null;
    }
    @Test
    public void testsNominauxdeFirstController(){
        Assert.assertNotEquals(firstController, null);
        String result =firstController.getPremiere(0);
        Assert.assertEquals("premiere", result);
        Assert.assertNotEquals(firstController, null);
        
        result =firstController.getPremiere(10);
        Assert.assertEquals("premiere", result);
    }
    
  @Test
    public void testsLimitedeFirstController(){
        Assert.assertNotEquals(firstController, null);
        try {
          String result =firstController.getPremiere(15);
          Assert.assertFalse("Il devait lancer une runtimeException", true);
      } catch (Exception e) {
          Assert.assertEquals(RuntimeException.class, e.getClass());
      }
        
       try {
          String result =firstController.getPremiere(150);
          Assert.assertFalse("Il devait lancer une MyException", true);
      } catch (Exception e) {
          Assert.assertEquals(MyException.class, e.getClass());
      }
        
    try {
          String result =firstController.getPremiere(501);
          Assert.assertFalse("Il devait lancer une Exception", true);
      } catch (Exception e) {
          Assert.assertEquals(NullPointerException.class, e.getClass());
      }
     
        
         try {
          String result =firstController.getPremiere(1001);
          Assert.assertFalse("Il devait lancer une HttpClientErrorException", true);
      } catch (Exception e) {
          Assert.assertEquals(HttpClientErrorException.class, e.getClass());
          Assert.assertEquals(HttpStatus.NOT_FOUND, ((HttpClientErrorException)e).getStatusCode());
      }
            String result =firstController.getPremiere(751);
        Assert.assertEquals("C'est une adresse de redirection qui devrait faire une 404","toto", result);
       
    }
       
    
}
