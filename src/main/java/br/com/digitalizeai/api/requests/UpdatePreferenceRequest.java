package br.com.digitalizeai.api.requests;

import java.math.BigDecimal;

public class UpdatePreferenceRequest {
    private String preferenceId;
    private BigDecimal totalValue;

    // Getters and Setters
    public String getPreferenceId() {
        return preferenceId;
    }

    public void setPreferenceId(String preferenceId) {
        this.preferenceId = preferenceId;
    }

    public BigDecimal getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(BigDecimal totalValue) {
        this.totalValue = totalValue;
    }
}
