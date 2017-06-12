package com.istv.crypto.structure;

import java.util.List;


// D�finition de la cl� priv�e pour le protoco:e Merkle Hellman
public class PrivateKeyMerkleHellman {
	
	List<Integer> SuperGrowingList;
	int m;
	int p;
	
	
	public PrivateKeyMerkleHellman(List<Integer> notSuperGrowingList, int m, int p) {
		super();
		this.SuperGrowingList = notSuperGrowingList;
		this.m = m;
		this.p = p;
	}


	public List<Integer> getSuperGrowingList() {
		return SuperGrowingList;
	}


	public int getM() {
		return m;
	}


	public int getP() {
		return p;
	}
	
	

}
