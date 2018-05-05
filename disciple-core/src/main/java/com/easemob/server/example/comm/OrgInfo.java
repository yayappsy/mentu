package com.easemob.server.example.comm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thinkgem.javamg.common.config.Global;

/**
 * Created by easemob on 2017/3/31.
 */
public class OrgInfo {

	public static String ORG_NAME = Global.getConfig("easemob.orgName");
	public static String APP_NAME = Global.getConfig("easemob.appName");
	public static final Logger logger = LoggerFactory.getLogger(OrgInfo.class);

}
