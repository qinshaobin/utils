package com.bin.apache.test;

import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

public class StringUtilsTest {

	@Test
	public void testEmpty()
	{
		String str = "";
		String str2 = "abc";
		
		System.out.println(StringUtils.isBlank(str));
		System.out.println(StringUtils.isNotBlank(str));
		
		System.out.println(StringUtils.isEmpty(str));
		System.out.println(StringUtils.isNotEmpty(str));
		
		System.out.println(StringUtils.isAnyBlank(str, str2));
		System.out.println(StringUtils.isAnyEmpty(str, str2));
		
		System.out.println(StringUtils.isNoneBlank(str, str2));
		System.out.println(StringUtils.isNoneEmpty(str, str2));
	}
	
	@Test
	public void testTrim()
	{
		String str = "  sfsf sfsf  sf";
		
		System.out.println(StringUtils.trim(str));
		
		System.out.println(StringUtils.trimToEmpty((str)));
		System.out.println(StringUtils.trimToEmpty((" ")));
		System.out.println(StringUtils.trimToEmpty((null)));
		
		System.out.println(StringUtils.trimToNull((str)));
		System.out.println(StringUtils.trimToNull((" ")));
		System.out.println(StringUtils.trimToNull((null)));
	}
	
	@Test
	public void testStrip()
	{
		String str = "yab c z";
		
		System.out.println(StringUtils.strip((str)));
		
		System.out.println(StringUtils.stripToEmpty((str)));
		System.out.println(StringUtils.stripToEmpty((" ")));
		System.out.println(StringUtils.stripToEmpty((null)));
		
		System.out.println(StringUtils.stripToNull((str)));
		System.out.println(StringUtils.stripToNull((" ")));
		System.out.println(StringUtils.stripToNull((null)));
		
		System.out.println(StringUtils.strip(str, "yz"));//ab c 
		System.out.println(StringUtils.strip(str, "yz"));//ab c  
		
		System.out.println(StringUtils.stripStart(str, "yz"));//ab c z
		System.out.println(StringUtils.stripEnd(str, "yz"));//ab c 
		
		System.out.println(StringUtils.stripAll(new String[]{str, null}));
		System.out.println(StringUtils.stripAll(new String[]{str, null}, "yz"));
	}
	
	@Test
	public void testEquals()
	{
		System.out.println(StringUtils.equals(null, null));
		System.out.println(StringUtils.equals("sss", null));
		
		System.out.println(StringUtils.equalsIgnoreCase("sss", "ssS"));
	}
	
	@Test
	public void testLeftAndRight()
	{
		System.out.println(StringUtils.left("abcd", 5));
		System.out.println(StringUtils.right("abcd", 3));
		System.out.println(StringUtils.mid("abcd", 2, 6));
	}
	
	@Test
	public void testPad()
	{
		String str = "abcd";
		System.out.println(StringUtils.leftPad(str, 7));
		System.out.println(StringUtils.leftPad(str, 7, 'e'));
		System.out.println(StringUtils.leftPad(str, 7, "egf"));
		
		System.out.println(StringUtils.rightPad(str, 7));
		System.out.println(StringUtils.rightPad(str, 7, 'e'));
		System.out.println(StringUtils.rightPad(str, 7, "egf"));
	}
	
	@Test
	public void testSplit()
	{
		String str = "ab c  d f ";
		System.out.println(Arrays.toString(str.split(" ")));
		System.out.println(Arrays.toString(StringUtils.split(str)));
		System.out.println(Arrays.toString(StringUtils.split(str, " ")));
		System.out.println(Arrays.toString(StringUtils.splitByWholeSeparator(str, " ")));
	}
	
	@Test
	public void testRepeat()
	{
		String str = "abc";
		System.out.println(StringUtils.repeat(str, 3));
		System.out.println(StringUtils.repeat(str, "de", 3));
	}
	
	@Test
	public void testCountMatches()
	{
		String str = "sfjslfjwesdfsfbaasd";
		System.out.println(StringUtils.countMatches(str, 's'));
		System.out.println(StringUtils.countMatches(str, "sf"));
	}
	
	@Test
	public void testDifference()
	{
		String str1 = "abcsfsfwerwcxvh";
		String str2 = "abcsswerwfssfsffwerwwreafdscxvh";
		System.out.println(StringUtils.difference(str1, str2));
	}
}
