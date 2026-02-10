package com.ztsoft.aegis_analyzer.domain.model;

public class RetentionDecision {

    private String customerId;
    private Double riskScore; // A nota que a IA dá para o risco de cancelamento (0 a 10).
    private String reasoning; // A explicação "humana" que a IA vai dar.
    private String suggestedAction;// O que devemos fazer (ex: "Dar 10% de desconto").

    public RetentionDecision(String customerId, Double riskScore, String reasoning, String suggestedAction) {
        this.customerId = customerId;
        this.riskScore = riskScore;
        this.reasoning = reasoning;
        this.suggestedAction = suggestedAction;
        validate();
    }

    public void validate(){
        if(riskScore < 0.0 || riskScore > 10.0){
        throw new IllegalArgumentException("riskScore must be between 0.0 and 10.0");
        }
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Double getRiskScore() {
        return riskScore;
    }

    public void setRiskScore(Double riskScore) {
        this.riskScore = riskScore;
    }

    public String getReasoning() {
        return reasoning;
    }

    public void setReasoning(String reasoning) {
        this.reasoning = reasoning;
    }

    public String getSuggestedAction() {
        return suggestedAction;
    }

    public void setSuggestedAction(String suggestedAction) {
        this.suggestedAction = suggestedAction;
    }
}
