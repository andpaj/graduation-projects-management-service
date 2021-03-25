package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared;


import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Random;

@Component
public class Utils {

    private final Random RANDOM = new SecureRandom();
    private final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";


    public String generateUserId(int length) {
        return generateRandomString(length);
    }

    public String generateProjectId(int length) {
        return generateRandomString(length);
    }

    public String generateGroupId(int length) {
        return generateRandomString(length);
    }

    public String generateTagId(int length) {
        return generateRandomString(length);
    }

    public String generateTeamId(int length) {
        return generateRandomString(length);
    }

    public String generateTeamMemberId(int length) {
        return generateRandomString(length);
    }

    public String generateApplicationId(int length) {
        return generateRandomString(length);
    }

    private String generateRandomString(int length) {
        StringBuilder returnValue = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            returnValue.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
        }

        return new String(returnValue);
    }
}