package com.bin.apache.test;

import org.apache.commons.text.RandomStringGenerator;
import org.junit.Test;

/**
 * https://baike.baidu.com/item/ASCII/309296?fr=aladdin&fromid=19660475&fromtitle=ascii%E7%A0%81%E8%A1%A8
 * 以ascii码表生成随机的字符串
 *
 * @author shaobin.qin
 */
public class RandomStringGeneratorTest {
	
	@Test
	public void testRandomString()
	{
		//生成a-z的10位随机字符串
		System.out.println(new RandomStringGenerator.Builder().withinRange('a', 'z').build().generate(10));
		//生成A-Z的10位随机字符串
		System.out.println(new RandomStringGenerator.Builder().withinRange('A', 'Z').build().generate(10));
		//TODO 生成A-z的10位随机字符串，会包含acsii码在91-96之间的符号
		System.out.println(new RandomStringGenerator.Builder().withinRange('A', 'z').build().generate(10));
		//生成0-9的10位随机字符串
		System.out.println(new RandomStringGenerator.Builder().withinRange('0', '9').build().generate(10));
		//TODO 生成0-z的10位随机字符串，会包含acsii码在58-54和91-96之间的符号
		System.out.println(new RandomStringGenerator.Builder().withinRange('0', 'z').build().generate(10));

	}
}