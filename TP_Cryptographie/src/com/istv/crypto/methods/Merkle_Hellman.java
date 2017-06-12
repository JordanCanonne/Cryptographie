package com.istv.crypto.methods;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.istv.crypto.structure.CodingTable;
import com.istv.crypto.structure.PrivateKeyMerkleHellman;
import com.istv.crypto.utils.Utils;

public class Merkle_Hellman {

	public PrivateKeyMerkleHellman privateKey;
	public List<Integer> publicKey;

	public List<Integer> encrypt(String clear){


		List<Integer> chiffred = new ArrayList<Integer>();

		CodingTable codingTable = new CodingTable();
		//Choix d'un n fixe comme vu en cours
		int n = 6;
		List<Integer> superGrowingList = Utils.generateSuperGrowingList(n);
		int m = Utils.generateM(superGrowingList);
		int p = Utils.generateP(m);
		List<Integer> notSuperGrowingList = Utils.generateNOTSuperGrowingList(p, m, superGrowingList);

		char[] clearArray = clear.toCharArray();
		String clearToBits = "";
		for(char c : clearArray){
			clearToBits += codingTable.getCorrespondance().get(c);
		}
		//On ajoute aléatoirement des 0 ou 1 pour arriver à un multiple de n.
		while(!((clearToBits.length() % n) == 0)){
			clearToBits += Utils.generateBit();
		}

		int i = 0;
		int result = 0;

		for(char c : clearToBits.toCharArray()){

			int bit = Integer.valueOf(String.valueOf(c));
			if (bit == 1) {
				result += notSuperGrowingList.get(i) * bit;
			}
			i++;
			if (i == n){
				chiffred.add(result);
				result = 0;
				i = 0;
			}
		}

		//Initialisation des clés public et privée.
		this.publicKey = notSuperGrowingList;
		this.privateKey = new PrivateKeyMerkleHellman(superGrowingList, m, p);

		System.out.println("Variables utilisées pour ce cryptage : \n    m : " + m + "\n    p : " + p + "\n    Liste non Super Croissante : " + notSuperGrowingList + "\n");

		return chiffred;
	}

	public String decipher(List<Integer> chiffred, int m, int p, List<Integer> notSuperGrowingList){

		String clear = "";
		List<Integer> superGrowingList = new ArrayList<Integer>();

		//Calcul de l'inverse multiplicatif de p modulo m
		int multiplicativeInverse = Utils.getMulitplicativeInverse(p, m);

		Iterator<Integer> iterator = notSuperGrowingList.iterator();
		//Construction de la liste superCroissante
		//Parcours de la liste non supperCroissante
		while (iterator.hasNext()) {
			Integer memberNSGL = (Integer) iterator.next();
			//Chaque terme est égal au term de la liste non superCroissante multiplié par l'inverse muliplicatif modulo m.
			int term = (memberNSGL * multiplicativeInverse) % m;
			superGrowingList.add(term);
		}


		String resultBits = "";
		String resultStr = "";

		iterator = chiffred.iterator();

		// Pour chaque entier du chiffré
		while (iterator.hasNext()) {
			Integer memberChiffred = (Integer) iterator.next();
			// Calcul de ce (nombre * inverse multipicatif) modulo m
			memberChiffred = (memberChiffred * multiplicativeInverse) % m;
			int resultInt = 0;
			int i = superGrowingList.size();
			// Recherche de la décomposition du nombre obtenu en fonction de la liste super croissante
			while(i > 0){
				int memberSGL = superGrowingList.get(i - 1);
				if (memberSGL + resultInt <= memberChiffred){
					resultInt += memberSGL;
					resultBits = '1' + resultBits;
				} else {
					resultBits = '0' + resultBits;
				}
				i--;
			}
			resultStr += resultBits;
			resultBits = "";
		}

		// Création de bloc de 5 bits pour faire la correspondance
		List<String> resultStrList = new ArrayList<>();
		int i = 0;
		char[] resultBitsTab = resultStr.toCharArray(); 
		String bloc5 = "";
		int index = 0;
		// On ne parcours pas les bits qui ont été généré pour arrivé à un multiple de n.
		while (i < (resultStr.length() - (resultStr.length() % 5))){
			if ( index < 5){
				bloc5 += resultBitsTab[i];
				index ++;
			} else {
				index = 0;
				resultStrList.add(bloc5);
				bloc5 = "";
				bloc5 += resultBitsTab[i];
				index ++;
			}
			i++;
			//Ajout du dernier bloc de 5
			if(i >= (resultStr.length() - (resultStr.length() % 5))){
				resultStrList.add(bloc5);
			}
		}
		
		
		
		CodingTable codingTable = new CodingTable();
		
		
		// On affecte les lettres décryptée en fonction de la table de codage
		Iterator<String> iterator2 = resultStrList.iterator();
		while (iterator2.hasNext()) {
			String bloc5Str = (String) iterator2.next();
			Set<Character> keys = codingTable.getCorrespondance().keySet();
			Iterator<Character> keyIterator = keys.iterator();
			while (keyIterator.hasNext()) {
				Character character = (Character) keyIterator.next();
				if (codingTable.getCorrespondance().get(character).equals(bloc5Str)){
					clear += character;
				}
			}
			
		}

		return clear;
	}

}
