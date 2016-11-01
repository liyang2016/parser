package com.asiainfo.parser.visitor;

import java.util.ArrayList;

import com.asiainfo.parser.entity.MethodEntity;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class MethodVisitor extends VoidVisitorAdapter<Object> {

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
