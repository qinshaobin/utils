package com.bin.util.jackson.test;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Before;
import org.junit.Test;

import com.bin.util.jackson.test.util.MyBooleanDeserializer;
import com.bin.util.jackson.test.util.MyBooleanSerializer;
import com.bin.util.jackson.test.util.MyDateDeserializer;
import com.bin.util.jackson.test.util.MyDateSerializer;
import com.bin.util.jackson.test.util.MyPropertyNamingStrategyBase;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.common.collect.Lists;

/**
 * 基于注解的jackson测试
 */
public class JacksonAnnotationTest {
	
	private ObjectMapper objectMapper;

	@Before
	public void init()
	{
		objectMapper = new ObjectMapper();
		//缩放排列输出
		objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
		
		//全局自定义的自动化别名
		//objectMapper.setPropertyNamingStrategy(new MyPropertyNamingStrategyBase());
	}
	
	/**
	 * 自定义格式化
	 * @JsonSerialize(using = MyDateSerializer.class)
	 * @JsonDeserialize(using = MyDateDeserializer.class)
	 * @throws IOException
	 */
	@Test
	public void testSerializeAndDeserialize() throws Exception
	{
		A a = new A();
		a.date = new Date();
		a.flag = false;
		
		String str;
		System.out.println(str = objectMapper.writeValueAsString(a));
		System.out.println(ToStringBuilder.reflectionToString(objectMapper.readValue(str, A.class),
				ToStringStyle.MULTI_LINE_STYLE));
	}
	
	/**
	 * 自定义别名
	 * @JsonProperty("first_name")
	 */
	@Test
	public void testJsonProperty() throws Exception
	{
		B b = new B();
		b.firstName = "qin";
		b.secondName = "shaobin";
		
		System.out.println(objectMapper.writeValueAsString(b));
		
		String str = "{\"first_name\":\"qin\",\"secondName\":\"shaobin\"}";
		System.out.println(ToStringBuilder.reflectionToString(objectMapper.readValue(str, B.class),
				ToStringStyle.MULTI_LINE_STYLE));
	}
	
	/**
	 * 忽略属性,序列化或反序列化时不会处理
	 * @JsonIgnore
	 */
	@Test
	public void testJsonIgnore() throws Exception
	{
		C c = new C();
		c.userName = "qin";
		c.password = "11111";
		
		System.out.println(objectMapper.writeValueAsString(c));
		
		//忽略属性password,age
		String str = "{\"userName\":\"bin\",\"password\":\"111111\"}";
		System.out.println(ToStringBuilder.reflectionToString(objectMapper.readValue(str, C.class),
				ToStringStyle.MULTI_LINE_STYLE));
	}
	
	/**
	 * 忽略属性(批量操作),序列化或反序列化时不会处理
	 * @JsonIgnoreProperties({"password", "age"})
	 * @throws Exception
	 */
	@Test
	public void testJsonIgnoreProperties() throws Exception
	{
		D d = new D();
		d.userName = "qin";
		d.password = "111111";
		d.age = 24;
		
		System.out.println(objectMapper.writeValueAsString(d));
		
		//忽略属性password,age
		String str = "{\"userName\":\"bin\",\"password\":\"111111\",\"age\":24}";
		System.out.println(ToStringBuilder.reflectionToString(objectMapper.readValue(str, D.class),
				ToStringStyle.MULTI_LINE_STYLE));
	}
	
	/**
	 * 忽略属性,序列化或反序列化时不会处理未知属性
	 * @JsonIgnoreProperties(ignoreUnknown = true)
	 */
	@Test
	public void testJsonIgnoreProperties2() throws Exception
	{
		//未知属性age
		String str = "{\"userName\":\"bin\",\"password\":\"111111\",\"age\":24}";
		System.out.println(ToStringBuilder.reflectionToString(objectMapper.readValue(str, E.class),
				ToStringStyle.MULTI_LINE_STYLE));
	}
	
	/**
	 * @JsonUnwrapped:将JSON对象的属性添加到封闭的JSON对象
	 * @throws Exception
	 */
	@Test
	public void testJsonUnwrapped() throws Exception
	{
		A a = new A();
		a.date = new Date();
		a.flag = true;
		
		E e = new E();
		e.userName = "qin";
		e.password = "111111";
		
		F f = new F();
		f.a = a;
		f.e = e;
		
		System.out.println(objectMapper.writeValueAsString(f));
		/**
		  	{
			  "a" : {
			    "date" : "2016-05-06 11:17:36",
			    "flag" : "1"
			  },
			  "userName" : "qin",
			  "password" : "111111"
			}
		 */
	}
	
	/**
	 * 自动化处理自定义别名:大写转换为小写并添加下划线
	 * @JsonNaming(SnakeCaseStrategy.class)
	 */
	@Test
	public void testJsonNaming() throws Exception
	{
		G g = new G();
		g.firstName = "qin";
		g.secondName = "shaobin";
		
		System.out.println(objectMapper.writeValueAsString(g));
		
		String str = "{\"first_name\":\"qin\",\"second_name\":\"shaobin\"}";
		System.out.println(ToStringBuilder.reflectionToString(objectMapper.readValue(str, G.class),
				ToStringStyle.MULTI_LINE_STYLE));
	}
	
	/**
	 * 自动化处理自定义别名:大写转换为小写并添加中划线
	 * @JsonNaming(MyPropertyNamingStrategyBase.class)
	 */
	@Test
	public void testJsonNaming2() throws Exception
	{
		H h = new H();
		h.firstName = "qin";
		h.secondName = "shaobin";
		
		System.out.println(objectMapper.writeValueAsString(h));
		
		String str = "{\"first-name\":\"qin\",\"second-name\":\"shaobin\"}";
		System.out.println(ToStringBuilder.reflectionToString(objectMapper.readValue(str, H.class),
				ToStringStyle.MULTI_LINE_STYLE));
	}
	
	/**
	 * @JsonUnwrapped:将JSON对象的属性添加到封闭的JSON对象
	 * @throws Exception
	 */
	@Test
	public void testArray() throws Exception
	{
		A a = new A();
		a.date = new Date();
		a.flag = true;
		
		List<E> es = Lists.newArrayList();
		for(int i=0; i<5; i++)
		{
			E e = new E();
			e.userName = "qin";
			e.password = "111111";
			es.add(e);
		}
		
		I i = new I();
		i.a = a;
		i.es = es;
		
		System.out.println(objectMapper.writeValueAsString(i));
		
		//
		objectMapper.configure(SerializationFeature.WRITE_CHAR_ARRAYS_AS_JSON_ARRAYS, true);
		System.out.println(objectMapper.writeValueAsString(i));
	}
	
	static class A{
		
		//自定义格式化
		@JsonSerialize(using = MyDateSerializer.class)
		@JsonDeserialize(using = MyDateDeserializer.class)
		public Date date;
		
		@JsonSerialize(using = MyBooleanSerializer.class)
		@JsonDeserialize(using = MyBooleanDeserializer.class)
		public boolean flag;
		
	}
	
	static class B {
		
		//别名
		@JsonProperty("first_name")
		public String firstName;
		
		public String secondName;
	}
	
	static class C {
		
		public String userName;
		
		@JsonIgnore
		public String password;
	}
	
	@JsonIgnoreProperties({"password", "age"})
	static class D {
		
		public String userName;
		
		public String password;
		
		public int age;
	}
	
	@JsonIgnoreProperties(ignoreUnknown = true)
	static class E {
		
		public String userName;
		
		public String password;
	}
	
	static class F {
		
		public A a;
		
		@JsonUnwrapped
		public E e;
	}
	
	@JsonNaming(SnakeCaseStrategy.class)
	static class G {
		
		public String firstName;
		
		public String secondName;
	}
	
	@JsonNaming(MyPropertyNamingStrategyBase.class)
	static class H {
		
		public String firstName;
		
		public String secondName;
	}
	
	static class I {
		
		public A a;
		
		public List<E> es;
	}
	
}
