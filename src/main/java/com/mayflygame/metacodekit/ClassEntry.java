package com.mayflygame.metacodekit;

import com.intellij.psi.*;
import com.mayflygame.metacodekit.util.MetaCodeKitUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.jetbrains.plugins.scala.lang.psi.api.ScalaFile;
import org.jetbrains.plugins.scala.lang.psi.api.toplevel.typedef.ScClass;

import java.util.Collections;
import java.util.List;

/**
 * @author hansong.xhs
 * @version $Id: ClassEntry.java, v 0.1 2017-01-22 9:53 hansong.xhs Exp $$
 */
@Data
@AllArgsConstructor
public class ClassEntry {

    private String className;
    private String packageName;
    private List<String> importList;
    private List<Field> fields;
    private List<Field> allFields;
    private List<Method> methods;
    private List<Method> allMethods;
    private List<String> typeParams = Collections.emptyList();

    @Data
    @AllArgsConstructor
    public static class Method {
        /**
         * method name
         */
        private String name;
        /**
         * the method modifier, like "private",or "@Setter private" if include annotations
         */
        private String modifier;
        /**
         * the method returnType
         */
        private String returnType;
        /**
         * the method params, like "(String name)"
         */
        private String params;
    }

    @Data
    @AllArgsConstructor
    public static class Field {
        /**
         * field type
         */
        private String type;
        /**
         * field name
         */
        private String name;
        /**
         * the field modifier, like "private",or "@Setter private" if include annotations
         */
        private String modifier;
        /**
         * field doc comment
         */
        private String comment;

    }

    private ClassEntry() {

    }

    public static ClassEntry create(PsiClass psiClass) {
        PsiFile psiFile = psiClass.getContainingFile();
        ClassEntry classEntry = new ClassEntry();
        classEntry.setClassName(psiClass.getName());
        classEntry.setPackageName(((PsiClassOwner)psiFile).getPackageName());
        if(psiFile instanceof PsiJavaFile)
        {
            classEntry.setFields(MetaCodeKitUtil.getFields(psiClass));
            classEntry.setImportList(MetaCodeKitUtil.getImportList((PsiJavaFile) psiFile));
            classEntry.setAllFields(MetaCodeKitUtil.getAllFields(psiClass));
        }
        else if(psiClass instanceof ScClass) {
            ScClass scalaClass = (ScClass) psiClass;
            classEntry.setFields(MetaCodeKitUtil.getScalaClassFields(scalaClass));
            classEntry.setImportList(MetaCodeKitUtil.getScalaImportList((ScalaFile)psiFile));
        }

        classEntry.setMethods(MetaCodeKitUtil.getMethods(psiClass));
        classEntry.setAllMethods(MetaCodeKitUtil.getAllMethods(psiClass));
        classEntry.setTypeParams(MetaCodeKitUtil.getClassTypeParameters(psiClass));
        return classEntry;
    }

}
