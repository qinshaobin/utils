package com.bin.fst.test;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author shaobin.qin
 * @Date 2017/3/30
 */
@Getter
@Setter
//@ToString
public class Student implements Serializable{

	private static final long serialVersionUID = 5807888749489817116L;

	private int id;

	private String name;

	private Date birthDay;
}
