package vttp2023.batch3.assessment.paf.bookings.utility;

import java.security.SecureRandom;

import org.springframework.stereotype.Component;

@Component
public class BookingsUtility {
    
    public String generateUUID(int limit) {
        SecureRandom sr = new SecureRandom();
        StringBuilder strbuilder = new StringBuilder();
        while(strbuilder.length() < limit) {
            strbuilder.append(Integer.toHexString(sr.nextInt()));
        }

        return strbuilder.toString().substring(0, limit);

    }
}
