package com.fn.utils;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Excel 导入工具类
 */
public class WriteExcelUtil {

    private static final Logger logger = LoggerFactory.getLogger(WriteExcelUtil.class);


    public static List<String[]> importExcelData(MultipartFile upload) {
        // 1.将上传文件中的数据解析出来--重新封装成List<String[]>，一行数据一个String[]
        // 首先要定义一个List<String[]>来接收解析出来的数据
        List<String[]> stringsList = new ArrayList<String[]>();
        // 通过上传的文件拿到输入流
        try {
            InputStream is = upload.getInputStream();
            // 创建一个上传的内存对象
            XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
            int sheerNumber = xssfWorkbook.getNumberOfSheets();//获取表数量
            for (int k = 0; k < sheerNumber; k++) {
                // 通过内存对象，获得表
                XSSFSheet sheet = xssfWorkbook.getSheetAt(k);
                // 获得该表中最大行数
                int maxRowNum = sheet.getLastRowNum();// 拿到的是最后一行的行数，是从0开始的
                // 循环拿出里面的数据，排除第一行表头信息，从1开始循环
                for (int i = 1; i <= maxRowNum; i++) {
                    Row row = sheet.getRow(i);// 获得行对象
                    short maxCellNum = row.getLastCellNum();// 获取行中的最后一个单元格，也就是拿到单元格的最大数，从0开始，所以它的总数是最大+1
                    // 循环拿出单元格里的数据,每一行创建一个String[]来接收每一行的数据
                    String[] data = new String[maxCellNum];
                    for (int j = 0; j <= maxCellNum; j++) {
                        Cell cell = row.getCell(j);// 拿到单元格对象,判断其是否为null
                        if (cell != null) {
                            if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
                                data[j] = cell.getStringCellValue();
                            }
                            if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                                double numericCellValue = cell.getNumericCellValue();
                                data[j] = String.valueOf((long) numericCellValue);
                                if ((numericCellValue - Math.floor(numericCellValue)) > 0) {
                                    data[j] = String.valueOf(numericCellValue);
                                } else if (HSSFDateUtil.isCellDateFormatted(cell)) {
                                    data[j] = DatetimeUtil.formatDate(cell.getDateCellValue(), DatetimeUtil.STANDARD_DATETIME_PATTERN);
                                }
                            }
                        }
                    }
                    // 一行结束后就将这行的String[]放到list中
                    stringsList.add(data);
                }
            }
            // 关闭流
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringsList;
    }


}
