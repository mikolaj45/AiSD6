package pl.edu.pw.ee.aisd2025zex6.matrixchainorder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import org.junit.jupiter.api.Test;

public abstract class MatrixChainOrderTest {

    MatrixChainOrder matrixChain;
    DataUtils dataUtils;

    private MatrixChainOrderTest() {
    }

    public MatrixChainOrderTest(MatrixChainOrder matrixChain) {
        this.matrixChain = matrixChain;
        this.dataUtils = new DataUtils();
    }

    @Test
    public void should_ThrowException_When_InputIsNull() {
        // given
        int[] matrixSizes = null;

        // when
        Throwable exceptionCaught = catchThrowable(() -> {
            matrixChain.findOptimalOrder(matrixSizes);
        });

        // then
        assertThat(exceptionCaught)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("The matrixSizes cannot be null!");
    }

    @Test
    public void should_ThrowException_When_InputSizeIsLessThanTwo() {
        // given
        int[] matrixSizes = {7};

        // when
        Throwable exceptionCaught = catchThrowable(() -> {
            matrixChain.findOptimalOrder(matrixSizes);
        });

        // then
        assertThat(exceptionCaught)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("The matrixSizes must contain at least two values!");
    }

    @Test
    public void should_ReturnCorrectResult_When_OnlyTwoMatricesAsInput() {
        // given
        int[] matrixSizes = {2, 2, 2};

        // when
        MatrixChainOrderResult result = matrixChain.findOptimalOrder(matrixSizes);
        int minSumResult = result.getMinMultiplyCost();

        // then
        int expectedMinSum = 8;
        assertThat(minSumResult).isEqualTo(expectedMinSum);
    }

    /*
     * Based on: Cormen, Leiserson, Rivest, Stein
     */
    @Test
    public void should_ReturnCorrectResult_When_CorrectSmallInput() {
        // given
        int[] matrixSizes = {30, 35, 15, 5, 10, 20, 25};

        // when
        MatrixChainOrderResult result = matrixChain.findOptimalOrder(matrixSizes);
        int resultMinSum = result.getMinMultiplyCost();

        // then
        int expectedMinSum = 15125;
        assertThat(resultMinSum).isEqualTo(expectedMinSum);
    }

    @Test
    public void should_ReturnCorrecetResult_When_AllSizesAreEquals() {
        // given
        int nOfMatrices = 20;
        int nOfSizes = nOfMatrices + 1;
        int sizeOfMatrix = 2;
        int[] matrixSizes = dataUtils.prepareArrayWithTheSameValue(nOfSizes, sizeOfMatrix);

        // when
        MatrixChainOrderResult result = matrixChain.findOptimalOrder(matrixSizes);
        int resultMinSum = result.getMinMultiplyCost();

        // then
        int expectedMinSum = 152;
        assertThat(resultMinSum).isEqualTo(expectedMinSum);
    }
}
