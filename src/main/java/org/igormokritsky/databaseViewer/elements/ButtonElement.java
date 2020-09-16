package org.igormokritsky.databaseViewer.elements;

public class ButtonElement {
    private final String tablesInMigrateSchema;

    public ButtonElement(String tablesInMigrateSchema) {
        this.tablesInMigrateSchema = tablesInMigrateSchema;
    }

    public String getTablesInMigrateSchema() {
        return tablesInMigrateSchema;
    }

}
