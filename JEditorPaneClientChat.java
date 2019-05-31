import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JEditorPaneClientChat extends JFrame {


    private JTextArea editor; // вывод сообщения;
    private JTextField enterMessenge;  // ввод сообщения;


    public JEditorPaneClientChat() {
        setTitle("Chat");
        setDefaultCloseOperation(EXIT_ON_CLOSE); // Создаие пользовательского интерфейса

        JFrame f = new JFrame("panel");
        f.setLocation(600,200);

        JPanel p = new JPanel(new BorderLayout());

        JButton enter = new JButton("Enter");
        enter.setPreferredSize(new Dimension(100,50));
        enterMessenge = new JTextField("Введите сообщение и нажмите Enter", 100);
        enterMessenge.setPreferredSize(new Dimension(50,50));

        editor = new JTextArea(); // поле вывода сообщения

        //Пытался осуществить перенос строки при новом сообщении
        editor.setLineWrap(true);
        editor.setWrapStyleWord(true);

        p.add(enter, BorderLayout.LINE_END);

        // По клику "enter" вывод текста сообщения;
        enter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String str = enterMessenge.getText();
                editor.setText(str);
            }
        }
        );

        p.add(enterMessenge, BorderLayout.LINE_START);

        f.add(p, BorderLayout.SOUTH);
        f.add(editor, BorderLayout.CENTER);

        f.setPreferredSize(new Dimension(600, 600));
        f.pack();
        f.setVisible(true);
    }

}