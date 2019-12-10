package com.prisonapp.apppush.ios;

import com.prisonapp.apppush.IOSNotification;

public class IOSUnicast extends IOSNotification {
	public IOSUnicast(String appkey, String appMasterSecret) throws Exception {
		setAppMasterSecret(appMasterSecret);
		setPredefinedKeyValue("appkey", appkey);
		this.setPredefinedKeyValue("type", "unicast");
	}

	public void setDeviceToken(String token) throws Exception {
		setPredefinedKeyValue("device_tokens", token);
	}
}
