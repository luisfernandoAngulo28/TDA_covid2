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
public class Vector7Bits {

    int V[];
    int cant;
    //int cantidadBits;
                       //10
    public Vector7Bits(int NumElementos) {
        int NumBits = NumElementos * 7;//70
        int NumeroEnteros = NumBits / 32;//2
        if ((NumBits % 32) != 0) {
            NumeroEnteros++;
        }
        V = new int[NumeroEnteros];
        cant = NumElementos;
        //cantidadBits = 7;
    }

    public void insertar(int elemento, int pos) {
        if (pos <= cant) {
            int ele1 = elemento;
            int mask = (int) (Math.pow(2, 7) - 1);
            int NumeroBits = calcularBits(pos);
            int NumeroEntero = calcularEntero(pos);
            mask = mask << NumeroBits;
            mask = ~mask;
            V[NumeroEntero] = V[NumeroEntero] & mask;
            elemento = elemento << NumeroBits;
            V[NumeroEntero] = V[NumeroEntero] | elemento;
            if ((NumeroBits + 7) > 32) {
                int mask1 = (int) (Math.pow(2, 7) - 1);
                mask1 = mask1 >>> (32 - NumeroBits);
                mask1 = ~mask1;
                V[NumeroEntero] = V[NumeroEntero + 1] & mask1;
                ele1 = ele1 >>> (32 - NumeroBits);
                V[NumeroEntero + 1] = V[NumeroEntero + 1] | ele1;
            }
        }
    }

    public int sacar(int pos) {
        int mask = (int) (Math.pow(2, 7) - 1);
        int NumeroBits = calcularBits(pos);
        int NumeroEntero = calcularEntero(pos);
        mask = mask << NumeroBits;
        mask = mask & V[NumeroEntero];
        mask = mask >>> NumeroBits;
        if ((NumeroBits + 7) > 32) {
            int mask1 = (int) (Math.pow(2, 7) - 1);
            mask1 = mask1 >>> (32 - NumeroBits);
            mask1 = mask1 & V[NumeroEntero + 1];
            mask1 = mask1 << (32 - NumeroBits);
            mask = mask | mask1;
        }
        return mask;
    }

    private int calcularBits(int pos) {
        return (((pos - 1) * 7 % 32));
    }

    private int calcularEntero(int pos) {
        return (((pos - 1) * 7 / 32));
    }

    public String toString() {
        String S = "V=[";
        for (int i = 1; i <= cant; i++) {
            S = S + sacar(i) + ",";
        }
        S = S.replace(",$", " ");
        return S + "]";
    }

    public static void main(String[] args) {
        Vector7Bits x = new Vector7Bits(10);
        x.insertar(33, 1);
        x.insertar(22, 3);
        x.insertar(58, 7);
        x.insertar(35, 10);
        x.insertar(8, 6);
        x.insertar(34, 5);
        x.insertar(18, 4);
        System.out.println(x);
        x.insertar(55, 4);
        System.out.println(x);

    }

}
