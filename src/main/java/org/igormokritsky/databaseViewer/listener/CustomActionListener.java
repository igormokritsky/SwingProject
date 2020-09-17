package org.igormokritsky.databaseViewer.listener;


import lombok.SneakyThrows;
import org.igormokritsky.databaseViewer.db.MetadataHelper;
import org.igormokritsky.databaseViewer.elements.FieldElement;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JFrame;
import javax.swing.JTable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.List;


public class CustomActionListener implements ActionListener {

    private JScrollPane jScrollPane;
    private final String elementsData;
    private final MetadataHelper metadataHelper;
    private final JFrame frame;
    private final JPanel panel;

    public CustomActionListener(String elementsData, MetadataHelper metadataHelper, JFrame frame,
                                JPanel panel) {
        this.elementsData = elementsData;
        this.metadataHelper = metadataHelper;
        this.frame = frame;
        this.panel = panel;

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
        JTable jTable = new JTable(objectRows, fields.toArray());
        jScrollPane = new JScrollPane(jTable);
        panel.add(jScrollPane);
        frame.revalidate();
        frame.repaint();
    }
}
