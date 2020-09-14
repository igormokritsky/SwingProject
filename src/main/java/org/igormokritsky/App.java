package org.igormokritsky;

import org.igormokritsky.databaseViewer.ButtonActionListener;
import org.igormokritsky.databaseViewer.MetadataImpl;
import org.igormokritsky.databaseViewer.elements.ButtonElement;
import org.igormokritsky.databaseViewer.elements.FieldElement;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;


public class App extends JPanel {

    public static void createGUI() throws SQLException {
        JFrame frame = new JFrame();
        MetadataImpl databaseMetadata = new MetadataImpl();
        List<ButtonElement> elements = databaseMetadata.showTables();

        JPanel buttons = new JPanel(new GridLayout(0, 1));

        for (ButtonElement buttonElements : elements) {
            JButton jButton = new JButton(buttonElements.getTables_in_migrate_schema());
            jButton.addActionListener(new ButtonActionListener(buttonElements));
            buttons.add(jButton);

        }


        JPanel panel = new JPanel(new BorderLayout());
        JTable jTable = new JTable();

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

