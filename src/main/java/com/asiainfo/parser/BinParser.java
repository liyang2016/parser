package com.asiainfo.parser;

import java.io.IOException;
import java.util.ArrayList;

public class BinParser extends Parser {
	public void modify(String fromFile, ArrayList<Integer> delLinesList, ArrayList<Integer> addLinesList)
			throws IOException {
		ArrayList<MethodEntity> methods = methodsName(fromFile);
		// for (MethodEntity methodEntity : methods) {
		// System.out.println(methodEntity);
		// }
		ArrayList<MethodEntity> resultMethods = new ArrayList<MethodEntity>();
		for (Integer i : delLinesList) {
			int index = binarySearch(methods, i);
			if (index >= 0) {
				resultMethods.add(methods.get(index));
			}
		}
		for (Integer i : addLinesList) {
			int index = binarySearch(methods, i);
			if (index >= 0) {
				resultMethods.add(methods.get(index));
			}
		}
		removeSame(resultMethods);
		resultMap.put("methods", resultMethods);
	}

	public void delOrAdd(String srcFile) throws IOException {
		ArrayList<MethodEntity> entities = methodsName(srcFile);
		removeSame(entities);
		resultMap.put("methods", entities);
	}

	private int binarySearch(ArrayList<MethodEntity> methods, int i) {
		int low = 0;
		int high = methods.size() - 1;
		while (low <= high) {
			int middle = (low + high) / 2;
			if (i >= methods.get(middle).getBeginIndex() && i <= methods.get(middle).getEndIndex()) {
				return middle;
			} else if (i < methods.get(middle).getBeginIndex()) {
				high = middle - 1;
			} else {
				low = middle + 1;
			}
		}
		return -1;
	}
}
