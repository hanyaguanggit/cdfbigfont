package cn.com.ktm.mt.model.home.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 富文本
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RichTextRes extends BaseHomeRes {

    //富文本
    private String richContent;
}
