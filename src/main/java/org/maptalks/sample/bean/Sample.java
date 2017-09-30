package org.maptalks.sample.bean;

import java.io.Serializable;
import java.math.BigDecimal;

import cn.com.seegoo.web.util.BigDecimalTool;
import cn.com.seegoo.web.util.TimeTool;

/**
 * 项目名：seegoo-jquery-spring-archetype
 * 类   名：Sample.java
 * 作   者：wangjun
 * 日   期：2013-6-30
 * 说   明：seegoo maven框架实体bean例子
 */
public class Sample implements Serializable{
	

	/**
	 * 序列化时为了保持版本的兼容性，在版本升级时反序列化仍保持对象的唯一性。
	 * 最好在实体bean的属性、方法都写好之后再生成，这样可以根据类名、接口名、成员方法及属性
	 * 生成一个64位的哈希属性。下方的序列版本UID是默认值。
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 表字段
	 */
	public static String TABLE_NAME = new String("SEEGOO_SAMPLE");
	
	public static String FIELD_ID = new String("SAMPLE_ID");
	
	public static String FIELD_NAME = new String("SAMPLE_NAME");
	
	public static String FIELD_STATUS = new String("SAMPLE_STATUS");
	
	public static String FIELD_CREATEDATE = new String("SAMPLE_CREATEDATE");
	
	public static String FIELD_MONEY = new String("SAMPLE_MONEY");
	
	/**
	 * 长整型属性
	 */
	private long id;
	
	/**
	 * 字符串属性
	 */
	private String name;
	
	/**
	 * 整型属性
	 */
	private int status;
	
	/**
	 * 日期类型持久化成long类型
	 */
	private long createDate;
	
	/**
	 * 转化java.util.Date为日期字符串
	 */
	private String createDateStr;
	
	/**
	 * 大数字属性
	 */
	private BigDecimal money;
	
	/**
	 * 转化BigDecimal为数字字符串
	 */
	private String moneyStr;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public long getCreateDate() {
		return createDate;
	}

	public void setCreateDate(long createDate) {
		this.createDate = createDate;
	}

	public String getCreateDateStr() {
		return TimeTool.longTimeToStrDateTime(this.createDate);
	}

	public void setCreateDateStr(String createDateStr) {
		this.createDateStr = createDateStr;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public String getMoneyStr() {
		return BigDecimalTool.getWebStr(this.money, true, false) ;
	}

	public void setMoneyStr(String moneyStr) {
		this.moneyStr = moneyStr;
	}

	@Override
	public String toString() {
		return "Sample [id=" + id + ", name=" + name + ", status=" + status
				+ ", createDate=" + createDateStr + ", money=" + moneyStr + "]";
	}


}
