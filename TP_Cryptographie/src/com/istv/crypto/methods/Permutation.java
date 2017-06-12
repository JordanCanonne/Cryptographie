package com.istv.crypto.methods;

import com.istv.crypto.structure.Alphabet;
import com.istv.crypto.structure.FrequencyTable;
import com.istv.crypto.utils.Utils;

public class Permutation {

	public String encrypt(String clear, String key) {	
		if (Utils.isAuthorized(clear)){
			if(Utils.isAuthorizedAlphabet(key).equals("true")){
				
				// Initialisation des tableaux avec la taille du texte  clair
				char [] clearArray = clear.toCharArray();
				char [] chiffred = clear.toCharArray();
				
				Alphabet alphabet = new Alphabet(key);
				
				for (int i = 0; i < clear.length(); i++){
					chiffred[i] = alphabet.getChiffred(clearArray[i]);
				}

				String chiffredStr = new String(chiffred);

				return chiffredStr;
				
			} else {
				return Utils.isAuthorizedAlphabet(key);
			}
		} else {
			return "invalid input text.";
		}
	}

	public String decipher(String chiffred, String key) {
		if (Utils.isAuthorized(chiffred)){
			if(Utils.isAuthorizedAlphabet(key).equals("true")){
				
				// Initialisation des tableaux avec la taille du texte  clair
				char [] chiffredArray = chiffred.toCharArray();
				char [] clear = chiffred.toCharArray();
				
				// On construit un alphabet en fonction d'un alphabet donn� en param�tre
				// Attention : cet alphabet doit respecter les r�gles.
				Alphabet alphabet = new Alphabet(key);
				
				// Indentique au d�chiffrement de C�sar pour chaque lettre
				for (int i = 0; i < chiffred.length(); i++){
					clear[i] = alphabet.getClear(chiffredArray[i]);
				}

				String clearStr = new String(clear);

				return clearStr;
				
			} else {
				return Utils.isAuthorizedAlphabet(key);
			}
		} else {
			return "invalid input text.";
		}
	}
	
	// Fonction de d�cryptage
	// Marche uniquement pour les textes tr�s longs
	public String decrypt(String chiffred){
		char[] clear = chiffred.toCharArray();
		
		char[] chiffredTab = chiffred.toCharArray();
		
		// Construction d'une table contenant les fr�quence d'apparition de chaque lettre pour un texte donn� en param�tre
		FrequencyTable frequencyTable = new FrequencyTable(chiffred);
		
		// Pour chaque lettre du chiffred on r�cup�re la correspondance dans la trable
		for (int i = 0; i < chiffredTab.length; i++) {
			clear[i] = frequencyTable.getCorrespondence().get(chiffredTab[i]);
		}
		
		return new String(clear);
	}
	
}
