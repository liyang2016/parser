package com.asiainfo.parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.asiainfo.parser.visitor.MethodEntity;
import com.asiainfo.parser.visitor.ParserAbstract;

public class OptimizeTest {
	Map<String,Object> map = new HashMap<>();

	@Test
	public void testModifyByBin() throws IOException {
		map.put("fromFileName",
				"source_to_parse/junit-master/src/test/java/org/junit/tests/junit3compatibility/SuiteMethodTest.java");
		map.put("fileType", "modify");
		ArrayList<Integer> delList = new ArrayList<Integer>();
		ArrayList<Integer> addList = new ArrayList<Integer>();
		delList.add(10);
		delList.add(11);
		delList.add(12);
		delList.add(13);
		delList.add(20);
		delList.add(21);
		delList.add(22);
		delList.add(23);
		delList.add(30);
		delList.add(31);
		delList.add(32);
		delList.add(34);
		delList.add(40);
		delList.add(59);
		addList.add(10);
		addList.add(30);
		addList.add(59);
		map.put("delLines", delList);
		map.put("addLines", addList);
		ParserAbstract parserAbstract = new BinParser();
		long startTime = System.currentTimeMillis();
		HashMap<String,Object> hashMap = parserAbstract.parserMd(map);
		long endTime = System.currentTimeMillis();
		ArrayList<MethodEntity> list = (ArrayList<MethodEntity>) hashMap.get("methods");
		for (MethodEntity entity : list) {
			System.out.println(entity.toString());
		}
		String str = (String) hashMap.get("packageName");
		System.out.println(str);
		System.out.println("Time:" + (endTime - startTime));
	}

	@Test
	public void testModifyBySe() throws IOException {
		map.put("fromFileName",
				"source_to_parse/junit-master/src/test/java/org/junit/tests/junit3compatibility/SuiteMethodTest.java");
		map.put("fileType", "modify");
		ArrayList<Integer> delList = new ArrayList<Integer>();
		ArrayList<Integer> addList = new ArrayList<Integer>();
		delList.add(10);
		delList.add(11);
		delList.add(12);
		delList.add(13);
		delList.add(20);
		delList.add(21);
		delList.add(22);
		delList.add(23);
		delList.add(30);
		delList.add(31);
		delList.add(32);
		delList.add(34);
		delList.add(40);
		delList.add(59);
		addList.add(10);
		addList.add(30);
		addList.add(59);
		map.put("delLines", delList);
		map.put("addLines", addList);
		ParserAbstract parserAbstract = new SeParser();
		long startTime = System.currentTimeMillis();
		HashMap<String,Object> hashMap = parserAbstract.parserMd(map);
		long endTime = System.currentTimeMillis();
		ArrayList<MethodEntity> list = (ArrayList<MethodEntity>) hashMap.get("methods");
		for (MethodEntity entity : list) {
			System.out.println(entity.toString());
		}
		String str = (String) hashMap.get("packageName");
		System.out.println(str);
		System.out.println("Time:" + (endTime - startTime));
	}
}
