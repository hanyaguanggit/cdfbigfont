package cn.com.ktm.mt.model.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 短信信息bean
 * Created by gaocl on 16-4-7.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SmsBean implements Serializable {
    private static final long serialVersionUID = 8783495088529587613L;

    private String phone;
    private String context;
    private int  type =0; //是否需要重发 0：不需要  1：需要
    
    public SmsBean(String phone,String context){
        this.phone=phone;
        this.context=context;
    }
}
