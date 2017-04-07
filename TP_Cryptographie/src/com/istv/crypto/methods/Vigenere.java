package com.istv.crypto.methods;

import com.istv.crypto.methods.interfaces.Cryptologist;
import com.istv.crypto.structure.Alphabet;
import com.istv.crypto.structure.Type;
import com.istv.crypto.utils.Utils;

public class Vigenere implements Cryptologist {


	@Override
	public String encrypt(String clear, String key) {

		if (Utils.isAuthorized(clear)){

			if (Utils.isAuthorized(key)) {

				// Initialisation des tableaux avec la taille du texte  clair
				char [] clearArray = clear.toCharArray();
				char [] keyArray = clear.toCharArray();
				char [] chiffred = clear.toCharArray();

				// Mise en place de la clé pour chaque lettre du texte clair
				int j = 0;
				char[] keyCharArray = key.toCharArray();
				for (int i = 0; i < clear.length(); i++){
					keyArray[i] = keyCharArray[j];
					if (j < key.length() - 1){
						j++;
					} else {
						j = 0;
					}
				}

				for (int i = 0; i < clear.length(); i++){
					Alphabet alphabet = new Alphabet(keyArray[i], Type.CLEAR);
					chiffred[i] = alphabet.getChiffred(clearArray[i]);
				}

				String chiffredStr = new String(chiffred);

				return chiffredStr;

			} else {
				return "Invalid key";
			}

		} else {
			return "Invalid input text.";
		}
	}

	@Override
	public String decrypt(String chiffred, String key) {

		if(Utils.isAuthorized(chiffred)) {

			if(Utils.isAuthorized(key)) {

				// Initialisation des tableaux avec la taille du texte chiffré
				char [] chiffredArray = chiffred.toCharArray();
				char [] keyArray = chiffred.toCharArray();
				char [] clear = chiffred.toCharArray();

				// Mise en place de la clé pour chaque lettre du texte clair
				int j = 0;
				char[] keyCharArray = key.toCharArray();
				for (int i = 0; i < chiffred.length(); i++){
					keyArray[i] = keyCharArray[j];
					if (j < key.length() - 1){
						j++;
					} else {
						j = 0;
					}
				}

				for (int i = 0; i < chiffred.length(); i++){
					Alphabet alphabet = new Alphabet(keyArray[i], Type.CHIFFRED);
					clear[i] = alphabet.getClear(chiffredArray[i]);
				}

				String clearStr = new String(clear);

				return clearStr;

			} else {
				return "Invalid key";
			}

		} else {
			return "Invalid input text.";
		}
	}

}
