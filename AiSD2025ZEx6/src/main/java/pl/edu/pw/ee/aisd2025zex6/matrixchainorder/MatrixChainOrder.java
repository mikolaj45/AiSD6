package pl.edu.pw.ee.aisd2025zex6.matrixchainorder;

import static java.util.Objects.isNull;

public abstract class MatrixChainOrder {

    public abstract MatrixChainOrderResult findOptimalOrder(int[] matrixSizes);

    void validateInput(int[] matrixSizes) {
        if (isNull(matrixSizes)) {
            throw new IllegalArgumentException("The matrixSizes cannot be null!");
        }
        if (matrixSizes.length < 2) {
            throw new IllegalArgumentException("The matrixSizes must contain at least two values!");
        }
    }

}
