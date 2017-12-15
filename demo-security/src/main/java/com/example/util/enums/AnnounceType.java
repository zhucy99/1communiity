package com.example.util.enums;

public enum AnnounceType {
	secondHand(1), share(2), question(3);
	private int code;

	AnnounceType(int code) {
		this.code = code;
	}
	public static String getNameByCode(int code) {
		for(AnnounceType type : AnnounceType.values()) {
			if(code==type.code) {
				return type.name();
			}
		}
		return null;
	}
}
