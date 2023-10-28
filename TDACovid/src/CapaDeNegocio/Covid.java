/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaDeNegocio;

/**
 *
 * @author ferna
 */
public class Covid {

    //Atributos
    VectorNBits VectorCovid;

    int cant;
    String nombre[];
    //constructor

    public Covid(int cantidaddeElementos) {
        nombre = new String[cantidaddeElementos];
        VectorCovid = new VectorNBits(cantidaddeElementos, 31);
        cant = 1;
    }
    
    public int getCantidad(){
        return cant;
    }
    
    
    //el or es para meter valor a la mask
    public void insertar(int Nro, String nombre, int Edad, int Sexo, int peso,
            int fiebre, int tos, int dolordeGarganta, int dolordeCabeza,
            int faltaDeRespiracion, int diarrea, int dolorMuscular,
            int perdidadeOlfato, int perdidaDelGusto) {

        int mask = Nro;//7   //1100100
        //  System.out.println("mask="+mask+"=>"+Integer.toBinaryString(mask));
        mask = mask << 7; //1100100|0000000
        // System.out.println("mask="+mask+"=>"+Integer.toBinaryString(mask));
        mask = mask | Edad;//7+7=14 //1100100|1100011
        // System.out.println("mask="+mask+"=>"+Integer.toBinaryString(mask));
        mask = mask << 1;
        mask = mask | Sexo;//14+1=15

        mask = mask << 7;
        //System.out.println("mask=" + mask + "=>" + Integer.toBinaryString(mask));
        mask = mask | peso; //15+7=22
        // System.out.println("mask=" + mask + "=>" + Integer.toBinaryString(mask));
        mask = mask << 1;
        mask = mask | fiebre; //22+1=23
        mask = mask << 1;
        mask = mask | tos;    //23+1=24
        mask = mask << 1;
        mask = mask | dolordeGarganta;  //24+1=25
        mask = mask << 1;
        mask = mask | dolordeCabeza;   //25+1=26
        mask = mask << 1;
        mask = mask | faltaDeRespiracion; //26+1=27
        mask = mask << 1;
        mask = mask | diarrea;   //27+1=28
        mask = mask << 1;
        mask = mask | dolorMuscular;  //28+1=29
        mask = mask << 1;
        mask = mask | perdidadeOlfato;  //29+1=30
        mask = mask << 1;
        mask = mask | perdidaDelGusto;  //30+1=31
        // System.out.println("mask=" + mask + "=>" + Integer.toBinaryString(mask));
        //insertamos nuestra mask
        VectorCovid.insertar(mask, cant);
        // System.out.println(VectorCovid.toStringP());
        this.nombre[cant - 1] = nombre;
        cant++;
    }

    //Geter Recuperar valores  (Funciones)
    public int getNro(int pos) {
        int dato = VectorCovid.sacar(pos);
        //31-7=24
     //   System.out.println("dato=" + dato + "=>" + Integer.toBinaryString(dato));
        //|1100100|110001111111000111111111
        int mask = (int) (Math.pow(2, 7) - 1);//1111111
        dato = dato >>> 24;
        //  00000000000000000000000000|1100100|
       // System.out.println("dato=" + dato + "=>" + Integer.toBinaryString(dato));
        dato = dato & mask;
        //  00000000000000000000000000|1100100|
        //                             1111111
        //mask=00000000000000000000000000|1100100|
      //  System.out.println("dato=" + dato + "=>" + Integer.toBinaryString(dato));
        return dato;

    }

    public int getEdad(int pos) {
        int dato = VectorCovid.sacar(pos);
        //|1100100|[1100011]|11111000111111111
        //System.out.println("dato=" + dato + "=>" + Integer.toBinaryString(dato));
        //24-7=17
        int mask = (int) (Math.pow(2, 7) - 1);//00000000000001111111
        dato = dato >>> 17;   //000000000000000000|1100100|[1100011]|
        //  System.out.println("dato=" + dato + "=>" + Integer.toBinaryString(dato));
        dato = dato & mask;
        /*
         //000000000000000000|1100100|[1100011]|
        //                000000000000 1111111
        ________________________________________
             0000000000000000000000000[1100011]
         */
        // System.out.println("dato=" + dato + "=>" + Integer.toBinaryString(dato));
        return dato;
    }

    public int getSexo(int pos) {
        int dato = VectorCovid.sacar(pos);
        //17-1=16
        int mask = (int) (Math.pow(2, 1) - 1);
        dato = dato >>> 16;
        dato = dato & mask;
        return dato;
    }

    public char getSexo2(int pos) {
        int dato = VectorCovid.sacar(pos);
        //17-1=16
        int mask = (int) (Math.pow(2, 1) - 1);
        dato = dato >>> 16;
        dato = dato & mask;
        if (dato == 1) {
            return 'M';
        } else {
            return 'F';
        }
    }

    public int getPeso(int pos) {
        int dato = VectorCovid.sacar(pos);
        //16-7=9
        int mask = (int) (Math.pow(2, 7) - 1);
        dato = dato >>> 9;
        dato = dato & mask;
        return dato;
    }

    public int getFiebre(int pos) {
        int dato = VectorCovid.sacar(pos);
        //9-1=8;
        int mask = (int) (Math.pow(2, 1) - 1);
        dato = dato >>> 8;
        dato = dato & mask;
        return dato;

    }

    public int getTos(int pos) {
        int dato = VectorCovid.sacar(pos);
        //8-1=7;
        int mask = (int) (Math.pow(2, 1) - 1);
        dato = dato >>> 7;
        dato = dato & mask;
        return dato;
    }

    public int getDolorDeGarganta(int pos) {
        int dato = VectorCovid.sacar(pos);
        //7-1=6;
        int mask = (int) (Math.pow(2, 1) - 1);
        dato = dato >>> 6;
        dato = dato & mask;
        return dato;
    }

    public int getDolorDeCabeza(int pos) {
        int dato = VectorCovid.sacar(pos);
        //6-1=5;
        int mask = (int) (Math.pow(2, 1) - 1);
        dato = dato >>> 5;
        dato = dato & mask;
        return dato;
    }

    public int getFaltaDeRespiracion(int pos) {
        int dato = VectorCovid.sacar(pos);
        //5-1=4;
        int mask = (int) (Math.pow(2, 1) - 1);
        dato = dato >>> 4;
        dato = dato & mask;
        return dato;
    }

    public int getDiarrea(int pos) {
        int dato = VectorCovid.sacar(pos);
        //4-1=3;
        int mask = (int) (Math.pow(2, 1) - 1);
        dato = dato >>> 3;
        dato = dato & mask;
        return dato;
    }

    public int getDolorMuscular(int pos) {
        int dato = VectorCovid.sacar(pos);
        //3-1=2;
        int mask = (int) (Math.pow(2, 1) - 1);
        dato = dato >>> 2;
        dato = dato & mask;
        return dato;
    }

    public int getPerdidaDeOlfato(int pos) {
        int dato = VectorCovid.sacar(pos);
        //2-1=1;
        int mask = (int) (Math.pow(2, 1) - 1);
        dato = dato >>> 1;
        dato = dato & mask;
        return dato;
    }

    public int getPerdidaDeGusto(int pos) {
        int dato = VectorCovid.sacar(pos);
        //1-1=0;
        int mask = (int) (Math.pow(2, 1) - 1);
        //dato=dato>>>8;
        dato = dato & mask;
        return dato;
    }

    public String getNombre(int pos) {
        return nombre[pos - 1];
    }

    public String Mostrar(int pos) {
        String S = "Formulario Covid\n"
                + "Nro:" + getNro(pos) + "\n"
                + "Nombre:" + getNombre(pos) + "\n"
                + "Edad:" + getEdad(pos) + "\n"
                + "Sexo:" + getSexo2(pos) + "\n"
                + "Peso:" + getPeso(pos) + "\n"
                + "Sintomas\n"
                + "Fiebre:" + (getFiebre(pos) == 1 ? "si" : "no") + "\n"
                + "Tos:" + (getTos(pos) == 1 ? "si" : "no") + "\n"
                + "Dolor De Garganta:" + (getDolorDeGarganta(pos) == 1 ? "si" : "no") + "\n"
                + "Dolor De Cabeza:" + (getDolorDeCabeza(pos) == 1 ? "si" : "no") + "\n"
                + "Falta De Respiracion:" + (getFaltaDeRespiracion(pos) == 1 ? "si" : "no") + "\n"
                + "Diarrea:" + (getDiarrea(pos) == 1 ? "si" : "no") + "\n"
                + "Dolor Muscular:" + (getDolorMuscular(pos) == 1 ? "si" : "no") + "\n"
                + "Perdida de Olfato:" + (getPerdidaDeOlfato(pos) == 1 ? "si" : "no") + "\n"
                + "Perdida de Gusto:" + (getPerdidaDeGusto(pos) == 1 ? "si" : "no") + "\n";
        return S;
    }

    public static void main(String[] args) {
        Covid A = new Covid(10);
        A.insertar(100, "fernando", 99, 1, 120, 1, 0, 1, 1, 1, 1, 1, 1, 1);
        //System.out.println(A.getNro(1));
        // System.out.println(A.getEdad(1));
        //System.out.println(A.getSexo2(1));
        //  System.out.println(A.getPeso(1));
        /* System.out.println(A.getFiebre(1));
          System.out.println(A.getTos(1));
          System.out.println(A.getDolorDeGarganta(1));
          System.out.println(A.getDolorDeCabeza(1));
          System.out.println(A.getFaltaDeRespiracion(1));
          System.out.println(A.getDiarrea(1));
          System.out.println(A.getDolorMuscular(1));
          System.out.println(A.getPerdidaDeOlfato(1));
          System.out.println(A.getPerdidaDeGusto(1));*/
        //System.out.println(A.getNombre(1));
        System.out.println(A.Mostrar(1));
    }

}
