package org.campus.model;

public class FavoriteFreshNews extends FreshNews {
	
	/**
	 *  ts_app_user_favorite.uid
	 */
	private String favoriteId;

	public String getFavoriteId() {
		return favoriteId;
	}

	public void setFavoriteId(String favoriteId) {
		this.favoriteId = favoriteId;
	}
	
}
