package org.igormokritsky.databaseViewer.db;

public class MetadataAccess {

    private final MetadataHelper databaseMetadata = new MetadataHelper();

    public MetadataHelper getDatabaseMetadata() {
        return databaseMetadata;
    }

}
