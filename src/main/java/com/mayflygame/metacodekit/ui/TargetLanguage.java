package com.mayflygame.metacodekit.ui;

import lombok.Data;
import lombok.Getter;

/**
 * @author GODWiT
 * @description
 * @datae 2022/5/26
 */
@Data
@Getter
public class TargetLanguage {
    public String name;
    public String fileType;

    public TargetLanguage(String name, String fileType) {
        this.name = name;
        this.fileType = fileType;
    }

    @Override
    public String toString() {
        return name;
    }
}
