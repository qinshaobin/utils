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
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

/**
 * @Expose注解的作用：区分实体中不想被序列化的属性，其自身包含两个属性deserialize(反序列化)和serialize（序列化），
 * 默认都为true。使用 new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
 * 创建Gson对象，没有@Expose注释的属性将不会被序列化.
 * 
 * @SerializedName注解的作用：定义属性序列化后的名称
 *
 * 基于注解
 * 格式化JSON
 * 日期格式化
 */
public class GsonAnnotationTest {

	private Gson gson;
	
	@Before
	public void init()
	{
		GsonBuilder gsonBuilder = new GsonBuilder();
		//格式化输出
		gsonBuilder.setPrettyPrinting();
		//日期格式化
		gsonBuilder.setDateFormat("yyyy-MM-dd hh:mm:ss");
		//过滤未标记@Expose的属性
		gsonBuilder.excludeFieldsWithoutExposeAnnotation();
		gson = gsonBuilder.create();
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
		
		@Expose
		public int id;
		
		@Expose
		@SerializedName("user_name")
		public String name;
		
		@Expose
		public Date date;
		
		public List<B> bs;
	}
	
	static class B {
		
		public boolean flag;
		
		public String sex;
	}
}
