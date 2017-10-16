package com.bin.util.jackson.test.util;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

/**
 * 自定义JSON日期格式化
 * yyyy-MM-dd HH:mm:ss
 */
public class MyDateDeserializer extends JsonDeserializer<Date> {

	@Override
	public Date deserialize(JsonParser p, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		
		try {
			return DateUtils.parseDate(p.getValueAsString(), new String[]{"yyyy-MM-dd HH:mm:ss"});
		} catch (ParseException e) {
			
		}
		return new Date();
	}
}
