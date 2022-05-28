package com.mayflygame.metacodekit.ui;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author GODWiT
 * @description
 * @datae 2022/5/26
 */
public class TargetLanguageSelect {

    public static TargetLanguage defaultLanguage = new TargetLanguage("Java", "java");
    public static List<TargetLanguage> popularLanguages = new ArrayList<>();

    static {
        popularLanguages.add( defaultLanguage );
        popularLanguages.add( new TargetLanguage("Scala", "scala") );
        popularLanguages.add( new TargetLanguage("Kotlin", "kt") );
        popularLanguages.add( new TargetLanguage("JavaScript", "js") );
        popularLanguages.add( new TargetLanguage("TypeScript", "ts") );
        popularLanguages.add( new TargetLanguage("SQL", "sql") );
    }

    public static void initCombo(JComboBox<TargetLanguage> langCombo, String selected) {
        // this sets the width of the combobox
        langCombo.setPrototypeDisplayValue( new TargetLanguage("xxxxxxxxxxxxxx", "")) ;
        popularLanguages.forEach(targetLanguage -> langCombo.addItem( targetLanguage ) );
        langCombo.setSelectedItem( find( selected) );
    }
    public static TargetLanguage find(String selected) {
        for (TargetLanguage popularLanguge : popularLanguages) {
            if( popularLanguge.fileType.toLowerCase().equals(selected.toLowerCase())) {
                return popularLanguge;
            }
        }
        return defaultLanguage;
    }
}

