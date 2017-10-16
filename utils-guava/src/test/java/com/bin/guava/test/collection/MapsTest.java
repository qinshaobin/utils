package com.bin.guava.test.collection;

import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;
import com.google.common.collect.Maps.EntryTransformer;
import com.google.common.collect.Sets;

/**
 * map工具类
 *
 */
public class MapsTest {
	
	private Set<A> setA;
	
	private Map<String, A> mapA;
	
	private Map<String, A> mapB;
	
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
		
		mapA = Maps.newHashMap();
		for(int i=0; i<10; i++)
		{
			A a = new A();
			a.age = i;
			a.name = "name" + i;
			a.date = new Date();
			mapA.put(a.name, a);
		}
		
		mapB = Maps.newHashMap();
		for(int i=5; i<10; i++)
		{
			A a = new A();
			a.age = i;
			a.name = "name" + i;
			a.date = new Date();
			mapB.put(a.name, a);
		}
	}
	
	@Test
	public void testDifference()
	{
		MapDifference<String, A> mapDifference = Maps.difference(mapA, mapB);
		
		System.out.println("inCommon");
		for(Entry<String, A> entry: mapDifference.entriesInCommon().entrySet())
			System.out.println(entry.getKey() + ":" + entry.getValue());
		
		System.out.println("left");
		for(Entry<String, A> entry: mapDifference.entriesOnlyOnLeft().entrySet())
			System.out.println(entry.getKey() + ":" + entry.getValue());
		
		System.out.println("right");
		for(Entry<String, A> entry: mapDifference.entriesOnlyOnRight().entrySet())
			System.out.println(entry.getKey() + ":" + entry.getValue());
	}

	@Test
	public void testUniqueIndex()
	{
		ImmutableMap<String, A> map = Maps.uniqueIndex(setA, new Function<A, String>() {

			public String apply(A input) {
				return input.name;
			}
		});
		
		for(Entry<String, A> entry: map.entrySet())
			System.out.println(entry.getKey() + ":" + entry.getValue());
	}
	
	@Test
	public void testToMap()
	{
		ImmutableMap<A, String> map = Maps.toMap(setA, new Function<A, String>() {

			public String apply(A input) {
				return input.name;
			}
		});
		
		for(Entry<A, String> entry: map.entrySet())
			System.out.println(entry.getKey() + ":" + entry.getValue());
	}
	
	@Test
	public void testAsMap()
	{
		Map<A, String> map = Maps.asMap(setA, new Function<A, String>() {

			public String apply(A input) {
				return input.name;
			}
		});
		
		for(Entry<A, String> entry: map.entrySet())
			System.out.println(entry.getKey() + ":" + entry.getValue());
	}
	
	@Test
	public void testFilterValues()
	{
		Map<String, A> map = Maps.filterValues(mapA, new Predicate<A>() {

			public boolean apply(A input) {
				
				return input.age > 5;
			}
		});
		
		for(Entry<String, A> entry: map.entrySet())
			System.out.println(entry.getKey() + ":" + entry.getValue());
	}
	
	@Test
	public void testFilterKeys()
	{
		Map<String, A> map = Maps.filterKeys(mapA, new Predicate<String>() {

			public boolean apply(String input) {
				return Integer.parseInt(input.substring(4)) > 5;
			}
		});
		
		for(Entry<String, A> entry: map.entrySet())
			System.out.println(entry.getKey() + ":" + entry.getValue());
	}
	
	@Test
	public void testFilterEntries()
	{
		Map<String, A> map = Maps.filterEntries(mapA, new Predicate<Entry<String, A>>() {

			public boolean apply(Entry<String, A> input) {
				return input.getValue().age > 5;
			}
		});
		
		for(Entry<String, A> entry: map.entrySet())
			System.out.println(entry.getKey() + ":" + entry.getValue());
	}
	
	@Test
	public void testTransformValues()
	{
		Map<String, B> map = Maps.transformValues(mapA, new Function<A, B>() {

			public B apply(A a) {
				B b = new B();
				b.age = a.age;
				b.name = a.name;
				return b;
			}
		});
		
		for(Entry<String, B> entry: map.entrySet())
			System.out.println(entry.getKey() + ":" + entry.getValue());
	}
	
	@Test
	public void testTransformEntries()
	{
		Map<String, B> map = Maps.transformEntries(mapA, new EntryTransformer<String, A, B>(){

			public B transformEntry(String key, A a) {
				B b = new B();
				b.age = a.age;
				b.name = a.name;
				return b;
			}
		});

		for(Entry<String, B> entry: map.entrySet())
			System.out.println(entry.getKey() + ":" + entry.getValue());
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
	
	static class B {
		public String name;
		
		public int age;

		@Override
		public String toString() {
			return "B [name=" + name + ", age=" + age + "]";
		}
	}
}
