package org.igormokritsky.databaseViewer.elements;

import lombok.Getter;

public class FieldElement {

    @Getter
    private String field;

    @Override
    public String toString() {
        return field;
    }
}
