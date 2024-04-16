package com.exam.system.security.configurations;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@Setter
@Getter
@ConfigurationProperties(prefix = "rsa")
public class JwtKeys {
    private RSAPrivateKey privateKey;
    private RSAPublicKey publicKey;
    private String keyID;
}
