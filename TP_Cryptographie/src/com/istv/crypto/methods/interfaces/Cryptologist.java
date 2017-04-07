package com.istv.crypto.methods.interfaces;

public interface Cryptologist {

	public String encrypt(String clear, String key);
	public String decrypt(String chiffred, String key);
	
}
