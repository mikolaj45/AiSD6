package pl.edu.pw.ee.aisd2025zex6.matrixchainorder;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

public class MatrixChainOrderSolutionTest {

    @Test
    public void should_ReconstructSolution_For_CormenData() {
        int[] matrixSizes = {30, 35, 15, 5, 10, 20, 25};
        MatrixChainOrderBottomUp alg = new MatrixChainOrderBottomUp();

        // when
        MatrixChainOrderExtendedResult result = alg.findOptimalOrder(matrixSizes);
        String solutionStr = result.reconstructOptimalSolutions();
        int minCost = result.getMinMultiplyCost();

        // then
        System.out.println("=== ZADANIE 2: Odtworzenie rozwiązania ===");
        System.out.println("Dane wejściowe (wymiary): {30, 35, 15, 5, 10, 20, 25}");
        System.out.println("Minimalny koszt mnożeń: " + minCost);
        System.out.println("Optymalne nawiasowanie: " + solutionStr);
        System.out.println("==========================================");

        // Oczekiwany wynik dla tego zestawu danych: ((A1 * (A2 * A3)) * ((A4 * A5) * A6))
        assertThat(solutionStr).isEqualTo("((A1 * (A2 * A3)) * ((A4 * A5) * A6))");
    }
}