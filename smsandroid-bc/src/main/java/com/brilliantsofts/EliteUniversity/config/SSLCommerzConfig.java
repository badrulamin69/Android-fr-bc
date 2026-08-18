package com.brilliantsofts.EliteUniversity.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "sslcommerz")
public class SSLCommerzConfig {

    private String storeId;
    private String storePassword;
    private boolean sandbox = true;
    private String apiUrl;
    private String validationUrl;
    private String successUrl;
    private String failUrl;
    private String cancelUrl;
    private String ipnUrl;
}
