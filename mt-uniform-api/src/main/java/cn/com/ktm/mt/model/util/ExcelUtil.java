package cn.com.ktm.mt.model.util;

import cn.com.ktm.mt.model.util.utils.DateTimeUtil;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


/**
 * Excel工具类
 * 
 * @author maqiang
 * 
 * 日期 2015-01-19
 *
 */
public class ExcelUtil {
	/**
	 * 解析一个Excel文件的某个特定的sheet (2003格式)
	 * 
	 * @param file excel文件
	 * @param startRows 起始行数
	 * @param startColumn 起始列 （如：第一行为空时，此值传1，表示第一列不取）
	 * @param endRows 过滤行（如：最后2行为说明行，此值传2，表示不读取最后两行）
	 * @param index sheet的页码       
	 * @return
	 * @throws Exception 
	 */
	public static String[][] getLowVersionData(File file, int startRows,
			int startColumn, int endRows, int index)
			throws Exception {
		List result = new ArrayList();
		int rowSize = 0;
		BufferedInputStream in = new BufferedInputStream(new FileInputStream(
				file));
		// 打开HSSFWorkbook
		POIFSFileSystem fs = new POIFSFileSystem(in);
		HSSFWorkbook wb = new HSSFWorkbook(fs);
		HSSFCell cell = null;
		HSSFSheet st = wb.getSheetAt(index - 1);
		int actualRowNum = st.getLastRowNum() - endRows;//实际行数
		// 第一行为标题，不取
		for (int rowIndex = startRows; rowIndex <= actualRowNum; rowIndex++) {
			HSSFRow row = st.getRow(rowIndex);
			if (row == null) {
				continue;
			}
			int cellNum = row.getLastCellNum();
			int tempRowSize = cellNum + 1;
			if (tempRowSize > rowSize) {
				rowSize = tempRowSize;
			}
			cellNum = cellNum - startColumn;
			String[] values = new String[rowSize - startColumn];
			Arrays.fill(values, "");

			boolean hasValue = false;
			for (int columnIndex = 0; columnIndex <= cellNum; columnIndex++) {
				String value = "";
				cell = row.getCell(columnIndex + startColumn);
				if (cell != null) {
					switch (cell.getCellType()) {
					case HSSFCell.CELL_TYPE_STRING:
						value = cell.getStringCellValue().trim();
						break;
					case HSSFCell.CELL_TYPE_NUMERIC:
						if (HSSFDateUtil.isCellDateFormatted(cell)) {
							Date date = cell.getDateCellValue();
							if (date != null) {
								//yyyy-mm-dd 日期格式
								value=	DateUtil.getDateFormatFromDate(DateTimeUtil.format3,date).toString();
							} else {
								value = "";
							}
						} else {
							cell.setCellType(1);
//							value = cell.getStringCellValue().trim();//得到值
//							value = Util.getBigDecimalVal(cell.getStringCellValue()).toString();
							BigDecimal bd = new BigDecimal(cell.getStringCellValue()); 
							value = bd.toPlainString();
						}
						break;
					case HSSFCell.CELL_TYPE_FORMULA:
						// 导入时如果为公式生成的数据则无值
						try {
							if (!cell.getStringCellValue().equals("")) {
								value = cell.getStringCellValue();
							} else {
								value = cell.getNumericCellValue() + "";
							}
						} catch (Exception e) {
//							value = String.valueOf(cell.getNumericCellValue()); 
							value = String.valueOf(cell); 
						}
						break;
					case HSSFCell.CELL_TYPE_BLANK:
						break;
					case HSSFCell.CELL_TYPE_ERROR:
						value = "";
						break;
					case HSSFCell.CELL_TYPE_BOOLEAN:
						value = (cell.getBooleanCellValue() == true ? "Y" : "N");
						break;
					default:
						value = "";
					}
				}
				if (columnIndex == 0 && "".equals(value.trim())
						&& isRowEmpty( row,  startColumn, cellNum)) {
					break;
				}
				values[columnIndex] = value.trim();
				hasValue = true;
			}
			if (hasValue) {
				result.add(values);
			}
		}

		in.close();
		String[][] returnArray = new String[result.size()][rowSize];
		for (int i = 0; i < returnArray.length; i++) {
			returnArray[i] = (String[]) result.get(i);
		}
		return returnArray;
	}

	/**
	 * 是否整行都为空
	 * @param row 行
	 *  @param startColumn 第几列开始计算
	 * @param colNum 列数
	 * @return true 都为空
	 */
     public static boolean isRowEmpty(Row row,int startColumn,int colNum){
		 if(row == null){
			 return true;
		 }
		 int emptyNum=0;
		 for (int columnIndex = 0; columnIndex < colNum; columnIndex++) {
			 Cell cell = row.getCell(columnIndex + startColumn);
			 if(cell ==null){
				 emptyNum++;
			 }else{
                String cellVal = getConvertCellValue(cell);
				 if("".equals(cellVal.trim())){
					 emptyNum++;
				 }
			 }
		 }
		 if(emptyNum == (colNum-startColumn)){
			 return true;
		 }else{
			 return false;
		 }
	 }


	public static String getConvertCellValue(Cell cell){
		String value = "";
		switch (cell.getCellType()) {
			case HSSFCell.CELL_TYPE_STRING:
				value = cell.getStringCellValue().trim();
				break;
			case HSSFCell.CELL_TYPE_NUMERIC:
				if (HSSFDateUtil.isCellDateFormatted(cell)) {
					Date date = cell.getDateCellValue();
					if (date != null) {
						//yyyy-mm-dd 日期格式
						value=	DateUtil.getDateFormatFromDate(DateTimeUtil.format3,date).toString();
					} else {
						value = "";
					}
				} else {
					cell.setCellType(1);
//							value = cell.getStringCellValue().trim();//得到值
//							value = Util.getBigDecimalVal(cell.getStringCellValue()).toString();
					BigDecimal bd = new BigDecimal(cell.getStringCellValue());
					value = bd.toPlainString();
				}
				break;
			case HSSFCell.CELL_TYPE_FORMULA:
				// 导入时如果为公式生成的数据则无值
				try {
					if (!cell.getStringCellValue().equals("")) {
						value = cell.getStringCellValue();
					} else {
						value = cell.getNumericCellValue() + "";
					}
				} catch (Exception e) {
//							value = String.valueOf(cell.getNumericCellValue());
					value = String.valueOf(cell);
				}
				break;
			case HSSFCell.CELL_TYPE_BLANK:
				break;
			case HSSFCell.CELL_TYPE_ERROR:
				value = "";
				break;
			case HSSFCell.CELL_TYPE_BOOLEAN:
				value = (cell.getBooleanCellValue() == true ? "Y" : "N");
				break;
			default:
				value = "";
		}
		return  value;
	}

	/**
	 * 获取固定行、固定列的值(2003)
	 * 
	 * @param file 文件路径
	 * @param startRows 固定行
	 * @param endColumn  固定列
	 * @param sheetNum sheet的页码
	 * @return
	 * @throws Exception 
	 */
	public static String getLowVersionFixedData(File file, int startRows,
			int endColumn, int sheetNum) throws Exception {
		int rowSize = 0;
		String value = "";
		BufferedInputStream in = new BufferedInputStream(new FileInputStream(
				file));
		// 打开HSSFWorkbook
		POIFSFileSystem fs = new POIFSFileSystem(in);
		HSSFWorkbook wb = new HSSFWorkbook(fs);
		HSSFCell cell = null;
		HSSFSheet st = wb.getSheetAt(sheetNum - 1);
		HSSFRow row = st.getRow(startRows);
		if (row != null) {
			int tempRowSize = row.getLastCellNum() + 1;
			if (tempRowSize > rowSize) {
				rowSize = tempRowSize;
			}
			String[] values = new String[rowSize];
			Arrays.fill(values, "");
			cell = row.getCell(endColumn);
			if (cell != null) {
				switch (cell.getCellType()) {
				case HSSFCell.CELL_TYPE_STRING:
					value = cell.getStringCellValue();
					break;
				case HSSFCell.CELL_TYPE_NUMERIC:
					if (HSSFDateUtil.isCellDateFormatted(cell)) {
						Date date = cell.getDateCellValue();
						if (date != null) {
							//yyyy-mm-dd 日期格式
							value=	DateUtil.getDateFormatFromDate(DateTimeUtil.format3,date);
						} else {
							value = "";
						}
					} else {
						value = new DecimalFormat("0").format(cell
								.getNumericCellValue());
					}
					break;
				case HSSFCell.CELL_TYPE_FORMULA:
					// 导入时如果为公式生成的数据则无值
					if (!cell.getStringCellValue().equals("")) {
						value = cell.getStringCellValue();
					} else {
						value = cell.getNumericCellValue() + "";
					}
				default:
					value = "";
				}
			}
		}
		in.close();

		return value;
	}

	/**
	 * 读取2007-2013格式 解析一个Excel文件的某个特定的sheet
	 * 
	 * 解析excel数据时，如果有合并单元格的情况，进行如下处理：
	 * 1、创建一个数组，大小为3，用来存放合并单元格的值（因为只有前三列的数据会出现合并单元格的情况）
	 * 2、先统计该sheet中所有被合并的单元格，并将这些单元格翻入list中；
	 * 3、当获取到某一单元格时，判断该单元格是否是合并单元格：
	 * 		如果是合并单元格，先拿到该单元格的值，判断该单元格的值是否为空
	 * 		①如果不为空，就将这个值放入对应的数组位置（第一次获取的数据肯定是不为空的）
	 * 		②如果为空，就从数组中取得对应位置的值
	 * 
	 * @param file excel文件
	 * @param startRows 起始行数
	 * @param startColumn 起始列 （如：第一行为空时，此值传1，表示第一列不取）
	 * @param endRows 过滤行（如：最后2行为说明行，此值传2，表示不读取最后两行）
	 * @param index sheet的页码       
	 * @return
	 * @throws Exception 
	 */
	public static String[][] getHighVersionData(File file, int startRows,
			int startColumn, int endRows, int index)
			throws Exception {
		String[] cellValues = new String[3];
		ArrayList result = new ArrayList();
		int rowSize = 0;
		BufferedInputStream in = new BufferedInputStream(new FileInputStream(
				file));
		XSSFWorkbook xwb = new XSSFWorkbook(in); // 构造 XSSFWorkbook 对象，strPath
													// 传入文件路径
		XSSFSheet st = xwb.getSheetAt(index - 1);
		
		List<CellRangeAddress> list = new ArrayList<CellRangeAddress>();
		ExcelUtil.getCombineCell(st, list);
		
		XSSFCell cell = null;
		int actualRowNum = st.getLastRowNum() - endRows;
		// 第一行为标题，不取
		for (int rowIndex = startRows; rowIndex <= actualRowNum; rowIndex++) {
			XSSFRow row = st.getRow(rowIndex);
			if (row == null) {
				continue;
			}
			int cellNum = row.getLastCellNum();
			int tempRowSize = cellNum + 1;
			if (tempRowSize > rowSize) {
				rowSize = tempRowSize;
			}
			cellNum = cellNum - startColumn;
			String[] values = new String[rowSize - startColumn];
			Arrays.fill(values, "");

			boolean hasValue = false;
			for (int columnIndex = 0; columnIndex <= cellNum; columnIndex++) {
				String value = "";
				cell = row.getCell(columnIndex + startColumn);
				if (cell != null) {
					Boolean isCombineCell = ExcelUtil.isCombineCell(list, cell, st);
					if(isCombineCell){
						String str = cell.getStringCellValue();
						if(!"".equals(str)){
							cellValues[columnIndex]=str;
							value = str;
						}else{
							value = cellValues[columnIndex];
						}
					}else{
						switch (cell.getCellType()) {
						case HSSFCell.CELL_TYPE_STRING:
							value = cell.getStringCellValue().trim();
							break;
						case HSSFCell.CELL_TYPE_NUMERIC:
							if (HSSFDateUtil.isCellDateFormatted(cell)) {
								Date date = cell.getDateCellValue();
								if (date != null) {
									//yyyy-mm-dd 日期格式
									value=	DateUtil.getDateFormatFromDate(DateTimeUtil.format3,date).toString();
								} else {
									value = "";
								}
							} else {
	//							value = new DecimalFormat("0").format(cell
	//									.getNumericCellValue());
//								value=cell.getRawValue();
								/**
								 * value=cell.getRawValue(); 不知道谁加的；
								 * value=cell.getRawValue(); 当取值为0.015 等带小数点的值时，会出现浮点转换错误 数据转为1.499999999999999E 此类数值,
								 * 故 暂时注调代码，使用 cell.toString() 
								 * @createDate 2016-1-22  导入打分表时出现的错误
								 */
//								value = cell.toString();
								//2007 读取长数字值时 出现科学计数法
//								DecimalFormat df = new DecimalFormat("0");  
//								value = df.format(cell.getNumericCellValue());
								BigDecimal bd = new BigDecimal(cell.toString()); 
								value = bd.toPlainString();
							}
							break;
						case HSSFCell.CELL_TYPE_FORMULA:
							// 导入时如果为公式生成的数据则无值
							try {
								if (!cell.getStringCellValue().equals("")) {
									value = cell.getStringCellValue();
								} else {
									value = cell.getNumericCellValue() + "";
								}
							} catch (Exception e) {
								value = String.valueOf(cell.getNumericCellValue());  
	//							value = String.valueOf(cell.getStringCellValue());
							}
							break;
						case HSSFCell.CELL_TYPE_BLANK:
							break;
						case HSSFCell.CELL_TYPE_ERROR:
							value = "";
							break;
						case HSSFCell.CELL_TYPE_BOOLEAN:
							value = (cell.getBooleanCellValue() == true ? "Y" : "N");
							break;
						default:
							value = "";
						}
					}
				}
				if (columnIndex == 0 && value.trim().equals("")
						&& isRowEmpty( row,  startColumn, cellNum)) {
					break;
				}
				values[columnIndex] = value.trim();
				hasValue = true;
			}
			if (hasValue) {
				result.add(values);
			}
		}

		in.close();
		String[][] returnArray = new String[result.size()][rowSize];
		for (int i = 0; i < returnArray.length; i++) {
			returnArray[i] = (String[]) result.get(i);
		}
		return returnArray;
	}

	/**
	 * 获取固定行、固定列的值(2007~2013)
	 * 
	 * @param file 文件路径
	 * @param startRows 固定行
	 * @param endColumn  固定列
	 * @param sheetNum sheet的页码
	 * @return
	 * @throws Exception 
	 */
	public static String getHighVersionFixedData(File file, int startRows,
			int endColumn, int sheetNum) throws Exception {
		int rowSize = 0;
		String value = "";
		BufferedInputStream in = new BufferedInputStream(new FileInputStream(
				file));
		// 打开XSSFWorkbook
		XSSFWorkbook xwb = new XSSFWorkbook(in); // 构造 XSSFWorkbook 对象，strPath													
		XSSFSheet st = xwb.getSheetAt(sheetNum - 1);
		XSSFCell cell = null;
		XSSFRow row = st.getRow(startRows);
		if (row != null) {
			int tempRowSize = row.getLastCellNum() + 1;
			if (tempRowSize > rowSize) {
				rowSize = tempRowSize;
			}
			String[] values = new String[rowSize];
			Arrays.fill(values, "");
			cell = row.getCell(endColumn);
			if (cell != null) {
				switch (cell.getCellType()) {
				case HSSFCell.CELL_TYPE_STRING:
					value = cell.getStringCellValue();
					break;
				case HSSFCell.CELL_TYPE_NUMERIC:
					if (HSSFDateUtil.isCellDateFormatted(cell)) {
						Date date = cell.getDateCellValue();
						if (date != null) {
							//yyyy-mm-dd 日期格式
							value=	DateUtil.getDateFormatFromDate(DateTimeUtil.format3,date).toString();
						} else {
							value = "";
						}
					} else {
						value = new DecimalFormat("0").format(cell
								.getNumericCellValue());
					}
					break;
				case HSSFCell.CELL_TYPE_FORMULA:
					// 导入时如果为公式生成的数据则无值
					if (!cell.getStringCellValue().equals("")) {
						value = cell.getStringCellValue();
					} else {
						value = cell.getNumericCellValue() + "";
					}
				default:
					value = "";
				}
			}
		}
		in.close();

		return value;
	}

	/**
	 * 获得excel中sheet页个数（2003）
	 * 
	 * @param file
	 * @param ignoreRows
	 * @param index
	 * @return
	 */
	public static int getLowVersionSheetNum(File file) throws FileNotFoundException,
			IOException {
		int sheetNum = 0;
		BufferedInputStream in = new BufferedInputStream(new FileInputStream(
				file));
		// 打开HSSFWorkbook
		POIFSFileSystem fs = new POIFSFileSystem(in);
		HSSFWorkbook wb = new HSSFWorkbook(fs);
		sheetNum = wb.getNumberOfSheets();
		in.close();
		return sheetNum;
	}
	
	/**
	 * 获得excel中sheet页个数（2007~2013）
	 * 
	 * @param file
	 * @param ignoreRows
	 * @param index
	 * @return
	 */
	public static int getHighVersionSheetNum(File file) throws FileNotFoundException,
			IOException {
		int sheetNum = 0;
		BufferedInputStream in = new BufferedInputStream(new FileInputStream(
				file));
		// 打开HSSFWorkbook
		XSSFWorkbook wb = new XSSFWorkbook(in); // 构造 XSSFWorkbook 对象，strPath	
		sheetNum = wb.getNumberOfSheets();
		in.close();
		return sheetNum;
	}
	/**
	 * * 解析一个Excel文件的某个特定的sheet
	 * @param file 文件路径
	 * @param fileName 文件名称
	 * @param startRows 起始行数
	 * @param startColumn 起始列 （如：第一行为空时，此值传1，表示第一列不取）
	 * @param endRows 过滤行（如：最后2行为说明行，此值传2，表示不读取最后两行）
	 * @param sheetNum sheet的页码  
	 * @return
	 * @throws Exception
	 */
	public static String[][] getData(File file, String fileName, int startRows,int startColumn,
			int endRows, int sheetNum) throws Exception {
		String[][] result = null;
		if (fileName.endsWith(".xlsx")) {// 2007~2013版本
			result = ExcelUtil.getHighVersionData(file,startRows,startColumn, endRows, sheetNum);
		} else {
			result = ExcelUtil.getLowVersionData(file,startRows,startColumn, endRows, sheetNum);
		}
		return result;
	}
	
	/**
	 * 获得excel中sheet页个数
	 * 
	 * @param file
	 * @param ignoreRows
	 * @param index
	 * @return
	 */
	public static int getSheetNum(String fileInput) throws FileNotFoundException,
			IOException {
		int sheetNum = 0;
		File file = new File(fileInput);
		if (fileInput.endsWith(".xlsx")) {// 2007~2013版本
			sheetNum = ExcelUtil.getHighVersionSheetNum(file);
		} else {
			sheetNum = ExcelUtil.getLowVersionSheetNum(file);
		}
		return sheetNum;
	}
	/**
	 * 获取固定行、固定列的值(2007~2013)
	 * 
	 * @param file 文件路径
	 * @param fileName 文件名称
	 * @param startRows 固定行
	 * @param endColumn  固定列
	 * @param sheetNum sheet的页码
	 * @return
	 * @throws Exception 
	 */
	public static String getFixedData(File file, String fileName,int startRows,
			int endColumn, int sheetNum) throws Exception {
		String result = "";
		if (fileName.endsWith(".xlsx")) {// 2007~2013版本
			result = ExcelUtil.getHighVersionFixedData(file, startRows, endColumn, sheetNum);
		} else {
			result = ExcelUtil.getLowVersionFixedData(file, startRows, endColumn, sheetNum);
		}
		return result;
	}
	/**
	 * 用于将Excel表格中列号字母转成列索引，从1对应A开始
	 * @param column 列号
	 * @return 列索引
	 */
	public static int columnToIndex(String column) {
		if (!column.matches("[A-Z]+")) {
			try {
				throw new Exception("Invalid parameter");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		int index = 0;
		char[] chars = column.toUpperCase().toCharArray();
		for (int i = 0; i < chars.length; i++) {
			index += ((int) chars[i] - (int) 'A' + 1)
					* (int) Math.pow(26, chars.length - i - 1);
		}
		return index;
	}

	/**
	 * 用于将excel表格中列索引转成列号字母，从A对应1开始
	 * @param index 列索引
	 * @return 列号
	 */
	public static String indexToColumn(int index) {
		if (index <= 0) {
			try {
				throw new Exception("Invalid parameter");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		index--;
		String column = "";
		do {
			if (column.length() > 0) {
				index--;
			}
			column = ((char) (index % 26 + (int) 'A')) + column;
			index = (int) ((index - index % 26) / 26);
		} while (index > 0);
		return column;
	}
	
	/**
	* 合并单元格处理--加入list
	* 
	* @param sheet
	* @return
	*/
	public static void getCombineCell(XSSFSheet sheet, List<CellRangeAddress> list) {
		// 获得一个 sheet 中合并单元格的数量
		int sheetmergerCount = sheet.getNumMergedRegions();
		// 遍历合并单元格
		for (int i = 0; i < sheetmergerCount; i++) {
		// 获得合并单元格加入list中
		CellRangeAddress ca = sheet.getMergedRegion(i);
		list.add(ca);
		}
	}
	
	/**
	* 判断单元格是否为合并单元格
	* 
	* @param listCombineCell
	* 存放合并单元格的list
	* @param cell
	* 需要判断的单元格
	* @param st
	* sheet
	* @return
	*/
	public static Boolean isCombineCell(List<CellRangeAddress> listCombineCell,
		XSSFCell cell, XSSFSheet st) {
		int firstC = 0;
		int lastC = 0;
		int firstR = 0;
		int lastR = 0;
		for (CellRangeAddress ca : listCombineCell) {
		// 获得合并单元格的起始行, 结束行, 起始列, 结束列
		firstC = ca.getFirstColumn();
		lastC = ca.getLastColumn();
		firstR = ca.getFirstRow();
		lastR = ca.getLastRow();
		if (cell.getColumnIndex() <= lastC&& cell.getColumnIndex()>= firstC) {
		if (cell.getRowIndex() <= lastR && cell.getRowIndex() >= firstR) {
		return true;
		}
		}
		}
		return false;
	}
}
