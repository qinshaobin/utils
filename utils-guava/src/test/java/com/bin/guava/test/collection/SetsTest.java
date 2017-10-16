package com.bin.guava.test.collection;

import java.util.Date;
import java.util.Set;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Before;
import org.junit.Test;

import com.google.common.base.Predicate;
import com.google.common.collect.Sets;

/**
 * Set工具类
 *
 */
public class SetsTest {
	
	private Set<A> setA;
	
	private Set<A> setB;
	
	@Before
	public void init()
	{
		setA = Sets.newHashSet();
		for(int i=0; i<10; i++)
		{
			A a = new A();
			a.age = i;
			a.name = "name" + i;
			a.date = new Date();
			setA.add(a);
		}
		
		setB = Sets.newHashSet();
		for(int i=5; i<10; i++)
		{
			A a = new A();
			a.age = i;
			a.name = "name" + i;
			a.date = new Date();
			setB.add(a);
		}
	}

	@Test
	public void testFilter()
	{
		setA = Sets.filter(setA, new Predicate<A>() {

			public boolean apply(A input) {
				return input.age > 5;
			}
		});
		
		for(A a : setA)
			System.out.println(ToStringBuilder.reflectionToString(a, ToStringStyle.MULTI_LINE_STYLE));
	}
	
	@Test
	public void testUnion()
	{
		Set<A> sets = Sets.union(setA, setB);
		System.out.println(sets.size());
		for(A a : sets)
			System.out.println(ToStringBuilder.reflectionToString(a, ToStringStyle.MULTI_LINE_STYLE));
		
	}
	
	@Test
	public void testDifference()
	{
		Set<A> sets = Sets.difference(setA, setB);
		System.out.println(sets.size());
		for(A a : sets)
			System.out.println(ToStringBuilder.reflectionToString(a, ToStringStyle.MULTI_LINE_STYLE));
		
	}
	
	@Test
	public void testIntersection()
	{
		Set<A> sets = Sets.intersection(setA, setB);
		System.out.println(sets.size());
		for(A a : sets)
			System.out.println(ToStringBuilder.reflectionToString(a, ToStringStyle.MULTI_LINE_STYLE));
		
	}
	
	@Test
	public void testPowerSet()
	{
		Set<Set<A>> sets = Sets.powerSet(setB);
		System.out.println(sets.size());
		for(Set<A> set : sets)
		{
			System.out.println("------------------");
			for(A a : set)
				System.out.println(ToStringBuilder.reflectionToString(a, ToStringStyle.MULTI_LINE_STYLE));
		}
	}
	
	static class A {
		public String name;
		
		public int age;
		
		public Date date;

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + age;
			result = prime * result + ((date == null) ? 0 : date.hashCode());
			result = prime * result + ((name == null) ? 0 : name.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			A other = (A) obj;
			if (age != other.age)
				return false;
			if (date == null) {
				if (other.date != null)
					return false;
			} else if (!date.equals(other.date))
				return false;
			if (name == null) {
				if (other.name != null)
					return false;
			} else if (!name.equals(other.name))
				return false;
			return true;
		}
	}
	
}
