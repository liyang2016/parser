package com.asiainfo.parser;

import com.github.javaparser.ast.PackageDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

class ClassVisitor extends VoidVisitorAdapter {
	public void visit(ClassOrInterfaceDeclaration declaration, Object object) {
		//System.out.println(declaration.getName());
		super.visit(declaration, object);
	}
}
