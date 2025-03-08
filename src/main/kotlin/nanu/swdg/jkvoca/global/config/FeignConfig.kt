package nanu.swdg.jkvoca.global.config

import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.Configuration

@Configuration
@EnableFeignClients(basePackages = ["nanu.swdg.jkvoca.global.external"])
class FeignConfig