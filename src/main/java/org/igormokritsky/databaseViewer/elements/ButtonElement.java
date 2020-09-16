package org.igormokritsky.databaseViewer.elements;

import lombok.Getter;
import lombok.Setter;

public class ButtonElement {

    @Getter
    @Setter
    private String tablesInMigrateSchema;

    public ButtonElement(String tablesInMigrateSchema) {
        this.tablesInMigrateSchema = tablesInMigrateSchema;
    }

}
