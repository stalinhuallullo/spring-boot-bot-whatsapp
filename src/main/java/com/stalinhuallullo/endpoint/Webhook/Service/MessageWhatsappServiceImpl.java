package com.stalinhuallullo.endpoint.Webhook.Service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.stalinhuallullo.endpoint.Webhook.Model.WhatsAppBusiness;
import com.stalinhuallullo.endpoint.Webhook.Model.WhatsAppBusinessAccount;
import com.stalinhuallullo.endpoint.Webhook.Repository.MessageWhatsappRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageWhatsappServiceImpl implements MessageWhatsappService {

    @Autowired
    private MessageWhatsappRepository repository;

    @Override
    public List<WhatsAppBusiness> listarMensajes() {
        return repository.listarMensajes();
    }

    @Override
    public int guardarMensaje(String json) {
        try {
            WhatsAppBusiness chatBotWhatsapp = new WhatsAppBusiness();
            //repository.save(gg);

            ObjectMapper mapper = new ObjectMapper();

            WhatsAppBusinessAccount whatsAppBusinessAccount = mapper.readValue(json, WhatsAppBusinessAccount.class);

            chatBotWhatsapp.setObject_name(whatsAppBusinessAccount.getObject());
            chatBotWhatsapp.setName(whatsAppBusinessAccount.getEntry().get(0).getChanges().get(0).getValue().getContacts().get(0).getProfile().getName());
            chatBotWhatsapp.setWa_id(whatsAppBusinessAccount.getEntry().get(0).getChanges().get(0).getValue().getMessages().get(0).getFrom());
            chatBotWhatsapp.setMessaging_product(whatsAppBusinessAccount.getEntry().get(0).getChanges().get(0).getValue().getMessaging_product());
            chatBotWhatsapp.setTimes_tamp(whatsAppBusinessAccount.getEntry().get(0).getChanges().get(0).getValue().getMessages().get(0).getTimestamp());
            chatBotWhatsapp.setBody(whatsAppBusinessAccount.getEntry().get(0).getChanges().get(0).getValue().getMessages().get(0).getText().getBody());
            chatBotWhatsapp.setJson_mensaje(json);
            chatBotWhatsapp.setEstado("ACTIVO");
            chatBotWhatsapp.setSeguimiento("ENVIADO");
            chatBotWhatsapp.setType_message(whatsAppBusinessAccount.getEntry().get(0).getChanges().get(0).getValue().getMessages().get(0).getType());


            chatBotWhatsapp = repository.saveAndFlush(chatBotWhatsapp);

            System.out.println("saveChat chatBotWhatsapp ===> " + chatBotWhatsapp.getId());
            return chatBotWhatsapp.getId();

        } catch (Exception e) {
            System.out.println("error ==> " + e);
            e.printStackTrace();
        }
        return 0;
    }


}
