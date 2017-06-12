package com.istv.crypto.menu;

import java.util.Scanner;

import com.istv.crypto.menu.itf.Menu;

public class MainMenu implements Menu {

	@Override
	public void launch() {

		printHeader();
		menu();

	}

	private void printHeader(){
		System.out.println("******************TP de Cryptologie******************\n\n");
		System.out.println("              Auteur : Jordan CANONNE \n\n");
		System.out.println("*****************************************************\n\n");
	}

	@Override
	public void menu(){
		Scanner sc = new Scanner(System.in);

		System.out.println("******************Menu Principal******************\n\n");
		System.out.println("Selectionner un protocole de décyptage : \n");
		System.out.println("\t1 - Cesar");
		System.out.println("\t2 - Vigenere");
		System.out.println("\t3 - Permutation");
		System.out.println("\t4 - Merkle-Hellman\n\n");
		System.out.println("\t5 - Quitter le programme");

		System.out.println("Choix (1/2/3/4/5): ");

		int choice = sc.nextInt();

		Menu nextMenu = null;

		switch (choice) {
		case 1:
			nextMenu = new CesarMenu();
			break;

		case 2:
			nextMenu = new VigenereMenu();
			break;

		case 3:
			nextMenu = new PermutationMenu();
			break;

		case 4:
			nextMenu = new MerkleHellmanMenu();
			break;
			
		case 5:
			System.out.println("\n\n\n\nProgramme terminé .....");
			System.exit(0);

		default:
			System.out.println("Choix non valide ... \n");
			menu();
			break;
		}
		
		nextMenu.launch();
		System.out.println("\n\n");
	}

}
