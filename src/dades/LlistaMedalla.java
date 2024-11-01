package dades;

public class LlistaMedalla {
   private int numMedalles;
   private Medalla[] llistaMedalles;

   
   /**
    * Constructor de la llista de medalles
    * @param nTotal nombre màxim de medalles de la llista
    */
   public LlistaMedalla(int nTotal){
        numMedalles = 0;
       if(nTotal >= 0){
            llistaMedalles = new Medalla[nTotal];
       } 
       else {
            llistaMedalles = new Medalla[100];
       }
   }

     public String toString() {
          String stringSalida = "\n";
          
          //stringSalida = stringSalida + llistaMedalles[1].toString();
          if(numMedalles > 0){
               for (int i = 0; i<numMedalles; i++){
                    stringSalida = stringSalida + llistaMedalles[i].toString() + "\n";
               }
          }
          else{
               stringSalida = stringSalida +"Cap medalla a la llista";
          }
          return stringSalida;
     }

   /**
    * Afegir una medalla nova
    * @param newMedalla
    */
   public void afegirDades(Medalla newMedalla){
        if(numMedalles < llistaMedalles.length){
               llistaMedalles[numMedalles] = newMedalla.copia();
               numMedalles++;
        }   
   }


   /**
    * Buscar les medalles a una edicio concreta d'un pais concret i d'un tipus concret
    * @param poblacio 
    * @param any
    * @param tipus
    * @param pais
    * @return int nombre de medalles del pais, del tipus i de la edició indicada
    */
   public int medallesEdicio(String poblacio, int any, String tipus, String pais){
        int medallesObtingudes = 0;
        Medalla medalla; 

        //Per cada medalla
        for(int i = 0; i < numMedalles; i++){
            medalla = llistaMedalles[i];

            //Si es cumpleix que la medalla té les característiques especificades als paràmetres
            if(medalla.celebratEn(poblacio) && medalla.celebratAny(any) && medalla.esTipusMedalla(tipus) && medalla.esDaquestPais(pais)){
               //S'augmenta una unitat el nombre de medalles buscades
                medallesObtingudes ++;
            }
        }
        return medallesObtingudes;
   }


   /**
    * Obté la primera medalla que compleix els requisits
    * @param tipus
    * @param sexe
    * @param any
    * @return retorna la instància de la llista que cumpleix les condiciones / retorna null si no s'ha trobat
    */
   public Medalla primeraMedalla(String tipus, String sexe, int any) {
          boolean trobat = false; 
          Medalla medalla = null;

          //Per cada medalla
          int i = 0 ;
          while(!trobat && i<numMedalles) {
               //Si no s'ha trobat la medalla buscada
               medalla = llistaMedalles[i];
               trobat = medalla.esTipusMedalla(tipus) && medalla.esDaquestSexe(sexe) && medalla.celebratAny(any);
               i++;
          } 
         
          //Retornem la instància de la taula (no una copia)
          return medalla;
   }


   /**
    * Obté la primera dona en conseguir una medalla en una edició concreta
    * @param any
    * @param poblacio
    * @return medalla de la dona si s'ha trobat / null si no s'ha trobat
    */
   public Medalla primeraDona(int any, String poblacio) {
          boolean trobat = false;
          Medalla medalla = null;

          int i = 0;
          while(!trobat && i<numMedalles){
              medalla = llistaMedalles[i];
              trobat = medalla.celebratAny(any) && medalla.celebratEn(poblacio) && medalla.esDaquestSexe("Women"); 
              i++;
          }

          //Si no s'ha trobat es retorna null
          if(!trobat) {return null;}
          //Si s'ha trobat es retorna una copia de la instància de la llista
          else {return medalla.copia();}
   }


   /**
    * Obtindre el pais amb més medalles d'un tipus
    * @param tipus
    * @return inicials pais amb més medalles del tipus especificat
    */
   public String paisMesMedalles(String tipus){
          //String dels paisos
          String[] llistaPaisos = new String[220];
          int numPaisosLlista = 0;
          //String que conté el nombre de medalles d'un pais indexat a la llistaPaisos
          int[] numMedallesPaisos = new int[220];

          Medalla medalla;

          //Per totes les medalles
          for(int i = 0; i<numMedalles; i++){
               medalla = llistaMedalles[i];

               //Si la medalla es del tipus indicat
               if(medalla.esTipusMedalla(tipus)){ 
                    //Es busca l'index del pais a la llista de paisos
                    int indexPais = indexPaisALlista(medalla.getPais(), llistaPaisos, numPaisosLlista);
                    //Si no existeix el pais a la llista
                    if(indexPais == -1){
                         //S'afegeix el pais a la llista
                         llistaPaisos[numPaisosLlista] = medalla.getPais();
                         //S'indica que hi ha una medalla d'aquest pais nou
                         numMedallesPaisos[numPaisosLlista] = 1;
                         //S'augmenta en un el nombre de paisos a la llista de paisos
                         numPaisosLlista ++;
                    }
                    else{
                         //Si el pais es troba a la llista s'augmenta en un les medalles del pais al index d'aquest 
                         numMedallesPaisos[indexPais] ++; 
                    } 
               } 
          }
          
          //Si no s'ha trobat cap medalla del tipus especificat es retorna null
          if (numPaisosLlista == 0) {return null;}
          //En cas contrari es retornen les inicials del pais amb més medalles
          else {
               //Es busca l'index del pais amb més medalles
               int indexPaisMesMedalles = indexMesGran(numMedallesPaisos, numPaisosLlista);
               return llistaPaisos[indexPaisMesMedalles];
          }
     }



   /**
    * Calcula el medaller del país especificat
    * @param pais
    * @return int[] medaller del pais
    */
   public int[] medallerPais(String pais) {
          int[] medaller = {0,0,0};
          Medalla medalla; 

          //Per cada medalla
          for (int i=0; i<numMedalles; i++){
              medalla = llistaMedalles[i];
              if(medalla.esDaquestPais(pais)){
                    //En cas de ser del pais especificat
                    if(medalla.esTipusMedalla("GOLD")){ medaller[0] ++;}
                    else if(medalla.esTipusMedalla("SILVER")){ medaller[1] ++;}
                    else{medaller[2] ++;}
              }
          }

          return medaller;
   }


   /**
    * Genera una llista de les medalles repartides a una prova una certa edició
    * @param prova
    * @param poblacio
    * @param any
    * @return LlistaMedalla retorna una llista de medalles
    */
   public LlistaMedalla medallesProvaIEdicio(String prova, String poblacio, int any){
          //Com a màxim a una prova concreta es repartirán 12 medalles
          //3 per categoria masculina
          //3 per categoria femenina
          //3 per equips mixtos
          //3 per equips oberts
          LlistaMedalla llistaSeleccioMedalles = new LlistaMedalla(100);
          Medalla medalla;

          for(int i = 0; i<numMedalles; i++){
               medalla = llistaMedalles[i];
               if(medalla.esAquestaProva(prova) && medalla.celebratEn(poblacio) && medalla.celebratAny(any)){
                    llistaSeleccioMedalles.afegirDades(medalla);
               }
          }
          
          return llistaSeleccioMedalles; 
   }

   /**
    * Elimina les proves d'una edicio concreta
    * @param prova
    * @param poblacio
    * @param any
    */
   public void eliminaMedalles(String prova, String poblacio, int any){

          boolean provaTrobada = false;
          boolean fiBusquedaProves = false;
          int indexPrimeraProva = 0;
          int indexÚltimaProva = 0;
          boolean arribatFinal = false;

          if(numMedalles > 0){               
               int i = 0;
               while(!(i>= numMedalles)){

                    //Es busquen els indexos del principi i final del bloc de proves que es volen eliminar
                    Medalla medalla;
                    i = indexPrimeraProva;
                    provaTrobada = false;
                    fiBusquedaProves = false;
                    while(i < numMedalles && !fiBusquedaProves)
                    {
                         medalla = llistaMedalles[i]; 
                         if(medalla.esAquestaProva(prova) && medalla.celebratEn(poblacio) && medalla.celebratAny(any)){
                              if(!provaTrobada){
                                   provaTrobada = true;
                                   indexPrimeraProva = i;
                              }
                         }
                         else{
                              if(provaTrobada){
                                   fiBusquedaProves = true;
                                   indexÚltimaProva = i-1;
                              }
                         }
                         i++;
                    }


                    int diferenciaIndex = indexÚltimaProva - indexPrimeraProva + 1;

                    for(int j = (indexÚltimaProva + 1); j<numMedalles; j++){
                         llistaMedalles[j-diferenciaIndex] = llistaMedalles[j];
                    }
               }

          }


     }


   //RUTINES AUXILIARS

   private static int indexPaisALlista(String pais, String[] llista, int numPaisos){
          boolean trobat = false;

          //Es recorren els paisos de la llista fins que es troba el pais o fins que s'acaben el paisos
          int i = 0;
          while(!trobat && i<numPaisos){ 
               trobat = pais.equalsIgnoreCase(llista[i]);
               i++;
          }
          i --; 
          //Si no s'ha trobat es retorna el codi d'error -1
          if(!trobat) i = -1;
          return i;
   }

   private static int indexMesGran(int[] llistaMedalles, int numElements){
          int indexMax = 0;

          int medallasMax = llistaMedalles[0];
          for(int i=1; i<numElements; i++){
               if(llistaMedalles[i] > medallasMax){
                    medallasMax = llistaMedalles[i];
                    indexMax = i;
               }
          }

          return indexMax;
   }

}