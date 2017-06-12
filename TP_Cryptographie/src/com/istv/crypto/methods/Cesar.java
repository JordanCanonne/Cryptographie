package com.istv.crypto.methods;

import java.util.ArrayList;
import java.util.List;

import com.istv.crypto.structure.Alphabet;
import com.istv.crypto.structure.Type;
import com.istv.crypto.utils.Utils;

public class Cesar {

	public Cesar(){

	}

	public String encrypt(String clear, String key) {		

		if (Utils.isAuthorized(clear)) {
			if (key.length() == 1){
				char k = key.charAt(0);
				char[] clearC = clear.toCharArray();
				// On place le texte clair dans le tableau du chiffré simplement pour avoir un tableau de bonne dimension.
				char[] chiffredC = clear.toCharArray();

				Alphabet alphabet = new Alphabet(k, Type.CLEAR);
				
				for(int i = 0; i < clear.length(); i++){
					char c = clearC[i];
					chiffredC[i]=alphabet.getChiffred(c);
				}
				String result = new String(chiffredC);
				return result;
			} else {
				return "Invalid key.";
			}
		} else {
			return "Invalid input text.";
		}
	}


	public String decipher(String chiffred, String key) {

		if(Utils.isAuthorized(chiffred)){
			if (key.length() == 1){
				char k = key.charAt(0);
				char [] chiffredC = chiffred.toCharArray();
				// On place le chiffré dans le tableau du texte clair simplement pour avoir un talbeau de bonne dimension.
				char[] clearC = chiffred.toCharArray();

				Alphabet alphabet = new Alphabet(k,  Type.CHIFFRED);

				for(int i = 0; i < chiffred.length(); i++){
					char c = chiffredC[i];
					clearC[i]=alphabet.getClear(c);
				}

				String result = new String (clearC);
				return result;
			} else {
				return "Invalid key.";
			}
		} else {
			return "Invalid input text.";
		}
	}

	// Fonction de décryptage
	public List<String> decrypt (String chiffred){
		List<String> result = new ArrayList<String>();
		
		// Liste de toutes les solutions possibles
		for (int i = 0; i <= 25; i++) {
			char key = (char)(65 + (i/26)*6 + i);
			result.add(this.decipher(chiffred, String.valueOf(key)));
		}
		return result;
	}

}
