package com.bin.fst.test;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @Author shaobin.qin
 * @Date 2017/3/30
 */
@Getter
@Setter
@ToString
public class Teacher implements Serializable{

	private static final long serialVersionUID = 4595442008605944622L;

	private int id;

	private String name;

	private List<Student> students;
}
