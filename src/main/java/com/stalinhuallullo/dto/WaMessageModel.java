package com.stalinhuallullo.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WaMessageModel {
    private int id;
    private String object_name;
    private String name;
    private String wa_id;
    private String messaging_product;
    private String times_tamp;
    private String body;
    private String json_mensaje;
    private String estado;
    private String seguimiento;
    private String type_message;

    public void print() {
        //Logger.log("id " + id + " jid " + remoteJid + " service " + service + " text " + text + " timestamp " + timestamp + " attachment " + mime);
        System.out.println("id " + id + " wa_id " + wa_id + " type_message " + type_message + " text " + body + " timestamp " + times_tamp );
    }


}
