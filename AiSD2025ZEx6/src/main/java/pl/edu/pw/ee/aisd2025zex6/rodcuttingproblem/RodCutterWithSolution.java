package pl.edu.pw.ee.aisd2025zex6.rodcuttingproblem;

public class RodCutterWithSolution extends RodCutter {

    @Override
    public RodCutterExtendedResult cutRod(int[] prices, int rodLength) {
        validateInput(prices, rodLength);

        int[] results = new int[rodLength + 1];
        int[] solutions = new int[rodLength + 1];

        cutRod(prices, rodLength, results, solutions);

        RodCutterExtendedResult finalResult = new RodCutterExtendedResult(
                results[rodLength],
                results,
                solutions);

        return finalResult;
    }

    private void cutRod(int[] prices, int rodLength, int[] results, int[] solutions) {
        int prevResult;
        int result;

        for (int i = 1; i <= rodLength; i++) {
            prevResult = Integer.MIN_VALUE;

            for (int j = 1; j <= i; j++) {
                result = prices[j - 1] + results[i - j];

                if (result > prevResult) {
                    prevResult = result;
                    solutions[i] = j;
                }
            }

            results[i] = prevResult;
        }

    }

}
