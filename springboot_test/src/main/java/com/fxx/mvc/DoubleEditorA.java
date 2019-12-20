package com.fxx.mvc;

import org.springframework.beans.propertyeditors.PropertiesEditor;

/**
 * @Description: 添加参数校验器
 * @Author: FeiXinXin
 * @CreateDate: 2019/5/8 9:55
 * @Version: 1.0
 */

public class DoubleEditorA extends PropertiesEditor {
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (text == "111" || text.equals("")) {
            text = "222";
        }
        setValue(Double.parseDouble(text));
    }

    @Override
    public String getAsText() {
        return getValue().toString();
    }
}
