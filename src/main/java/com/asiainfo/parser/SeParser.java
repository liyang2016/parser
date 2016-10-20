package com.asiainfo.parser;

import java.io.IOException;
import java.util.ArrayList;

public class SeParser extends Parser {
	public void modify(String fromFile, ArrayList<Integer> delLinesList, ArrayList<Integer> addLinesList)
			throws IOException {
		ArrayList<MethodEntity> methods = methodsName(fromFile);
		// for (MethodEntity methodEntity : methods) {
		// System.out.println(methodEntity);
		// }
		ArrayList<MethodEntity> resultMethods = new ArrayList<MethodEntity>();
		search(methods, addLinesList, resultMethods);
		search(methods, delLinesList, resultMethods);
		// for (Integer i : delLinesList) {
		// int index = Util.binarySearch(methods, i);
		// if (index >= 0) {
		// resultMethods.add(methods.get(index));
		// }
		// }
		// for (Integer i : addLinesList) {
		// int index = Util.binarySearch(methods, i);
		// if (index >= 0) {
		// resultMethods.add(methods.get(index));
		// }
		// }
		removeSame(resultMethods);
		resultMap.put("methods", resultMethods);
	}

	public void delOrAdd(String srcFile) throws IOException {
		ArrayList<MethodEntity> entities = methodsName(srcFile);
		removeSame(entities);
		resultMap.put("methods", entities);
	}

	private ArrayList<MethodEntity> search(ArrayList<MethodEntity> methods, ArrayList<Integer> linesList,
			ArrayList<MethodEntity> resultMethods) {
		int index = 0;
		int i = 0;
		while (index < linesList.size()) {
			if (linesList.get(index) < methods.get(0).getBeginIndex()) {
				index++;
				continue;
			}
			while (index < linesList.size() && i < methods.size()) {
				if (linesList.get(index) <= methods.get(i).getEndIndex()
						&& linesList.get(index) >= methods.get(i).getBeginIndex()) {
					resultMethods.add(methods.get(i));
					while (index < linesList.size()) {
						if (linesList.get(index) <= methods.get(i).getEndIndex()) {
							index++;
						} else {
							break;
						}
					}
				} else {
					i++;
				}
			}
			index++;
			i = 0;
		}
		return resultMethods;
	}
}
