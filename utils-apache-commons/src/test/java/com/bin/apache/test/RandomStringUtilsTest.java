package com.bin.apache.test;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;

/**
 * 生成随机的字符串
 * RandomStringUtils已经被废弃,使用RandomStringGenerator
 *
 * @author shaobin.qin
 */
@Deprecated
public class RandomStringUtilsTest {
	
	@Test
	public void testRandomString()
	{
		System.out.println(RandomStringUtils.random(10));
		System.out.println(RandomStringUtils.random(10, 'a','b','c'));
		System.out.println(RandomStringUtils.random(10, "abc"));
		System.out.println(RandomStringUtils.random(10, true, true));
		System.out.println(RandomStringUtils.random(10, true, false));
		System.out.println(RandomStringUtils.random(10, false, true));
		System.out.println(RandomStringUtils.random(10, false, false));
	}

}
