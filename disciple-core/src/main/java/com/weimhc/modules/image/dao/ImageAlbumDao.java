/**
 * 
 */
package com.weimhc.modules.image.dao;

import com.thinkgem.javamg.common.persistence.annotation.MyBatisDao;
import com.weimhc.framework.persistence.TreeDao;
import com.weimhc.modules.image.entity.ImageAlbum;

/**
 * 相册DAO接口
 * @author zsm
 * @version 2017-05-17
 */
@MyBatisDao
public interface ImageAlbumDao extends TreeDao<ImageAlbum> {
	
}