package com.istv.crypto.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.istv.crypto.menu.itf.Menu;
import com.istv.crypto.methods.Merkle_Hellman;
import com.istv.crypto.utils.Utils;

public class MerkleHellmanMenu implements Menu {
	
	private List<Integer> notSuperGrowingList = null;
	private List<Integer> chiffred = null; 
	private int m = 0;
	private int p = 0;

	@Override
	public void launch() {

		menu();

	}

	@Override
	public void menu() {

		Scanner sc = new Scanner(System.in);

		System.out.println("******************Menu du protocole de Merkle Hellman******************\n\n");
		System.out.println("Selectionner une action : \n");
		System.out.println("\t1 - Chiffrer");
		System.out.println("\t2 - Dechiffrer");
		System.out.println("\t3 - Retour au menu principal\n");

		System.out.println("Choix (1/2/3): ");

		
		int choice = sc.nextInt();


		System.out.println("\n\n");

		Merkle_Hellman cryptologist = new Merkle_Hellman();
		String clear;

		switch (choice) {
		case 1:
			System.out.println("******************Chiffrage de Merkle Hellman******************\n\n");

			System.out.println("Saisissez un texte clair ([a-z,A-Z, é, è, à, -, _, ! .] : \n");
			
			clear = sc.next();



			System.out.println("\n\nTexte chiffré par le protocole de Merkle Hellman : \n");

			chiffred = cryptologist.encrypt(Utils.format(clear));
			notSuperGrowingList = cryptologist.publicKey;
			m = cryptologist.privateKey.getM();
			p = cryptologist.privateKey.getP();
			System.out.println("\tChiffré : " + chiffred + "\n\n");
			System.out.println("\tm(privé) = " + m);
			System.out.println("\tp(privé) = " + p);
			System.out.println("\tListe super croissante(privée) : " + cryptologist.privateKey.getSuperGrowingList());
			System.out.println("\tListe non super croissante : " + notSuperGrowingList);

			System.out.println("\n\n");

			menu();
			break;

		case 2:

			System.out.println("******************Dechiffrage de Merkle Hellman******************\n\n");

			boolean inputTest = false;

			if (chiffred != null){
				System.out.println("Voulez-vous utiliser le chiffré précédement généré et les mêmes paramètres ( \n\tChiffré : " + chiffred + "\n\tm = " + m + ", \n\tp = " + p +", \n\tliste non super croissante = " + notSuperGrowingList + "\n) ? (y/n)");
				while (!inputTest){
					
					String decision = sc.next();
					switch (decision) {
					case "y":
						System.out.println("\n\nTexte déchiffré par le protocole Merkle Hellman : \n");
						System.out.println("\t" + cryptologist.decipher(chiffred, m, p, notSuperGrowingList));
						inputTest = true;
						break;
					
					case "n":
						chiffred = new ArrayList<Integer>();
						notSuperGrowingList = new ArrayList<Integer>();
						System.out.println("Saisissez une liste d'entier chiffrée : \n");
						// n arbitrairement initialisé à 6 comme dans le cours
						for(int i = 0; i < 6; i++){
							System.out.println("Saisissez l'entier numero " + (i+1) + " : ");
							
							int integer = sc.nextInt();
							chiffred.add(integer);
						}
						System.out.println("Saisissez l'entier m : ");
						
						m = sc.nextInt();
						System.out.println("Saisissez l'entier p : ");
						
						p = sc.nextInt();
						System.out.println("Saisissez une liste non super croissante : \n");
						// n arbitrairement initialisé à 6 comme dans le cours
						for(int i = 0; i < 6; i++){
							System.out.println("Saisissez l'entier numero " + (i+1) + " : ");
							
							int integer = sc.nextInt();
							chiffred.add(integer);
						}
						
						System.out.println("Recapitulatif : ");
						System.out.println("\tChiffré : " + chiffred);
						System.out.println("\tm : " + m);
						System.out.println("\tp : " + p);
						System.out.println("\tListe non super Croissante");
						
						
						System.out.println("Texte dechiffre par le protocole de Merkle Hellman :");
						System.out.println("\t" + cryptologist.decipher(chiffred, m, p, notSuperGrowingList));
						System.out.println("\n\n");
						inputTest = true;
						break;
						
					default:
						System.out.println("Choix invalid\n");
						break;
					}
				}


			} else {
				chiffred = new ArrayList<Integer>();
				notSuperGrowingList = new ArrayList<Integer>();
				System.out.println("Saisissez une liste d'entier chiffrée : \n");
				// n arbitrairement initialisé à 6 comme dans le cours
				for(int i = 0; i < 6; i++){
					System.out.println("Saisissez l'entier numero " + (i+1) + " : ");
					
					int integer = sc.nextInt();
					chiffred.add(integer);
				}
				System.out.println("Saisissez l'entier m : ");
				
				m = sc.nextInt();
				System.out.println("Saisissez l'entier p : ");
				
				p = sc.nextInt();
				System.out.println("Saisissez une liste non super croissante : \n");
				// n arbitrairement initialisé à 6 comme dans le cours
				for(int i = 0; i < 6; i++){
					System.out.println("Saisissez l'entier numero " + (i+1) + " : ");
					
					int integer = sc.nextInt();
					chiffred.add(integer);
				}
				
				System.out.println("Recapitulatif : ");
				System.out.println("\tChiffré : " + chiffred);
				System.out.println("\tm : " + m);
				System.out.println("\tp : " + p);
				System.out.println("\tListe non super Croissante");
				
				
				System.out.println("Texte dechiffre par le protocole de Merkle Hellman :");
				System.out.println("\t" + Utils.getBlocksOf4(cryptologist.decipher(chiffred, m, p, notSuperGrowingList)));
				System.out.println("\n\n");
			}

			menu();
			break;

		case 3:
			Menu nextMenu = new MainMenu();
			nextMenu.menu();
			break;

		default:
			System.out.println("Choix non valide ... \n");
			menu();
			break;
		}

		System.out.println("\n\n");

	}

}
