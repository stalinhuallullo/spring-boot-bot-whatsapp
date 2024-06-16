package com.stalinhuallullo.ivr;

import com.stalinhuallullo.base.db.Db;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Session {

    public final static int ACTIVA = 1;
    public final static int REDIRIGIDA = 2;
    public final static int FINALIZADA = 3;
    public final static int INICIANDO = 4;

    public int status = INICIANDO;

    private int idRule = 1;
    public int retry = 4;
    public int lastQuestionSent = -1;
    WaMessage lastMessage;
    private long tsIncomingMessage = 0;
    private Secuence secuence;

    public void setNextRule(int nextRule) {
        this.idRule = nextRule;
    }

    public int reply(String text) {
        return reply(text, 0, 0);
    }

    public int reply(String text, int idAttachment, int idmessagereply) {
        try {
            System.out.println("reply ====================>");
            java.util.Date date = new java.util.Date(); // new
            // java.sql.Date(timeStamp.getTime());
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String datetime = df.format(date);
            String idAttach = (idAttachment == 0 ? null : "" + idAttachment);
            String mime = "";
            String curtext = text;

            String grouphash = "";
            // if (isgroup) grouphash = Db.getGroupHash(jid);

            curtext = curtext.replace("'", " ");
            curtext = curtext.replace("\"", "\\\"");

            String query = "insert into chat_bot_whatsapp (id, object_name, name, wa_id, messaging_product, times_tamp, body, json_mensaje, estado, seguimiento, type_message)" +
                    " values (0, 'whatsapp_automatico', 'Stalin', '', 'whatsapp','" + datetime + "', '" + curtext + "', '" + text
                    + "', 'ACTIVO','ENVIADO', 'text')";

            System.out.println("query ====> "  + query);
            lastQuestionSent = Db
                    .execute2(query);

        } catch (Exception e) {
            //Logger.log(e.getMessage());
            System.out.println(e.getMessage());
        }
        return 0;
    }


    public void process(final WaMessage m) {
        tsIncomingMessage = System.currentTimeMillis();
        // msgInstenciaEnviado = false;
        //Db.setStatusMsg(m.getId(), "R2");
        final ItemRule rule = secuence.getRute(idRule);

        lastMessage = m;
        /*
         * Thread t = new Thread() { public void run() {
         * IVR.sm.currentProcessing.put(m.getJid(), System.currentTimeMillis());
         * rule.apply(Session.this, lastMessage);
         * IVR.sm.currentProcessing.remove(m.getJid());
         *
         * } };
         *
         * t.start();
         */
        rule.apply(Session.this, lastMessage);

    }
}
