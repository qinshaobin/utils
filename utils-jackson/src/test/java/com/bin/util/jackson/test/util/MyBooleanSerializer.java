package com.bin.util.jackson.test.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * 自定义JSON日期格式化
 * true:1,false:0
 */
public class MyBooleanSerializer extends JsonSerializer<Boolean> {

	@Override
	public void serialize(Boolean value, JsonGenerator gen,
			SerializerProvider serializers) throws IOException,
			JsonProcessingException {
		gen.writeString(value == true ? "1" : "0");
	}
}
