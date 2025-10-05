

package com.miostore.user.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProfileDTO {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private Boolean verified;
}
