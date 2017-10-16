package com.bin.dom4j.test;

import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Field;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.joda.time.DateTime;
import org.junit.Test;

public class Dom4jTest {
	
	private Document document;
	
	private File fileWrite = new File(new File("").getAbsoluteFile() + "/src/test/java/com/bin/dom4j/test/test.xml");
	
	private File fileWriteN = new File(new File("").getAbsoluteFile() + "/src/test/java/com/bin/dom4j/test/testN.xml");
	
	@Test
	public void testWrite() throws Exception
	{
		document = DocumentHelper.createDocument();
		
		Element root = document.addElement("request");
		
		Element head = root.addElement("head");
		head.addElement("merCode").setText("808801");
		head.addElement("billNo").setText("123456");
		head.addElement("trdDate").setText(new DateTime().toString("yyyy-MM-dd"));
		
		Element detail = root.addElement("detail");
		Element users = detail.addElement("users");
		for(int i=0; i<5; i++)
		{
			A a = new A();
			a.name = "秦少彬" + i;
			a.age = 24 + i;
			a.sex = i/2 == 0 ? "male" : "female";
			
			Element userEle = users.addElement("user");
			for(Field field : A.class.getDeclaredFields())
				userEle.addElement(field.getName()).setText(field.get(a) + "");
		}
		
		//格式化输出
		OutputFormat format = OutputFormat.createPrettyPrint();
		//设置编码
		format.setEncoding("UTF-8");
		
		XMLWriter writer = new XMLWriter(new FileWriter(fileWrite), format);
		writer.write(document);
		writer.flush();
		writer.close();
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testRead() throws Exception
	{
		document = new SAXReader().read(fileWrite);
		System.out.println(document.asXML());
		
		//取单节点值
		System.out.println(document.selectSingleNode("/request/head/merCode").getText());
		
		//取多节点
		List<Node> users = document.selectNodes("/request/detail/users/user");
		for(Node node : users)
			for(Field field : A.class.getDeclaredFields())
				System.out.println(node.selectSingleNode(field.getName()).getText());
			
	}
	
	@Test
	public void testParse() throws Exception
	{
		String str = "<head><merCode>808801</merCode><billNo>123456</billNo><trdDate>2016-05-11</trdDate></head>";
		
		document = DocumentHelper.parseText(str);
		
		System.out.println(document.asXML());
	}
	
	@Test
	public void testEdit() throws Exception
	{
		String str = "<head><merCode>808801</merCode><billNo>123456</billNo><trdDate>2016-05-11</trdDate></head>";
		
		document = DocumentHelper.parseText(str);
		
		Element root = document.getRootElement();
		//add
		root.addElement("channel").setText("10001");
		//update
		root.selectSingleNode("billNo").setText("654321");
		//delete
		root.remove(root.selectSingleNode("trdDate"));
		
		System.out.println(document.asXML());
	}
	
	@Test
	public void testNamespaceWrite() throws Exception
	{
		document = DocumentHelper.createDocument();
		
		Element root = document.addElement("request");
		root.addNamespace("bin", "com.bin");
		
		Element head = root.addElement("head");
		head.addElement("merCode").setText("808801");
		head.addElement("billNo").setText("123456");
		head.addElement("trdDate").setText(new DateTime().toString("yyyy-MM-dd"));
		
		Element detail = root.addElement("detail");
		Element users = detail.addElement("users");
		for(int i=0; i<5; i++)
		{
			A a = new A();
			a.name = "秦少彬" + i;
			a.age = 24 + i;
			a.sex = i/2 == 0 ? "male" : "female";
			
			Element userEle = users.addElement("user");
			for(Field field : A.class.getDeclaredFields())
				userEle.addElement(field.getName()).setText(field.get(a) + "");
		}
		
		//格式化输出
		OutputFormat format = OutputFormat.createPrettyPrint();
		//设置编码
		format.setEncoding("UTF-8");
		
		XMLWriter writer = new XMLWriter(new FileWriter(fileWriteN), format);
		writer.write(document);
		writer.flush();
		writer.close();
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testNamespaceRead() throws Exception
	{
		document = new SAXReader().read(fileWriteN);
		System.out.println(document.asXML());
		
		//取单节点值
		System.out.println(document.selectSingleNode("/request/head/merCode").getText());
		
		//取多节点
		List<Node> users = document.selectNodes("/request/detail/users/user");
		for(Node node : users)
			for(Field field : A.class.getDeclaredFields())
				System.out.println(node.selectSingleNode(field.getName()).getText());
			
	}
	
	static class A
	{
		public String name;
		
		public int age;
		
		public String sex;
	}
}
