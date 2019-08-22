import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FourAfterMasTest {
    private static FourAfterMas fourAfterMas;
    private int[] mas;

    @BeforeAll
    public static void initTest(){
        fourAfterMas = new FourAfterMas();
        System.out.println("init suite");
    }

    @Test
    void fourAfterMassiv1() {
        int[] trueMass = {1, 7};
        int[] currentMas = {1, 2, 4, 2 ,3, 4, 1, 7};
        Assertions.assertArrayEquals(trueMass, fourAfterMas.fourAfterMassiv(currentMas));
    }

    @Test
    void fourAfterMassiv2() {
        int[] trueMass = {2, 3, 1, 7};
        int[] currentMas = {1, 2, 4, 2 ,3, 1, 7};
        Assertions.assertArrayEquals(trueMass, fourAfterMas.fourAfterMassiv(currentMas));
    }

    @Test
    void exceptionTesting() {
        int[] currentMas = {1, 2, 2 ,3, 1, 7};
        Assertions.assertThrows(RuntimeException.class,() -> {
            fourAfterMas.fourAfterMassiv(currentMas);
        });
    }

    @Test
    void checkForNull() {
        Assertions.assertThrows(NullPointerException.class,() -> {
            fourAfterMas.fourAfterMassiv(mas);
        });
    }

    @AfterAll
    public static void tearDown(){
        fourAfterMas = null;
    }


}