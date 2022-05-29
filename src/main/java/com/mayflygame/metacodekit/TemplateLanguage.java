package com.mayflygame.metacodekit;

public enum TemplateLanguage {
    vm("vm"),
    groovy("gsp");

    public final String fileType;

    TemplateLanguage(String fileType) {
        this.fileType = fileType;
    }
}
