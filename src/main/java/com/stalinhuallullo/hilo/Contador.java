package com.stalinhuallullo.hilo;

public class Contador implements Runnable{


    private int contador;
    private String nombre;
    private int limite;

    public Contador(String nombre, int limite){
        this.nombre = nombre;
        this.limite = limite;
    }


    @Override
    public void run() {

        while (contador <= limite){

            System.out.println("Hilo " + nombre + " :  " + contador);
            contador++;
        }

        System.out.println("ya acabo " + nombre);

    }


}
