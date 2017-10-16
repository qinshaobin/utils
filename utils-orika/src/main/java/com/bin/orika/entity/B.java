package com.bin.orika.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * @author shaobin.qin
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class B {

	private int id;

	private String nameB;

	private Date birthDayB;
}
