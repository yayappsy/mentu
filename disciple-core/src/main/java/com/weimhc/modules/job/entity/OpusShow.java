/**
 * 
 */
package com.weimhc.modules.job.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.javamg.common.persistence.DataEntity;

/**
 * 作品展示Entity
 * @author cwl
 * @version 2017-12-26
 */
public class OpusShow extends DataEntity<OpusShow>{
	
	private static final long serialVersionUID = 1L;
	/**
	 * 简历id
	 * 
	 */		
	private String resumeId;	
	/**
	 * 会员id
	 * 
	 */		
	private String memberId;	
	/**
	 * 作品链接
	 * 
	 */		
	private String url;	
	/**
	 * 作品说明
	 * 
	 */		
	private String explain;	
	
	public OpusShow() {
		super();
	}

	public OpusShow(String id){
		super(id);
	}

	/**
	 * 获取简历id
	 * 
	 * @return 简历id
	 */	
	@Length(min=0, max=64)
	public String getResumeId() {
		return resumeId;
	}
	/**
	 * 设置简历id
	 * 
	 * @param resumeId
	 *            简历id
	 */
	public void setResumeId(String resumeId) {
		this.resumeId = resumeId;
	}
	
	/**
	 * 获取会员id
	 * 
	 * @return 会员id
	 */	
	@Length(min=0, max=64)
	public String getMemberId() {
		return memberId;
	}
	/**
	 * 设置会员id
	 * 
	 * @param memberId
	 *            会员id
	 */
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	
	/**
	 * 获取作品链接
	 * 
	 * @return 作品链接
	 */	
	@Length(min=1, max=255)
	public String getUrl() {
		return url;
	}
	/**
	 * 设置作品链接
	 * 
	 * @param url
	 *            作品链接
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	
	/**
	 * 获取作品说明
	 * 
	 * @return 作品说明
	 */	
	public String getExplain() {
		return explain;
	}
	/**
	 * 设置作品说明
	 * 
	 * @param explain
	 *            作品说明
	 */
	public void setExplain(String explain) {
		this.explain = explain;
	}
	
}