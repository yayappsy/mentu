/**
 * 
 */
package com.weimhc.modules.image.dao;

import com.thinkgem.javamg.common.persistence.annotation.MyBatisDao;
import com.weimhc.modules.image.entity.Image;

import com.weimhc.framework.persistence.SortableDao;

/**
 * 图片DAO接口
 * @author zsm
 * @version 2017-05-17
 */
@MyBatisDao
public interface ImageDao extends SortableDao<Image> {

}