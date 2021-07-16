package cn.com.ktm.mt.model.exception;


import cn.com.ktm.mt.model.constant.ResponseConsts;

import java.io.Serializable;

/**
 * 服务异常.
 * 
 * <p>
 * 服务类代码中异常应该都包装为ServiceError或者ServiceError的子类.
 * </p>
 * 
 * @author wjq
 * 
 */
public class AssertError extends BaseException implements Serializable {
	private static final long serialVersionUID = -6924564120267866554L;

	/** 错误码. */
	private ResCodeEntity errorCode;
	/** 错误码. */
	private Long channelId;
	public Long getChannelId() {
		return channelId;
	}

	public String getPartnerId() {
		return partnerId;
	}

	public String getFileName() {
		return fileName;
	}

	/** 错误码. */
	private String partnerId ;
	
	private String fileName ;

	/**
	 * 构造函数.
	 */
	public AssertError() {
	}

	/**
	 * 构造函数.
	 *
	 * @param errorCode
	 *            错误码
	 */
	public AssertError(int errorCode) {
		this(errorCode, ResponseConsts.getCodeDesc(errorCode));
	}

	/**
	 * 构造函数.
	 *
	 * @param errorCode
	 *            错误码
	 * @param resCodeEntity
	 *            错误码描述
	 */
	public AssertError(int errorCode, ResCodeEntity resCodeEntity) {
		super(resCodeEntity.toString());
		this.errorCode = resCodeEntity;
	}

	/**
	 * 构造函数.
	 *
	 * @param errorCode
	 *            错误码
	 * @param message
	 *            错误码描述
	 */
	public AssertError(int errorCode, String message) {
		super(message);
		ResCodeEntity codeInfo = ResponseConsts.getCodeInfo(errorCode);
		codeInfo.setDesc(message);
		this.errorCode = codeInfo;
	}
	
	/**
	 * 构造函数.
	 *
	 * @param errorCode
	 *            错误码
	 * @param message
	 *            错误码描述
	 */
	public AssertError(int errorCode,String fileName,String partnerId,Long channelId,String message ) {
		
		
		ResCodeEntity codeInfo = ResponseConsts.getCodeInfo(errorCode);
		if(message!=null&&message!="") {
		codeInfo.setDesc(message);
		}
		this.errorCode = codeInfo;
		this.partnerId = partnerId;
		this.channelId = channelId;
		this.fileName = fileName;
	}


	/**
	 * 构造函数.
	 * 
	 * @param errorCode
	 *            错误码
	 * @param cause
	 *            cause exception
	 */
	public AssertError(int errorCode, Throwable cause) {
		this(errorCode, ResponseConsts.getCodeInfo(errorCode), cause);
	}

	/**
	 * 构造函数.
	 * 
	 * @param errorCode
	 *            错误码
	 *            错误码描述
	 * @param cause
	 *            cause exception
	 */
	public AssertError(int errorCode, ResCodeEntity errorEntity, Throwable cause) {
		super(errorEntity.toString() , cause);
		this.errorCode = errorEntity;
	}

	/**
	 * 构造函数.
	 *
	 * @param errorCode
	 *            错误码
	 * @param message
	 *            错误码描述
	 * @param cause
	 *            cause exception
	 */
	public AssertError(int errorCode, String message, Throwable cause) {
		super(message , cause);
		ResCodeEntity codeInfo = ResponseConsts.getCodeInfo(errorCode);
		codeInfo.setDesc(message);
		this.errorCode = codeInfo;
	}

	public AssertError(int errorCode ,Object... param) {
		super(ResponseConsts.getCodeDesc(errorCode,param));
        ResCodeEntity codeInfo = ResponseConsts.getCodeInfo(errorCode);
        codeInfo.setDesc(ResponseConsts.getCodeDesc(errorCode,param));
        this.errorCode = codeInfo;
	}

	/**
	 * @return the errorCode
	 */
	public ResCodeEntity getErrorCode() {
		return errorCode;
	}

}
