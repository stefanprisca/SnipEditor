package org.eclipse.recommenders.snipmatch.rcp.core;

public class LightweightSnippet {
    public String description;
    public String filePath;

    public LightweightSnippet(String s, String file) {
        this.description = s;
        this.filePath = file;
    }
}
