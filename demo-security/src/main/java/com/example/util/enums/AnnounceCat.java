package com.example.util.enums;

public enum AnnounceCat {
	secondHand("secondHand_cat"), share("share_cat"), question("question_cat");
	private String code;

	AnnounceCat(String code) {
		this.code = code;
	}
	public String getCode() {
		return code;
	}
	public static String getNameByCode(String code) {
		for(AnnounceCat type : AnnounceCat.values()) {
			if(code==type.code) {
				return type.name();
			}
		}
		return null;
	}
}
