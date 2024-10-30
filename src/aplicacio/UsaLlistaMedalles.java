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
		LlistaMedalla llistaMedalles = procesarLinies(dataset, numLinies);
		//1
		System.out.printf(llistaMedalles.toString());
		/*
		//2
		System.out.print(procesarNumeroMedalla (llistaMedalles));
		//3
		procesarPrimeraMedalla(llistaMedalles);
		//4
		procesarPrimeraMedallaDona(llistaMedalles);
		//5
		String mesMedallesPais = procesarPaisMesMedalles(llistaMedalles);
		System.out.printF(mesMedallesPais);
		//6
		int[] medallerDePais = procesarMedallerPais(llistaMedalles);
        System.out.print("Medaller del pais: ");
        System.out.print("Or: " + medallerDePais[0] + ", Plata: " + medallerDePais[1] + ", Bronze: " + medallerDePais[2]);
		//7
		System.out.print(procesarProvaEdicio(llistaMedalles));
		*/
		//8
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
			String linea = f.nextLine();
			result[i] = linea;
		}
		f.close();
		return result;
	}

	// 1
	private static LlistaMedalla procesarLinies(String[] dataset, int numLinies) {

		LlistaMedalla llistaMedalles = new LlistaMedalla(numLinies);

		for (int i = 0; i<dataset.length; i++){
			
			String disciplina;
			String poblacioJocs;
			int anyJocs;
			String nomProva;
			String genere;
			String tipusMedalla;
			String tipusParticipacio;	
			String paisAtleta;
			
			String[] arrayMedalla = dataset[i].split(";"); 
			disciplina = arrayMedalla[0];
			String[] poblacioYAny = arrayMedalla[1].split("-");
			poblacioJocs = poblacioYAny[0];
			anyJocs = Integer.parseInt(poblacioYAny[1]);
			nomProva = arrayMedalla[2];
			genere = arrayMedalla[3];
			tipusMedalla = arrayMedalla[4];
			tipusParticipacio = arrayMedalla[5];
			paisAtleta = arrayMedalla[6];

			Medalla medalla = new Medalla(disciplina, poblacioJocs, anyJocs, nomProva, genere, tipusMedalla, tipusParticipacio, paisAtleta);

			llistaMedalles.afegirDades(medalla);
		}
	
		return llistaMedalles;
	}

	//2
	private static int procesarNumeroMedalla(LlistaMedalla llistaMedalles) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Introdueix la poblacio: ");
		String poblacio = scanner.nextLine();
		System.out.print("Introdueix l'any: ");
		int any = scanner.nextInt();
		scanner.nextLine();
		System.out.print("Introdueix el tipus de medalla: ");
		String tipus = scanner.nextLine();
		System.out.print("Introdueix el pais: ");
		String pais = scanner.nextLine();
		int numeroMedallas = llistaMedalles.medallesEdicio(poblacio, any, tipus, pais);
		scanner.close();
		return numeroMedallas;
	}

	//3
	private static void procesarPrimeraMedalla(LlistaMedalla llistaMedalles) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Introdueix sexe de la persona: ");
		String sexe = scanner.nextLine();
		System.out.print("Introdueix l'any: ");
		int any = scanner.nextInt();
		scanner.nextLine();
		System.out.print("Introdueix el tipus de primera medalla: ");
		String tipus = scanner.nextLine();
		Medalla primeraMedalla = llistaMedalles.primeraMedalla(tipus, sexe, any);
		scanner.close();
		if (primeraMedalla != null) {
			System.out.println("Es trobat la primera medalla per requisits que has posat:");
			System.out.println(primeraMedalla);
		} else {
			System.out.println("No es trobat la primera medalla per requisits que has posat");
		}
	}

	//4

	private static void procesarPrimeraMedallaDona(LlistaMedalla llistaMedalles) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Introdueix l'any: ");
		int any = scanner.nextInt();
		scanner.nextLine();
		System.out.print("Introdueix la poblacio: ");
		String poblacio = scanner.nextLine();
		Medalla primeraMedalla = llistaMedalles.primeraDona(any, poblacio);
		scanner.close();
		if (primeraMedalla != null) {
			System.out.println("Es trobat la primera medalla d'una dona per requisits que has posat:");
			System.out.println(primeraMedalla);
		} else {
			System.out.println("No es trobat la primera medalla d'una dona per requisits que has posat");
		}
	}
	
	//5

	private static String procesarPaisMesMedalles(LlistaMedalla llistaMedalles) {
		Scanner scanner = new Scanner(System.in);
		String medallesPais;
		System.out.print("Introdueix el tipus de medalla: ");
		String tipus = scanner.nextLine();
		medallesPais = llistaMedalles.paisMesMedalles(tipus);
		scanner.close();
		return medallesPais;
	}

	//6
	
	private static int[] procesarMedallerPais(LlistaMedalla llistaMedalles) {
        Scanner scanner = new Scanner(System.in);
        int[] medaller = {0, 0, 0};
        System.out.print("Introdueix el pais: ");
        String pais = scanner.nextLine();
        medaller = llistaMedalles.medallerPais(pais);
        scanner.close();
        return medaller;
	}

	//7

	private static LlistaMedalla procesarProvaEdicio(LlistaMedalla llistaMedalles) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Introdueix la poblacio: ");
		String poblacio = scanner.nextLine();
		System.out.print("Introdueix l'any: ");
		int any = scanner.nextInt();
		scanner.nextLine();
		System.out.print("Introdueix la: ");
		String prova = scanner.nextLine();
		scanner.close();
		return llistaMedalles.medallesProvaIEdicio(prova, poblacio, any);
	}

	//8
}
