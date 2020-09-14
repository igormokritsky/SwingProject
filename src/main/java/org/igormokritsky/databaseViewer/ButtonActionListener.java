package org.igormokritsky.databaseViewer;

import org.igormokritsky.databaseViewer.elements.ButtonElement;
import org.igormokritsky.databaseViewer.elements.FieldElement;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class ButtonActionListener implements ActionListener {
    List<FieldElement> fields = null;
    List<Map<String, Object>> data = null;
    Object[][] objectRows = null;

    private final ButtonElement buttonElements;
    public ButtonActionListener(ButtonElement buttonElements) {
        this.buttonElements = buttonElements;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        MetadataImpl databaseMetadata = new MetadataImpl();
        try {
            fields = databaseMetadata.showFields(buttonElements.getTables_in_migrate_schema());
            data = databaseMetadata.selectAll(buttonElements.getTables_in_migrate_schema());
            objectRows = data.stream().map(m -> m.values().toArray()).toArray(Object[][]::new);
            JFrame frame = new JFrame();
            JPanel panel = new JPanel();
            JTable jTable = new JTable(objectRows, fields.toArray());
            jTable.setBounds(30, 40, 400, 300);
            panel.add(new JScrollPane(jTable));
            frame.add(panel);
            frame.setSize(550, 400);
            frame.setVisible(true);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }


}
