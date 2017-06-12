package com.istv.crypto.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.istv.crypto.structure.Alphabet;
import com.istv.crypto.structure.Type;

public class Utils {

	//Fonction qui vérifie si un texte est valide
	public static boolean isAuthorized(String text) {
		if (text.isEmpty())
			return false;
		return true;
	}

	//Fonction qui vérifie si un alphabet est conforme
	public static String isAuthorizedAlphabet(String key) {
		if (isAuthorized(key)){
			if (isOk(key)){
				return "true";
			} else {
				return "Invalid key : more/less than 26 characters or Absent character(s).";
			}
		} else {
			return "Invalid key.";
		}
	}

	//Fonction qui vérifie si un alphabet contient bien une et une seule fois chaque lettre de l'alphabet
	private static boolean isOk(String key){
		if (key.length() != 26){
			return false;
		}
		for (int i = 0; i <= 25; i++) {
			char c = (char) (65 + (i/26)*6 + i);
			if (key.indexOf(c) == -1){
				return false;
			}
		}
		return true;
	}

	//Fonction qui retroune l'indice de coincidence d'un texte
	public static float getCoincidenceIndex(String text){

		float index = 0;
		int length = text.length();
		// Pour chaque lettre i, on calcul (ni(ni-1))/(n*(n-1))
		// On ajoute le résultat à l'index total pour obtenir la somme globale
		for (int i = 0; i <= 25; i++) {
			char c = (char) (65 + (i/26)*6 + i);
			float occur = (float)countOccurrency(text, c);
			index = index + (occur * (occur - 1))/(length * (length - 1)); 
		}
		return index;
	}

	
	//Fonction qui retourne une liste de String.
	//Cette liste correspond à la liste des sous séquence d'un texte en fonction de la longueur d'une clé 
	public static List<String> getSubsequences(String sequence, int length){

		List<String> subsequences = new ArrayList<String>();
		char[] sequenceChar = sequence.toCharArray();

		for(int i = 0; i < length; i++){
			String subsequence = "";
			int j =  i;

			while(j < sequence.length()){
				subsequence += sequenceChar[j];
				j += length;
			}
			subsequences.add(subsequence);
		}
		return subsequences;
	}

	//Fonction qui retorune le nombre d'occurence d'un caractère dans un texte
	private static int countOccurrency(String text, char c)
	{
		int count = 0;
		for (int i=0; i < text.length(); i++)
		{
			if (text.charAt(i) == c)
			{
				count++;
			}
		}
		return count;
	}

	
	//Fonction qui génère une liste super croissante de taille n
	public static List<Integer> generateSuperGrowingList(int n){
		List<Integer> superGrowing = new ArrayList<Integer>();
		for(int i = 0; i < n; i++){
			int number = findNumberToSuperGrowingList(superGrowing);
			superGrowing.add(number);
		}
		return superGrowing;
	}

	
	// Fonction qui retourne un nombre aléatoire supérieur à la somme des terme d'une liste supercroissante
	private static int findNumberToSuperGrowingList(List<Integer> superGrowing) {
		int number = 1;
		int min = sumIntegerList(superGrowing);
		// Choix arbitraire d'avoir une valeur assez proche pour éviter les limites du type Integer. 
		int max = min + 100;
		number = (int)(Math.random()*(max-min))+ min + 1;
		return number;
	}

	
	// Fonction qui retourne la somme des termes d'une liste d'entiers.
	private static int sumIntegerList(List<Integer> superGrowingList) {
		int sum = 0;
		Iterator<Integer> iterator = superGrowingList.iterator();
		while (iterator.hasNext()) {
			Integer integer = (Integer) iterator.next();
			sum += (int)integer;
		}
		return sum;
	}

	
	// Fonction qui retourne une liste non superCroissante en fonction de m, p et une liste superCroissante passée en paramètre
	public static List<Integer> generateNOTSuperGrowingList(int p, int m, List<Integer> superGrowingList){
		List<Integer> notSuperGrowingList = new ArrayList<Integer>();

		Iterator<Integer> iterator = superGrowingList.iterator();
		while (iterator.hasNext()) {
			Integer integer = (Integer) iterator.next();
			int number = (integer * p) % m;
			notSuperGrowingList.add(number);
		}

		return notSuperGrowingList;
	}

	//Fonction de génération du paramètre m
	public static int generateM(List<Integer> superGrowingList) {
		return findNumberToSuperGrowingList(superGrowingList);
	}

	//Fonction de génération du paramètre p
	public static int generateP(int m) {

		int p = m;
		int max = p + 100;
		int min = 2;
		// On vérifie que p est premier avec le paramètre m
		while (PGCD(p, m) != 1){
			p = (int)(Math.random()*(max-min))+ min + 1;
		}
		return p;
	}

	
	// Fonction qui génère aléatoirement un bit
	// 0 ou 1
	public static String generateBit() {
		return String.valueOf((int)(Math.random()*2));
	}


	// Calcul de l'inverse multiplicatif de p modulo m
//	public static int getMulitplicativeInverse(int p, int m){
//
//		int result = 0;
//
//		//On verifie bien que m et p soient premiers entre eux
//		if (PGCD(p, m) != 1){
//			return result;
//		}
//
//		//Création des listes contenant les valeurs des suites
//		List<Integer> U = new ArrayList<Integer>();
//		List<Integer> V = new ArrayList<Integer>();
//		List<Integer> R = new ArrayList<Integer>();
//		List<Integer> Q = new ArrayList<Integer>();
//
//		//Initialisation des suites : U0=1 V0=0; U1=0 V1=1; R0=p; R1=m
//		U.add(0, 1);
//		U.add(1, 0);
//		V.add(0, 0);
//		V.add(1, 1);
//		R.add(0, p);
//		R.add(1, m);
//
//		boolean status = true;
//		int i= 0;
//
//		while (status){
//
//			if (!(R.get(i + 1) == 0)) {
//				//Calcul du terme i2 de la suite R et du Qi
//
//				//Ri = R(i+1) * Qi + Riplus2; 
//
//				int Riplus2;
//				int Qi;
//				int Ri = R.get(i);
//				int Riplus1 = R.get(i + 1);
//
//				Riplus2 =  Ri % Riplus1;
//				Qi = (Ri - Riplus2) / Riplus1;
//
//				// Ajout de ces termes aux listes;
//
//				R.add(i + 2, Riplus2);
//				Q.add(i, Qi);
//			}
//
//
//			if(i > 1){
//
//				//Calcul des termes i des suites U et V;
//
//				int Ui;
//				int Vi;
//				int Uimoins1 = U.get(i - 1);
//				int Uimoins2 = U.get(i - 2);
//				int Vimoins1 = V.get(i - 1);
//				int Vimoins2 = V.get(i - 2);
//				int Qimoins2 = Q.get(i - 2);
//
//				Ui = Uimoins2 - Qimoins2 * Uimoins1;
//				Vi = Vimoins2 - Qimoins2 * Vimoins1;
//
//				//Ajout de ces termes aux listes;
//
//				U.add(i, Ui);
//				V.add(i, Vi);
//
//			}
//
//			if (R.get(i + 1) == 0){
//				status = false;
//			}
//
//			i++;
//		}
//
//		if ((U.get(i - 1) * p + V.get(i - 1) * m) == 1 ){
//			if (V.get(i - 1) > 0){
//				result = V.get(i - 1);
//			} else {
//				result = V.get(i - 1) + m;
//			}
//		}
//		
//		return result;
//	}
	
	//Calcul de l'inverse multiplicatif de p modulo m
	public static int getMulitplicativeInverse(int p, int m){
		int mzero = m;
		int pzero = p;
		int tzero = 0;
		int t = 1;
		int q = Math.floorDiv(mzero, pzero);
		int r = mzero - q * pzero;
		while(r > 0){
			int temp = tzero - q * t;
			if (temp >= 0) {
				temp = temp % m;
			} else {
				temp = m - ((-temp) % m);
			}
			tzero = t;
			t = temp;
			mzero = pzero;
			pzero = r;
			q = Math.floorDiv(mzero, pzero);
			r = mzero - q * pzero;
		}
		if (pzero != 1){
			return 0;
		} else {
			return t;
		}
	}

	// Fonction qui retroune le PGCD de deux nombres entiers
	public static long PGCD (int a, int b) {

		long pgcd = 0;
		int r = 0;

		a = Math.abs(a);
		b = Math.abs(b);

		while(true){
			if (b == 0) {
				pgcd = a;
				break;
			} else {
				r = a % b;
				a = b;
				b = r;
			}
		}
		return pgcd;

	}

	// Retourne le caractere le plus utilisé dans une chaine de caractères passée en paramètre.
	public static char getMoreUsedChar(String text) {
		
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		map = initMap(map);
		char[] textTab = text.toCharArray();
		int textLengh = text.length();
		
		//Compte du nombre d'apparition de chacune des lettres
		for (int i = 0; i < textLengh; i++) {
			map.put(textTab[i], map.get(textTab[i]) + 1);
		}
		
		Set<Character> keys = map.keySet();
		Iterator<Character> iterator = keys.iterator();
		
		int count = 0;
		char moreUsedChar = '0';
		
		// On cherche la lettre la plus fréquente dans la map
		while (iterator.hasNext()) {
			Character c = (Character) iterator.next();
			if(map.get(c) >= count){
				//Lettre plus utilisée trouvée
				moreUsedChar = c;
				count = map.get(c);
			}
		}
		
		return moreUsedChar;
		
	}

	
	// Initialisation d'une Map
	// La clé correspond à l'ensemble des caractères de l'alphabet traité
	// la valeur correpsond au nombre d'occurence
	private static HashMap<Character, Integer> initMap(HashMap<Character, Integer> map) {
		map.put('A', 0);
		map.put('B', 0);
		map.put('C', 0);
		map.put('D', 0);
		map.put('E', 0);
		map.put('F', 0);
		map.put('G', 0);
		map.put('H', 0);
		map.put('I', 0);
		map.put('J', 0);
		map.put('K', 0);
		map.put('L', 0);
		map.put('M', 0);
		map.put('N', 0);
		map.put('O', 0);
		map.put('P', 0);
		map.put('Q', 0);
		map.put('R', 0);
		map.put('S', 0);
		map.put('T', 0);
		map.put('U', 0);
		map.put('V', 0);
		map.put('W', 0);
		map.put('X', 0);
		map.put('Y', 0);
		map.put('Z', 0);
		
		return map;
	}

	// retourne le caractère clé permettant, dans un chiffrement de César
	// paramètres : le caractère en clair et le caractère chiffré
	public static char getShift(char chiffred, char clear) {
		char key = '0';
		int i = 0;
		while(i <= 25 && key == '0'){
			char c  = (char) (65 + (i/26)*6 + i);
			Alphabet alphabet = new Alphabet(c, Type.CLEAR);
			if (alphabet.getChiffred(clear) == chiffred){
				key = c;
			}
			i++;
		}
		return key;
	}

	// Fonction transformant les caractère spéciaux pour obtenir une chaine exploitable par le programme
	public static String format(String clear) {
		clear.replaceAll(" ", "");
		clear.replaceAll(",", "");
		clear.replaceAll(".", "");
		clear.replaceAll("\'", "");
		clear.replaceAll("\"", "");
		clear.replaceAll("!", "");
		clear.replaceAll("-", "");
		clear.replaceAll("_", "");
		clear.replaceAll("é", "e");
		clear.replaceAll("è", "e");
		clear.replaceAll("ê", "e");
		clear.replaceAll("ë", "e");
		clear.replaceAll("ï", "i");
		clear = clear.toUpperCase();
		return clear;
	}
	
	// Fonction permettant d'obtenir les String sous forme de bloc de 4 lettres
	public static String getBlocksOf4(String text){
		String result = "";
		
		char[] textChars = text.toCharArray();
		for(int i = 0; i < text.length(); i++){
			result += textChars[i];
			if ((i+1) % 4 == 0){
				result += " ";
			}
		}
		
		return result;
	}

}