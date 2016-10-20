package com.asiainfo.parser;

import java.util.ArrayList;

class Util {
	public static int binarySearch(ArrayList<MethodEntity> methods, int i) {
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

	public static ArrayList<MethodEntity> search(ArrayList<MethodEntity> methods, ArrayList<Integer> linesList,
			ArrayList<MethodEntity> resultMethods) {
		int index = 0;
		int i = 0;
		while (index < linesList.size()) {
			if(linesList.get(index)<methods.get(0).getBeginIndex()){
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
