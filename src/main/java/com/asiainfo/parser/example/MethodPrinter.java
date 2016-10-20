package com.asiainfo.parser.example;

import java.io.FileInputStream;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class MethodPrinter {
	public static void main(String[] args) throws Exception {
		// creates an input stream for the file to be parsed
		FileInputStream in = new FileInputStream("resource/test.java");

		CompilationUnit cu;
		try {
			// parse the file
			cu = JavaParser.parse(in);
		} finally {
			in.close();
		}

		// visit and print the methods names
		new MethodVisitor().visit(cu, null);
	}

	/**
	 * Simple visitor implementation for visiting MethodDeclaration nodes.
	 */
	private static class MethodVisitor extends VoidVisitorAdapter<Object> {

		@Override
		public void visit(MethodDeclaration n, Object arg) {
			// here you can access the attributes of the method.
			// this method will be called for all methods in this
			// CompilationUnit, including inner class methods
			System.out.println(n.getName());
			// System.out.println(n.getBeginLine());
			// System.out.println(n.getDeclarationAsString());
			// System.out.println(n.getEndLine());
			// System.out.println(n.getBody());
			System.out.println(n.toString());
			System.out.println();
			super.visit(n, arg);
		}
	}
}
