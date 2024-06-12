import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

//@RunWith(value = Parameterized.class)
public class OneOrFourCheckTest {

    public static OneOrFourCheck oneOrFourCheck;
    private Integer[] mas;
    private boolean res;

//    public OneOrFourCheckTest(Integer[] mas, boolean res){
//        this.mas = mas;
//        this.res = res;
//    }
//
//
//    @Parameters
//    public static Collection allMassive() {
//        return Arrays.asList(new Object[][]{
//                        {new Integer[] {1,4}, true},
//                        {new Integer[] {2,4}, false},
//                        {new Integer[] {1,3}, false},
//                }
//        );
//    }

    // Не удалось запустить с параметрами. Хотелось найти assertArrayEquals c boolean \ boolean но в итоге
    // пробовал Object \ object но вылетает java.lang.NullPointerException;

    @Before
    public void init() {
        System.out.println("init calc");
        oneOrFourCheck = new OneOrFourCheck();

    }

    @Test
    public void boolTest1(){
        Assertions.assertTrue(oneOrFourCheck.oneOrFourCheck(new Integer[] {1,4}));
    }

    @Test
    public void boolTest2(){
        Assertions.assertFalse(oneOrFourCheck.oneOrFourCheck(new Integer[] {2,4}));
    }

    @Test
    public void boolTest3(){
        Assertions.assertFalse(oneOrFourCheck.oneOrFourCheck(new Integer[] {1,3}));
    }

    @Test
    public void checkForNull() {
        Assertions.assertThrows(NullPointerException.class,() -> {
            oneOrFourCheck.oneOrFourCheck(null);
        });
    }

    @After
    public void tearDown() {
        oneOrFourCheck = null;
    }

}