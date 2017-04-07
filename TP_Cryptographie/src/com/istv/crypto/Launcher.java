package com.istv.crypto;

import com.istv.crypto.methods.Cesar;
import com.istv.crypto.methods.Vigenere;
import com.istv.crypto.methods.interfaces.Cryptologist;
import com.istv.crypto.structure.Alphabet;
import com.istv.crypto.structure.Type;	

public class Launcher {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Cryptologist cryptologist = new Cesar();
		Alphabet alphabet = new Alphabet('B', Type.CLEAR);
		System.out.println(alphabet);
		
		alphabet = new Alphabet('B', Type.CHIFFRED);
		System.out.println(alphabet);
		//TODO
		//TODO penser � faire une fonction qui enl�ve les espaces et remplace les caract�res sp�ciaux
		//TODO modification � fair edans le Utils
		//TODO
		System.out.println(cryptologist.encrypt("MAXIMEAUNEPETITEBITE", "B"));
		System.out.println(cryptologist.decrypt("OCZKOGCWPGRGVKVGDKVG", "B"));

		Cryptologist cryptologist2 = new Vigenere();
		System.out.println(cryptologist2.encrypt("TEST", "KEY"));
		System.out.println(cryptologist2.decrypt("EJRE", "KEY"));
		
		System.out.println("test");

	}

}
