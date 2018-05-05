/**
 * 
 */
package com.weimhc.modules.image.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weimhc.modules.image.entity.Image;
import com.weimhc.modules.image.dao.ImageDao;

import com.weimhc.framework.service.impl.SortableServiceImpl;

/**
 * 图片Service
 * @author zsm
 * @version 2017-05-17
 */
@Service
@Transactional(readOnly = true)
public class ImageService extends SortableServiceImpl<ImageDao, Image> {

	
}