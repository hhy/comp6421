package concordia.haiyang.assign1;


import static org.junit.Assert.*;

import java.util.regex.Pattern;

import org.junit.Test;

/**
 * Unit test for simple App.
 */


public class SpecificationTest{
    
    @Test
    public void testId(){
    	assertTrue(Specification.isId("rfdsa21212"));
    	assertTrue(Specification.isId("A22fdsa21212"));
    	assertFalse(Specification.isId("1A22fdsa21212"));
    	assertFalse(Specification.isId("A22 fdsa21212"));
    }
    
    @Test
    public void testFloat(){
    	
    	for(String s: new String[]{"111.1343","0",  "0.0", "9.912", "0.989", "212312", "211.0"}){
    		assertTrue(s,Specification.isNum(s));
    	}
    	for(String s: new String[]{"afdsa", "a.dfsa", "23afdsa", "000", "0.9780", ".00", ".0"}){
    		assertFalse(s, Specification.isNum(s));
    	}
    }
}
