package cn.com.ktm.mt.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderQurey implements Serializable {
    private static final long serialVersionUID = -5809782578272943999L;
    private String    partnerId;
    private Integer    orderChannel;
    private Long       orderUserId;

}