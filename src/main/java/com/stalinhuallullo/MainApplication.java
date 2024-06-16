package com.stalinhuallullo;

import com.stalinhuallullo.base.db.Db;
import com.stalinhuallullo.dto.WaMessageModel;
import com.stalinhuallullo.hilo.Contador;
import com.stalinhuallullo.ivr.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import java.beans.PropertyVetoException;
import java.util.ArrayList;

@SpringBootApplication
public class MainApplication extends SpringBootServletInitializer {

    public static final int NODE_SOLICITA_RUT = 0;
    public static final int NODE_VALIDA_RUT_DERIVA = 9;

    public static Secuence secuence;

    public static SessionManager sm;
    public static int idcontext = 9;

    public static void main(String[] args) {
        System.out.println("Hello world!");
        SpringApplication.run(MainApplication.class, args);
        System.out.println("1111111111 ====================>");
        try {
            Db.open();
        } catch (PropertyVetoException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
            return;
        }
        System.out.println("222222222 ====================>");

        secuence = new Secuence();

        System.out.println("333333333 ====================>");
        secuence.addRule(new ItemRule(NODE_SOLICITA_RUT) {
            @Override
            public void apply(Session s, WaMessage m) {

                System.out.println("apply ====================>");
                try {
                    s.reply("➡  Por favor, indica tu rut en este formato: 12345678-9");
                    s.setNextRule(NODE_VALIDA_RUT_DERIVA);
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        MainApplication myIvr = new MainApplication();
        myIvr.run();

 /*
		Contador contador1 = new Contador("Contador 1 ", 10);
		Contador contador2 = new Contador("Contador 2 ", 20);
		Contador contador3 = new Contador("Contador 3 ", 30);
		Contador contador4 = new Contador("Contador 4 ", 40);

		Thread thread1 = new Thread(contador1);
		Thread thread2 = new Thread(contador2);
		Thread thread3 = new Thread(contador3);
		Thread thread4 = new Thread(contador4);


		thread1.start();
		thread2.start();
		thread3.start();
		thread4.start();

		try{
			thread1.join();
			thread2.join();
			thread3.join();
			thread4.join();
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}

		System.out.println("Fin de la ejecución");*/

    }

    public void run() {
        int contador = 0;
        sm = new SessionManager(idcontext, secuence);


        while (true) {
            try {
                System.out.println("sm.lastidmessage ===> "  + sm.lastidmessage);

                ArrayList<WaMessageModel> msgs = WaMessage.getNewMessages(sm.lastidmessage);
                for (WaMessageModel w : msgs) {
                    w.print();
                    sm.process(w);
                }

                Thread.sleep(1000);
                sm.cleanTimeOut();
                System.out.println("ejecutando contador ===> " + contador);
            } catch (InterruptedException ex) {
                //Logger.log(ex.getMessage());
                System.out.println(ex.getMessage());
            }
            contador++;
        }
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(MainApplication.class);
    }
}