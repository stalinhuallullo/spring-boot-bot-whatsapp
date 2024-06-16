package com.stalinhuallullo.ivr;

import com.stalinhuallullo.dto.WaMessageModel;

import java.util.HashMap;
import java.util.Iterator;

public class SessionManager {
    private HashMap<Integer, Session> list = new HashMap<Integer, Session>();
    public int idcontext;
    public Secuence secuence;
    WaMessage lastMessage;

    public static final long TIMEOUT_INSISTENCIA = 5 * 60 * 1000; // 5 minutos
    public static final long TIMEOUT_DAY = 10 * 60  * 1000; // 10 minutos
    public static final long TIMEOUT_ERROR = 10 * 60 * 1000;
    public int lastidmessage = 0;

    public HashMap<String, Long> currentProcessing = new HashMap<String, Long>();
    public long lastCleanTimeout = 0;



    public SessionManager(int idcontext, Secuence secuence) {
        this.idcontext = idcontext;
        this.secuence = secuence;
    }



    public void process(WaMessageModel m) {
        String jid = m.getWa_id();
        Long ts = null;

        if ((ts = currentProcessing.get(jid)) != null) {

            long tts = ts.longValue();
            if (System.currentTimeMillis() - tts > 30000) {
                currentProcessing.remove(jid);
            } else {
                //Logger.log(jid + " " + m.getId() + " aun procesando mensaje. Lo deja para otra iteracion.");
                System.out.println(jid + " " + m.getWa_id() + " aun procesando mensaje. Lo deja para otra iteracion.");
                return;
            }
        }

        System.out.println(jid + " va en proceso");
        lastidmessage = m.getId();
        Session s = list.get(jid.hashCode());



    }


    public void cleanTimeOut() {
        if (System.currentTimeMillis() - lastCleanTimeout < 10000) return;
        lastCleanTimeout = System.currentTimeMillis();

        //Logger.log("cleanout for " + list.size() + " elements");
        System.out.println("cleanout for " + list.size() + " elements");
        Iterator<Session> it = list.values().iterator();
        while (it.hasNext()) {
            final Session s = it.next();
            if (s.status == Session.FINALIZADA) {
                it.remove();
                continue;
            }



        }
    }


}
