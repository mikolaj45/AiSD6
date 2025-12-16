package pl.edu.pw.ee.aisd2025zex6.matrixchainorder;

public class MatrixChainOrderTopDown extends MatrixChainOrder {

    @Override
    public MatrixChainOrderResult findOptimalOrder(int[] matrixSizes) {
        validateInput(matrixSizes);

        int numOfMatrices = matrixSizes.length - 1;
        int[][] costCounters = initCostCounters(numOfMatrices + 1);

        int startIndex = 1;
        int minMultiplyCost = findOptimalOrderCost(costCounters, matrixSizes, startIndex, numOfMatrices);

        return new MatrixChainOrderResult(minMultiplyCost);
    }

    private int[][] initCostCounters(int size) {
        int[][] costCounters = new int[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                costCounters[i][j] = Integer.MAX_VALUE;
            }
        }
        return costCounters;
    }

    private int findOptimalOrderCost(int[][] costCounters, int[] matrixSizes, int startId, int endId) {
        if (startId == endId) {
            return 0;
        }

        // SPAMIĘTYWANIE: Jeśli wartość została już obliczona, zwróć ją.
        if (costCounters[startId][endId] < Integer.MAX_VALUE) {
            return costCounters[startId][endId];
        }

        int calculationCost;
        int minCost = Integer.MAX_VALUE;

        for (int pivotIndex = startId; pivotIndex < endId; pivotIndex++) {
            int subMatricesCost = findOptimalOrderCost(costCounters, matrixSizes, startId, pivotIndex)
                                + findOptimalOrderCost(costCounters, matrixSizes, pivotIndex + 1, endId);

            int currentMultiplyCost = matrixSizes[startId - 1] * matrixSizes[pivotIndex] * matrixSizes[endId];
            calculationCost = subMatricesCost + currentMultiplyCost;

            if (calculationCost < minCost) {
                minCost = calculationCost;
            }
        }

        // Zapisz wynik w tablicy
        costCounters[startId][endId] = minCost;

        return minCost;
    }
}