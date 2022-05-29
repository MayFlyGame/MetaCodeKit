package com.mayflygame.metacodekit;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.util.xmlb.XmlSerializerUtil;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author hansong.xhs
 * @version $Id: CodeMakerSettings.java, v 0.1 2017-01-28 9:30 hansong.xhs Exp $$
 */
@State(name = "MetaCodeKitSettings", storages = {@Storage("$APP_CONFIG$/MetaCodeKit-settings.xml")})
public class MetaCodeKitSettings implements PersistentStateComponent<MetaCodeKitSettings> {

    private static final Logger LOGGER = Logger.getInstance(MetaCodeKitSettings.class);

    public MetaCodeKitSettings() {
    }

    private void loadDefaultSettings() {
        try {
            Map<String, CodeTemplate> codeTemplates = new LinkedHashMap<>();
            codeTemplates.put("SqlSelectExample",
                    createCodeTemplate("SqlSelectExample", "SqlExample.vm", "${class0.className}Example", "java"));

            this.codeTemplates = codeTemplates;
        } catch (Exception e) {
            LOGGER.error("loadDefaultSettings failed", e);
        }
    }

    @NotNull
    private CodeTemplate createCodeTemplate(String name, String sourceTemplateName, String classNameVm, String targetLanguage) throws IOException {
        String velocityTemplate = FileUtil.loadTextAndClose(MetaCodeKitSettings.class.getResourceAsStream("/template/" + sourceTemplateName));
        return new CodeTemplate(name, classNameVm, velocityTemplate, TemplateLanguage.vm, targetLanguage);
    }

    /**
     * Getter method for property <tt>codeTemplates</tt>.
     *
     * @return property value of codeTemplates
     */
    public Map<String, CodeTemplate> getCodeTemplates() {
        if (codeTemplates == null) {
            loadDefaultSettings();
        }
        return new LinkedHashMap<>(codeTemplates);
    }

    @Setter
    private Map<String, CodeTemplate> codeTemplates;

    @Nullable
    @Override
    public MetaCodeKitSettings getState() {
        if (this.codeTemplates == null) {
            loadDefaultSettings();
        }
        return this;
    }

    @Override
    public void loadState(@NotNull MetaCodeKitSettings metaCodeKitSettings) {
        XmlSerializerUtil.copyBean(metaCodeKitSettings, this);
    }

    public CodeTemplate getCodeTemplate(String template) {
        return codeTemplates.get(template);
    }

    public void removeCodeTemplate(String template) {
        codeTemplates.remove(template);
    }

    public void addTemplate(String key, CodeTemplate template) {
        codeTemplates.put(key, template);
    }
}
