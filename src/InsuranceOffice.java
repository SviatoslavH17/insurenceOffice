import java.util.ArrayList;

public class InsuranceOffice {
    private String name;
    private ArrayList<Policy> policies;
    public InsuranceOffice(String name) {
        this.name = name;
        policies = new ArrayList<>();
    }
    public void addPolicy(Policy policy) {
        policies.add(policy);
    }
    public void printReport() {
        System.out.println("Insurance office: " + name);
        for (int i = 0; i < policies.size(); i++) {
            System.out.println(policies.get(i));
        }
    }
    public double calculateTotalPremium() {
        double total = 0;
        for (int i = 0; i < policies.size(); i++) {
            total = total + policies.get(i).calculateFinalPremium();
        }
        return Math.round(total * 100.0) / 100.0;
    }
    public double calculateTotalRenewalForecast() {
        double total = 0;
        for (int i = 0; i < policies.size(); i++) {
            total = total + policies.get(i).calculateRenewalPremium();
        }
        return Math.round(total * 100.0) / 100.0;
    }
    public int countHighRiskPolicies() {
        int count = 0;
        for (int i = 0; i < policies.size(); i++) {
            if (policies.get(i).getRiskLevel() >= 4) {
                count++;
            }
        }
        return count;
    }
    public Policy findByNumber(String policyNumber) {
        for (int i = 0; i < policies.size(); i++) {
            if (policies.get(i).getPolicyNumber().equals(policyNumber)) {
                return policies.get(i);
            }
        }
        return null;
    }
    public void printCheaperThan(double threshold) {
        for (int i = 0; i < policies.size(); i++) {
            if (policies.get(i).calculateFinalPremium() < threshold) {
                System.out.println(policies.get(i));
            }
        }
    }
}