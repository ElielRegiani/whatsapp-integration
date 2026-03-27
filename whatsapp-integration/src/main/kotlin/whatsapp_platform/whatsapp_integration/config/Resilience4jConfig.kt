package whatsapp_platform.whatsapp_integration.config

import io.github.resilience4j.bulkhead.BulkheadConfig
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig
import io.github.resilience4j.retry.RetryConfig
import io.github.resilience4j.timelimiter.TimeLimiterConfig
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.Duration

@Configuration
class Resilience4jConfig {
    @Bean
    fun circuitBreakerConfig(): CircuitBreakerConfig =
        CircuitBreakerConfig.custom()
            .failureRateThreshold(50f)
            .waitDurationInOpenState(Duration.ofSeconds(30))
            .slidingWindowSize(10)
            .build()

    @Bean
    fun retryConfig(): RetryConfig =
        RetryConfig.custom<Any>()
            .maxAttempts(3)
            .waitDuration(Duration.ofMillis(500))
            .retryExceptions(java.io.IOException::class.java)
            .build()

    @Bean
    fun timeLimiterConfig(): TimeLimiterConfig =
        TimeLimiterConfig.custom()
            .timeoutDuration(Duration.ofSeconds(2))
            .build()

    @Bean
    fun bulkheadConfig(): BulkheadConfig =
        BulkheadConfig.custom()
            .maxConcurrentCalls(5)
            .maxWaitDuration(Duration.ofMillis(100))
            .build()
}
