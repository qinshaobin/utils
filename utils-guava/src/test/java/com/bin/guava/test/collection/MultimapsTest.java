package com.bin.guava.test.collection;

import java.util.Date;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableListMultimap;
import com.google.common.collect.Multimaps;
import com.google.common.collect.Sets;

/**
 * Multimap工具类
 * 负责map
 */
public class MultimapsTest {
	
	private Set<A> setA;
	
	@Before
	public void init()
	{
		setA = Sets.newHashSet();
		for(int i=0; i<10; i++)
		{
			A a = new A();
			a.age = i;
			a.name = "name" + i/3;
			a.date = new Date();
			setA.add(a);
		}
	}
	
	@Test
	public void testIndex()
	{
		ImmutableListMultimap<String, A> map = Multimaps.index(setA, new Function<A, String>() {

			public String apply(A input) {
				
				return input.name;
			}
		});
		
		System.out.println(map.asMap().keySet());
		System.out.println(map.asMap().size());
		for(String key : map.asMap().keySet())
		{
			System.out.println(key);
			for(A a : map.get(key))
				System.out.println(a);
		}
	}
	
	static class A {
		public String name;
		
		public int age;
		
		public Date date;

		@Override
		public String toString() {
			return "A [name=" + name + ", age=" + age + ", date=" + date + "]";
		}
	}
}
