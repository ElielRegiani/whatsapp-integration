package whatsapp_platform.whatsapp_integration.client

import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono
import whatsapp_platform.whatsapp_integration.dto.request.WhatsAppSendMessageRequest
import reactor.core.publisher.Mono

@Component
class WhatsAppClient(
    @Value("\${whatsapp.api-url}") private val whatsappApiUrl: String,
    @Value("\${whatsapp.token}") private val whatsappToken: String,
    private val webClientBuilder: WebClient.Builder
) {
    private val webClient: WebClient by lazy {
        webClientBuilder.baseUrl(whatsappApiUrl).build()
    }

    fun sendMessage(phoneNumberId: String, request: WhatsAppSendMessageRequest): Mono<String> {
        return webClient.post()
            .uri("/v17.0/$phoneNumberId/messages")
            .header(HttpHeaders.AUTHORIZATION, "Bearer $whatsappToken")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(request)
            .retrieve()
            .bodyToMono(String::class.java)
    }
}
