package com.mayflygame.metacodekit.ui;

import com.mayflygame.metacodekit.TemplateLanguage;
import com.intellij.openapi.editor.Editor;
import javax.swing.*;
import java.awt.*;

/**
 * @author GODWiT
 * @description
 * @datae 2022/5/25
 */
public class TemplateEdit {

    private Editor templateEditor;
    private String templateContent;
    private JPanel templateEditPanel;
    private TemplateLanguage templateLanguage;

    public TemplateEdit(JPanel templateEditPanel, String templateContent, TemplateLanguage templateLanguage) {
        this.templateEditPanel = templateEditPanel;
        this.templateContent = templateContent;
        this.templateLanguage = templateLanguage;
        refreshTemplateEditor();
    }

    public void refreshTemplateEditor() {
        if (templateEditPanel.getComponentCount() > 0) {
            Editors.release(templateEditor);
            templateEditPanel.remove(0);
        }
        templateEditor = newTemplateEditor(templateContent, templateLanguage );
        templateEditPanel.add(templateEditor.getComponent(), BorderLayout.CENTER);
    }

    private Editor newTemplateEditor(String text, TemplateLanguage language) {
        return Editors.createSourceEditor(null, language.fileType, text, false);
    }

    public String getTemplateContent() {
        return templateEditor.getDocument().getText();
    }
}
