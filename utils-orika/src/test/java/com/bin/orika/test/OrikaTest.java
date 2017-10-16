package com.bin.orika.test;

import com.alibaba.fastjson.JSON;
import com.bin.orika.entity.A;
import com.bin.orika.entity.B;
import com.bin.orika.entity.C;
import com.bin.orika.entity.D;
import com.bin.orika.entity.E;
import com.bin.orika.entity.F;
import com.bin.orika.entity.G;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.metadata.TypeBuilder;
import org.junit.Test;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author shaobin.qin
 */
public class OrikaTest {

	/**
	 * javabean互转
	 * 属性名相同不用映射、属性名不同，需做映射
	 */
	@Test
	public void testBean2Bean() {

		MapperFactory factory = new DefaultMapperFactory.Builder().build();
		factory.registerClassMap(factory.classMap(A.class, B.class)
				.mapNulls(false)
				.mapNullsInReverse(false)
				// 属性名不同，需做映射
				.field("nameA", "nameB")
				.field("birthDayA", "birthDayB")
				.byDefault()
				.toClassMap());
		MapperFacade mapperFacade = factory.getMapperFacade();

		A a = A.builder().id(1).nameA("bin").birthDayA(new Date()).build();
		System.err.println(JSON.toJSONString(a));

		B b = mapperFacade.map(a, B.class);
		System.err.println(JSON.toJSONString(b));
	}

	/**
	 * array互转
	 */
	@Test
	public void testArray2Array() {

		MapperFactory factory = new DefaultMapperFactory.Builder().build();
		factory.registerClassMap(factory.classMap(C.class, D.class).byDefault().toClassMap());
		MapperFacade mapperFacade = factory.getMapperFacade();

		int length = 5;
		C[] cArray = new C[length];
		for(int i=0; i<length; i++)
			cArray[i] = (C.builder().id(i).name("name" + i).birthDay(new Date()).build());

		for(C c : cArray)
			System.err.println(JSON.toJSONString(c));

		System.err.println("----------mapAsArray-----------");

		D[] dArray = new D[length];
		mapperFacade.mapAsArray(dArray, cArray, D.class);
		for(D d : dArray)
			System.err.println(JSON.toJSONString(d));
	}

	/**
	 * list互转
	 */
	@Test
	public void testList2List() {

		MapperFactory factory = new DefaultMapperFactory.Builder().build();
		factory.registerClassMap(factory.classMap(C.class, D.class).byDefault().toClassMap());
		MapperFacade mapperFacade = factory.getMapperFacade();

		List<C> cList = Lists.newArrayList();
		for(int i=0; i<5; i++)
			cList.add(C.builder().id(i).name("name" + i).birthDay(new Date()).build());
		cList.forEach(c -> System.err.println(JSON.toJSONString(c)));

		System.err.println("----------mapAsList-----------");

		List<D> dList = mapperFacade.mapAsList(cList, D.class);
		dList.forEach(d -> System.err.println(JSON.toJSONString(d)));
	}

	/**
	 * set互转
	 */
	@Test
	public void testSet2Set() {

		MapperFactory factory = new DefaultMapperFactory.Builder().build();
		factory.registerClassMap(factory.classMap(C.class, D.class).byDefault().toClassMap());
		MapperFacade mapperFacade = factory.getMapperFacade();

		Set<C> cSet = Sets.newHashSet();
		for(int i=0; i<5; i++)
			cSet.add(C.builder().id(i).name("name" + i).birthDay(new Date()).build());
		cSet.forEach(c -> System.err.println(JSON.toJSONString(c)));

		System.err.println("----------mapAsSet-----------");

		Set<D> dSet = mapperFacade.mapAsSet(cSet, D.class);
		dSet.forEach(d -> System.err.println(JSON.toJSONString(d)));
	}

	/**
	 * map互转
	 */
	@Test
	public void testMap2Map() {

		MapperFactory factory = new DefaultMapperFactory.Builder().build();
		factory.registerClassMap(factory.classMap(C.class, D.class).byDefault().toClassMap());
		MapperFacade mapperFacade = factory.getMapperFacade();

		Map<String, C> cMap = Maps.newHashMap();
		for(int i=0; i<5; i++)
			cMap.put(i + "", C.builder().id(i).name("name" + i).birthDay(new Date()).build());
		cMap.entrySet().forEach(c -> System.err.println(JSON.toJSONString(c)));

		System.err.println("----------mapAsMap-----------");

		Map<String, D> dMap = mapperFacade.mapAsMap(cMap, new TypeBuilder<Map<String, C>>(){}.build(),
				new TypeBuilder<Map<String, D>>(){}.build());
		dMap.entrySet().forEach(d -> System.err.println(JSON.toJSONString(d)));
	}

	/**
	 * 复杂bean互转
	 * 属性名和类型相同
	 */
	@Test
	public void testComplexBean() {

		MapperFactory factory = new DefaultMapperFactory.Builder().build();
		factory.registerClassMap(factory.classMap(E.class, F.class).byDefault().toClassMap());
		MapperFacade mapperFacade = factory.getMapperFacade();

		E e = E.builder().id(1).name("bin").birthDay(new Date()).build();

		List<C> cList = Lists.newArrayList();
		for(int i=0; i<5; i++)
			cList.add(C.builder().id(i).name("name" + i).birthDay(new Date()).build());
		e.setCList(cList);

		Set<C> cSet = Sets.newHashSet();
		for(int i=0; i<5; i++)
			cSet.add(C.builder().id(i).name("name" + i).birthDay(new Date()).build());
		e.setCSet(cSet);

		Map<String, C> cMap = Maps.newHashMap();
		for(int i=0; i<5; i++)
			cMap.put(i + "", C.builder().id(i).name("name" + i).birthDay(new Date()).build());
		e.setCMap(cMap);
		System.err.println(JSON.toJSONString(e));

		System.err.println("----------testComplexBean-----------");

		F f = mapperFacade.map(e, F.class);
		System.err.println(JSON.toJSONString(f));
	}

	/**
	 * 复杂bean互转
	 * 属性名和类型都不同
	 */
	@Test
	public void testComplexBean2() {

		MapperFactory factory = new DefaultMapperFactory.Builder().build();
		factory.registerClassMap(factory.classMap(E.class, G.class)
				.field("cList", "dList")
				.field("cSet", "dSet")
				.field("cMap", "dMap")
				.byDefault().toClassMap());
		factory.registerClassMap(factory.classMap(C.class, D.class).byDefault().toClassMap());
		MapperFacade mapperFacade = factory.getMapperFacade();

		E e = E.builder().id(1).name("bin").birthDay(new Date()).build();

		List<C> cList = Lists.newArrayList();
		for(int i=0; i<5; i++)
			cList.add(C.builder().id(i).name("name" + i).birthDay(new Date()).build());
		e.setCList(cList);

		Set<C> cSet = Sets.newHashSet();
		for(int i=0; i<5; i++)
			cSet.add(C.builder().id(i).name("name" + i).birthDay(new Date()).build());
		e.setCSet(cSet);

		Map<String, C> cMap = Maps.newHashMap();
		for(int i=0; i<5; i++)
			cMap.put(i + "", C.builder().id(i).name("name" + i).birthDay(new Date()).build());
		e.setCMap(cMap);
		System.err.println(JSON.toJSONString(e));

		System.err.println("----------testComplexBean2-----------");

		// TODO 无法赋值
		G g = mapperFacade.map(e, G.class);
		System.err.println(JSON.toJSONString(g));
	}
}

