package pl.edu.pw.ee.aisd2025zex6.rodcuttingproblem;

import static java.util.Objects.isNull;

public abstract class RodCutter {

    public abstract RodCutterResult cutRod(int[] prices, int rodLength);

    void validateInput(int[] prices, int rodLength) {
        if (isNull(prices)) {
            throw new IllegalArgumentException("Price array cannot be null!");
        }

        if (rodLength < 0) {
            throw new IllegalArgumentException("Rod length cannot be negative!");
        }

        if (prices.length < rodLength) {
            throw new IllegalArgumentException("The size of the price array cannot be smaller than the length of the rod!");
        }

    }
}
