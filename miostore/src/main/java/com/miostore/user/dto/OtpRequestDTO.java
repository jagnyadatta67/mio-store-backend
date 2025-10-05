package com.miostore.user.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OtpRequestDTO {
    private String identifier; // email or phone
}