package com.asiainfo.parser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseException;
import com.github.javaparser.ast.CompilationUnit;

public class ClassNameTest {
	@Test
	public void testClassName() throws ParseException, IOException {
		FileInputStream in = new FileInputStream(
				"source_to_parse/junit-master/src/test/java/org/junit/tests/junit3compatibility/SuiteMethodTest.java");
		CompilationUnit cu;
		try {
			cu = JavaParser.parse(in);
		} finally {
			in.close();
		}
		ClassVisitor classVisitor = new ClassVisitor();
		classVisitor.visit(cu, null);
	}
}
