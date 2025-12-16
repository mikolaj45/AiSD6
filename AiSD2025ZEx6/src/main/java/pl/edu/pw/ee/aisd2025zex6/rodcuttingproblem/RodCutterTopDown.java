package pl.edu.pw.ee.aisd2025zex6.rodcuttingproblem;

import static java.lang.Math.max;

public class RodCutterTopDown extends RodCutter {

    @Override
    public RodCutterResult cutRod(int[] prices, int rodLength) {
        validateInput(prices, rodLength);

        int[] results = initResults(rodLength + 1);

        int maxSumResult = cutRodTopDown(prices, rodLength, results);

        RodCutterResult finalResult = new RodCutterResult(maxSumResult);

        return finalResult;
    }

    private int[] initResults(int size) {
        int[] results = new int[size];

        for (int i = 0; i < size; i++) {
            results[i] = Integer.MIN_VALUE;
        }

        return results;
    }

    private int cutRodTopDown(int[] prices, int rodLength, int[] results) {
        int result = Integer.MIN_VALUE;

        if (results[rodLength] >= 0) {
            result = results[rodLength];

        } else if (rodLength == 0) {
            result = 0;

        } else {
            for (int i = 1; i <= rodLength; i++) {
                result = max(result, prices[i - 1] + cutRodTopDown(prices, rodLength - i, results));
            }
        }

        results[rodLength] = result;

        return result;
    }

}
