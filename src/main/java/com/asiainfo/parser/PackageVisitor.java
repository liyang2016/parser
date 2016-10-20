package com.asiainfo.parser;

import com.github.javaparser.ast.PackageDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

class PackageVisitor extends VoidVisitorAdapter {
	private String packageName;

	public void visit(PackageDeclaration declaration, Object object) {
		packageName = declaration.getName().toString();
		super.visit(declaration, object);
	}

	public String getPackageName() {
		return this.packageName;
	}
}
