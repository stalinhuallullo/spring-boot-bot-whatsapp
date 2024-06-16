package com.stalinhuallullo.endpoint.Webhook.Controller;

import com.stalinhuallullo.endpoint.Webhook.Model.WhatsAppBusiness;
import com.stalinhuallullo.endpoint.Webhook.Service.MessageWhatsappService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class MensajesWebhookController {

    @Autowired
    private MessageWhatsappService service;


    @GetMapping(value="mensajes")
    @ResponseBody
    public ResponseEntity<List<WhatsAppBusiness>> listarMensajes(){
        List<WhatsAppBusiness> listadoMensajesWhatsapp = service.listarMensajes();

        return ResponseEntity.status(200).body(listadoMensajesWhatsapp);
    }

}
