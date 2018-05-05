package com.easemob.server.example.api.impl;

import org.springframework.stereotype.Service;

import com.easemob.server.example.api.AuthTokenAPI;
import com.easemob.server.example.comm.TokenUtil;

@Service
public class EasemobAuthToken implements AuthTokenAPI {

	@Override
	public Object getAuthToken() {
		return TokenUtil.getAccessToken();
	}
}
