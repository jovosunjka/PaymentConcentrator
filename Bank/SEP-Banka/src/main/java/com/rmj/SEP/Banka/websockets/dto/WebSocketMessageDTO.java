package com.rmj.SEP.Banka.websockets.dto;

public class WebSocketMessageDTO {
    private String status;
    private String redirectUrl;

    public WebSocketMessageDTO() {

    }

    public WebSocketMessageDTO(String status, String redirectUrl) {
        this.status = status;
        this.redirectUrl = redirectUrl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }
}
