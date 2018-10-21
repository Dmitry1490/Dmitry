import javax.swing.*;
import java.awt.*;

public class Map extends JPanel {

    public static final int MODE_HUM_HUM = 0;
    public static final int MODE_AI_HUM = 1;

    Map(){
        setBackground(Color.BLUE);
    }

    void gameParamtr(int mode, int fieldSizwX, int fieldSizwY, int winLenght){
        System.out.printf("M: %d, FSX: %d, FST: %d, WL: %d\n", mode, fieldSizwX, fieldSizwY, winLenght );
    }
}
