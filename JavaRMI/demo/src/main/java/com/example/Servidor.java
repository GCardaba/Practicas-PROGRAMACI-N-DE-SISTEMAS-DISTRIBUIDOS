package com.example;

/* 
import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.math.*;

public class Servidor {
	private static final int PUERTO = 1100; //Si cambias aquí el puerto, recuerda cambiarlo en el cliente
    public static void main(String[] args) throws RemoteException, AlreadyBoundException {
        Remote remote = UnicastRemoteObject.exportObject(new Interfaz() {
        	/*
				Sobrescribir opcionalmente los métodos que escribimos en la interfaz
        	
            @Override
            public float sumar(float numero1, float numero2) throws RemoteException {
                return numero1 + numero2;
            };
            
            @Override
            public float restar(float numero1, float numero2) throws RemoteException {
                return numero1 - numero2;
            };

            @Override
            public float multiplicar(float numero1, float numero2) throws RemoteException {
                return numero1 * numero2;
            };

            @Override
            public float dividir(float numero1, float numero2) throws RemoteException {
                return numero1 / numero2;
            };

            @Override
            public float sqrt(float numero1) throws RemoteException {
                return (float) Math.sqrt(numero1); 
            };
            
        }, 0);
        Registry registry = LocateRegistry.createRegistry(PUERTO);
       	System.out.println("Servidor escuchando en el puerto " + String.valueOf(PUERTO));
        registry.bind("Calculadora", remote); // Registrar calculadora
    }
}
*/

import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
public class Servidor {
	private static final int PUERTO = 1100; //Si cambias aquí el puerto, recuerda cambiarlo en el cliente
    public static void main(String[] args) throws RemoteException, AlreadyBoundException {
        Remote remote = UnicastRemoteObject.exportObject(new Interfaz() {
        	/*
				Sobrescribir opcionalmente los métodos que escribimos en la interfaz
        	*/
            @Override
            public float sumar(float numero1, float numero2) throws RemoteException {
                return numero1 + numero2;
            };
            @Override
            public float restar(float numero1, float numero2) throws RemoteException {
                return numero1 - numero2;
            };

            @Override
            public float multiplicar(float numero1, float numero2) throws RemoteException {
                return numero1 * numero2;
            };

            @Override
            public float dividir(float numero1, float numero2) throws RemoteException {
                if (numero2 == 0) {
                    throw new RemoteException( "No se puede dividir entre 0"); //el "s:" indica que es un string
                }   
                return numero1 / numero2;
            };

            @Override
            public float sqrt(float numero1) throws RemoteException {
                if (numero1 < 0) {
                    throw new RemoteException("No se puede calcular la raíz cuadrada de un número negativo"); 
                }
                return (float) Math.sqrt(numero1); 
            };
        }, 0);
        Registry registry = LocateRegistry.createRegistry(PUERTO);
       	System.out.println("Servidor escuchando en el puerto " + String.valueOf(PUERTO));
        registry.bind("Calculadora", remote); // Registrar calculadora
    }
}