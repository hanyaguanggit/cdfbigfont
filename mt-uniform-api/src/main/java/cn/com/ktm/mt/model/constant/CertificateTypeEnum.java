package cn.com.ktm.mt.model.constant;

/**
 * @Description: 支付返回码定义
 * @author dingzhiwei jmdhappy@126.com
 * @date 2017-07-05
 * @version V1.0
 * @Copyright: www.xxpay.org
 */
public enum CertificateTypeEnum {

  

    Cert_0010("0", "身份证"),
    Cert_0011("1", "护照"),
    Cert_0012("2", "军官证"),
    Cert_0013("3", "台胞证"),
    Cert_0014("4", "港澳通行证"),
	Cert_0016("6", "非实名制"),
	Cert_0017("7", "学生证"),
	Cert_0018("8", "残疾证"),
	Cert_0019("9", " 团队领队");
   

    private String code;
    private String message;

    CertificateTypeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode()
    {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

}
