package com.istv.crypto.menu;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.istv.crypto.menu.itf.Menu;
import com.istv.crypto.methods.Cesar;
import com.istv.crypto.utils.Utils;

public class CesarMenu implements Menu {

	@Override
	public void launch() {

		menu();

	}

	@Override
	public void menu(){

		Scanner sc = new Scanner(System.in);

		System.out.println("******************Menu du protocole de Cesar******************\n\n");
		System.out.println("Selectionner une action : \n");
		System.out.println("\t1 - Chiffrer");
		System.out.println("\t2 - Dechiffrer");
		System.out.println("\t3 - Decrypter\n");
		System.out.println("\t4 - Retour au menu principal\n");

		System.out.println("Choix (1/2/3/4): ");
		
		int choice = sc.nextInt();
		

		System.out.println("\n\n");


		Cesar cryptologist = new Cesar();
		String clear;
		String key;
		String chiffred;

		switch (choice) {
		case 1:
			System.out.println("******************Chiffrage de Cesar******************\n\n");

			System.out.println("Saisissez un texte clair ([a-z,A-Z, é, è, à, -, _, !, .] : \n");
			
			clear = sc.next();

			System.out.println("Saisissez une cle [a-zA-Z] : ");
			
			key = sc.next();

			System.out.println("\n\nTexte chiffré par le protocole de Cesar : \n");
			System.out.println("\t" + Utils.getBlocksOf4(cryptologist.encrypt(Utils.format(clear), Utils.format(key))) + "\n\n");

			menu();
			break;

		case 2:
			System.out.println("******************Dechiffrage de Cesar******************\n\n");

			System.out.println("Saisissez un texte chiffré ([a-z,A-Z, é, è, à, -, _, !, .] : \n");
			
			chiffred = sc.next();

			System.out.println("Saisissez une cle [a-zA-Z] : ");
			
			key = sc.next();

			System.out.println("\n\nTexte déchiffré par le protocole de Cesar : \n");
			System.out.println("\t" + Utils.getBlocksOf4(cryptologist.decipher(Utils.format(chiffred), Utils.format(key))) + "\n\n");

			menu();
			break;

		case 3:
			System.out.println("******************Decryptage de Cesar******************\n\n");

			System.out.println("Saisissez un texte chiffré ([a-z,A-Z, é, è, à, -, _, !, .] : \n");
			
			chiffred = sc.next();

			System.out.println("\n\nPosibilités de decryptage trouvées par le protocole de Cesar : \n");
			List<String> results = cryptologist.decrypt(Utils.format(chiffred));
			Iterator<String> iterator = results.iterator();
			while (iterator.hasNext()) {
				String result = (String) iterator.next();
				System.out.println("\t" + Utils.getBlocksOf4(result));
			}
			
			System.out.println("\n\n");
			menu();
			break;

		case 4:
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
