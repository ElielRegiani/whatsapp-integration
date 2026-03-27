package whatsapp_platform.whatsapp_integration.dto.response

data class InternalSendMessageResponse(
    val status: String,
    val event: String = "whatsapp_send",
    val latency: Long? = null,
    val error: String? = null
)
