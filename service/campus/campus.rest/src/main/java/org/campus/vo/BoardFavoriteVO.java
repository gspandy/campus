package org.campus.vo;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class BoardFavoriteVO extends BoardVO {
	
	private String favoriteId;

	@ApiModelProperty(value = "收藏列表编号")
	public String getFavoriteId() {
		return favoriteId;
	}
	
	@ApiModelProperty(value = "收藏列表编号")
	public void setFavoriteId(String favoriteId) {
		this.favoriteId = favoriteId;
	}
	
}
