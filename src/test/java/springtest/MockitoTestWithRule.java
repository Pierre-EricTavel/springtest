/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package springtest;

import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Rule;
import org.junit.Test;
import static org.mockito.ArgumentMatchers.eq;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpression;


public class MockitoTestWithRule {
    
    @Mock
    private List liste;
    
    @Rule
    public MockitoRule rule = MockitoJUnit.rule();
    
    @Test
    public void testQuery(){
        
        when(liste.get(eq(9))).thenReturn("Element 9");
        when(liste.get(eq(10))).thenReturn("Element 10");
        String dixieme= (String) liste.get(9);
        assertEquals("Element 9", dixieme);
        assertEquals(0, liste.size());
        
      
    }
    
}
