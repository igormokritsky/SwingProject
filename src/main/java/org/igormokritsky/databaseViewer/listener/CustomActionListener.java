package org.igormokritsky.databaseViewer.listener;


import lombok.SneakyThrows;
import org.igormokritsky.databaseViewer.db.MetadataHelper;
import org.igormokritsky.databaseViewer.elements.ButtonElement;
import org.igormokritsky.databaseViewer.elements.FieldElement;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.List;


public class CustomActionListener implements ActionListener {

    String elementsData;
    MetadataHelper metadataHelper;
    JFrame frame;
    JPanel panel;
    JScrollPane jScrollPane;
    JTable jTable;


    public CustomActionListener(String elementsData, MetadataHelper metadataHelper, JFrame frame,
                                JPanel panel, JScrollPane jScrollPane, JTable jTable) {
        this.elementsData = elementsData;
        this.metadataHelper = metadataHelper;
        this.frame = frame;
        this.panel = panel;
        this.jScrollPane = jScrollPane;
        this.jTable = jTable;
    }

    @SneakyThrows
    @Override
    public void actionPerformed(ActionEvent e) {

        List<FieldElement> fields = metadataHelper.showFields(elementsData);
        List<Map<String, Object>> data = metadataHelper.selectAll(elementsData);
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
}
