package com.stalinhuallullo.ivr;

import com.stalinhuallullo.base.db.Db;
import com.stalinhuallullo.dto.WaMessageModel;

import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class WaMessage {


    private int id;
    private String remoteJid;



    public static ArrayList<WaMessageModel> getNewMessages(int lastidmessage) {

        try {
            String sql = "select * from chat_bot_whatsapp order by id asc limit 20";
            Db db = new Db(sql);
            ResultSet r = db.ask();

            ArrayList<WaMessageModel> result = new ArrayList<>();
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            while (r.next()) {
                //Logger.log("procesando " + r.getString("media_contentype"));

                WaMessageModel mes = new WaMessageModel();
                mes.setId(r.getInt("id"));
                mes.setObject_name(r.getString("object_name"));
                mes.setName(r.getString("name"));
                mes.setWa_id(r.getString("wa_id"));
                mes.setMessaging_product(r.getString("messaging_product"));
                mes.setTimes_tamp(r.getString("times_tamp"));
                mes.setBody(r.getString("body"));
                mes.setJson_mensaje(r.getString("json_mensaje"));
                mes.setEstado(r.getString("estado"));
                mes.setSeguimiento(r.getString("seguimiento"));
                mes.setType_message(r.getString("type_message"));
                //mes.idattach = r.getInt("id_attachment");
                //mes.mediahash = r.getString("media_hash");
                result.add(mes);
            }

            db.closeQuery();
            return result;

        } catch (Exception e){
            System.out.println("Exception WaMessage " + e);
        }

        return null;
    }

    public int getId() {
        return id;
    }

    public String getJid() {
        return remoteJid;
    }
}
