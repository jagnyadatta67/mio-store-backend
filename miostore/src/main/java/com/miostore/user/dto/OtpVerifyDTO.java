package com.miostore.user.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OtpVerifyDTO {
    private String identifier;
    private String otp;
}