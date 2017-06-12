package com.istv.crypto.structure;

import java.util.HashMap;

//Table de correspondance pour Merkle-Hellman
public class CodingTable {
	
	private HashMap<Character, String> correspondance;
	
	public CodingTable(){
		correspondance = new HashMap<>();
		correspondance.put('A', "00000");
		correspondance.put('B', "00001");
		correspondance.put('C', "00010");
		correspondance.put('D', "00011");
		correspondance.put('E', "00100");
		correspondance.put('F', "00101");
		correspondance.put('G', "00110");
		correspondance.put('H', "00111");
		correspondance.put('I', "01000");
		correspondance.put('J', "01001");
		correspondance.put('K', "01010");
		correspondance.put('L', "01011");
		correspondance.put('M', "01100");
		correspondance.put('N', "01101");
		correspondance.put('O', "01110");
		correspondance.put('P', "01111");
		correspondance.put('Q', "10000");
		correspondance.put('R', "10001");
		correspondance.put('S', "10010");
		correspondance.put('T', "10011");
		correspondance.put('U', "10100");
		correspondance.put('V', "10101");
		correspondance.put('W', "10110");
		correspondance.put('X', "10111");
		correspondance.put('Y', "11000");
		correspondance.put('Z', "11001");
		
	}
	
	public HashMap<Character, String> getCorrespondance() {
		return correspondance;
	}

}
