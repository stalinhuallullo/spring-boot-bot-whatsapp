package com.stalinhuallullo.endpoint.Webhook.Model;


import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "chat_bot_whatsapp")
public class WhatsAppBusiness {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String object_name;
    private String name;
    private String wa_id;
    private String messaging_product;
    private String times_tamp;
    private String body;
    @Column(name = "json_mensaje", columnDefinition = "TEXT")
    private String json_mensaje;
    private String estado;
    private String seguimiento;
    private String type_message;
}
