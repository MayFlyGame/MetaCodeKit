package com.mayflygame.metacodekit;

import com.intellij.openapi.util.text.StringUtil;
import lombok.Data;

@Data
public class CodeTemplate {

    private String templateName;
    private String className;
    private String templateContent;
    private String targetLanguage;
    private TemplateLanguage templateLanguage;

    public CodeTemplate() {

    }

    public CodeTemplate(String templateName, String className, String templateContent, TemplateLanguage templateLanguage,
                        String targetLanguage ) {
        this.templateName = templateName;
        this.className = className;
        this.templateContent = templateContent;
        this.templateLanguage = templateLanguage;
        this.targetLanguage = targetLanguage;
    }

    public static CodeTemplate empty(String title) {
        return new CodeTemplate(title, "", "", TemplateLanguage.vm, "java");
    }

    public TemplateLanguage getTemplateLanguage() {
        return templateLanguage == null? TemplateLanguage.vm : templateLanguage;
    }

    public void setTemplateLanguage(TemplateLanguage templateLanguage) {
        if(templateLanguage == null) {
            templateLanguage = TemplateLanguage.vm;
        }
        this.templateLanguage = templateLanguage;
    }

    public boolean isValid() {
        return StringUtil.isNotEmpty(getClassName()) && StringUtil.isNotEmpty(getTemplateName())
                && StringUtil.isNotEmpty(getTemplateContent()) ;
    }

    public String getTargetLanguage() {
        return targetLanguage == null ? "java" : targetLanguage;
    }

    public void setTargetLanguage(String targetLanguage) {
        this.targetLanguage = targetLanguage == null ? "java" : targetLanguage;
    }
}
