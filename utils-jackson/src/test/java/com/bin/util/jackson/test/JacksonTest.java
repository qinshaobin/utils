package com.bin.util.jackson.test;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class JacksonTest {

	private ObjectMapper objectMapper;

	@Before
	public void init()
	{
		objectMapper = new ObjectMapper();
		//缩放排列输出
		objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
		//属性为null不序列化
		objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		//属性为null或者""不序列化
		objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
		//属性为默认值不序列化,eg:Boolean的默认值为false
		objectMapper.setSerializationInclusion(JsonInclude.Include.NON_DEFAULT);
	}
	
	@Test
	public void testEntity() throws Exception
	{
		A a = new A();
		a.id = 1;
		a.name = "bin";
		
		List<B> bs = Lists.newArrayList();
		for(int j=0; j<2; j++)
		{
			B b = new B();
			b.flag = true;
			b.sex = j + "";
			bs.add(b);
		}
		a.bs = bs;
		
		String str = objectMapper.writeValueAsString(a);
		System.out.println(str);
		
		System.out.println(ToStringBuilder.reflectionToString(objectMapper.readValue(str, A.class),
				ToStringStyle.MULTI_LINE_STYLE));
	}
	
	@Test
	public void testCollection() throws Exception
	{
		List<A> list = Lists.newArrayList();
		for(int i=0; i<5; i++)
		{
			A a = new A();
			a.id = i;
			a.name = "bin" + i;
			
			List<B> bs = Lists.newArrayList();
			for(int j=0; j<2; j++)
			{
				B b = new B();
				b.flag = true;
				b.sex = j + "";
				bs.add(b);
			}
			a.bs = bs;
			list.add(a);
		}
		String str;
		System.out.println(str = objectMapper.writeValueAsString(list));
		
		//json字符串转为list<object>,需指明类型
		list = objectMapper.readValue(str, new TypeReference<List<A>>() {});
		for(A a : list)
			System.out.println(ToStringBuilder.reflectionToString(a, ToStringStyle.MULTI_LINE_STYLE));
	}
	
	@Test
	public void testArray() throws Exception
	{
		A[] array = new A[5];
		for(int i=0; i<5; i++)
		{
			A a = new A();
			a.id = i;
			a.name = "bin" + i;
			
			List<B> bs = Lists.newArrayList();
			for(int j=0; j<2; j++)
			{
				B b = new B();
				b.flag = true;
				b.sex = j + "";
				bs.add(b);
			}
			a.bs = bs;
			
			array[i] = a;
		}
		String str;
		System.out.println(str = objectMapper.writeValueAsString(array));
		
		array = objectMapper.readValue(str, A[].class);
		for(A a :array)
			System.out.println(ToStringBuilder.reflectionToString(a, ToStringStyle.MULTI_LINE_STYLE));
	}
	
	@Test
	public void testMap() throws Exception
	{
		Map<String, A> map = Maps.newHashMap();
		for(int i=0; i<5; i++)
		{
			A a = new A();
			a.id = i;
			a.name = "bin" + i;
			
			List<B> bs = Lists.newArrayList();
			for(int j=0; j<2; j++)
			{
				B b = new B();
				b.flag = true;
				b.sex = j + "";
				bs.add(b);
			}
			a.bs = bs;
			
			map.put(a.name, a);
		}
		
		String str;
		System.out.println(str = objectMapper.writeValueAsString(map));
		
		//json字符串转为map<object, object>,需指明类型
		map = objectMapper.readValue(str, new TypeReference<Map<String, A>>() {});
		for(String key : map.keySet())
			System.out.println(ToStringBuilder.reflectionToString(map.get(key), ToStringStyle.MULTI_LINE_STYLE));
	}
	
	
	static class A {
		
		public int id;
		
		public String name;
		
		public List<B> bs;
	}
	
	static class B {
		
		public boolean flag;
		
		public String sex;
	}
}
