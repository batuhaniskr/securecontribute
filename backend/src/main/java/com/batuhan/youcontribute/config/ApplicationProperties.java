package com.batuhan.youcontribute.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("application")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ApplicationProperties {

    private Integer importFrequency;
}
