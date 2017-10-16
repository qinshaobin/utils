package com.bin.jodd.test;

import com.alibaba.fastjson.JSON;
import com.bin.jodd.entity.A;
import com.bin.jodd.entity.B;
import com.google.common.collect.Maps;
import jodd.bean.BeanCopy;
import org.junit.Test;

import java.util.Date;
import java.util.Map;

/**
 * <pre>
 *     spring/apache commons_beanutils都提供了javabean对象之间的属性复制，
 *     但是都只支持普通的javabean，类似于map无法复制
 *    并且apache commons_beanutils复制日期类型的属性时有问题
 * </pre>
 * jodd可以复制任意类型
 *
 * @author shaobin.qin
 */
public class BeanCopyTest {

	/**
	 * javabean转javabean,不同名属性无法复制
	 */
	@Test
	public void testBean2Bean() {

		A a = A.builder().id(1).name("bin").birthDay(new Date()).nickNameA("bin").build();

		B b = new B();
		BeanCopy.fromBean(a).toBean(b).copy();

		// b属性nickNameB无法赋值
		System.err.println(JSON.toJSONString(b));
	}

	/**
	 * javabean转map
	 */
	@Test
	public void testBean2Map() {

		A a = A.builder().id(1).name("bin").birthDay(new Date()).nickNameA("bin").build();

		Map<String, Object> map = Maps.newHashMap();
		BeanCopy.fromBean(a).toMap(map).copy();

		System.err.println(JSON.toJSONString(map));
	}

	/**
	 * map转javabean，key与属性相同
	 */
	@Test
	public void testMap2Bean() {

		Map<String, Object> map = Maps.newHashMap();
		map.put("id", 1);
		map.put("name", "bin");
		map.put("birthDay", new Date());
		map.put("nickNameA", "bin");

		A a = new A();
		BeanCopy.fromMap(map).toBean(a).copy();
		System.err.println(JSON.toJSONString(a));
	}

	/**
	 * map转javabean，key与属性不同
	 */
	@Test
	public void testMap2Bean2() {

		Map<String, Object> map = Maps.newHashMap();
		map.put("id", 1);
		map.put("name", "bin");
		map.put("birthDay", new Date());
		// key与属性名不同，无法赋值
		map.put("nickName", "bin");

		A a = new A();
		BeanCopy.fromMap(map).toBean(a).copy();
		// a属性nickNameA无法赋值
		System.err.println(JSON.toJSONString(a));
	}

}

