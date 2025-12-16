package pl.edu.pw.ee.aisd2025zex6.rodcuttingproblem;

import static java.lang.Math.max;

public class RodCutterRecursive extends RodCutter {

    @Override
    public RodCutterResult cutRod(int[] prices, int rodLength) {
        validateInput(prices, rodLength);

        int result = cutRodTopDown(prices, rodLength);

        RodCutterResult finalResult = new RodCutterResult(result);

        return finalResult;
    }

    private int cutRodTopDown(int[] prices, int rodLength) {

        int result = Integer.MIN_VALUE;

        if (rodLength == 0) {
            result = 0;

        } else {
            for (int i = 1; i <= rodLength; i++) {
                result = max(result, prices[i - 1] + cutRodTopDown(prices, rodLength - i));
            }
        }

        return result;
    }

}
