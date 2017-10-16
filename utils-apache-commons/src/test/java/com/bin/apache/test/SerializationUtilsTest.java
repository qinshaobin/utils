package com.bin.apache.test;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.SerializationUtils;
import org.junit.Test;

import lombok.Data;


public class SerializationUtilsTest {
	
	@Test
	public void testSerialize()
	{
		A a = new A(10001, "qinshaobin", new Date());
		byte[] ab = SerializationUtils.serialize(a);
		
		a = SerializationUtils.deserialize(ab);
		System.out.println(a);
	}

	@Data
	static class A implements Serializable
	{
		private static final long serialVersionUID = 2919205289198309973L;

		public A(int id, String name, Date date) {
			super();
			this.id = id;
			this.name = name;
			this.date = date;
		}

		private int id;
		
		private String name;
		
		private Date date;
	}
}
