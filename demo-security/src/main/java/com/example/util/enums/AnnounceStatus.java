package com.example.util.enums;

public enum AnnounceStatus {
	created(1), published(2), finished(3), cancelled(3);
	private int code;

	AnnounceStatus(int code) {
		this.code = code;
	}
	public static String getNameByCode(int code) {
		for(AnnounceStatus status : AnnounceStatus.values()) {
			if(code==status.code) {
				return status.name();
			}
		}
		return null;
	}
}
