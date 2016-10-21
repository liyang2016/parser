package com.asiainfo.parser.visitor;

public class MethodEntity {
	private String name;
	private int beginIndex;
	private int endIndex;

	public MethodEntity() {
	}

	public MethodEntity(String name, int beginIndex, int endIndex) {
		this.name = name;
		this.beginIndex = beginIndex;
		this.endIndex = endIndex;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getBeginIndex() {
		return beginIndex;
	}

	public void setBeginIndex(int beginIndex) {
		this.beginIndex = beginIndex;
	}

	public int getEndIndex() {
		return endIndex;
	}

	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}

	@Override
	public String toString() {
		return "MethodEntity [name=" + name + ", beginIndex=" + beginIndex + ", endIndex=" + endIndex + "]";
	}
	
}
