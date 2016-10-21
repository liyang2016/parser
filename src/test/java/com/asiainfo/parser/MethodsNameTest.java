package com.asiainfo.parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.asiainfo.parser.visitor.MethodEntity;
import com.asiainfo.parser.visitor.ParserAbstract;

public class MethodsNameTest {
	Map<String,Object> map = new HashMap<>();

	@Test
	public void testDel() throws IOException {
		map.put("fromFileName",
				"source_to_parse/junit-master/src/test/java/org/junit/tests/junit3compatibility/SuiteMethodTest.java");
		map.put("toFileName", null);
		map.put("fileType", "del");
		ParserAbstract parserAbstract = new SeParser();
		HashMap<String,Object> hashMap = parserAbstract.parserMd(map);
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
		ParserAbstract parserAbstract = new SeParser();
		HashMap<String,Object> hashMap = parserAbstract.parserMd(map);
		ArrayList<MethodEntity> list = (ArrayList<MethodEntity>) hashMap.get("methods");
		for (MethodEntity entity : list) {
			System.out.println(entity.toString());
		}
		String str = (String) hashMap.get("packageName");
		System.out.println(str);
	}
}
