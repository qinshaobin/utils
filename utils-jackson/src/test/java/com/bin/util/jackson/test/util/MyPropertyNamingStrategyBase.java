package com.bin.util.jackson.test.util;

import com.fasterxml.jackson.databind.PropertyNamingStrategy.PropertyNamingStrategyBase;

/**
 * 自定义的自动化别名
 * 大写转换为小写并添加中划线
 * eg:firestName--->first-name
 *
 */
public class MyPropertyNamingStrategyBase extends PropertyNamingStrategyBase {

	private static final long serialVersionUID = 7411753395339977277L;

	@Override
	public String translate(String input) {
        if (input == null) 
        	return input; // garbage in, garbage out
        int length = input.length();
        StringBuilder result = new StringBuilder(length * 2);
        int resultLength = 0;
        boolean wasPrevTranslated = false;
        for (int i = 0; i < length; i++)
        {
            char c = input.charAt(i);
            if (i > 0 || c != '-') // skip first starting underscore
            {
                if (Character.isUpperCase(c))
                {
                    if (!wasPrevTranslated && resultLength > 0 && result.charAt(resultLength - 1) != '-')
                    {
                        result.append('-');
                        resultLength++;
                    }
                    c = Character.toLowerCase(c);
                    wasPrevTranslated = true;
                }
                else
                {
                    wasPrevTranslated = false;
                }
                result.append(c);
                resultLength++;
            }
        }
        return resultLength > 0 ? result.toString() : input;
    }

}
