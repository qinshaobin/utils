package com.bin.util.jackson.test.util;

import java.io.IOException;
import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * 自定义JSON日期格式化
 * yyyy-MM-dd HH:mm:ss
 */
public class MyDateSerializer extends JsonSerializer<Date> {

	@Override
	public void serialize(Date value, JsonGenerator gen,
			SerializerProvider serializers) throws IOException,
			JsonProcessingException {
		gen.writeString(DateFormatUtils.format(value, "yyyy-MM-dd HH:mm:ss"));
	}
}
