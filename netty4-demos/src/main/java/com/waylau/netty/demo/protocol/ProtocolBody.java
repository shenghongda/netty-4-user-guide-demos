/**
 * 
 */
package com.waylau.netty.demo.protocol;

import java.io.Serializable;

/**
 * 说明：协议消息体
 *
 * @author <a href="http://www.waylau.com">waylau.com</a> 2015年11月4日 
 */
public class ProtocolBody implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2675677077736752935L;
	public Object getBody() {
		return body;
	}
	public void setBody(Object body) {
		this.body = body;
	}
	private Object body;
	/**
	 * 
	 */
	public ProtocolBody() {
		// TODO Auto-generated constructor stub
	}

}