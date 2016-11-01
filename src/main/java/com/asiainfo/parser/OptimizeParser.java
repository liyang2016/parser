package com.asiainfo.parser;

import java.io.IOException;
import java.util.ArrayList;

import com.asiainfo.parser.entity.MethodEntity;

/**
 * 利用类中的search方法，寻找被修改的方法，放入结果集中
 * @author Administrator
 *
 */
public class OptimizeParser extends ParserAbstract {
	
	/**
	 * 文件为修改状态时
	 * 将改变的方法信息，放入结果集中
	 */
	protected void modify(String fromFile, ArrayList<Integer> delLinesList, ArrayList<Integer> addLinesList)
			throws IOException {
		ArrayList<MethodEntity> methods = methodsName(fromFile);
		ArrayList<MethodEntity> resultMethods = new ArrayList<MethodEntity>();
		search(methods, addLinesList, resultMethods);
		search(methods, delLinesList, resultMethods);
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

	private ArrayList<MethodEntity> search(ArrayList<MethodEntity> methods, ArrayList<Integer> linesList,
			ArrayList<MethodEntity> resultMethods) {
		int index = 0;
		int i = 0;
		
		//遍历linesList列表
		while (index < linesList.size()) {
			//判断linesList中的参数，如果参数小于第一个方法体的开始行数，直接跳过，不执行后面的操作
			if (linesList.get(index) < methods.get(0).getBeginIndex()) {
				index++;
				continue;
			}
			
			//遍历方法体的列表
			while (index < linesList.size() && i < methods.size()) {
				
				//如果修改行数在方法体中
				if (linesList.get(index) <= methods.get(i).getEndIndex()
						&& linesList.get(index) >= methods.get(i).getBeginIndex()) {
					resultMethods.add(methods.get(i));
					//继续判断修改行数列表的下一个值，仍然在当前函数体中，就执行下一个参数
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
