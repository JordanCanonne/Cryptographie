package com.istv.crypto.structure;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class FrequencyTable {
	
	//Pour mettre une valeur float dans une map, on effectue une multiplication par 100
	public HashMap<Character, Integer> frenchReference;
	public HashMap<Character, Integer> customFrequencies;
	private String text;
	
	public FrequencyTable(String text) {
		this.frenchReference = new HashMap<Character, Integer>();
		this.customFrequencies = new HashMap<Character, Integer>();
		this.text = text;
		
		initMaps();
		
	}
	
	private void initMaps() {
		// source : https://fr.wikipedia.org/wiki/Analyse_fr%C3%A9quentielle
		
				// avec la multiplication par 100
				// par exemple, pour A la fr�quence d'apparition en fran�ais est de 7,11%
				this.frenchReference.put('A', 942);
				this.frenchReference.put('B', 102);
				this.frenchReference.put('C', 264);
				this.frenchReference.put('D', 339);
				this.frenchReference.put('E', 1587);
				this.frenchReference.put('F', 95);
				this.frenchReference.put('G', 104);
				this.frenchReference.put('H', 77);
				this.frenchReference.put('I', 841);
				this.frenchReference.put('J', 89);
				this.frenchReference.put('K', 0);
				this.frenchReference.put('L', 534);
				this.frenchReference.put('M', 324);
				this.frenchReference.put('N', 715);
				this.frenchReference.put('O', 514);
				this.frenchReference.put('P', 286);
				this.frenchReference.put('Q', 106);
				this.frenchReference.put('R', 646);
				this.frenchReference.put('S', 790);
				this.frenchReference.put('T', 726);
				this.frenchReference.put('U', 624);
				this.frenchReference.put('V', 215);
				this.frenchReference.put('W', 1);
				this.frenchReference.put('X', 30);
				this.frenchReference.put('Y', 24);
				this.frenchReference.put('Z', 32);
				
				//Initialisation de la fr�quence du texte � 0
				this.customFrequencies.put('A', 0);
				this.customFrequencies.put('B', 0);
				this.customFrequencies.put('C', 0);
				this.customFrequencies.put('D', 0);
				this.customFrequencies.put('E', 0);
				this.customFrequencies.put('F', 0);
				this.customFrequencies.put('G', 0);
				this.customFrequencies.put('H', 0);
				this.customFrequencies.put('I', 0);
				this.customFrequencies.put('J', 0);
				this.customFrequencies.put('K', 0);
				this.customFrequencies.put('L', 0);
				this.customFrequencies.put('M', 0);
				this.customFrequencies.put('N', 0);
				this.customFrequencies.put('O', 0);
				this.customFrequencies.put('P', 0);
				this.customFrequencies.put('Q', 0);
				this.customFrequencies.put('R', 0);
				this.customFrequencies.put('S', 0);
				this.customFrequencies.put('T', 0);
				this.customFrequencies.put('U', 0);
				this.customFrequencies.put('V', 0);
				this.customFrequencies.put('W', 0);
				this.customFrequencies.put('X', 0);
				this.customFrequencies.put('Y', 0);
				this.customFrequencies.put('Z', 0);
				
				setupCustomReference(this.text);
		
	}

	private void setupCustomReference(String text){
		char[] textTab = text.toCharArray();
		int textLengh = text.length();
		
		//Compte du nombre d'apparition de chacune des lettres
		for (int i = 0; i < textTab.length; i++) {
			this.customFrequencies.put(textTab[i], customFrequencies.get(textTab[i]) + 1);
		}
		
		
		//On parcours la HashMap pour convertir le compte pr�c�dent en fr�quence d'apparition
		Set<Character> keys = this.customFrequencies.keySet();
		Iterator<Character> iterator = keys.iterator();
		
		while (iterator.hasNext()) {
			Character character = (Character) iterator.next();
			float occuracy = (float)this.customFrequencies.get(character);
			
			float frequency = ((occuracy / textLengh) * 100);
			
			setCharFrequency(character, frequency);
		}
		
		
	}
	
	private void setCharFrequency(char c, float frequency){
		// frequence multipli� par 100 pour le stockage dans la HashMap
		this.customFrequencies.put(c, (int)(frequency * 100));
	}
	
	// return le caractere dont la frequence d'apparition est la plus proche du caract�re pass� en param�tre
	public char getChar(char c){
		
		int delta = 2000;
		int localFrequency = this.customFrequencies.get(c);
		
		// Initialisation � 0 pour detecter un eventuel disfonctionnement de la fonction.
		char result = '0';
		
		Set<Character> keys = this.frenchReference.keySet();
		Iterator<Character> iterator = keys.iterator();
		
		//Parcours de la HashMap contenant les fr�quences des lettres de la langue fran�aise
		while (iterator.hasNext()) {
			Character character = (Character) iterator.next();
			int characterFrequency = this.frenchReference.get(character);
			
			// si la fr�quence de cette lettre L correspond � la fr�quence de la lettre pass�e en param�tre
			// On retourne cette lettre L
			if (characterFrequency == localFrequency){
				return character;
			} else {
				// Sinon on regarde si la fr�quence est assez proche
				// Dans ce cas, on m�morise la lettre L associ�
				// But: trouver la lettre avec la fr�quence la plus proche
				if (Math.abs(localFrequency - characterFrequency) < delta){
					delta = Math.abs(localFrequency - characterFrequency);
					result = character;
				}
			}
		}
		return result;
	}
	
	
	//Fonction qui retoune une HashMap de correspondance des fr�quence d'apparition
	public HashMap<Character, Character> getCorrespondence(){
		
		HashMap<Character, Character> result = new HashMap<Character, Character>();
		
		while (!(this.customFrequencies.isEmpty() || this.frenchReference.isEmpty())){
			
			Set<Character> customKeys = this.customFrequencies.keySet();
			Iterator<Character> customIterator = customKeys.iterator();
			
			Set<Character> referenceKeys = this.frenchReference.keySet();
			Iterator<Character> referenceIterator = referenceKeys.iterator();
			
			int tempCustom = 0;
			int tempReference = 0;
			char moreUsedCustom = '0';
			char moreUsedReference = '0';
			
			// On cherche la lettre la plus fr�quente dans la liste custom
			while (customIterator.hasNext()) {
				Character customCharacter = (Character) customIterator.next();
				if(this.customFrequencies.get(customCharacter) >= tempCustom){
					//Lettre plus utilis�e trouv�e
					moreUsedCustom = customCharacter;
					tempCustom = this.customFrequencies.get(customCharacter);
				}
			}
			
			//On cherche la lettre la plus utilis�e dans la liste de r�f�rence
			while (referenceIterator.hasNext()) {
				Character referenceCharacter = (Character) referenceIterator.next();
				if(this.frenchReference.get(referenceCharacter) >= tempReference){
					//Letttre plus utilis�e trouv�e
					moreUsedReference = referenceCharacter;
					tempReference = this.frenchReference.get(referenceCharacter);
				}
			}
			
			//On ajoute une entr�e � la map result
			//key : lettre custom
			//value : lettre de r�f�rence
			//Cela va permettre de retrouver plus facilement de d�chiffrer le texte chiffr�
			result.put(moreUsedCustom, moreUsedReference);
			//On supprime les characteres courant des deux listes avoir une correspondance unique
			this.customFrequencies.remove(moreUsedCustom);
			this.frenchReference.remove(moreUsedReference);
			
		}
		
		
		// On r�initialise les listes qui ont �t� vid�es
		initMaps();
		return result;
		
	}
	
}
