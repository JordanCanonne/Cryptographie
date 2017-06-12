package com.istv.crypto.methods;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.istv.crypto.structure.Alphabet;
import com.istv.crypto.structure.Type;
import com.istv.crypto.utils.Utils;

public class Vigenere {

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

	public String decipher(String chiffred, String key) {

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
	
	public String decrypt(String chiffred){
		if (Utils.isAuthorized(chiffred)){
			String clear = "";
			HashMap<Integer, Float> indices = new HashMap<Integer, Float>(); 
			int keySize = 1;
			// La taille maximum de la clé est fixé à 25, taille du mot le plus long du dictonnaire francais
			int maxKeySize = 25;
			while(keySize <= maxKeySize){
				List<String> subsequences = Utils.getSubsequences(chiffred, keySize);
				float indexAVG = 0;
				for (Iterator<String> iterator = subsequences.iterator(); iterator.hasNext();) {
					String subsequence = (String) iterator.next();
					float index = Utils.getCoincidenceIndex(subsequence);
					indexAVG += index;
				}
				indexAVG = indexAVG / subsequences.size();
				indices.put(keySize, indexAVG);
				keySize++;
			}
			
			// arbitraire, delta grand
			float delta = (float)100;
			// source : Friedman
			float frenchIndex = (float) 0.0778;
			int keyLength = 1;
			// Recherche de la longueur de clé qui donne l'indice de coincidence le plus proche de la langue francaise.
			for(keySize = 1; keySize <= maxKeySize;  keySize++){
				if ( Math.abs(frenchIndex - indices.get(keySize)) <= delta ){
					delta = Math.abs(frenchIndex - indices.get(keySize));
					keyLength = keySize;
				}
			}
			// On a maintenant la longueur probable de la clé.
			
			List<String> subsequences = Utils.getSubsequences(chiffred, keyLength);
			
			String key = "";
			
			Iterator<String> iterator = subsequences.iterator();
			
			while (iterator.hasNext()) {
				String subsequence = (String) iterator.next();
				char moreUsedChar = Utils.getMoreUsedChar(subsequence);
				char elementKey = Utils.getShift(moreUsedChar, 'E');
				key += elementKey;
			}
			
			clear = this.decipher(chiffred, key);
			
			return clear;
		} else {
			return "Invalid input text";
		}
	}

}
