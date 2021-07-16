package cn.com.ktm.mt.model.entity;


/*
　@Author:gaopeng
  @Description:
  @Date:Creted in 10:38　2019/9/28/028　　
  　　　　　　　
*/
@SuppressWarnings("serial")
public class TCountryVo {
	private String id;	 	//	
	private String countryName;	 	
	private String countryCode	; 	
	private String sort	 ;	//	
	public String getId() {
		return id;
	}
	 
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public void setId(String id) {
		this.id = id;
	}
	 
	 
}


   
