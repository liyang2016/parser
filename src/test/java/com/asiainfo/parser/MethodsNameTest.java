package com.asiainfo.parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class MethodsNameTest {
	Map map = new HashMap();

	@Test
	public void testDel() throws IOException {
		map.put("fromFileName",
				"source_to_parse/junit-master/src/test/java/org/junit/tests/junit3compatibility/SuiteMethodTest.java");
		map.put("toFileName", null);
		map.put("fileType", "del");
		Parser parser = new SeParser();
		HashMap hashMap = parser.parserMd(map);
		ArrayList<MethodEntity> list = (ArrayList<MethodEntity>) hashMap.get("methods");
		for (MethodEntity entity : list) {
			System.out.println(entity.toString());
		}
		String str = (String) hashMap.get("packageName");
		System.out.println(str);
	}

	@Test
	public void testAdd() throws IOException {
		map.put("fromFileName", null);
		map.put("toFileName",
				"source_to_parse/junit-master/src/test/java/org/junit/tests/junit3compatibility/SuiteMethodTest.java");
		map.put("fileType", "add");
		Parser parser = new SeParser();
		HashMap hashMap = parser.parserMd(map);
		ArrayList<MethodEntity> list = (ArrayList<MethodEntity>) hashMap.get("methods");
		for (MethodEntity entity : list) {
			System.out.println(entity.toString());
		}
		String str = (String) hashMap.get("packageName");
		System.out.println(str);
	}
}