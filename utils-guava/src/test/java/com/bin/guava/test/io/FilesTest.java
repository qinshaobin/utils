package com.bin.guava.test.io;

import com.google.common.io.FileWriteMode;
import com.google.common.io.Files;
import org.junit.Test;

import java.io.File;
import java.nio.charset.Charset;
import java.util.List;

/**
 * file工具类
 */
public class FilesTest {
	
	private File fileReader = new File(new File("").getAbsoluteFile() + "/src/test/java/com/bin/guava/test/node.txt");
	
	private File fileWriter = new File(new File("").getAbsoluteFile() + "/src/test/java/com/bin/guava/test/node");
	
	private Charset utf8Charset = Charset.forName("UTF-8");
	
	private String lineSeparator = System.getProperty("line.separator", "/n");
	
	@Test
	@Deprecated
	public void testReadLines() throws Exception
	{
		//获取第一行内容
		System.out.println("获取第一行内容:");
		System.out.println(Files.readFirstLine(fileReader, utf8Charset));
		
		//获取所有内容
		System.out.println("\n获取所有内容:");
		List<String> content = Files.readLines(fileReader, utf8Charset);
		for(String str : content)
			System.out.println(str);
		
	}

	/**
	 * Files.readFirstLine(...)已经呗废弃，
	 * 使用Files.asCharSource(...).readFirstLine
	 */
	@Test
	public void testReadLines2() throws Exception
	{
		//获取第一行内容
		System.out.println("获取第一行内容:");
		System.out.println(Files.asCharSource(fileReader, utf8Charset).readFirstLine());

		//获取所有内容
		System.out.println("\n获取所有内容:");
		List<String> content = Files.readLines(fileReader, utf8Charset);
		for(String str : content)
			System.out.println(str);

	}

	@Test
	@Deprecated
	public void testWrite() throws Exception
	{
		String writeContent = "滴答滴" + lineSeparator;
		Files.write(writeContent, fileWriter, utf8Charset);
	}

	/**
	 * Files.write(...)已经被废弃
	 * 使用Files.asCharSink(...).write(...)
	 */
	@Test
	public void testWrite2() throws Exception
	{
		String writeContent = "滴答滴" + lineSeparator;
		Files.asCharSink(fileWriter, utf8Charset).write(writeContent);
	}

	@Test
	@Deprecated
	public void testAppend() throws Exception
	{
		String writeContent = "滴答滴" + lineSeparator;
		Files.append(writeContent, fileWriter, utf8Charset);
	}

	/**
	 * Files.append(...)已经被废弃
	 * 使用Files.asCharSink(...).write(...)
	 */
	@Test
	public void testAppend2() throws Exception
	{
		String writeContent = "滴答滴" + lineSeparator;
		Files.asCharSink(fileWriter, utf8Charset, FileWriteMode.APPEND).write(writeContent);
	}

	@Test
	public void testCopy() throws Exception
	{
		File fileWriterCopy = new File(new File("").getAbsoluteFile() + "/src/test/java/com/bin/guava/test/nodeC");
		Files.copy(fileWriter, fileWriterCopy);
	}
	
	@Test
	public void testCreateTempDir()
	{
		File tempFile = Files.createTempDir();
		System.out.println(tempFile.getAbsolutePath());
	}
	
	@Test
	public void testTouch() throws Exception
	{
		File touchFile = new File("node.txt");
		Files.touch(touchFile);
	}
	
	@Test
	public void testMove() throws Exception
	{
		File from = new File("from.txt");
		File to = new File("to.txt");
		
		Files.move(from, to);
	}
}
