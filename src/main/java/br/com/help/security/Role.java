package br.com.help.security;

public enum Role {
	ROLE_ADMIN("ROLE_ADMIN"), 
	ROLE_USER("ROLE_USER");
	
	
	String text;

	Role(String t) {
		this.text = t;
	}

	public String getText() {
		return this.text;
	}

}
