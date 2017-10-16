package com.bin.fst.test;

import com.google.common.collect.Lists;
import org.junit.Test;
import org.nustaq.serialization.FSTConfiguration;

import java.util.Date;
import java.util.List;

/**
 * @Author shaobin.qin
 * @Date 2017/3/30
 */
public class FstTest {

	private FSTConfiguration fstConfiguration = FSTConfiguration.createStructConfiguration();

	@Test
	public void testSerialize()
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

		byte[] bytes = fstConfiguration.asByteArray(teacher);

		teacher = (Teacher)fstConfiguration.asObject(bytes);
		System.out.println(teacher.getStudents().get(0));
		//System.out.println(teacher);
	}
}
