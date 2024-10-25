package dades;

public class Medalla {
	private String disciplina;
	private String poblacioJocs;
	private int anyJocs;
	private String nomProva;
	private String genere;
	private String medalla;
	private String tipusParticipacio;
	private String paisAtleta;
	
	/**
	 * Constructor objecte Medalla
	 * @param String disciplina
	 * @param String poblacio jocs
	 * @param int any dels jocs
	 * @param String nom de la prova
	 * @param String genere del atleta
	 * @param String tipus de medalla (GOLD, SILVER, BRONZE)
	 * @param String tipus de participació (Athlete, GameTeam)
	 * @param String inicials pais del atleta
	 */
	public Medalla(String disciplina, String poblacioJocs, int anyJocs, String nomProva, String genere, String medalla,
			String tipusParticipacio, String paisAtleta) {
		this.disciplina = disciplina;
		this.poblacioJocs = poblacioJocs;
		this.anyJocs = anyJocs;
		this.nomProva = nomProva;
		this.genere = genere;
		this.medalla = medalla;
		this.tipusParticipacio = tipusParticipacio;
		this.paisAtleta = paisAtleta;
	}


	/**
	 * Retorna la informació de la medalla en forma de String
	 * @return String infor de la medalla
	 */
	@Override
	public String toString() {
		return "Medalla [disciplina=" + disciplina + ", poblacioJocs=" + poblacioJocs + ", anyJocs=" + anyJocs
				+ ", nomProva=" + nomProva + ", genere=" + genere + ", medalla=" + medalla + ", tipusParticipacio="
				+ tipusParticipacio + ", paisAtleta=" + paisAtleta + "]";
	}


	/**
	 * Crea una copia de la medalla
	 * @return Medalla retorna un objecte copia de la clase Medalla
	 */
	public Medalla copia() {
		Medalla aux=new Medalla(disciplina, poblacioJocs, anyJocs, nomProva, genere, medalla, tipusParticipacio, paisAtleta);
		return aux;
	}

	/**
	 * Indica si la medalla va ser guanyada a la ciutat indicada
	 * @param nomCiutat nom de la ciutat on es vol comprovar si es va guanyar
	 * @return boolean retorna cert si la ciutat on es va guanyar es la especificada
	 */
    public boolean celebratEn(String nomCiutat) {
        return (poblacioJocs.equalsIgnoreCase(nomCiutat));
    }

	/**
	 * Indica si la medalla va ser guanyada a l'any indicat
	 * @param anyJocs any que es vol comprovar si es va guanyar la medalla
	 * @return retorna cert si la medalla es va guanyar a l'any especificat
	 */
    public boolean celebratAny(int anyJocs) {
        return (this.anyJocs==anyJocs);
    }

	/**
	 * Indica si la medalla es del tipus especificat
	 * @param tipusMedalla tipus de medalla que es vol comprovar si eś
	 * @return retorna cert si la medalla és d'aquest tipus
	 */
    public boolean esTipusMedalla(String tipusMedalla) {
        return (medalla.equalsIgnoreCase(tipusMedalla));
    }

	/**
	 * Indica si la medalla es del pais indicat
	 * @param pais Tres primeres lletres del pais que es vol comprovar
	 * @return boolean retorna cert si la medalla és del país indicat
	 */
    public boolean esDaquestPais(String pais) {
        return (paisAtleta.equalsIgnoreCase(pais));
    }

	/**
	 * Indica si el guanyador de la medalla és d'un sexe
	 * @param sexe sexe que es vol comprovar el atleta 
	 * @return boolean retorna cert si el sexe del atleta és l'indicat
	 */
    public boolean esDaquestSexe(String sexe) {
        return (genere.equalsIgnoreCase(sexe));
    }

	/**
	 * Obté la informació del pais de la medalla
	 * @return String tres primeres lletres del pais del atleta guanyador
	 */
    public String getPais() {
        return paisAtleta;
    }

	public boolean esAquestaProva(String nomProva) {
		return (this.nomProva.equalsIgnoreCase(nomProva));
	}
}
