package pl.edu.pw.ee.aisd2025zex6.rodcuttingproblem;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class RodCutterWithSolutionTest extends RodCutterTest {

    private RodCutterWithSolution rodCutter;

    public RodCutterWithSolutionTest() {
        super(new RodCutterWithSolution());
    }

    @BeforeEach
    public void setup() {
        rodCutter = new RodCutterWithSolution();
    }

    /*
     * Based on: Cormen, Leiserson, Rivest, Stein
     */
    @ParameterizedTest
    @CsvSource({
        "1, 1",
        "2, 2",
        "3, 3",
        "10, 10"
    })
    public void should_FindCorrectSolution_When_NoCuttingIsNeeded(int rodLength, String expectedSolution) {
        // given
        int[] prices = {1, 5, 8, 9, 10, 17, 17, 20, 24, 30};

        // when
        RodCutterExtendedResult result = rodCutter.cutRod(prices, rodLength);

        // then
        assertThat(result.buildSolutionStr()).isEqualTo(expectedSolution);
    }

    /*
     * Based on: Cormen, Leiserson, Rivest, Stein
     */
    @ParameterizedTest
    @CsvSource({
        "4, 2_2",
        "5, 2_3",
        "7, 1_6",
        "8, 2_6",
        "9, 3_6"
    })
    public void should_FindCorrectSolution_When_CuttingIsNeeded(int rodLength, String expectedSolution) {
        // given
        int[] prices = {1, 5, 8, 9, 10, 17, 17, 20, 24, 30};

        // when
        RodCutterExtendedResult result = rodCutter.cutRod(prices, rodLength);

        // then
        assertThat(result.buildSolutionStr()).isEqualTo(expectedSolution);
    }

}
