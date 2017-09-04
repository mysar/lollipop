package io.github.mysar.blog.common.excel;

import com.alibaba.fastjson.JSONObject;
import io.github.mysar.blog.common.ErrorCode.ExceptionErrCode;
import io.github.mysar.blog.common.flagcode.CommonCode;
import io.github.mysar.blog.common.flagcode.NormalCode;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * excel处理工具
 * @param <T>
 */
public class ExcelHandler<T extends ExcelModel>{

    private final static Logger logger = LoggerFactory.getLogger(ExcelHandler.class);

    //excel行的数据模型
    private Class<T> excelRowModel;
    private T excelRowModelObject;

    //excel输入流
    private File excelFile;

    //excel的校验规则
    private ExcelValiRule valiRule;

    //excel 文档文件
    private HSSFWorkbook hssfWrokBook;

    //excel sheet数量
    private int sheetSize;

    //excel sheet表row 数量
    private Map<Integer,Integer> sheetRowSize = new HashMap<>();

    //当前的sheet索引
    private int currentSheetIndex = 0;

    //当前的sheet的row索引
    private int currentSheetRowIndex = 1;

    //excel 的 row  的cell读取器
    private ExcelRowConsumer excelRowHeadConsumer = new ExcelRowConsumer(new HSSFCellRowHeadHandler());
    private ExcelRowConsumer excelRowContentConsumer = new ExcelRowConsumer(new HSSFCellRowContentHandler());
    //excel 的头信息处理器
    private List<String> excelRowHeadErr = new ArrayList<String>();

    /**
     * 准备excel数据信息
     * @param excelFile
     * @param excelRowModel
     */
    public void prepareExcelInfo(ExcelValiRule valiRule,File excelFile, Class<T> excelRowModel) throws IOException, IllegalAccessException, InstantiationException {
        this.valiRule = valiRule;
        this.excelFile = excelFile;
        this.excelRowModel = excelRowModel;
        //
        hssfWrokBook = new HSSFWorkbook(new FileInputStream(excelFile));
        excelRowModelObject = excelRowModel.newInstance();
        sheetRowSize.clear();
        currentSheetIndex = 0;
        currentSheetRowIndex = 1;
    }

    /**
     * 下一行数据
     * @return
     */
    public T nextRowDataModel() throws ExlRowClCovtException {
        logger.debug("current sheet num 是 : {}",currentSheetIndex);
        HSSFSheet sheet = hssfWrokBook.getSheetAt(currentSheetIndex);
        logger.debug("current sheet Row num 是 : {}",currentSheetRowIndex);
        HSSFRow row = sheet.getRow(currentSheetRowIndex);
        logger.debug("获取到的行数据对象为：{}", row);
        if (row == null) return null;
        row.forEach(excelRowContentConsumer);
        //调用转换方法
        excelRowModelObject.convertDataMode();
        currentSheetRowIndex ++;
        if(currentSheetRowIndex > sheetRowSize.get(currentSheetIndex)){
            currentSheetIndex ++;//移到下一个sheet
            currentSheetRowIndex = 0; //移动下一个sheet的第一行
        }
        return excelRowModelObject;
    }

    /**
     * 验证excel相关信息
     * @return
     */
    public JSONObject valiSuc() {
        JSONObject result = new JSONObject();
        result.put(CommonCode.ERR_CODE, NormalCode.NORMAL_CODE);
        //1.验证表头是否正确
        HSSFSheet sheet = hssfWrokBook.getSheetAt(0);
        HSSFRow row = sheet.getRow(0);
        row.forEach(excelRowHeadConsumer);
        if(excelRowHeadErr.size() >0){
            StringBuilder sb = new StringBuilder();
            for(String err : excelRowHeadErr){
                sb.append(err);
                sb.append(";");
            }
            result.put(CommonCode.ERR_CODE, ExceptionErrCode.EXCEL_HEADER_PARSER_ERROR);
            result.put(CommonCode.ERR_MSG,sb.toString());
        }
        excelRowHeadErr.clear();
        return result;
    }

    /**
     * 获取行数据记录
     * @return
     */
    public int getRowDataSize() {
        int allRows = 0;
        this.sheetSize = hssfWrokBook.getNumberOfSheets();
        logger.debug("sheetSize 是:{}",sheetSize);
        for(int i=0; i<sheetSize; i++){
            HSSFSheet sheet = hssfWrokBook.getSheetAt(i);
            int sheetRows = 0;
            logger.debug("sheetRowSize是: {}",sheetRows);
            if(i == 0){//第一个sheet页
                sheetRows = sheet.getPhysicalNumberOfRows() -1;//去除第一行的头信息
            }else {
                sheetRows = sheet.getPhysicalNumberOfRows();
            }
            logger.debug("current sheet rows 是: {},i是:{}, physiscalNumber 是:{}",sheetRows,i,sheet.getPhysicalNumberOfRows());
            allRows += sheetRows;
            sheetRowSize.put(i,sheetRows);
        }
        return allRows;
    }

    /**
     * 获取excel名称
     * @return
     */
    public String getExcelFileName() {
        return excelFile.getName();
    }

    /**
     * excel的row的cell的内容读取器
     */
    class ExcelRowConsumer implements Consumer<Cell> {

        private HSSFCellHandler hssfCellHandler;

        public ExcelRowConsumer(HSSFCellHandler hssfCellHandler) {
            this.hssfCellHandler = hssfCellHandler;
        }

        @Override
        public void accept(Cell cell) {
            int index = cell.getColumnIndex();
            String value = getStringCell(cell);
            hssfCellHandler.handleCellStr(index,value);
        }

        /**
         * 获取字符串的Cell值
         * @param cell
         * @return
         */
        private String getStringCell(Cell cell) {
            String strCell = "";
            switch (cell.getCellType()) {
                case Cell.CELL_TYPE_STRING:
                    strCell = cell.getStringCellValue();
                    break;
                case Cell.CELL_TYPE_NUMERIC:
                    strCell = String.valueOf(cell.getNumericCellValue());
                    break;
                case Cell.CELL_TYPE_BOOLEAN:
                    strCell = String.valueOf(cell.getBooleanCellValue());
                    break;
                case Cell.CELL_TYPE_BLANK:
                    strCell = "";
                    break;
                default:
                    strCell = "";
                    break;
            }
            if (strCell.equals("") || strCell == null) {
                return "";
            }
            if (cell == null) {
                return "";
            }
            return strCell;
        }
    }

    /**
     * cell row内容读取
     */
    class HSSFCellRowContentHandler implements HSSFCellHandler{

        @Override
        public void handleCellStr(int index, String value) {
            try {
                Field field = excelRowModel.getDeclaredField("c"+index);
                field.setAccessible(true);
                field.set(excelRowModelObject,value);
                field.setAccessible(false);
            } catch (NoSuchFieldException e) {
                logger.error("{} 无字段{}",excelRowModel.getName(),"c"+index);
            } catch (IllegalAccessException e) {
                logger.error("{} 不能访问字段 {}",excelRowModel.getName(),"c" + index);
            }
        }
    }

    /**
     * cell row头信息处理
     */
    class HSSFCellRowHeadHandler implements HSSFCellHandler{

        @Override
        public void handleCellStr(int index, String value) {
            Class<?> valiRuleClass = valiRule.getClass();
            try {
                Field field = valiRuleClass.getDeclaredField("c"+index);
                field.setAccessible(true);
                Object objValue = field.get(valiRule);
                if(objValue == null || !String.valueOf(objValue).equals(value)){
                    excelRowHeadErr.add("Excel头[ " + objValue +" ]不符合要求,请按照模板头信息填写");
                }
                field.setAccessible(false);
            } catch (NoSuchFieldException e) {
                logger.error("{} 无字段{}", excelRowModel.getName(), "c" + index);
            } catch (IllegalAccessException e) {
                logger.error("{} 不能访问字段 {}",excelRowModel.getName(),"c" + index);
            }
        }
    }

}
