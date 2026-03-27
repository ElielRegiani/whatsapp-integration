package whatsapp_platform.whatsapp_integration.controller

import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import whatsapp_platform.whatsapp_integration.dto.request.InternalSendMessageRequest
import whatsapp_platform.whatsapp_integration.dto.response.InternalSendMessageResponse
import whatsapp_platform.whatsapp_integration.service.SendMessageService
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/internal")
class InternalSendController(
    private val sendMessageService: SendMessageService,
    @Value("\${whatsapp.phone-number-id}") private val phoneNumberId: String
) {
    @PostMapping("/send")
    fun send(@RequestBody request: InternalSendMessageRequest): Mono<ResponseEntity<InternalSendMessageResponse>> {
        return sendMessageService.send(request, phoneNumberId)
            .map { ResponseEntity.ok(it) }
    }
}
