package com.bin.guava.test.collection;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;

/**
 * Collection2工具类
 * transform:    集合转换
 * filter:       集合过滤
 */
public class Collection2Test {
	
	@Test
	public void TestTransform()
	{
		List<A> listA = Lists.newArrayList();
		for(int i=0; i<10; i++)
		{
			A a = new A();
			a.age = i;
			a.name = "name" + i;
			a.date = new Date();
			listA.add(a);
		}
		
		List<B> listB = Lists.newArrayList(Collections2.transform(listA, new Function<A, B>() {

			public B apply(A a) {
				B b = new B();
				b.age = a.age;
				b.name = a.name;
				return b;
			}
		}));
		
		for(B b : listB)
			System.out.println(ToStringBuilder.reflectionToString(b, ToStringStyle.MULTI_LINE_STYLE));
	}
	
	@Test
	public void testFilter()
	{
		List<A> listA = Lists.newArrayList();
		for(int i=0; i<10; i++)
		{
			A a = new A();
			a.age = i;
			a.name = "name" + i;
			a.date = new Date();
			listA.add(a);
		}
		
		listA = Lists.newArrayList(Collections2.filter(listA, new Predicate<A>() {

			public boolean apply(A input) {
				return input.age > 5;
			}
		}));
		
		for(A a : listA)
			System.out.println(ToStringBuilder.reflectionToString(a, ToStringStyle.MULTI_LINE_STYLE));
	}
	
	static class A {
		public String name;
		
		public int age;
		
		public Date date;
	}
	
	static class B {
		public String name;
		
		public int age;
	}

}
