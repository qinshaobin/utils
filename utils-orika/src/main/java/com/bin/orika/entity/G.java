package com.bin.orika.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author shaobin.qin
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class G {

	private int id;

	private String name;

	private Date birthDay;

	private List<D> dList;

	private Set<D> dSet;

	private Map<String, D> dMap;
}
