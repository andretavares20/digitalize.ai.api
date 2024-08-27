package br.com.digitalizeai.api.application.adapters.controllers;

import java.math.BigDecimal;
import java.util.Collections;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.preference.PreferenceBackUrlsRequest;
import com.mercadopago.client.preference.PreferenceClient;
import com.mercadopago.client.preference.PreferenceItemRequest;
import com.mercadopago.client.preference.PreferenceRequest;
import com.mercadopago.resources.preference.Preference;

import br.com.digitalizeai.api.domain.dtos.requests.UpdatePreferenceRequest;

@RestController
@RequestMapping("/mercadopago")
public class MercadoPagoController {

    private final PreferenceClient preferenceClient;

    public MercadoPagoController() {
        MercadoPagoConfig.setAccessToken("APP_USR-1802293093399013-073020-35dc3c2ad278358c917c78fc117b9677-511926509");
        this.preferenceClient = new PreferenceClient();
    }

    @GetMapping("/create_preference")
    public ResponseEntity<?> createPreference() {
        try {
            PreferenceItemRequest itemRequest = PreferenceItemRequest.builder()
                    .title("Meu produto")
                    .quantity(1)
                    .unitPrice(new BigDecimal("0.01")) // Valor simbólico para inicializar
                    .build();

            PreferenceRequest preferenceRequest = PreferenceRequest.builder()
                    .items(Collections.singletonList(itemRequest))
                    .backUrls(
                            PreferenceBackUrlsRequest.builder()
                                    .success("http://www.seusite.com/success")
                                    .failure("http://www.seusite.com/failure")
                                    .pending("http://www.seusite.com/pending")
                                    .build())
                    .autoReturn("approved")
                    .build();

            Preference preference = preferenceClient.create(preferenceRequest);
            return ResponseEntity.ok(Collections.singletonMap("preferenceId", preference.getId()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("error", e.getMessage()));
        }
    }

    @PostMapping("/update_preference")
    public ResponseEntity<?> updatePreference(@RequestBody UpdatePreferenceRequest updateRequest) {
        try {
            if (updateRequest.getPreferenceId() == null || updateRequest.getTotalValue().compareTo(BigDecimal.ZERO) <= 0) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(Collections.singletonMap("error", "Missing or invalid preferenceId or totalValue"));
            }

            PreferenceItemRequest itemRequest = PreferenceItemRequest.builder()
                    .title("Meu produto")
                    .quantity(1)
                    .unitPrice(updateRequest.getTotalValue())
                    .build();

            PreferenceRequest preferenceUpdateRequest = PreferenceRequest.builder()
                    .items(Collections.singletonList(itemRequest))
                    .backUrls(
                            PreferenceBackUrlsRequest.builder()
                                    .success("http://www.seusite.com/success")
                                    .failure("http://www.seusite.com/failure")
                                    .pending("http://www.seusite.com/pending")
                                    .build())
                    .autoReturn("approved")
                    .build();

            Preference preference = preferenceClient.update(updateRequest.getPreferenceId(), preferenceUpdateRequest);
            return ResponseEntity.ok(Collections.singletonMap("success", true));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("error", e.getMessage()));
        }
    }

    
}