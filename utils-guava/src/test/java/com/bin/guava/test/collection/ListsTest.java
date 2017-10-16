package com.bin.guava.test.collection;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Before;
import org.junit.Test;

import com.google.common.base.Function;
import com.google.common.collect.Lists;

/**
 * list工具类
 *
 */
public class ListsTest {
	
	private List<A> listA;
	
	private List<B> listB;
	
	@Before
	public void init()
	{
		listA = Lists.newArrayList();
		for(int i=0; i<10; i++)
		{
			A a = new A();
			a.age = i;
			a.name = "name" + i;
			a.date = new Date();
			listA.add(a);
		}
	}

	@Test
	public void testTransform()
	{
		listB = Lists.newArrayList(Lists.transform(listA, new Function<A, B>() {

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
	public void testPartition()
	{
		List<List<A>> listAs = Lists.partition(listA, 4);
		System.out.println(listAs.size());
		for(List<A> list : listAs)
		{
			System.out.println("---------------------------");
			for(A a : list)
				System.out.println(ToStringBuilder.reflectionToString(a, ToStringStyle.MULTI_LINE_STYLE));
		}
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testCartesianProduct()
	{
		List<B> listB1 = Lists.newArrayList();
		List<B> listB2 = Lists.newArrayList();
		List<B> listB3 = Lists.newArrayList();
		
		List<List<B>> listBs = Lists.cartesianProduct(listB1, listB2, listB3);
		System.out.println(listBs.size());
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
