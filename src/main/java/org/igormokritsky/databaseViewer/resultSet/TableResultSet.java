package org.igormokritsky.databaseViewer.resultSet;

import org.apache.commons.dbutils.ResultSetHandler;
import org.igormokritsky.databaseViewer.elements.ButtonElement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TableResultSet implements ResultSetHandler<List<ButtonElement>> {

    @Override
    public List<ButtonElement> handle(ResultSet resultSet) throws SQLException {
        List<ButtonElement> buttonElementList = new ArrayList<>();
        while (resultSet.next()) {
            buttonElementList.add(new ButtonElement(resultSet.getString(1)));
        }
        return buttonElementList;
    }
}
