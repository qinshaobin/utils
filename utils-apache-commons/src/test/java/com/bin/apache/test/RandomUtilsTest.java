package com.bin.apache.test;

import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;

public class RandomUtilsTest {
	
	@Test
	public void testNext()
	{
		System.out.println(RandomUtils.nextBytes(10));
		System.out.println(RandomUtils.nextInt(21, 100));
		System.out.println(RandomUtils.nextFloat(12.3f, 99.9f));
		System.out.println(RandomUtils.nextDouble(12.34, 99.99));
		System.out.println(RandomUtils.nextLong(21L, 1000L));
	}

}
