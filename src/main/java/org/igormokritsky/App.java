package org.igormokritsky;


import lombok.SneakyThrows;
import org.igormokritsky.databaseViewer.db.MetadataHelper;
import org.igormokritsky.databaseViewer.elements.ButtonElement;
import org.igormokritsky.databaseViewer.listener.CustomActionListener;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.sql.SQLException;
import java.util.List;



public class App extends JPanel {


    private static final JFrame frame = new JFrame();
    private static final JPanel panel = new JPanel();
    private static final JPanel buttons = new JPanel(new GridLayout(0, 1));
    private static final MetadataHelper metadataHelper = new MetadataHelper();


    public static void createGUI() throws SQLException {

        List<ButtonElement> elements = metadataHelper.showTables();

        for (ButtonElement buttonElements : elements) {
            String elementsData = buttonElements.getTablesInMigrateSchema();
            JButton jButton = new JButton(elementsData);
            buttons.add(jButton);
            jButton.addActionListener(new CustomActionListener(elementsData, metadataHelper, frame, panel));
        }


        panel.add(buttons, BorderLayout.EAST);
        frame.add(panel);
        frame.add(panel, BorderLayout.WEST);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("SwingSandbox");
        frame.setPreferredSize(new Dimension(850, 600));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.pack();
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @SneakyThrows
            public void run() {
                JFrame.setDefaultLookAndFeelDecorated(true);
                createGUI();
            }
        });
    }

}

