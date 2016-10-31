package com.asiainfo.parser.visitor;

import java.util.ArrayList;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

/*
 * 
 */
class ClassVisitor extends VoidVisitorAdapter<Object> {
	private ArrayList<String> classList = new ArrayList<String>();

	public void visit(ClassOrInterfaceDeclaration declaration, Object object) {
		classList.add(declaration.getName());
		super.visit(declaration, object);
	}
	
	public ArrayList<String> getClassList() {
		return classList;
	}
}
