/*
 * JsonMsg    Copyright (c) by HaoHan Corp.
 *
 * at 2016-11-11 14:44  by udi
 */

package com.weimhc.framework.web;

import com.alibaba.druid.support.json.JSONUtils;

/**
 * Created by udi on 2016/11/11.
 */
public class JsonMsg  {
    public String getMsg() {
        return msg;
    }

    public JsonMsg setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    String msg;

    public Object getData() {
        return data;
    }

    public JsonMsg setData(Object data) {
        this.data = data;
        return this;
    }

    Object data;

    @Override
    public String toString(){
        return JSONUtils.toJSONString(this);
    }
}
