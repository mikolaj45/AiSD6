package pl.edu.pw.ee.aisd2025zex6.matrixchainorder;

import java.util.Arrays;

public class MatrixChainOrderRecursive extends MatrixChainOrder {

    @Override
    public MatrixChainOrderResult findOptimalOrder(int[] matrixSizes) {
        validateInput(matrixSizes);

        int numOfMatrices = matrixSizes.length - 1;

        int[][] costCounters = initCostCounters(numOfMatrices + 1);

        int startIndex = 1;
        int minMultiplyCost = findOptimalOrderCost(costCounters, matrixSizes, startIndex, numOfMatrices);

        MatrixChainOrderResult finalResult = new MatrixChainOrderResult(minMultiplyCost);

        return finalResult;
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
        int calculationCost, subMatricesCost, currentMultiplyCost;
        int result;

        if (startId == endId) {
            costCounters[startId][endId] = 0;

        } else {

            for (int pivotIndex = startId; pivotIndex < endId; pivotIndex++) {

                subMatricesCost = findOptimalOrderCost(costCounters, matrixSizes, startId, pivotIndex)
                        + findOptimalOrderCost(costCounters, matrixSizes, pivotIndex + 1, endId);

                currentMultiplyCost = matrixSizes[startId - 1] * matrixSizes[pivotIndex] * matrixSizes[endId];
                calculationCost = subMatricesCost + currentMultiplyCost;

                if (calculationCost < costCounters[startId][endId]) {
                    costCounters[startId][endId] = calculationCost;
                }
            }
        }

        result = costCounters[startId][endId];

        return result;
    }

}
