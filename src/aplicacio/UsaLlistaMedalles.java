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
		System.out.printf(llistaMedalles.toString());
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
	private static LlistaMedalla procesarLinies(String[] dataset, int numLinies) {

		LlistaMedalla llistaMedalles = new LlistaMedalla(numLinies);
	
		/*for (String linea : dataset) {
			String[] campos = linea.split("\\s+");
	
			if (campos.length >= 8) {
				String disciplina = campos[0];
	
				String[] slugGame = campos[1].split("\\s+");
				String poblacioJocs = slugGame[0];
				int anyJocs = Integer.parseInt(slugGame[1]);
	
				String nomProva = campos[2];
				String genere = campos[3];
				String tipusMedalla = campos[4];
				String tipusParticipacio = campos[5];
				String paisAtleta = campos[6];
	
				Medalla medalla = new Medalla(disciplina, poblacioJocs, anyJocs, nomProva, genere, tipusMedalla, tipusParticipacio, paisAtleta);
	
				llistaMedalles.afegirDades(medalla);
			}
		}*/


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
}
