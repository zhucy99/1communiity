package com.example.util.enums;

import java.util.Locale;

public enum LocaleLanguage {
	fr(Locale.FRANCE),cn(Locale.CHINA);
	private Locale locale;

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	LocaleLanguage(Locale locale) {
		this.locale = locale;
	}
	
	public static Locale getLocaleByCode(String code) {
		for(LocaleLanguage locale : LocaleLanguage.values()) {
			if(locale.name().equals(code)) {
				return locale.getLocale();
			}
		}
		return null;
	}
	
}
