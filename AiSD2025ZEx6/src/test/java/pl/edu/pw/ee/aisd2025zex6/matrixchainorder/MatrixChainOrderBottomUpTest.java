package pl.edu.pw.ee.aisd2025zex6.matrixchainorder;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

public class MatrixChainOrderBottomUpTest extends MatrixChainOrderTest {

    public MatrixChainOrderBottomUpTest() {
        super(new MatrixChainOrderBottomUp());
    }

    @Test
    public void should_ReturnCorrectResult_When_BigNumsOfMatrices() {
        // given
        int nOfMatrices = 1800;
        int nOfSizes = nOfMatrices + 1;
        int sizeOfMatrix = 2;
        int[] matrixSizes = dataUtils.prepareArrayWithTheSameValue(nOfSizes, sizeOfMatrix);

        // when
        MatrixChainOrderResult result = matrixChain.findOptimalOrder(matrixSizes);
        int resultMinSum = result.getMinMultiplyCost();

        // then
        int expectedMinSum = 14392; // 2*2*2*(1800-1)=14392
        assertThat(resultMinSum).isEqualTo(expectedMinSum);
    }

}
