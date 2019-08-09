/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package springtest;


import net.itta.springtest.LeServiceExpress;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringTestConfig.class})
@TestExecutionListeners(DependencyInjectionTestExecutionListener.class)
public class ServiceExpressTest {
    
  @Autowired
  LeServiceExpress leServiceExpress;

    @Test
    public void testNominauxdeGetResult(){
        Assert.assertNotEquals(leServiceExpress, null);
        
       int r = leServiceExpress.getResult(10);
       Assert.assertEquals(30, r);
       
       
        r = leServiceExpress.getResult(20);
       Assert.assertEquals(40, r);
    }
    
     @Test
    public void testErreursdeGetResult(){
        Assert.assertNotEquals(leServiceExpress, null);
        
       int r = leServiceExpress.getResult(10);
       Assert.assertNotEquals(20, r);
    }
       
    
}
