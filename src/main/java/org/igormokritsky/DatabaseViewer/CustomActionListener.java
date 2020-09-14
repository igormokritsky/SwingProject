package org.igormokritsky.DatabaseViewer;

import org.igormokritsky.DatabaseViewer.dao.ButtonElements;
import org.igormokritsky.DatabaseViewer.dao.Fields;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class CustomActionListener implements ActionListener {

    private final ButtonElements buttonElements;

    public CustomActionListener(ButtonElements buttonElements) {
        this.buttonElements = buttonElements;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        DatabaseMetadata databaseMetadata = new DatabaseMetadata();
        try {
            List<Fields> fields = databaseMetadata.showFields(buttonElements.getTables_in_migrate_schema());
            List<Map<String, Object>> data = databaseMetadata.selectAll(buttonElements.getTables_in_migrate_schema());
            JTable jTable = new JTable((Object[][]) data.toArray(), fields.toArray());
            jTable.setBounds(30, 40, 400, 300);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

    }
}
