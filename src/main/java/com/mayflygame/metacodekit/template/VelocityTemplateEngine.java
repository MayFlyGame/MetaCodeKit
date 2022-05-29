package com.mayflygame.metacodekit.template;

import com.mayflygame.metacodekit.CodeTemplate;
import com.mayflygame.metacodekit.TemplateLanguage;
import com.mayflygame.metacodekit.util.VelocityUtil;

import java.util.Map;

public class VelocityTemplateEngine extends BaseTemplateEngine {

    @Override
    protected TemplateLanguage supportedLanguage() {
        return TemplateLanguage.vm;
    }

    @Override
    protected String doEvaluate(CodeTemplate template, Environment environment) {
        return VelocityUtil.evaluate(template.getTemplateContent(), environment.bindings);
    }

    @Override
    protected String generateClassName(String classNameTemplate, Map<String, Object> environment) {
        return VelocityUtil.evaluate(classNameTemplate, environment);
    }

}
