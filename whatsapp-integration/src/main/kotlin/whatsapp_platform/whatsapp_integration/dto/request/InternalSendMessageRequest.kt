package whatsapp_platform.whatsapp_integration.dto.request

data class InternalSendMessageRequest(
    val phone: String,
    val message: String
)
