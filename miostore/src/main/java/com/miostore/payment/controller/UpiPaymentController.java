package com.miostore.payment.controller;




import com.miostore.payment.dto.UpiQrResponse;
import com.miostore.payment.service.UpiQrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
@CrossOrigin(origins = "*") // or restrict to your frontend domain
public class UpiPaymentController {

    @Autowired
    private UpiQrService upiQrService;

    @PostMapping("/upi-qr")
    public UpiQrResponse getUpiQr(@RequestParam double amount, @RequestParam String orderId) {
        // Generate note
        String note = "Order " + orderId;
        String qrBase64 = upiQrService.generateUpiQr(String.format("%.2f", amount), orderId, note);

        return new UpiQrResponse(qrBase64);
    }
}
