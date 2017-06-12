package com.istv.crypto;

import com.istv.crypto.menu.MainMenu;
import com.istv.crypto.menu.itf.Menu;

public class Launcher {

	public static void main(String[] args) {
		Menu menu = new MainMenu();
		menu.launch();
	}

}
