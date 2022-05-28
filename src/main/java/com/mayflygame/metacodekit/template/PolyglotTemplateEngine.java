package com.mayflygame.metacodekit.template;

import com.mayflygame.metacodekit.ClassEntry;
import com.mayflygame.metacodekit.CodeTemplate;
import com.mayflygame.metacodekit.TemplateLanguage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PolyglotTemplateEngine implements TemplateEngine {

    private static Map<TemplateLanguage, TemplateEngine> engines;
    static {
        engines = new HashMap<>();
        engines.put(TemplateLanguage.vm, new VelocityTemplateEngine());
        engines.put(TemplateLanguage.groovy, new GroovyTemplateEngineImpl());
    }

    @Override
    public GeneratedSource evaluate(CodeTemplate codeTemplate, List<ClassEntry> selectClasses, ClassEntry currentClass) {
        final TemplateEngine engine = engines.get(codeTemplate.getTemplateLanguage());
        if(engine == null) {
            throw new IllegalArgumentException("Unsupported language: " + codeTemplate.getTemplateLanguage());
        }
        return engine.evaluate(codeTemplate, selectClasses, currentClass);
    }
}
