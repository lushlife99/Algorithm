import java.io.*;

public class Main {

    static double A;
    static double B;
    static int total = 18;
    static int[] primes = {2, 3, 5, 7, 11, 13, 17};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        A = Double.parseDouble(br.readLine()) / 100;
        B = Double.parseDouble(br.readLine()) / 100;

        double aPrimeProb = 0.0;
        double bPrimeProb = 0.0;

        for (int p : primes) {
            aPrimeProb += binomialProb(total, p, A);
            bPrimeProb += binomialProb(total, p, B);
        }

        double result = 1 - (1 - aPrimeProb) * (1 - bPrimeProb);
        System.out.printf("%.10f\n", result);
    }

    private static double binomialProb(int n, int k, double p) {
        return combination(n, k) * Math.pow(p, k) * Math.pow(1 - p, n - k);
    }

    private static long combination(int n, int k) {
        long res = 1;
        for (int i = 1; i <= k; i++) {
            res *= (n - i + 1);
            res /= i;
        }
        return res;
    }
}
