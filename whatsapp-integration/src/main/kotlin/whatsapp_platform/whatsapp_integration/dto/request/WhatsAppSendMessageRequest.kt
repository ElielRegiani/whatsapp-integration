package whatsapp_platform.whatsapp_integration.dto.request

data class WhatsAppSendMessageRequest(
    val messaging_product: String = "whatsapp",
    val to: String,
    val type: String = "text",
    val text: TextBody
) {
    data class TextBody(val body: String)
}
