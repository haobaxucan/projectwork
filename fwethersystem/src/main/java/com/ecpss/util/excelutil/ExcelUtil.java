/**
 * Project Name:excelutil
 * File Name:ExcelUtil.java
 * Package Name:com.lkx.util
 * Date:2017年6月7日上午9:44:58
 * Copyright (c) 2017~2020, 934268568@qq.com All Rights Reserved.
 *
 */

package com.ecpss.util.excelutil;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanUtils;

/**
 * ClassName:ExcelUtil Function: excel快速读取、写入公共类
 * Date: 2018年7月5日 上午9:44:58
 * @author likaixuan
 * @version V1.0
 * @since JDK 1.8
 */
public class ExcelUtil implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	/**
	 * 把传进指定格式的字符串解析到Map中
	 * @param keyValue
	 * @return
	 */
	public static Map<String, String> getMap(String keyValue) {

		Map<String, String> map = new HashMap<String, String>();
		if (keyValue != null) {
			String[] str = keyValue.split(",");
			for (String element : str) {
				String[] str2 = element.split(":");
				map.put(str2[0], str2[1]);
			}
		}
		return map;
	}
	
	
	/**
	 * 把传进指定格式的字符串解析到List中
	 * @param keyValue
	 * @return
	 */
	public static List<String> getList(String keyValue) {

		List<String> list = new ArrayList<String>();
		if (keyValue != null) {
			String[] str = keyValue.split(",");

			for (String element : str) {
				String[] str2 = element.split(":");
				list.add(str2[0]);
			}
		}
		return list;
	}
	
	/**
	 * 读取不完全匹配模版
	 * @param filePath
	 * @param map
	 * @param classPath
	 * @param rowNumIndex
	 * @param <T>
	 * @return
	 * @throws Exception
	 */
	public static <T> List<T> readXlsPart(String filePath, Map map, String classPath, int... rowNumIndex)
			throws Exception {

		Set keySet = map.keySet();// 返回键的集合

		/** 反射用 **/
		Class<?> demo = null;
		Object obj = null;
		/** 反射用 **/

		List<Object> list = new ArrayList<Object>();
		demo = Class.forName(classPath);
		String fileType = filePath.substring(filePath.lastIndexOf(".") + 1, filePath.length());
		InputStream is = new FileInputStream(filePath);
		Workbook wb = null;

		if (fileType.equals("xls")) {
			wb = new HSSFWorkbook(is);
		} else if (fileType.equals("xlsx")) {
			wb = new XSSFWorkbook(is);
		} else {
			throw new Exception("您输入的excel格式不正确");
		}
		for (int sheetNum = 0; sheetNum < 1; sheetNum++) {// 获取每个Sheet表

			int rowNum_x = -1;// 记录第x行为表头
			Map<String, Integer> cellmap = new HashMap<String, Integer>();// 存放每一个field字段对应所在的列的序号
			List<String> headlist = new ArrayList();// 存放所有的表头字段信息

			Sheet hssfSheet = wb.getSheetAt(sheetNum);

			// 设置默认最大行为50w行
			if (hssfSheet != null && hssfSheet.getLastRowNum() > 500000) {
				throw new Exception("Excel 数据超过50w行,请检查是否有空行,或分批导入");
			}

			// 循环行Row
			for (int rowNum = 0; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {

				if (rowNumIndex != null && rowNumIndex.length > 0 && rowNum_x == -1) {// 如果传值指定从第几行开始读，就从指定行寻找，否则自动寻找
					Row hssfRow = hssfSheet.getRow(rowNumIndex[0]);
					if (hssfRow == null) {
						throw new RuntimeException("指定的行为空，请检查");
					}
					rowNum = rowNumIndex[0] - 1;
				}
				Row hssfRow = hssfSheet.getRow(rowNum);
				if (hssfRow == null) {
					continue;
				}
				boolean flag = false;
				for (int i = 0; i < hssfRow.getLastCellNum(); i++) {
					if (hssfRow.getCell(i) != null && !("").equals(hssfRow.getCell(i).toString().trim())) {
						flag = true;
					}
				}
				if (!flag) {
					continue;
				}

				if (rowNum_x == -1) {
					// 循环列Cell
					for (int cellNum = 0; cellNum <= hssfRow.getLastCellNum(); cellNum++) {

						Cell hssfCell = hssfRow.getCell(cellNum);
						if (hssfCell == null) {
							continue;
						}

						String tempCellValue = hssfSheet.getRow(rowNum).getCell(cellNum).getStringCellValue();

						tempCellValue = StringUtils.remove(tempCellValue, (char) 160);
						tempCellValue = tempCellValue.trim();

						headlist.add(tempCellValue);

						Iterator it = keySet.iterator();

						while (it.hasNext()) {
							Object key = it.next();
							if (StringUtils.isNotBlank(tempCellValue)
									&& StringUtils.equals(tempCellValue, key.toString())) {
								rowNum_x = rowNum;
								cellmap.put(map.get(key).toString(), cellNum);
							}
						}
						if (rowNum_x == -1) {
							throw new Exception("没有找到对应的字段或者对应字段行上面含有不为空白的行字段");
						}
					}

				} else {
					obj = demo.newInstance();
					Iterator it = keySet.iterator();
					while (it.hasNext()) {
						Object key = it.next();
						Integer cellNum_x = cellmap.get(map.get(key).toString());
						if (cellNum_x == null || hssfRow.getCell(cellNum_x) == null) {
							continue;
						}
						String attr = map.get(key).toString();// 得到属性

						Class<?> attrType = BeanUtils.findPropertyType(attr, new Class[] { obj.getClass() });

						Cell cell = hssfRow.getCell(cellNum_x);
						getValue(cell, obj, attr, attrType, rowNum, cellNum_x, key);

					}
					list.add(obj);
				}

			}
		}
		is.close();
		// wb.close();
		return (List<T>) list;
	}
	
	
	/**
	 * 读取完全匹配模版
	 * @param filePath
	 * @param map
	 * @param classPath
	 * @param rowNumIndex
	 * @param <T>
	 * @return
	 * @throws Exception
	 */
	public static <T> List<T> readXls(String filePath, Map map, String classPath, int... rowNumIndex) throws Exception {

		Set keySet = map.keySet();// 返回键的集合

		/** 反射用 **/
		Class<?> demo = null;
		Object obj = null;
		/** 反射用 **/

		List<Object> list = new ArrayList<Object>();
		demo = Class.forName(classPath);
		String fileType = filePath.substring(filePath.lastIndexOf(".") + 1, filePath.length());
		InputStream is = new FileInputStream(filePath);
		Workbook wb = null;

		if (fileType.equals("xls")) {
			wb = new HSSFWorkbook(is);
		} else if (fileType.equals("xlsx")) {
			wb = new XSSFWorkbook(is);
		} else {
			throw new Exception("您输入的excel格式不正确");
		}
		// 默认循环所有sheet，如果rowNumIndex[]
		for (int sheetNum = 0; sheetNum < wb.getNumberOfSheets(); sheetNum++) {// 获取每个Sheet表

			int rowNum_x = -1;// 记录第x行为表头
			Map<String, Integer> cellmap = new HashMap<String, Integer>();// 存放每一个field字段对应所在的列的序号
			List<String> headlist = new ArrayList();// 存放所有的表头字段信息

			Sheet hssfSheet = wb.getSheetAt(sheetNum);

			// 循环行Row
			for (int rowNum = 0; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
				if (rowNumIndex != null && rowNumIndex.length > 0 && rowNum_x == -1) {// 如果传值指定从第几行开始读，就从指定行寻找，否则自动寻找
					Row hssfRow = hssfSheet.getRow(rowNumIndex[0]);
					if (hssfRow == null) {
						throw new RuntimeException("指定的行为空，请检查");
					}
					rowNum = rowNumIndex[0] - 1;
				}
				Row hssfRow = hssfSheet.getRow(rowNum);
				if (hssfRow == null) {
					continue;
				}
				boolean flag = false;
				for (int i = 0; i < hssfRow.getLastCellNum(); i++) {
					if (hssfRow.getCell(i) != null && !("").equals(hssfRow.getCell(i).toString().trim())) {
						flag = true;
					}
				}
				if (!flag) {
					continue;
				}

				if (rowNum_x == -1) {
					// 循环列Cell
					for (int cellNum = 0; cellNum <= hssfRow.getLastCellNum(); cellNum++) {

						Cell hssfCell = hssfRow.getCell(cellNum);
						if (hssfCell == null) {
							continue;
						}

						String tempCellValue = hssfSheet.getRow(rowNum).getCell(cellNum).getStringCellValue();

						tempCellValue = StringUtils.remove(tempCellValue, (char) 160);
						tempCellValue = tempCellValue.trim();

						headlist.add(tempCellValue);
						Iterator it = keySet.iterator();
						while (it.hasNext()) {
							Object key = it.next();
							if (StringUtils.isNotBlank(tempCellValue)
									&& StringUtils.equals(tempCellValue, key.toString())) {
								rowNum_x = rowNum;
								cellmap.put(map.get(key).toString(), cellNum);
							}
						}
						if (rowNum_x == -1) {
							throw new Exception("没有找到对应的字段或者对应字段行上面含有不为空白的行字段");
						}
					}

					// 读取到列后，检查表头是否完全一致--start
					for (int i = 0; i < headlist.size(); i++) {
						boolean boo = false;
						Iterator itor = keySet.iterator();
						while (itor.hasNext()) {
							String tempname = itor.next().toString();
							if (tempname.equals(headlist.get(i))) {
								boo = true;
							}
						}
						if (boo == false) {
							throw new Exception("表头字段和定义的属性字段不匹配，请检查");
						}
					}

					Iterator itor = keySet.iterator();
					while (itor.hasNext()) {
						boolean boo = false;
						String tempname = itor.next().toString();
						for (int i = 0; i < headlist.size(); i++) {
							if (tempname.equals(headlist.get(i))) {
								boo = true;
							}
						}
						if (boo == false) {
							throw new Exception("表头字段和定义的属性字段不匹配，请检查");
						}
					}
					// 读取到列后，检查表头是否完全一致--end

				} else {
					obj = demo.newInstance();
					Iterator it = keySet.iterator();
					while (it.hasNext()) {
						Object key = it.next();
						Integer cellNum_x = cellmap.get(map.get(key).toString());
						if (cellNum_x == null || hssfRow.getCell(cellNum_x) == null) {
							continue;
						}
						String attr = map.get(key).toString();// 得到属性

						Class<?> attrType = BeanUtils.findPropertyType(attr, new Class[] { obj.getClass() });

						Cell cell = hssfRow.getCell(cellNum_x);
						getValue(cell, obj, attr, attrType, rowNum, cellNum_x, key);

					}
					list.add(obj);
				}

			}
		}
		is.close();
		// wb.close();
		return (List<T>) list;
	}
	
	/**
	 * 读取不完全匹配模版
	 * @param param
	 * @param <T>
	 * @return
	 * @throws Exception
	 */
	public static <T> List<T> readXlsPart(ExcelParam param) throws Exception {

		Set keySet = param.getMap().keySet();// 返回键的集合

		/** 反射用 **/
		Class<?> demo = null;
		Object obj = null;
		/** 反射用 **/

		List<Object> list = new ArrayList<Object>();
		demo = Class.forName(param.getClassPath());
		String fileType = param.getFilePath().substring(param.getFilePath().lastIndexOf(".") + 1,
				param.getFilePath().length());
		InputStream is = new FileInputStream(param.getFilePath());
		Workbook wb = null;

		if (ExcelTypeEnum.EXCEL_THREE.getText().equals(fileType)) {
			wb = new HSSFWorkbook(is);
		} else if (ExcelTypeEnum.EXCEL_SEVEN.getText().equals(fileType)) {
			wb = new XSSFWorkbook(is);
		} else {
			throw new Exception("您输入的excel格式不正确");
		}
		int startSheetNum = 0;
		int endSheetNum = 1;
		if (null != param.getSheetIndex()) {
			startSheetNum = param.getSheetIndex() - 1;
			endSheetNum = param.getSheetIndex();
		}
		for (int sheetNum = startSheetNum; sheetNum < endSheetNum; sheetNum++) {// 获取每个Sheet表

			int rowNum_x = -1;// 记录第x行为表头
			Map<String, Integer> cellmap = new HashMap<String, Integer>();// 存放每一个field字段对应所在的列的序号
			List<String> headlist = new ArrayList();// 存放所有的表头字段信息

			Sheet hssfSheet = wb.getSheetAt(sheetNum);

			// 设置默认最大行为2w行
			if (hssfSheet != null && hssfSheet.getLastRowNum() > 60000) {
				throw new Exception("Excel 数据超过60000行,请检查是否有空行,或分批导入");
			}

			// 循环行Row
			for (int rowNum = 0; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {

				if (param.getRowNumIndex() != null && rowNum_x == -1) {// 如果传值指定从第几行开始读，就从指定行寻找，否则自动寻找
					Row hssfRow = hssfSheet.getRow(param.getRowNumIndex());
					if (hssfRow == null) {
						throw new RuntimeException("指定的行为空，请检查");
					}
					rowNum = param.getRowNumIndex() - 1;
				}
				Row hssfRow = hssfSheet.getRow(rowNum);
				if (hssfRow == null) {
					continue;
				}
				boolean flag = false;
				for (int i = 0; i < hssfRow.getLastCellNum(); i++) {
					if (hssfRow.getCell(i) != null && !("").equals(hssfRow.getCell(i).toString().trim())) {
						flag = true;
					}
				}
				if (!flag) {
					continue;
				}

				if (rowNum_x == -1) {
					// 循环列Cell
					for (int cellNum = 0; cellNum <= hssfRow.getLastCellNum(); cellNum++) {

						Cell hssfCell = hssfRow.getCell(cellNum);
						if (hssfCell == null) {
							continue;
						}

						String tempCellValue = hssfSheet.getRow(rowNum).getCell(cellNum).getStringCellValue();

						tempCellValue = StringUtils.remove(tempCellValue, (char) 160);
						tempCellValue = tempCellValue.trim();

						headlist.add(tempCellValue);

						Iterator it = keySet.iterator();

						while (it.hasNext()) {
							Object key = it.next();
							if (StringUtils.isNotBlank(tempCellValue)
									&& StringUtils.equals(tempCellValue, key.toString())) {
								rowNum_x = rowNum;
								cellmap.put(param.getMap().get(key).toString(), cellNum);
							}
						}
						if (rowNum_x == -1) {
							throw new Exception("没有找到对应的字段或者对应字段行上面含有不为空白的行字段");
						}
					}

				} else {
					obj = demo.newInstance();
					Iterator it = keySet.iterator();
					while (it.hasNext()) {
						Object key = it.next();
						Integer cellNum_x = cellmap.get(param.getMap().get(key).toString());
						if (cellNum_x == null || hssfRow.getCell(cellNum_x) == null) {
							continue;
						}
						String attr = param.getMap().get(key).toString();// 得到属性

						Class<?> attrType = BeanUtils.findPropertyType(attr, new Class[] { obj.getClass() });

						Cell cell = hssfRow.getCell(cellNum_x);
						getValue(cell, obj, attr, attrType, rowNum, cellNum_x, key);

					}
					list.add(obj);
				}

			}
		}
		is.close();
		// wb.close();
		return (List<T>) list;
	}

	public static void setter(Object obj, String att, Object value, Class<?> type, int row, int col, Object key)
			throws Exception {
		try {
			Method method = obj.getClass().getMethod("set" + StringUtil.toUpperCaseFirstOne(att), type);
			method.invoke(obj, value);
		} catch (Exception e) {
			throw new Exception("第" + (row + 1) + " 行  " + (col + 1) + "列   属性：" + key + " 赋值异常  " + e);
		}
	}


	public static Object getAttrVal(Object obj, String att, Class<?> type) throws Exception {
		try {
			Method method = obj.getClass().getMethod("get" + StringUtil.toUpperCaseFirstOne(att));
			Object value = method.invoke(obj);
			return value;
		} catch (Exception e) {
			return null;
		}
	}
	
	
	/**
	 * 得到Excel列的值
	 * @param cell
	 * @param obj
	 * @param attr
	 * @param attrType
	 * @param row
	 * @param col
	 * @param key
	 * @throws Exception
	 */
	public static void getValue(Cell cell, Object obj, String attr, Class attrType, int row, int col, Object key)
			throws Exception {
		Object val = null;

		if (cell.getCellType() == CellType.BOOLEAN) {
			val = cell.getBooleanCellValue();

		} else if (cell.getCellType() == CellType.NUMERIC) {
			if (DateUtil.isCellDateFormatted(cell)) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				try {
					if (attrType == String.class) {
						val = sdf.format(DateUtil.getJavaDate(cell.getNumericCellValue()));
					} else {
						val = dateConvertFormat(sdf.format(DateUtil.getJavaDate(cell.getNumericCellValue())));
					}
				} catch (ParseException e) {
					throw new Exception("第" + (row + 1) + " 行  " + (col + 1) + "列   属性：" + key + " 日期格式转换错误  ");
				}
			} else {
				if (attrType == String.class) {
					cell.setCellType(CellType.STRING);
					val = cell.getStringCellValue();
				} else if (attrType == BigDecimal.class) {
					val = new BigDecimal(cell.getNumericCellValue());
				} else if (attrType == long.class) {
					val = (long) cell.getNumericCellValue();
				} else if (attrType == Double.class) {
					val = cell.getNumericCellValue();
				} else if (attrType == Float.class) {
					val = (float) cell.getNumericCellValue();
				} else if (attrType == int.class || attrType == Integer.class) {
					val = (int) cell.getNumericCellValue();
				} else if (attrType == Short.class) {
					val = (short) cell.getNumericCellValue();
				} else {
					val = cell.getNumericCellValue();
				}
			}

		} else if (cell.getCellType() == CellType.STRING) {
			if(attrType.equals(double.class) || attrType.equals(Double.class)){
				val = Double.parseDouble(cell.getStringCellValue());
			}else{
				val = cell.getStringCellValue();
			}

		}

		setter(obj, attr, val, attrType, row, col, key);
	}
	
	/**
	 * 导出Excel
	 * @param outFilePath
	 * @param keyValue
	 * @param list
	 * @param classPath
	 * @throws Exception
	 */
	public static void exportExcel(String outFilePath, String keyValue, List<?> list, String classPath)
			throws Exception {

		Map<String, String> map = getMap(keyValue);
		List<String> keyList = getList(keyValue);

		Class<?> demo = null;
		demo = Class.forName(classPath);
		Object obj = demo.newInstance();
		// 创建HSSFWorkbook对象(excel的文档对象)
		Workbook wb = new XSSFWorkbook();
		// 建立新的sheet对象（excel的表单）
		Sheet sheet = wb.createSheet("sheet1");
		// 声明样式
		CellStyle style = wb.createCellStyle();
		// 居中显示
		style.setAlignment(HorizontalAlignment.CENTER);
		// 在sheet里创建第一行为表头，参数为行索引(excel的行)，可以是0～65535之间的任何一个
		Row rowHeader = sheet.createRow(0);
		// 创建单元格并设置单元格内容

		// 存储属性信息
		Map<String, String> attMap = new HashMap();
		int index = 0;

		for (String key : keyList) {
			rowHeader.createCell(index).setCellValue(key);
			attMap.put(Integer.toString(index), map.get(key).toString());
			index++;
		}

		// 在sheet里创建表头下的数据
		for (int i = 0; i < list.size(); i++) {
			Row row = sheet.createRow(i + 1);
			for (int j = 0; j < map.size(); j++) {

				Class<?> attrType = BeanUtils.findPropertyType(attMap.get(Integer.toString(j)),
						new Class[] { obj.getClass() });

				Object value = getAttrVal(list.get(i), attMap.get(Integer.toString(j)), attrType);
				if (null == value) {
					value = "";
				}
				row.createCell(j).setCellValue(value.toString());
				style.setAlignment(HorizontalAlignment.CENTER);
			}
		}

		// 输出Excel文件
		try {
			FileOutputStream out = new FileOutputStream(outFilePath);
			wb.write(out);
			out.close();
		} catch (FileNotFoundException e) {
			throw new FileNotFoundException("导出失败！" + e);
		} catch (IOException e) {
			throw new IOException("导出失败！" + e);
		}

	}
	
	
	/**
	 * 导出Excel
	 * @param response
	 * @param keyValue
	 * @param list
	 * @param classPath
	 * @param fileName
	 * @throws Exception
	 */
	public static void exportExcelOutputStream(HttpServletResponse response, String keyValue, List<?> list,
											   String classPath, String... fileName) throws Exception {

		Map<String, String> map = getMap(keyValue);
		List<String> keyList = getList(keyValue);

		Class<?> demo = null;
		demo = Class.forName(classPath);
		Object obj = demo.newInstance();
		// 创建HSSFWorkbook对象(excel的文档对象)
		Workbook wb = new XSSFWorkbook();
		// 建立新的sheet对象（excel的表单）
		Sheet sheet = wb.createSheet("sheet1");
		// 声明样式
		CellStyle style = wb.createCellStyle();
		// 居中显示
		style.setAlignment(HorizontalAlignment.CENTER);
		// 在sheet里创建第一行为表头，参数为行索引(excel的行)，可以是0～65535之间的任何一个
		Row rowHeader = sheet.createRow(0);
		// 创建单元格并设置单元格内容

		// 存储属性信息
		Map<String, String> attMap = new HashMap();
		int index = 0;

		for (String key : keyList) {
			rowHeader.createCell(index).setCellValue(key);
			attMap.put(Integer.toString(index), map.get(key).toString());
			index++;
		}

		// 在sheet里创建表头下的数据
		for (int i = 0; i < list.size(); i++) {
			Row row = sheet.createRow(i + 1);
			for (int j = 0; j < map.size(); j++) {
				Class<?> attrType = BeanUtils.findPropertyType(attMap.get(Integer.toString(j)),
						new Class[] { obj.getClass() });
				Object value = getAttrVal(list.get(i), attMap.get(Integer.toString(j)), attrType);
				if (null == value) {
					value = "";
				}
				row.createCell(j).setCellValue(value.toString());
				style.setAlignment(HorizontalAlignment.CENTER);
			}
		}

		// 输出Excel文件
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
			String newFileName = fileName[0];
			if (StringUtils.isEmpty(fileName[0])) {
				newFileName = df.format(new Date());
			}
			OutputStream outstream = response.getOutputStream();
			response.reset();
			response.setHeader("Content-disposition",
					"attachment; filename=" + new String(newFileName.getBytes(), "iso-8859-1") + ".xlsx");
			response.setContentType("application/x-download");
			wb.write(outstream);
			outstream.close();

		} catch (FileNotFoundException e) {
			throw new FileNotFoundException("导出失败！" + e);
		} catch (IOException e) {
			throw new IOException("导出失败！" + e);
		}

	}
	
	/**
	 *  使用流生成Excel
	 * @param buf
	 * @param map
	 * @param classPath
	 * @param rowNumIndex
	 * @param <T>
	 * @return
	 * @throws Exception
	 */
	public static <T> List<T> readXls(byte[] buf, Map map, String classPath, int... rowNumIndex) throws Exception {

		Set keySet = map.keySet();// 返回键的集合

		/** 反射用 **/
		Class<?> demo ;
		Object obj ;
		/** 反射用 **/

		List<Object> list = new ArrayList<Object>();
		demo = Class.forName(classPath);
		InputStream is = new ByteArrayInputStream(buf);
		Workbook wb = WorkbookFactory.create(is);

		// 默认循环所有sheet，如果rowNumIndex[]
		for (int sheetNum = 0; sheetNum < wb.getNumberOfSheets(); sheetNum++) {// 获取每个Sheet表

			int rowNum_x = -1;// 记录第x行为表头
			Map<String, Integer> cellmap = new HashMap<String, Integer>();// 存放每一个field字段对应所在的列的序号
			List<String> headlist = new ArrayList();// 存放所有的表头字段信息
			Sheet hssfSheet = wb.getSheetAt(sheetNum);

			// 循环行Row
			for (int rowNum = 0; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
				if (rowNumIndex != null && rowNumIndex.length > 0 && rowNum_x == -1) {// 如果传值指定从第几行开始读，就从指定行寻找，否则自动寻找
					Row hssfRow = hssfSheet.getRow(rowNumIndex[0]);
					if (hssfRow == null) {
						throw new RuntimeException("指定的行为空，请检查");
					}
					rowNum = rowNumIndex[0] - 1;
				}
				Row hssfRow = hssfSheet.getRow(rowNum);
				if (hssfRow == null) {
					continue;
				}
				boolean flag = false;
				for (int i = 0; i < hssfRow.getLastCellNum(); i++) {
					if (hssfRow.getCell(i) != null && !("").equals(hssfRow.getCell(i).toString().trim())) {
						flag = true;
					}
				}
				if (!flag) {
					continue;
				}

				if (rowNum_x == -1) {
					// 循环列Cell
					for (int cellNum = 0; cellNum <= hssfRow.getLastCellNum(); cellNum++) {

						Cell hssfCell = hssfRow.getCell(cellNum);
						if (hssfCell == null) {
							continue;
						}

						String tempCellValue = hssfSheet.getRow(rowNum).getCell(cellNum).getStringCellValue();

						tempCellValue = StringUtils.remove(tempCellValue, (char) 160);
						tempCellValue = tempCellValue.trim();
						headlist.add(tempCellValue);
						Iterator it = keySet.iterator();
						while (it.hasNext()) {
							Object key = it.next();
							if (StringUtils.isNotBlank(tempCellValue)
									&& StringUtils.equals(tempCellValue, key.toString())) {
								rowNum_x = rowNum;
								cellmap.put(map.get(key).toString(), cellNum);
							}
						}
						if (rowNum_x == -1) {
							throw new Exception("没有找到对应的字段或者对应字段行上面含有不为空白的行字段");
						}
					}

					// 读取到列后，检查表头是否完全一致--start
					for (int i = 0; i < headlist.size(); i++) {
						boolean boo = false;
						Iterator itor = keySet.iterator();
						while (itor.hasNext()) {
							String tempname = itor.next().toString();
							if (tempname.equals(headlist.get(i))) {
								boo = true;
							}
						}
						if (boo == false) {
							throw new Exception("表头字段和定义的属性字段不匹配，请检查");
						}
					}

					Iterator itor = keySet.iterator();
					while (itor.hasNext()) {
						boolean boo = false;
						String tempname = itor.next().toString();
						for (int i = 0; i < headlist.size(); i++) {
							if (tempname.equals(headlist.get(i))) {
								boo = true;
							}
						}
						if (boo == false) {
							throw new Exception("表头字段和定义的属性字段不匹配，请检查");
						}
					}
					// 读取到列后，检查表头是否完全一致--end

				} else {
					obj = demo.newInstance();
					Iterator it = keySet.iterator();
					while (it.hasNext()) {
						Object key = it.next();
						Integer cellNum_x = cellmap.get(map.get(key).toString());
						if (cellNum_x == null || hssfRow.getCell(cellNum_x) == null) {
							continue;
						}
						String attr = map.get(key).toString();// 得到属性

						Class<?> attrType = BeanUtils.findPropertyType(attr, new Class[] { obj.getClass() });

						Cell cell = hssfRow.getCell(cellNum_x);
						getValue(cell, obj, attr, attrType, rowNum, cellNum_x, key);

					}
					list.add(obj);
				}

			}
		}
		is.close();
		// wb.close();
		return (List<T>) list;
	}
	
	
	/**
	 *  String类型日期转为Date类型
	 * @param dateStr
	 * @return
	 * @throws ParseException
	 */
	public static Date dateConvertFormat(String dateStr) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = format.parse(dateStr);
		return date;
	}

}


