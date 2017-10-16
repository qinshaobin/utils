package com.bin.xstream.test;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;
import com.thoughtworks.xstream.io.xml.Xpp3DomDriver;
import com.thoughtworks.xstream.io.xml.Xpp3Driver;


public class XstreamTest {
	
	private XStream xStream;
	
	@Before
	public void init()
	{
		xStream = new XStream(new Xpp3DomDriver());
		//忽略未知节点
		xStream.ignoreUnknownElements();
	}
	
	@Test
	public void testEntity()
	{
		A a = new A();
		a.name = "bin";
		a.date = new Date();
		a.user_name = "qin";
		
		String str = xStream.toXML(a);
		System.out.println(str);
		
		a = (A) xStream.fromXML(str);
		System.out.println(a);
		
		System.out.println("------------------------------");
		xStream = new XStream(new Xpp3Driver(new XmlFriendlyNameCoder("_-", "_")));
		str = xStream.toXML(a);
		System.out.println(str);
		
		a = (A) xStream.fromXML(str);
		System.out.println(a);
	}
	
	@Test
	public void testCollection()
	{
		List<A> as = Lists.newArrayList(); 
		Map<String, A> aMap = Maps.newHashMap();
		for(int i=0; i<5; i++)
		{
			A a = new A();
			a.name = "bin" + i;
			a.date = new Date();
			a.user_name = "qin" + i;
			as.add(a);
			aMap.put(a.name, a);
		}
		B b = new B();
		b.name = "qin";
		b.as = as;
		b.aMap = aMap;
		
		xStream = new XStream(new Xpp3Driver(new XmlFriendlyNameCoder("_-", "_")));
		String str = xStream.toXML(b);
		System.out.println(str);
		
		b = (B) xStream.fromXML(str);
		System.out.println(b);
	}
	
	@Test
	public void testEntityAnnotation()
	{
		xStream.autodetectAnnotations(true);
		
		List<C> as = Lists.newArrayList(); 
		Map<String, C> aMap = Maps.newHashMap();
		for(int i=0; i<5; i++)
		{
			C c = new C();
			c.name = "bin" + i;
			c.date = new Date();
			c.user_name = "qin" + i;
			as.add(c);
			aMap.put(c.name, c);
		}
		D d = new D();
		d.name = "qin";
		d.as = as;
		d.aMap = aMap;
		
		String str = xStream.toXML(d);
		System.out.println(str);
		
		d = (D) xStream.fromXML(str);
		System.out.println(d);
		
		
		System.out.println("------------------------------");
		xStream.aliasPackage("", this.getClass().getPackage().getName());
		str = xStream.toXML(d);
		System.out.println(str);
		
		d = (D) xStream.fromXML(str);
		System.out.println(d);
	}
	
	static class A 
	{
		public String name;
		
		public Date date;
		
		public String user_name;
		
		@Override
		public String toString() {
			return "A [name=" + name + ", date=" + date + ", user_name="
					+ user_name + "]";
		}
	}
	
	static class B
	{
		public String name;
		
		public List<A> as;
		
		public Map<String, A> aMap;

		@Override
		public String toString() {
			return "B [name=" + name + ", as=" + as + ", aMap=" + aMap + "]";
		}
		
	}
	
	static class C 
	{
		public String name;
		
		@XStreamAlias("birthDay")
		public Date date;
		
		@XStreamAlias("userName")
		public String user_name;
		
		@Override
		public String toString() {
			return "C [name=" + name + ", date=" + date + ", user_name="
					+ user_name + "]";
		}
	}
	
	static class D
	{
		public String name;
		
		@XStreamAlias("listC")
		public List<C> as;
		
		@XStreamAlias("mapC")
		public Map<String, C> aMap;

		@Override
		public String toString() {
			return "D [name=" + name + ", as=" + as + ", aMap=" + aMap + "]";
		}
		
	}
	
}
