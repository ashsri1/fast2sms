package com.app.fast2sms.service;


import com.app.fast2sms.configuration.Fast2SMSConfig;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class OtpService {

    @Autowired
    private Fast2SMSConfig fast2SMSConfig;

    private Map<String, String> otpData = new HashMap<>();

    public String generateOtp() {
        int otp = (int) (Math.random() * 1000000);
        return String.format("%06d", otp);
    }

    public String sendOtp(String mobileNumber) throws IOException {
        String otp = generateOtp();
        otpData.put(mobileNumber, otp);

        OkHttpClient client = new OkHttpClient();

        String url = "https://www.fast2sms.com/dev/bulkV2?authorization=" + fast2SMSConfig.getApiKey() +
                "&route=v3&sender_id=TXTIND&message=" + otp + "&language=english&flash=0&numbers=" + mobileNumber;

        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("cache-control", "no-cache")
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public String validateOtp(String mobileNumber, String otp) {
        String storedOtp = otpData.get(mobileNumber);
        if (storedOtp != null && storedOtp.equals(otp)) {
            otpData.remove(mobileNumber);  // OTP is valid, remove it from the map
            return "OTP is valid!";
        } else {
            return "Invalid OTP!";
        }
    }
}
