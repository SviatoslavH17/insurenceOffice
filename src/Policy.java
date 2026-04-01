public class Policy {
    private String policyNumber;
    private String clientName;
    private double basePremium;
    private int riskLevel;
    private double vehicleValue;
    private boolean hasAlarm;
    private boolean claimFreeClient;

    private static int createdPolicyCount = 0;
    private static final double ADMINISTRATIVE_FEE = 80.0;

    public Policy(String policyNumber, String clientName, double basePremium, int riskLevel, double vehicleValue, boolean hasAlarm, boolean claimFreeClient) {
        this.policyNumber = policyNumber;
        this.clientName = clientName;
        this.basePremium = basePremium;
        this.riskLevel = riskLevel;
        this.vehicleValue = vehicleValue;
        this.hasAlarm = hasAlarm;
        this.claimFreeClient = claimFreeClient;
        createdPolicyCount++;
    }
    public String getPolicyNumber() {
        return policyNumber;
    }
    public String getClientName() {
        return clientName;
    }
    public double getBasePremium() {
        return basePremium;
    }
    public int getRiskLevel() {
        return riskLevel;
    }
    public double getVehicleValue() {
        return vehicleValue;
    }
    public boolean getHasAlarm() {
        return hasAlarm;
    }
    public boolean getClaimFreeClient() {
        return claimFreeClient;
    }
    public double calculateFinalPremium() {
        double result = basePremium + ADMINISTRATIVE_FEE;
        result = result + riskLevel * 120;
        if (vehicleValue > 60000) {
            result = result + 150;
        }
        if (hasAlarm) {
            result = result - 100;
        }
        if (claimFreeClient) {
            result = result * 0.9;
        }
        if (result < basePremium) {
            result = basePremium;
        }
        return Math.round(result * 100.0) / 100.0;
    }
    public double calculateRenewalPremium() {
        double current = calculateFinalPremium();
        double renewal = current;
        if (riskLevel == 4) {
            renewal = renewal * 1.10;
        } else if (riskLevel >= 5) {
            renewal = renewal * 1.20;
        }
        if (vehicleValue > 60000) {
            renewal = renewal + 150;
        }
        if (claimFreeClient) {
            renewal = renewal * 0.92;
        }
        if (hasAlarm) {
            renewal = renewal * 0.95;
        }
        if (renewal < current * 0.90) {
            renewal = current * 0.90;
        }
        if (renewal > current * 1.25) {
            renewal = current * 1.25;
        }
        return Math.round(renewal * 100.0) / 100.0;
    }
    public String getRiskSummary() {
        if (riskLevel <= 2) {
            return "Low risk";
        } else if (riskLevel == 3) {
            return "Medium risk";
        } else {
            return "High risk";
        }
    }
    public static int getCreatedPolicyCount() {
        return createdPolicyCount;
    }
    public String toString() {
        return "Policy number: " + policyNumber +
                ", Client: " + clientName +
                ", Final premium: " + calculateFinalPremium() +
                ", Renewal premium: " + calculateRenewalPremium();
    }
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Policy) {
            Policy other = (Policy) obj;
            return this.policyNumber.equals(other.policyNumber);
        }
        return false;
    }
}