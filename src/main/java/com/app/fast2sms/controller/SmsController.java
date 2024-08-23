package com.app.fast2sms.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.app.fast2sms.service.OtpService;

import java.io.IOException;

@RestController

public class SmsController {

    @Autowired
    private OtpService otpService;

    @PostMapping("/sendOtp")
    public String sendOtp(@RequestParam String mobileNumber) throws IOException {
        return otpService.sendOtp(mobileNumber);
    }

    @PostMapping("/validateOtp")
    public String validateOtp(@RequestParam String mobileNumber, @RequestParam String otp) {
        return otpService.validateOtp(mobileNumber, otp);
    }
}
