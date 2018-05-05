/**
 * 
 */
package com.weimhc.modules.image.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.thinkgem.javamg.common.utils.StringUtils;
import com.weimhc.framework.service.impl.TreeServiceImpl;
import com.weimhc.modules.image.entity.ImageAlbum;
import com.weimhc.modules.image.dao.ImageAlbumDao;

/**
 * 相册Service
 * @author zsm
 * @version 2017-05-17
 */
@Service
@Transactional(readOnly = true)
public class ImageAlbumService extends TreeServiceImpl<ImageAlbumDao, ImageAlbum> {

	
	public List<ImageAlbum> findList(ImageAlbum imageAlbum) {
		if (StringUtils.isNotBlank(imageAlbum.getParentIds())){
			imageAlbum.setParentIds(","+imageAlbum.getParentIds()+",");
		}
		return super.findList(imageAlbum);
	}
		
	
}