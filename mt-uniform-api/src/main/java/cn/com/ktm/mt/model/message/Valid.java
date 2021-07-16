package cn.com.ktm.mt.model.message;


/**
 * 
 * @author       wyf
 * @date         2017年2月4日 下午4:29:47
 * @Description  
 * @version      
 *
 */
public interface Valid {
    
    /**
     * 进行简单的自我检测：非空、长度、大小、范围...<br>
     * 不设计DB查询
     */
    void valid();
}
