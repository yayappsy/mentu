/*
 * 
 * 
 * 
 */
package com.thinkgem.javamg.common.persistence;

import java.io.Serializable;
import java.util.regex.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thinkgem.javamg.common.config.Global;

/**
 * 分页信息
 * 
 * 
 * 
 */
public class Pageable implements Serializable {

	private static final long serialVersionUID = -3930180379790344299L;

	private int pageNo = 1; // 当前页码
	private int pageSize = Integer.valueOf(Global.getConfig("page.pageSize")); // 页面大小，设置为“-1”表示不进行分页（分页无效）

	private long count;// 总记录数，设置为“-1”表示不查询总数

	private int first;// 首页索引
	private int last;// 尾页索引
	private int prev;// 上一页索引
	private int next;// 下一页索引

	private boolean firstPage;// 是否是第一页
	private boolean lastPage;// 是否是最后一页

	private int displayNumberLength = 8;// 显示页面长度
	private int displayNumberSlider = 1;// 前后显示页面长度

	private String orderBy = ""; // 标准查询有效， 实例： updatedate desc, name asc

	private String funcName = "page"; // 设置点击页码调用的js函数名称，默认为page，在一页有多个分页对象时使用。

	private String funcParam = ""; // 函数的附加参数，第三个参数值。

	private String message = ""; // 设置提示消息，显示在“共n条”之后

	public Pageable() {

	}

	/**
	 * 构造方法
	 * 
	 * @param pageNo
	 *            当前页码
	 * @param pageSize
	 *            分页大小
	 */
	public Pageable(int pageNo, int pageSize) {
		this(pageNo, pageSize, 0);
	}

	/**
	 * 构造方法
	 * 
	 * @param pageNo
	 *            当前页码
	 * @param pageSize
	 *            分页大小
	 * @param count
	 *            数据条数
	 */
	public Pageable(int pageNo, int pageSize, long count) {
		this.setCount(count);
		this.setPageNo(pageNo);
		this.pageSize = pageSize;
	}

	/**
	 * 初始化参数
	 */
	public void initialize() {

		// 1
		this.first = 1;

		this.last = (int) (count / (this.pageSize < 1 ? 20 : this.pageSize) + first - 1);

		if (this.count % this.pageSize != 0 || this.last == 0) {
			this.last++;
		}

		if (this.last < this.first) {
			this.last = this.first;
		}

		if (this.pageNo <= 1) {
			this.pageNo = this.first;
			this.firstPage = true;
		}

		if (this.pageNo >= this.last) {
			this.pageNo = this.last;
			this.lastPage = true;
		}

		if (this.pageNo < this.last - 1) {
			this.next = this.pageNo + 1;
		} else {
			this.next = this.last;
		}

		if (this.pageNo > 1) {
			this.prev = this.pageNo - 1;
		} else {
			this.prev = this.first;
		}

		// 2
		if (this.pageNo < this.first) {// 如果当前页小于首页
			this.pageNo = this.first;
		}

		if (this.pageNo > this.last) {// 如果当前页大于尾页
			this.pageNo = this.last;
		}

	}

	/**
	 * 输出Pageable中的部分信息
	 */
	@Override
	public String toString() {
		return createToString();
	}

	private String createToString() {
		StringBuilder sb = new StringBuilder();

		sb.append("{");
		sb.append("pageNo: " + pageNo + ",");
		sb.append("pageSize: " + pageSize + ",");
		sb.append("first: " + first + ",");
		sb.append("last: " + last + ",");
		sb.append("firstPage: " + firstPage + ",");
		sb.append("lastPage: " + lastPage + ",");
		sb.append("orderBy: " + orderBy + ",");
		sb.append("}");

		return sb.toString();
	}

	/**
	 * 创建分页HTML代码
	 * 
	 * @return
	 */
	private String createHtml() {
		StringBuilder sb = new StringBuilder();

		if (pageNo == first) {// 如果是首页
			sb.append("<li class=\"disabled\"><a href=\"javascript:\">&#171; 上一页</a></li>\n");
		} else {
			sb.append("<li><a href=\"javascript:\" onclick=\"" + funcName + "(" + prev + ","
					+ pageSize + ",'" + funcParam + "');\">&#171; 上一页</a></li>\n");
		}

		int begin = pageNo - (displayNumberLength / 2);

		if (begin < first) {
			begin = first;
		}

		int end = begin + displayNumberLength - 1;

		if (end >= last) {
			end = last;
			begin = end - displayNumberLength + 1;
			if (begin < first) {
				begin = first;
			}
		}

		if (begin > first) {
			int i = 0;
			for (i = first; i < first + displayNumberSlider && i < begin; i++) {
				sb.append("<li><a href=\"javascript:\" onclick=\"" + funcName + "(" + i + ","
						+ pageSize + ",'" + funcParam + "');\">" + (i + 1 - first) + "</a></li>\n");
			}
			if (i < begin) {
				sb.append("<li class=\"disabled\"><a href=\"javascript:\">...</a></li>\n");
			}
		}

		for (int i = begin; i <= end; i++) {
			if (i == pageNo) {
				sb.append("<li class=\"active\"><a href=\"javascript:\">" + (i + 1 - first)
						+ "</a></li>\n");
			} else {
				sb.append("<li><a href=\"javascript:\" onclick=\"" + funcName + "(" + i + ","
						+ pageSize + ",'" + funcParam + "');\">" + (i + 1 - first) + "</a></li>\n");
			}
		}

		if (last - end > displayNumberSlider) {
			sb.append("<li class=\"disabled\"><a href=\"javascript:\">...</a></li>\n");
			end = last - displayNumberSlider;
		}

		for (int i = end + 1; i <= last; i++) {
			sb.append("<li><a href=\"javascript:\" onclick=\"" + funcName + "(" + i + "," + pageSize
					+ ",'" + funcParam + "');\">" + (i + 1 - first) + "</a></li>\n");
		}

		if (pageNo == last) {
			sb.append("<li class=\"disabled\"><a href=\"javascript:\">下一页 &#187;</a></li>\n");
		} else {
			sb.append("<li><a href=\"javascript:\" onclick=\"" + funcName + "(" + next + ","
					+ pageSize + ",'" + funcParam + "');\">" + "下一页 &#187;</a></li>\n");
		}

		sb.append("<li class=\"disabled controls\"><a href=\"javascript:\">当前 ");
		sb.append("<input type=\"text\" tilte=\"输入值之后，按回车查询\" value=\"" + pageNo
				+ "\" onkeypress=\"var e=window.event||this;var c=e.keyCode||e.which;if(c==13)");
		sb.append(funcName + "(this.value," + pageSize + ",'" + funcParam
				+ "');\" onclick=\"this.select();\"/> / ");
		sb.append("<input type=\"text\" tilte=\"输入值之后，按回车查询\" value=\"" + pageSize
				+ "\" onkeypress=\"var e=window.event||this;var c=e.keyCode||e.which;if(c==13)");
		sb.append(funcName + "(" + pageNo + ",this.value,'" + funcParam
				+ "');\" onclick=\"this.select();\"/> 条，");
		sb.append("共 " + count + " 条" + (message != null ? message : "") + "</a></li>\n");

		sb.insert(0, "<ul>\n").append("</ul>\n");

		sb.append("<div style=\"clear:both;\"></div>");

		// sb.insert(0,"<div class=\"page\">\n").append("</div>\n");

		return sb.toString();
	}

	/**
	 * 获取分页HTML代码
	 * 
	 * @return
	 */
	public String getHtml() {
		return createHtml();
	}

	// public static void main(String[] args) {
	// Page<String> p = new Page<String>(3, 3);
	// System.out.println(p);
	// System.out.println("首页："+p.getFirst());
	// System.out.println("尾页："+p.getLast());
	// System.out.println("上页："+p.getPrev());
	// System.out.println("下页："+p.getNext());
	// }

	/**
	 * 获取设置总数
	 * 
	 * @return
	 */
	public long getCount() {
		return count;
	}

	/**
	 * 设置数据总数
	 * 
	 * @param count
	 */
	public void setCount(long count) {
		this.count = count;
		if (pageSize >= count) {
			pageNo = 1;
		}
	}

	/**
	 * 获取当前页码
	 * 
	 * @return
	 */
	public int getPageNo() {
		return pageNo;
	}

	/**
	 * 设置当前页码
	 * 
	 * @param pageNo
	 */
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	/**
	 * 获取页面大小
	 * 
	 * @return
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * 设置页面大小（最大500）
	 * 
	 * @param pageSize
	 */
	public void setPageSize(int pageSize) {
		if (pageSize < -1) {
			pageSize = 10;
		}
		this.pageSize = pageSize > 500 ? 500 : pageSize;
	}

	/**
	 * 首页索引
	 * 
	 * @return
	 */
	@JsonIgnore
	public int getFirst() {
		return first;
	}

	/**
	 * 尾页索引
	 * 
	 * @return
	 */
	@JsonIgnore
	public int getLast() {
		return last;
	}

	/**
	 * 显示页面数字长度 如果页面显示长度为8，则加上当前页面共显示8个数字。不包括前后显示
	 * 
	 * @return
	 */
	@JsonIgnore
	public int getDisplayNumberLength() {
		return displayNumberLength;
	}

	public void setDisplayNumberLength(int displayNumberLength) {
		this.displayNumberLength = displayNumberLength;
	}

	/**
	 * 页面数字前后显示长度 如果为1，则页面显示为 1..7 8...2 5; 如果为2，则页面显示为 1 2..7 8...24 25
	 * 
	 * @return
	 */
	@JsonIgnore
	public int getDisplayNumberSlider() {
		return displayNumberSlider;
	}

	public void setDisplayNumberSlider(int displayNumberSlider) {
		this.displayNumberSlider = displayNumberSlider;
	}

	/**
	 * 是否为第一页
	 * 
	 * @return
	 */
	@JsonIgnore
	public boolean isFirstPage() {
		return firstPage;
	}

	/**
	 * 是否为最后一页
	 * 
	 * @return
	 */
	@JsonIgnore
	public boolean isLastPage() {
		return lastPage;
	}

	/**
	 * 上一页索引值
	 * 
	 * @return
	 */
	@JsonIgnore
	public int getPrev() {
		if (isFirstPage()) {
			return pageNo;
		} else {
			return pageNo - 1;
		}
	}

	/**
	 * 下一页索引值
	 * 
	 * @return
	 */
	@JsonIgnore
	public int getNext() {
		if (isLastPage()) {
			return pageNo;
		} else {
			return pageNo + 1;
		}
	}

	/**
	 * 获取查询排序字符串
	 * 
	 * @return
	 */
	@JsonIgnore
	public String getOrderBy() {
		// SQL过滤，防止注入
		String reg = "(?:')|(?:--)|(/\\*(?:.|[\\n\\r])*?\\*/)|"
				+ "(\\b(select|update|and|or|delete|insert|trancate|char|into|substr|ascii|declare|exec|count|master|into|drop|execute)\\b)";
		Pattern sqlPattern = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);
		if (sqlPattern.matcher(orderBy).find()) {
			return "";
		}
		return orderBy;
	}

	/**
	 * 设置查询排序，标准查询有效， 实例： updatedate desc, name asc
	 */
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	/**
	 * 获取点击页码调用的js函数名称 function ${page.funcName}(pageNo){location=
	 * "${ctx}/list-${category.id}${urlSuffix}?pageNo="+i;}
	 * 
	 * @return
	 */
	@JsonIgnore
	public String getFuncName() {
		return funcName;
	}

	/**
	 * 设置点击页码调用的js函数名称，默认为page，在一页有多个分页对象时使用。
	 * 
	 * @param funcName
	 *            默认为page
	 */
	public void setFuncName(String funcName) {
		this.funcName = funcName;
	}

	/**
	 * 获取分页函数的附加参数
	 * 
	 * @return
	 */
	@JsonIgnore
	public String getFuncParam() {
		return funcParam;
	}

	/**
	 * 设置分页函数的附加参数
	 * 
	 * @return
	 */
	public void setFuncParam(String funcParam) {
		this.funcParam = funcParam;
	}

	/**
	 * 设置提示消息，显示在“共n条”之后
	 * 
	 * @param message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * 设置提示消息，显示在“共n条”之后
	 * 
	 * @param message
	 */
	public String getMessage() {
		return this.message;
	}

	/**
	 * 分页是否有效
	 * 
	 * @return this.pageSize==-1
	 */
	@JsonIgnore
	public boolean isDisabled() {
		return this.pageSize == -1;
	}

	/**
	 * 是否进行总数统计
	 * 
	 * @return this.count==-1
	 */
	@JsonIgnore
	public boolean isNotCount() {
		return this.count == -1;
	}

	/**
	 * 获取 Hibernate FirstResult
	 */
	public int getFirstResult() {
		int firstResult = (getPageNo() - 1) * getPageSize();
		if (firstResult >= getCount()) {
			firstResult = 0;
		}
		return firstResult;
	}

	/**
	 * 获取 Hibernate MaxResults
	 */
	public int getMaxResults() {
		return getPageSize();
	}

	// /**
	// * 获取 Spring data JPA 分页对象
	// */
	// public Pageable getSpringPage(){
	// List<Order> orders = new ArrayList<Order>();
	// if (orderBy!=null){
	// for (String order : StringUtils.split(orderBy, ",")){
	// String[] o = StringUtils.split(order, " ");
	// if (o.length==1){
	// orders.add(new Order(Direction.ASC, o[0]));
	// }else if (o.length==2){
	// if ("DESC".equals(o[1].toUpperCase())){
	// orders.add(new Order(Direction.DESC, o[0]));
	// }else{
	// orders.add(new Order(Direction.ASC, o[0]));
	// }
	// }
	// }
	// }
	// return new PageRequest(this.pageNo - 1, this.pageSize, new Sort(orders));
	// }
	//
	// /**
	// * 设置 Spring data JPA 分页对象，转换为本系统分页对象
	// */
	// public void setSpringPage(org.springframework.data.domain.Page<T> page){
	// this.pageNo = page.getNumber();
	// this.pageSize = page.getSize();
	// this.count = page.getTotalElements();
	// this.list = page.getContent();
	// }

}
