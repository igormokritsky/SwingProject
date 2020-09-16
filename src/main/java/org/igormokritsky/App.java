package org.igormokritsky;

import lombok.SneakyThrows;
import org.igormokritsky.databaseViewer.db.MetadataHelper;
import org.igormokritsky.databaseViewer.elements.ButtonElement;
import org.igormokritsky.databaseViewer.elements.FieldElement;
import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class App extends JPanel {

    private static List<FieldElement> fields = new ArrayList<>();
    private static List<Map<String, Object>> data = new ArrayList<>();
    private static final JFrame frame = new JFrame();
    private static final JPanel panel = new JPanel();
    private static JTable jTable = new JTable();
    private static final JPanel buttons = new JPanel(new GridLayout(0, 1));
    private static JScrollPane jScrollPane;
    private static final MetadataHelper metadataHelper = new MetadataHelper();



    public static void createGUI() throws SQLException {

        List<ButtonElement> elements = metadataHelper.showTables();

        for (ButtonElement buttonElements : elements) {

            String elementsData = buttonElements.getTablesInMigrateSchema();
            JButton jButton = new JButton(elementsData);

            buttons.add(jButton);
            jButton.addActionListener(new ActionListener() {
                @SneakyThrows
                @Override
                public void actionPerformed(ActionEvent e) {
                    fields = metadataHelper.showFields(elementsData);
                    data = metadataHelper.selectAll(elementsData);
                    Object[][] objectRows = data.stream().map(m -> m.values().toArray()).toArray(Object[][]::new);
                    if (jScrollPane != null) {
                        panel.remove(jScrollPane);
                    }
                    jTable = new JTable(objectRows, fields.toArray());
                    jScrollPane = new JScrollPane(jTable);
                    panel.add(jScrollPane);
                    frame.revalidate();
                    frame.repaint();
                }
            });
        }

        panel.add(buttons, BorderLayout.EAST);
        frame.add(panel);
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
            @SneakyThrows
            public void run() {
                JFrame.setDefaultLookAndFeelDecorated(true);
                createGUI();
            }
        });
    }

}

