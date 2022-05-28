package com.mayflygame.metacodekit.ui;

import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SearchableConfigurable;
import com.mayflygame.metacodekit.MetaCodeKitSettings;
import com.mayflygame.metacodekit.CodeTemplate;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.Map;

/**
 * @author hansong.xhs
 * @version $Id: CodeMakerConfigurable.java, v 0.1 2017-01-31 9:09 hansong.xhs Exp $$
 */
public class MetaCodeKitConfigurable implements SearchableConfigurable, Configurable.NoScroll {

    private MetaCodeKitSettings settings;

    private MetaCodeKitConfiguration configuration;

    public MetaCodeKitConfigurable() {
        settings = ServiceManager.getService(MetaCodeKitSettings.class);
    }

    @NotNull
    @Override
    public String getId() {
        return "plugins.metacodekit";
    }

    @Nullable
    @Override
    public Runnable enableSearch(String option) {
        return null;
    }

    @Nls
    @Override
    public String getDisplayName() {
        return "MetaCodeKit";
    }

    @Nullable
    @Override
    public String getHelpTopic() {
        return "help.metacodekit.configuration";
    }

    @Nullable
    @Override
    public JComponent createComponent() {
        if (configuration == null) {
            configuration = new MetaCodeKitConfiguration(settings);
        }
        return configuration.getMainPane();
    }

    /**
     * Compare the data to see if we are modified
     *
     * @return true if the settings should be 'applied'
     */
    @Override
    public boolean isModified() {
        if (settings.getCodeTemplates().size() != configuration.getTabTemplates().size()) {
            return true;
        }
        for (Map.Entry<String, CodeTemplate> entry : configuration.getTabTemplates().entrySet()) {
            CodeTemplate codeTemplate = settings.getCodeTemplate(entry.getKey());
            if (codeTemplate == null || !codeTemplate.equals(entry.getValue())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void apply() throws ConfigurationException {
        for (Map.Entry<String, CodeTemplate> entry : configuration.getTabTemplates().entrySet()) {
            if (!entry.getValue().isValid()) {
                throw new ConfigurationException(
                    "Not property can be empty and classNumber should be a number");
            }
        }
        settings.setCodeTemplates(configuration.getTabTemplates());
        configuration.refresh(settings.getCodeTemplates());
    }

    @Override
    public void reset() {
        if (configuration != null) {
            configuration.refresh(settings.getCodeTemplates());
        }
    }

    @Override
    public void disposeUIResources() {
        this.configuration = null;
    }
}
