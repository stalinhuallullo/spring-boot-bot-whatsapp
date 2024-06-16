package com.stalinhuallullo.endpoint.Webhook.Service;

import com.stalinhuallullo.endpoint.Webhook.Model.WhatsAppBusiness;

import java.util.List;


public interface MessageWhatsappService {

    List<WhatsAppBusiness> listarMensajes();
    int guardarMensaje(String obj);
}
