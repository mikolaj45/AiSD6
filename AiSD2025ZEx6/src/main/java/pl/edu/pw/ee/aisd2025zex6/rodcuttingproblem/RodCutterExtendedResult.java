package pl.edu.pw.ee.aisd2025zex6.rodcuttingproblem;

import static java.util.Objects.isNull;

public class RodCutterExtendedResult extends RodCutterResult {

    private final int[] maxSumResults;
    private final int[] solutions;

    public RodCutterExtendedResult(int maxSumResult, int[] maxSumResults, int[] solutions) {
        super(maxSumResult);
        validateParams(maxSumResults, solutions);

        this.maxSumResults = maxSumResults;
        this.solutions = solutions;
    }

    public int[] getMaxSumResults() {
        return maxSumResults;
    }

    public int[] getSolutions() {
        return solutions;
    }

    public String buildSolutionStr() {
        int currentRodLength = solutions.length - 1;

        StringBuilder finalPath = new StringBuilder();
        int howMuchToCut;

        while (currentRodLength > 0) {
            howMuchToCut = solutions[currentRodLength];

            finalPath.append(howMuchToCut);

            currentRodLength -= howMuchToCut;

            if (currentRodLength > 0) {
                finalPath.append("_");
            }
        }

        return finalPath.toString();
    }

    private void validateParams(int[] maxSumResults, int[] solutions) {
        if (isNull(maxSumResults)) {
            throw new IllegalArgumentException("Max sum results cannot be null!");
        }

        if (isNull(solutions)) {
            throw new IllegalArgumentException("Solutions cannot be null!");
        }

    }

}
