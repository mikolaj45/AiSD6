package pl.edu.pw.ee.aisd2025zex6.rodcuttingproblem;

import static java.lang.Math.max;

public class RodCutterBottomUp extends RodCutter {

    @Override
    public RodCutterResult cutRod(int[] prices, int rodLength) {
        validateInput(prices, rodLength);

        int[] results = new int[rodLength + 1];

        int result;

        for (int i = 1; i <= rodLength; i++) {
            result = Integer.MIN_VALUE;

            for (int j = 1; j <= i; j++) {
                result = max(result, prices[j - 1] + results[i - j]);
            }

            results[i] = result;
        }

        RodCutterResult finalResult = new RodCutterResult(results[rodLength]);

        return finalResult;
    }

}
