package io.github.mysar.blog.common.excel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Im.Yan on 2017/8/28.
 * 描述: Excel模型接口
 */
public abstract class ExcelModel <T extends ExcelModel>{

    private final static Logger logger = LoggerFactory.getLogger(ExcelModel.class);


    //不允许为空的字段
    protected List<String> noAllowNullCell = new ArrayList<String>();

    //存放全部的错误消息
    protected List<String> errMsgList = new ArrayList<String>();

    /**
     * 初始化不允许为null的cell字段
     */
    public abstract void initNoAllowNullCell();

    /**
     * 转换数据模型
     * @throws ExlRowClCovtException
     */
    public abstract void convertDataMode() throws ExlRowClCovtException;


    /**
     * 添加错误消息
     * @param err_msg
     */
    protected void addExlRowErrMsg(String err_msg){
        errMsgList.add(err_msg);
    }


    /**
     * 抛出错误异常
     * @param subClassObject
     */
    protected void throwExlRowClException(T subClassObject) throws ExlRowClCovtException {
        if(errMsgList.size() > 0){
            StringBuffer err_msg = new StringBuffer();
            for(String msg : errMsgList){
                err_msg.append(msg);
            }
            throw new ExlRowClCovtException(subClassObject,err_msg.toString());
        }
    }

    /**
     * 验证不允许为null
     * @param subClassObject
     */
    protected void validateNoAllowNull(T subClassObject) throws ExlRowClCovtException {
        errMsgList.clear();
        noAllowNullCell.clear();
        initNoAllowNullCell();
        Class<?> subClass = subClassObject.getClass();
        for(String cellIndexName : noAllowNullCell){
            try {
                Field field = subClass.getDeclaredField(cellIndexName);
                field.setAccessible(true);
                Object object = field.get(subClassObject);
                if(object == null || "".equals(((String)object).trim())){
                    errMsgList.add(cellIndexName +"字段不允许为空；");
                }
                field.setAccessible(false);
            } catch (NoSuchFieldException e) {
                logger.error("{} 无字段{}",subClass.getName(),cellIndexName);
            } catch (IllegalAccessException e) {
                logger.error("{} 不能访问{} 字段",subClass.getName(),cellIndexName);
            }
        }
    }

    /**
     * 验证参数是否为空
     * @param str
     * @return
     */
    protected Boolean checkNull(String str){
        if (str == null || "".equals(str.trim()))
            return true;
        return false;
    }


}
