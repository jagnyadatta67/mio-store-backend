package com.miostore.payment.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpiQrResponse {
    private String qrDataUrl;

}
