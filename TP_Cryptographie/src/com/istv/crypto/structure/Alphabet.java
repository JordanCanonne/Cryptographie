package com.istv.crypto.structure;

import java.util.ArrayList;
import java.util.List;

public class Alphabet {

	private List<Character> chiffred = new ArrayList<Character>();
	private List<Character> clear = new ArrayList<Character>();
	private char key;

	public Alphabet(char key, Type from){

		this.key=key;

		for(int i = 0; i <= 25; i++ ){
			this.clear.add(i, null);
			this.chiffred.add(i, null);
		}

		switch (from) {
		case CLEAR:

			for (int i = 0; i <= 25; i++) {
				clear.set(i, (char)(65 + (i/26)*6 + i));
			}

			for (int i = 0; i <= 25; i++) {
				char c = this.clear.get(i);
				int index = (( clear.indexOf(c) - keyToOffset(from) + 26 ) % 26 );
				chiffred.set(index, c);
			}

			break;

		case CHIFFRED:

			for (int i = 0; i <= 25; i++) {
				chiffred.set(i, (char)(65 + (i/26)*6 + i));
			}

			for (int i = 0; i <= 25; i++) {
				char c = this.chiffred.get(i);
				int index = (( chiffred.indexOf(c) + keyToOffset(from) + 26 ) % 26 );
				clear.set(index, c);
			}

			break;

		default:

			System.err.println("No type for origin text");

			break;
		}
	}

	public List<Character> getChiffred() {
		return chiffred;
	}

	public char getChiffred(char c){
		return chiffred.get(clear.indexOf(c));
	}

	public List<Character> getClear() {
		return clear;
	}

	public char getClear(char c){
		return clear.get(chiffred.indexOf(c));
	}

	public char getKey() {
		return key;
	}

	private int keyToOffset(Type from){
		if (from == Type.CLEAR)
			return clear.indexOf(this.key) + 1;
		return chiffred.indexOf(this.key) + 1;
	}

	public void setChiffred(List<Character> chiffred) {
		this.chiffred = chiffred;
	}

	public void setClear(List<Character> clear) {
		this.clear = clear;
	}

	public void setKey(char key) {
		this.key = key;
	}

	@Override
	public String toString() {
		return "Alphabet [\nclear=\n" + clear + ",\nchiffred=\n" + chiffred + ",\nkey=" + key + "\n]";
	}
}
