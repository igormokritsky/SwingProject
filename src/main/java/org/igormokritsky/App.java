package org.igormokritsky;

import org.igormokritsky.DatabaseViewer.CustomActionListener;
import org.igormokritsky.DatabaseViewer.DatabaseMetadata;
import org.igormokritsky.DatabaseViewer.dao.ButtonElements;
import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;


public class App extends JPanel {

    public static void createGUI() throws SQLException {
        JFrame frame = new JFrame();
        DatabaseMetadata databaseMetadata = new DatabaseMetadata();
        List<ButtonElements> elements = databaseMetadata.showTables();

        JPanel buttons = new JPanel(new GridLayout(0, 1));

        for (ButtonElements buttonElements : elements) {
            JButton jButton = new JButton(buttonElements.getTables_in_migrate_schema());
            jButton.addActionListener(new CustomActionListener(buttonElements));
            buttons.add(jButton);
        }


        JPanel panel = new JPanel(new BorderLayout());
        panel.add(buttons, BorderLayout.CENTER);
        frame.add(panel, BorderLayout.WEST);
        frame.setTitle("SwingSandbox");
        frame.setPreferredSize(new Dimension(850, 600));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
    }


    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame.setDefaultLookAndFeelDecorated(true);
                try {
                    createGUI();
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();
                }
            }
        });
    }

}

