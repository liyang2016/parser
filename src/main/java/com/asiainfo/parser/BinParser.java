package com.asiainfo.parser;

import java.io.IOException;
import java.util.ArrayList;

import com.asiainfo.parser.entity.MethodEntity;

public class BinParser extends ParserAbstract {
	
	/**
	 * 文件为修改状态时
	 * 将改变的方法信息，放入结果集中
	 */
	protected void modify(String fromFile, ArrayList<Integer> delLinesList, ArrayList<Integer> addLinesList)
			throws IOException {
		ArrayList<MethodEntity> methods = methodsName(fromFile);
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

	/**
	 * 文件为删除或者新增状态时
	 * 即将所有的方法，放入结果集中
	 */
	protected void delOrAdd(String srcFile) throws IOException {
		ArrayList<MethodEntity> entities = methodsName(srcFile);
		removeSame(entities);
		resultMap.put("methods", entities);
	}

	/**
	 * 二分查询
	 * @param methods
	 * @param i
	 * @return
	 */
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
