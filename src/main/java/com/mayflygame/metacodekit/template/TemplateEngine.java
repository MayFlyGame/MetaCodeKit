package com.mayflygame.metacodekit.template;

import com.mayflygame.metacodekit.ClassEntry;
import com.mayflygame.metacodekit.CodeTemplate;

import java.util.List;

public interface TemplateEngine {
    GeneratedSource evaluate(CodeTemplate codeTemplate, List<ClassEntry> selectClasses, ClassEntry currentClass);
}
