package com.bin.kryo.test;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;
import org.objenesis.strategy.StdInstantiatorStrategy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;

/**
 * @Author shaobin.qin
 * @Date 2017/3/30
 */
public class KryoTest {

	private Kryo kryo;

	@Before
	public void init()
	{
		kryo = new Kryo();
		kryo.setReferences(true);
		kryo.setRegistrationRequired(false);
		kryo.setInstantiatorStrategy(new StdInstantiatorStrategy());
		kryo.register(Teacher.class);
		kryo.register(Student.class);
	}

	@Test
	public void testSerialize() throws Exception
	{
		Teacher teacher = new Teacher();
		teacher.setId(1);
		teacher.setName("qin");

		List<Student> students = Lists.newArrayList();
		for (int i = 0; i < 10; i++) {
			Student student = new Student();
			student.setId(i);
			student.setName("name" + i);
			student.setBirthDay(new Date());
			students.add(student);
		}
		teacher.setStudents(students);

		File file = new File("text.txt");
		if(!file.exists())
			file.createNewFile();
		Output output = new Output(new FileOutputStream(file));

		kryo.writeObject(output, teacher);
		output.flush();
		output.close();
	}

	@Test
	public void testDeserialize() throws Exception
	{
		File file = new File("text.txt");
		if(!file.exists())
			file.createNewFile();
		Input input = new Input(new FileInputStream(file));
		Teacher teacher = kryo.readObject(input, Teacher.class);

		System.out.println(teacher);
	}
}
