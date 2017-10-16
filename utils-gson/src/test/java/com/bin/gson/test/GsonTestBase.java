package com.bin.gson.test;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * 标准GOSN
 */
public class GsonTestBase {

	private Gson gson;
	
	@Before
	public void init()
	{
		gson = new Gson();
	}
	
	@Test
	public void testEntity() throws Exception
	{
		A a = new A();
		a.id = 1;
		a.name = "bin";
		a.date = new Date();
		
		List<B> bs = Lists.newArrayList();
		for(int j=0; j<2; j++)
		{
			B b = new B();
			b.flag = true;
			b.sex = j + "";
			bs.add(b);
		}
		a.bs = bs;
		
		String str = gson.toJson(a);
		System.out.println(str);
		
		System.out.println(ToStringBuilder.reflectionToString(gson.fromJson(str, A.class),
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
			a.date = new Date();
			
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
		System.out.println(str = gson.toJson(list));
		
		//json字符串转为list<object>,需指明类型
		list = gson.fromJson(str, new TypeToken<List<A>>() {}.getType());
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
			a.date = new Date();
			
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
		System.out.println(str = gson.toJson(array));
		
		array = gson.fromJson(str, A[].class);
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
			a.date = new Date();
			
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
		System.out.println(str = gson.toJson(map));
		
		//json字符串转为map<object, object>,需指明类型
		map = gson.fromJson(str, new TypeToken<Map<String, A>>(){}.getType());
		for(String key : map.keySet())
			System.out.println(ToStringBuilder.reflectionToString(map.get(key), ToStringStyle.MULTI_LINE_STYLE));
	}
	
	static class A {
		
		public int id;
		
		public String name;
		
		public Date date;
		
		public List<B> bs;
	}
	
	static class B {
		
		public boolean flag;
		
		public String sex;
	}
}
