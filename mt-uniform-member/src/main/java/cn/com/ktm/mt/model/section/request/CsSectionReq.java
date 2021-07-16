package cn.com.ktm.mt.model.section.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.stream.BaseStream;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CsSectionReq {
    private BaseStream CommonParams;
    private int positionType;//位置类型
    private Object positionInfo;//对应位置类型实体
}
