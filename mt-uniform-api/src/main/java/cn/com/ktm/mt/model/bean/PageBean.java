package cn.com.ktm.mt.model.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageBean<T> {

	private Integer totalCount;

	private Integer pageSize = 10;

	private Integer pageIndex = 1;

	private List<T> result;

}
