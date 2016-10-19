package com.asiainfo.parser;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseException;
import com.github.javaparser.ast.CompilationUnit;

public class Parser {
	HashMap resultMap = new HashMap<>();

	private ArrayList<MethodEntity> methodsName(String file) throws IOException {
		// String basePath = this.getClass().getResource("/").getPath();
		// FileInputStream in = new FileInputStream(
		// "source_to_parse/junit-master/src/test/java/org/junit/tests/junit3compatibility/SuiteMethodTest.java");
		FileInputStream in = new FileInputStream(file);
		CompilationUnit unit = null;
		try {
			unit = JavaParser.parse(in);
		} catch (ParseException e) {
			e.printStackTrace();
		} finally {
			in.close();
		}
		MethodVisitor methodVisitor = new MethodVisitor();
		methodVisitor.visit(unit, null);
		resultMap.put("methods", methodVisitor.getMethodList());

		PackageVisitor packageVisitor = new PackageVisitor();
		packageVisitor.visit(unit, null);
		resultMap.put("packageName", packageVisitor.getPackageName());

		ClassVisitor classVisitor = new ClassVisitor();
		classVisitor.visit(unit, null);
		return methodVisitor.getMethodList();
	}

	private void modify(String fromFile, ArrayList<Integer> delLinesList, ArrayList<Integer> addLinesList)
			throws IOException {
		ArrayList<MethodEntity> methods = methodsName(fromFile);
		ArrayList<MethodEntity> resultMethods = new ArrayList<MethodEntity>();
		for (Integer i : delLinesList) {
			int index = binarySearch(methods, i);
			if (index >= 0 && index < methods.size()) {
				resultMethods.add(methods.get(index));
			}
		}
		for (Integer i : addLinesList) {
			int index = binarySearch(methods, i);
			if (index >= 0 && index < methods.size()) {
				resultMethods.add(methods.get(index));
			}
		}
		removeSame(resultMethods);
		resultMap.put("methods", resultMethods);
	}

	private void delOrAdd(String srcFile) throws IOException {
		ArrayList<MethodEntity> entities = methodsName(srcFile);
		removeSame(entities);
		resultMap.put("methods", entities);
	}

	public HashMap parserOutPut(Map map) throws IOException {
		if (map.get("fileType").equals("del")) {
			delOrAdd((String) map.get("fromFileName"));
		} else if (map.get("fileType").equals("add")) {
			delOrAdd((String) map.get("toFileName"));
		} else {
			modify((String) map.get("fromFileName"), (ArrayList<Integer>) map.get("delLines"),
					(ArrayList<Integer>) map.get("addLines"));
		}
		return resultMap;
	}

	private int binarySearch(ArrayList<MethodEntity> methods, int i) {
		int low = 0;
		int high = methods.size() - 1;
		while (low <= high) {
			int middle = (low + high) / 2;
			if (i >= methods.get(middle).getBeginIndex() && i <= methods.get(middle).getEndIndex()) {
				return middle;
			} else if (i < methods.get(middle).getBeginIndex()) {
				high = middle - 1;
			} else {
				low = middle + 1;
			}
		}
		return -1;
	}

	private void removeSame(ArrayList<MethodEntity> methods) {
		Set<MethodEntity> set = new LinkedHashSet<MethodEntity>();
		set.addAll(methods);
		methods.clear();
		methods.addAll(set);
	}
}
