package org.igormokritsky.databaseViewer.elements;

public class FieldElement {

    private String field;

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    @Override
    public String toString() {
        return field;
    }
}
