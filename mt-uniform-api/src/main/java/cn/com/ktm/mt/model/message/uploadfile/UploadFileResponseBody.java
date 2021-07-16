package cn.com.ktm.mt.model.message.uploadfile;

import cn.com.ktm.mt.model.message.Body;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UploadFileResponseBody implements Body {
    //美团URl，必须回传
    private String url;


    @Override
    public void valid() {

    }
}
