/* Ооочень богатый функционал. все довольно интуитивно, но в этом функицонале можно утонуть особенно новичку!
* Например связать в группу JRadioButton, как узнать новичку, что это надо связывать, дальше как узнать что именно
 * в классе ButtonGroup существует метод setSelected? Существует ли какая то единая карта или справочник чтобы был
  * виден весь функицонал общим объемом? Посмотрел , ага есть класс ButtonGroup нужен для "того то" и умеет setSelected.
  * Просто иначе лазить cmd + ЛКМ по всей библотеке искать какой то функционал для себя - это как искать .bin файл в
   * файловой системе windows без поиска. */

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsWindow extends JFrame {

    private static final int WINDOW_WIDTH = 350;
    private static final int WINDOW_HEIGHT = 230;

    private static final int MIN_WIN_LENGTH = 3;

    private static final int MIN_FIELD_SIZE = 3;
    private static final int MAX_FIELD_SIZE = 10;

    private static final String FIELD_SIZE_PREFIX = "Field size is: ";
    private static final String WIN_LENGTH_PREFIX = "Win length is: ";


    private GameWindow gameWindow;

    private JRadioButton humVSAI;
    private JRadioButton humVShum;

    private JSlider slideWinLen;
    private JSlider slideFieldSize;

    SettingsWindow(GameWindow gameWindow){
        this.gameWindow = gameWindow;
        setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
        Rectangle gameWindowBounds = gameWindow.getBounds(); // используем класс "прямоугольник", Bounds - граница;
        int posX = (int) gameWindowBounds.getCenterX() - WINDOW_WIDTH / 2;
        int posY = (int) gameWindowBounds.getCenterY() - WINDOW_HEIGHT / 2;
        setLocation(posX, posY);
        setResizable(false);
        setTitle("Settings");
        setLayout(new GridLayout(10,1));
        addGameModeCtrls();
        addFieldCtrl();
        JButton btnStart = new JButton("Apply");
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                applySettings();
            }
        });
        add(btnStart);

    }

    private void addFieldCtrl() {
        JLabel lblFieldSize = new JLabel(FIELD_SIZE_PREFIX + MIN_FIELD_SIZE);
        JLabel lblWinLength = new JLabel(WIN_LENGTH_PREFIX + MIN_WIN_LENGTH);
        slideFieldSize = new JSlider(MIN_FIELD_SIZE,MAX_FIELD_SIZE, MIN_FIELD_SIZE);
        slideWinLen = new JSlider(MIN_WIN_LENGTH, MIN_FIELD_SIZE, MIN_WIN_LENGTH);

        add(new JLabel("Choose field size"));
        add(lblFieldSize);
        add(slideFieldSize);
        slideFieldSize.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int currentValue = slideFieldSize.getValue();
                lblFieldSize.setText(FIELD_SIZE_PREFIX + currentValue);
                slideWinLen.setMaximum(currentValue);
            }
        });

        add(new JLabel("Choose win lenght"));
        add(lblWinLength);
        add(slideWinLen);
        slideWinLen.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int currentValue = slideWinLen.getValue();
                lblWinLength.setText(WIN_LENGTH_PREFIX + currentValue);
            }
        });
    }

    private void addGameModeCtrls() {
        add(new JLabel("Choose game mode"));
        humVSAI = new JRadioButton("h vs AI");
        humVShum = new JRadioButton(("h vs h"));
        ButtonGroup mode = new ButtonGroup();
        mode.add(humVSAI);
        mode.add(humVShum);
        humVSAI.setSelected(true);
        add(humVSAI);
        add(humVShum);
    }

    void applySettings(){
        int mode;
        if (humVSAI.isSelected())
            mode = Map.MODE_AI_HUM;
        else if (humVShum.isSelected())
            mode = Map.MODE_HUM_HUM;
        else
            throw new RuntimeException("Unexpected game mode");

        int fieldSize = slideFieldSize.getValue();
        int winLen = slideWinLen.getValue();

        gameWindow.gameParamtr(mode, fieldSize, fieldSize, winLen);
        setVisible(false);
    }

}
