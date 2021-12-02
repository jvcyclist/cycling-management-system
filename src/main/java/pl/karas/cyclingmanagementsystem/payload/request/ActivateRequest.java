package pl.karas.cyclingmanagementsystem.payload.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ActivateRequest {
    @NotBlank
    private String mail;

    @NotBlank
    private String password;

    @NotBlank
    private String activationCode;

}