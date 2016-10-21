package com.asiainfo.parser.visitor;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

/*
 * 
 */
class ClassVisitor extends VoidVisitorAdapter<Object> {
	public void visit(ClassOrInterfaceDeclaration declaration, Object object) {
		System.out.println(declaration.getName());
		super.visit(declaration, object);
	}
}
