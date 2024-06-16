package com.stalinhuallullo.endpoint.Webhook.Controller;

import com.stalinhuallullo.endpoint.Webhook.Service.MessageWhatsappService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/")
public class WebhookController {

    @Autowired
    private MessageWhatsappService service;

    private String VERIFY_TOKEN = "TaylorSwift";


    @GetMapping("webhook")
    public ResponseEntity<String> webhook(
            @RequestHeader Map<String, String> headers,
            @RequestParam(name = "hub.challenge") String challenge,
            @RequestParam(name = "hub.verify_token") String verify_token,
            @RequestParam(name = "hub.mode") String mode
    ){
        //@RequestParam(name = "hub.challenge") Optional<String> challenge,
        //@RequestParam(name = "hub.verify_token") Optional<String> verify_token
        System.out.println("Recibido el payload del webhook headers: " + headers);
        System.out.println("Recibido el payload del webhook challenge: " + challenge);
        System.out.println("Recibido el payload del webhook verify_token: " + verify_token);
        System.out.println("Recibido el payload del webhook mode: " + mode);
        //if(token === verify_token){

        //}
        if (mode != null && verify_token != null) {
            if (mode.equals("subscribe") && verify_token.equals(VERIFY_TOKEN)) {
                System.out.println("Webhook Verified");
                return ResponseEntity.ok(challenge);
            }
        }

        System.out.println("W3333333333");
        return ResponseEntity.status(403).body(null);

        //return new ResponseEntity<>(challenge.get("challenge"), HttpStatus.OK);
    }

    @PostMapping("/webhook")
    public ResponseEntity<String> handleWebhook(@RequestBody String payload) {
        // Aquí puedes manejar la lógica del webhook
        System.out.println("Recibido el payload del webhook: " + payload);

        // Puedes devolver una respuesta apropiada al cliente que envió el webhook
        service.guardarMensaje(payload);


        return new ResponseEntity<>("Webhook recibido correctamente", HttpStatus.OK);
    }
}
