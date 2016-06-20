import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by mulder on 16/6/16.
 */
public class DemoControllerTest {
    File f;

    @Before
    public void init() {
        System.out.println("befor test");
        f=new File("output"+File.separator+"test.xml");
    }

    @Test(expected=ArithmeticException.class)
    public void divideZero() {
        int n=2/0;
    }

    @Test(timeout = 1)
    public void timeoutTest() {
        int i=0;
        while (i<100000000){
            i++;
        }
    }

    @Test
    public void assertTest(){
        int i=0;
        i++;
        int expected = 0;
        assertEquals(expected,i);
    }

}
