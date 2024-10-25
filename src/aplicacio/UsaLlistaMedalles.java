package aplicacio;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import dades.*;

public class UsaLlistaMedalles {
	static Scanner teclat = new Scanner(System.in);

	public static void main(String[] args) throws FileNotFoundException {
		System.out.println("Indica el número de línies a llegir del fitxer (màxim 21694)");
		int numLinies = Integer.parseInt(teclat.nextLine());
		String[] dataset = llegirLiniesFitxer(numLinies);

		// mostrem el contingut que hem llegit. Això ho eliminarem en les
		// versions finals del codi
		for (int i = 0; i < dataset.length; i++) {
			 System.out.println("Linia " + (i + 1) + " conté " + dataset[i]);
		}

		// Completar el codi a partir d'aquí

		
	}

	private static String[] llegirLiniesFitxer(int nLinies) throws FileNotFoundException {
		String[] result;
		if (nLinies < 0)
			nLinies = 0;
		if (nLinies > 21694)
			nLinies = 21694;
		result = new String[nLinies];
		Scanner f = new Scanner(new File("olympic_medals_part_UTF8.csv"));

		String capcalera = f.nextLine();
		System.out.println("El format de les dades en cada línia és el següent\n" + capcalera);
		for (int i = 0; i < nLinies; i++) {
			result[i] = f.nextLine();
		}
		f.close();
		return result;
	}

}