package com.proyecto.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@ConfigurationProperties(prefix = "rsa")
@Configuration
@Getter
@Setter
public class RsaKeysConfig {
    private RSAPublicKey publicKey;
    private RSAPrivateKey privateKey;
}
