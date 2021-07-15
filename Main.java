package question2;

import javax.swing.*;

public class Main {

    public static void main(String[] ags) {
        JFrame frame = new JFrame("Restaurant menu");
        frame.add(new MainPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1900, 900);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
