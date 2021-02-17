package ua.knu.csc.ui;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {

    public MainWindow(String title) {
        super(title);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        setSize(500, 300);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(screenSize.width / 2 - getSize().width / 2, screenSize.height / 2 - getSize().height / 2);
    }
}
