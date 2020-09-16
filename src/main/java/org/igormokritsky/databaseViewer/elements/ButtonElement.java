package org.igormokritsky.databaseViewer.elements;

import lombok.Getter;

public class ButtonElement {
    @Getter
    private final String tablesInMigrateSchema;

    public ButtonElement(String tablesInMigrateSchema) {
        this.tablesInMigrateSchema = tablesInMigrateSchema;
    }



}
