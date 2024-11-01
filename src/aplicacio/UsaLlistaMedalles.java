package aplicacio;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import dades.*;

public class UsaLlistaMedalles {
	static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) throws FileNotFoundException {
		System.out.println("Indica el número de línies a llegir del fitxer (màxim 21694)");
		int numLinies = Integer.parseInt(scanner.nextLine());
		String[] dataset = llegirLiniesFitxer(numLinies); 
		LlistaMedalla llistaMedalles = procesarLinies(dataset, numLinies);
		int opcio = 0;
		while (opcio != 11) {

			mostraMenu();
			opcio = Integer.parseInt(scanner.nextLine());
			switch (opcio) {
			case 1:
				System.out.printf(llistaMedalles.toString());
				break;
			case 2:
				System.out.print("Número de medalles del pais: " + procesarNumeroMedalla (llistaMedalles));
				break;
			case 3:
				procesarPrimeraMedalla(llistaMedalles);
				break;
			case 4:
				procesarPrimeraMedallaDona(llistaMedalles);
				break;
			case 5:
				String mesMedallesPais = procesarPaisMesMedalles(llistaMedalles);
				System.out.printf(mesMedallesPais);
				break;
			case 6:
				int[] medallerDePais = procesarMedallerPais(llistaMedalles);
				System.out.print("Medaller del pais: ");
				System.out.print("Or: " + medallerDePais[0] + ", Plata: " + medallerDePais[1] + ", Bronze: " + medallerDePais[2]);
				break;
			case 7:
				System.out.printf(procesarProvaEdicio(llistaMedalles).toString());
				break;
			case 8:
				procesarPrimeraMedallaConjunt(llistaMedalles);
				break;
			case 9:
				String mesMedallesPaisConjunt = procesarPaisMesMedallesConjunt(llistaMedalles);
				System.out.printf(mesMedallesPaisConjunt);
			case 10:
				break;
			}
        }

		scanner.close();
}

	public static void mostraMenu() {
		System.out.println("\n\nOpcions del menu:");
		System.out.println("\n\t1. Mostrar el conjunt de dades de la llista");
		System.out.println("\t2. Mostrar el numero de medalles aconseguides per pais");
		System.out.println("\t3. Mostrar les dades de la primera medalla");
		System.out.println("\t4. Mostrar les dades de la primera medalla per una dona ");
		System.out.println("\t5. Mostrar el pais que ha aconseguit mes medalles");
		System.out.println("\t6. Mostrar el medaller del pais");
		System.out.println("\t7. Mostrar qui ha aconseguit les medalles");
		System.out.println("\t8. El conjunt de medalles d'un tipus de prova i joc(Primera medalla)");
		System.out.println("\t9. El conjunt de medalles d'un tipus de prova i joc(Mes medalles)");
		System.out.println("\t10. Eliminar el copnjunt de medalles d’uns jocs en un tipus de prova");
		System.out.println("\t11. Sortir");
		System.out.print("\n\t\t\tIndica opcio:\n");
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
		return numeroMedallas;
	}

	//3
	private static void procesarPrimeraMedalla(LlistaMedalla llistaMedalles) {
		System.out.print("Introdueix sexe de la persona: ");
		String sexe = scanner.nextLine();
		System.out.print("Introdueix l'any: ");
		int any = scanner.nextInt();
		scanner.nextLine();
		System.out.print("Introdueix el tipus de primera medalla: ");
		String tipus = scanner.nextLine();
		Medalla primeraMedalla = llistaMedalles.primeraMedalla(tipus, sexe, any);
		if (primeraMedalla != null) {
			System.out.println("Es trobat la primera medalla per requisits que has posat:");
			System.out.println(primeraMedalla);
		} else {
			System.out.println("No es trobat la primera medalla per requisits que has posat");
		}
	}

	//4

	private static void procesarPrimeraMedallaDona(LlistaMedalla llistaMedalles) {
		System.out.print("Introdueix l'any: ");
		int any = scanner.nextInt();
		scanner.nextLine();
		System.out.print("Introdueix la poblacio: ");
		String poblacio = scanner.nextLine();
		Medalla primeraMedalla = llistaMedalles.primeraDona(any, poblacio);
		if (primeraMedalla != null) {
			System.out.println("Es trobat la primera medalla d'una dona per requisits que has posat:");
			System.out.println(primeraMedalla);
		} else {
			System.out.println("No es trobat la primera medalla d'una dona per requisits que has posat");
		}
	}
	
	//5

	private static String procesarPaisMesMedalles(LlistaMedalla llistaMedalles) {
		String medallesPais;
		System.out.print("Introdueix el tipus de medalla: ");
		String tipus = scanner.nextLine();
		medallesPais = llistaMedalles.paisMesMedalles(tipus);
		return medallesPais;
	}

	//6
	
	private static int[] procesarMedallerPais(LlistaMedalla llistaMedalles) {
        int[] medaller = {0, 0, 0};
        System.out.print("Introdueix el pais: ");
        String pais = scanner.nextLine();
        medaller = llistaMedalles.medallerPais(pais);
        return medaller;
	}

	//7

	private static LlistaMedalla procesarProvaEdicio(LlistaMedalla llistaMedalles) {
		System.out.print("Introdueix la poblacio: ");
		String poblacio = scanner.nextLine();
		System.out.print("Introdueix l'any: ");
		int any = Integer.parseInt(scanner.nextLine());
		System.out.print("Introdueix la prova: ");
		String prova = scanner.nextLine();
		return llistaMedalles.medallesProvaIEdicio(prova, poblacio, any);
	}

	//8

	private static void procesarPrimeraMedallaConjunt(LlistaMedalla llistaMedalles) {
		System.out.print("Introdueix la poblacio: ");
		String poblacio = scanner.nextLine();
		System.out.print("Introdueix l'any: ");
		int anyJocs = scanner.nextInt();
		scanner.nextLine();
		System.out.print("Introdueix la prova: ");
		String prova = scanner.nextLine();
		LlistaMedalla conjuntMedalles = llistaMedalles.medallesProvaIEdicio(prova, poblacio, anyJocs);
	
		System.out.print("Introdueix sexe de la persona: ");
		String sexe = scanner.nextLine();
		System.out.print("Introdueix el tipus de primera medalla: ");
		String tipus = scanner.nextLine();
		Medalla primeraMedalla = conjuntMedalles.primeraMedalla(tipus, sexe, anyJocs);
		if (primeraMedalla != null) {
			System.out.println("Es trobat la primera medalla per requisits que has posat:");
			System.out.println(primeraMedalla);
		} else {
			System.out.println("No es trobat la primera medalla per requisits que has posat");
		}
	}

	//9
	private static String procesarPaisMesMedallesConjunt(LlistaMedalla llistaMedalles) {
		System.out.print("Introdueix la poblacio: ");
		String poblacio = scanner.nextLine();
		System.out.print("Introdueix l'any: ");
		int anyJocs = scanner.nextInt();
		scanner.nextLine();
		System.out.print("Introdueix la prova: ");
		String prova = scanner.nextLine();
		LlistaMedalla conjuntMedalles = llistaMedalles.medallesProvaIEdicio(prova, poblacio, anyJocs);	
		System.out.print("Introdueix el tipus de primera medalla: ");
		String tipus = scanner.nextLine();
	
		return conjuntMedalles.paisMesMedalles(tipus);
	}

	//10

}
