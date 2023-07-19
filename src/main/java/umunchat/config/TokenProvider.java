package umunchat.config;

import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
public class TokenProvider {
    public String createBasicAuthToken(String phone, String md5Password) {
        String plainToken = String.format("%s:%s", phone, md5Password);
        return String.format("Basic %s", Base64.getEncoder().encodeToString(plainToken.getBytes()).toString());
    }
}
