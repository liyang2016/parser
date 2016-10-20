package com.asiainfo.parser;

import java.io.FileInputStream;
import java.util.ArrayList;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class Main {

	public static void main(String[] args) throws Exception {
		// creates an input stream for the file to be parsed
		FileInputStream in = new FileInputStream(
				"source_to_parse/junit-master/src/test/java/org/junit/tests/junit3compatibility/SuiteMethodTest.java");

		CompilationUnit cu;
		try {
			// parse the file
			cu = JavaParser.parse(in);
		} finally {
			in.close();
		}
//		MethodVisitor methodVisitor = new MethodVisitor();
//		// visit and print the methods names
//		methodVisitor.visit(cu, null);
//		ArrayList<String> list = methodVisitor.getList();
//		for (String string : list) {
//			System.out.println(string);
//		}
		ClassVisitor classVisitor=new ClassVisitor();
		classVisitor.visit(cu, null);
	}

	/**
	 * Simple visitor implementation for visiting MethodDeclaration nodes.
	 */
	private static class MethodVisitor extends VoidVisitorAdapter<Object> {
		private ArrayList<String> list = new ArrayList<String>();

		@Override
		public void visit(MethodDeclaration n, Object obj) {
			// here you can access the attributes of the method.
			// this method will be called for all methods in this
			// CompilationUnit, including inner class methods

			list.add(n.getName());
			super.visit(n, null);
		}

		public ArrayList<String> getList() {
			return this.list;
		}
		
		private static class ClassVisitor extends VoidVisitorAdapter<Object> {
			@Override
			public void visit(ClassOrInterfaceDeclaration declaration, Object object) {
				System.out.println(declaration.getClass());
				super.visit(declaration, object);
			}
		}
	}
}
