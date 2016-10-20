package com.asiainfo.parser;

import java.util.ArrayList;

import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

class MethodVisitor extends VoidVisitorAdapter {

	private ArrayList<MethodEntity> methodList = new ArrayList<MethodEntity>();

	public void visit(MethodDeclaration n, Object object) {
		MethodEntity entity = new MethodEntity(n.getName(), n.getBeginLine(), n.getEndLine());
		methodList.add(entity);
		super.visit(n, object);
	}

	public ArrayList<MethodEntity> getMethodList() {
		return this.methodList;
	}

}
