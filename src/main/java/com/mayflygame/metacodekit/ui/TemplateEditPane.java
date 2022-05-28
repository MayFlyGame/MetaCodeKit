package com.mayflygame.metacodekit.ui;

import com.mayflygame.metacodekit.CodeTemplate;
import com.mayflygame.metacodekit.TemplateLanguage;
import com.intellij.ui.DocumentAdapter;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import java.util.function.Consumer;

public class TemplateEditPane {

    private JPanel     templateEdit;
    private JTextField templateNameText;
    private JTextField classNameText;
    private JComboBox  templateLanguage;
    private JComboBox<TargetLanguage> targetLanguage;
    private JPanel templateEditPanel;
    private TemplateEdit templateEditor;

    public TemplateEditPane(CodeTemplate codeTemplate, Consumer<String> titleChanged) {
        if (codeTemplate == null) {
            throw new NullPointerException("codeTemplate is null!");
        }

        templateNameText.setText(codeTemplate.getTemplateName());
        templateNameText.getDocument().addDocumentListener(new DocumentAdapter() {
            @Override
            protected void textChanged(@NotNull DocumentEvent e) {
                titleChanged.accept(templateNameText.getText());
            }
        });

        templateLanguage.setSelectedItem(codeTemplate.getTemplateLanguage().name());
        templateLanguage.addActionListener(e -> {
            templateEditor.refreshTemplateEditor();
        });

        classNameText.setText(codeTemplate.getClassName());

        templateEditor = new TemplateEdit(
                templateEditPanel,
                codeTemplate.getTemplateContent(),
                getTemplateLanguage() );

        TargetLanguageSelect.initCombo( targetLanguage, codeTemplate.getTargetLanguage() );
    }

    public JPanel getTemplateEdit() {
        return templateEdit;
    }

    public String getClassName() {
        return classNameText.getText();
    }

    public String getTemplateName() {
        return templateNameText.getText();
    }

    public TemplateLanguage getTemplateLanguage() {
        return TemplateLanguage.valueOf(String.valueOf(templateLanguage.getSelectedItem()));
    }

    public String getTemplate() {
        return templateEditor.getTemplateContent();
    }

    public String getTargetLanguage() {
        final TargetLanguage selectedItem = (TargetLanguage)targetLanguage.getSelectedItem();
        if(selectedItem == null) {
            return "java";
        } else {
            return selectedItem.getFileType();
        }
    }
}
