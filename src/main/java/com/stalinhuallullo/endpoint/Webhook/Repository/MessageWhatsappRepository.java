package com.stalinhuallullo.endpoint.Webhook.Repository;

import com.stalinhuallullo.endpoint.Webhook.Model.WhatsAppBusiness;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageWhatsappRepository extends JpaRepository<WhatsAppBusiness, Integer> {

    @Query(value = "SELECT * FROM chat_bot_whatsapp", nativeQuery = true)
    List<WhatsAppBusiness> listarMensajes();
    //List<ChatBotWhatsapp> listarMensajes();
}
