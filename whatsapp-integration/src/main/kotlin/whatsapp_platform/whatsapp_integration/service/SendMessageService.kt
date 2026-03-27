package whatsapp_platform.whatsapp_integration.service

import org.springframework.stereotype.Service
import whatsapp_platform.whatsapp_integration.client.WhatsAppClient
import whatsapp_platform.whatsapp_integration.dto.request.InternalSendMessageRequest
import whatsapp_platform.whatsapp_integration.dto.request.WhatsAppSendMessageRequest
import whatsapp_platform.whatsapp_integration.dto.response.InternalSendMessageResponse
import reactor.core.publisher.Mono
import java.time.Duration
import java.time.Instant

@Service
class SendMessageService(
    private val whatsAppClient: WhatsAppClient
) {
    fun send(request: InternalSendMessageRequest, phoneNumberId: String): Mono<InternalSendMessageResponse> {
        val start = Instant.now()
        val waRequest = WhatsAppSendMessageRequest(
            to = request.phone,
            text = WhatsAppSendMessageRequest.TextBody(request.message)
        )
        return whatsAppClient.sendMessage(phoneNumberId, waRequest)
            .map {
                InternalSendMessageResponse(
                    status = "success",
                    latency = Duration.between(start, Instant.now()).toMillis()
                )
            }
            .onErrorResume { ex ->
                Mono.just(
                    InternalSendMessageResponse(
                        status = "error",
                        latency = Duration.between(start, Instant.now()).toMillis(),
                        error = ex.message
                    )
                )
            }
    }
}
