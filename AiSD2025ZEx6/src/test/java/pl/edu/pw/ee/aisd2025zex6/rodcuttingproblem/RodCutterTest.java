package pl.edu.pw.ee.aisd2025zex6.rodcuttingproblem;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public abstract class RodCutterTest {

    private RodCutter rodCutter;

    public RodCutterTest() {
    }

    public RodCutterTest(RodCutter rodCutter) {
        this.rodCutter = rodCutter;
    }

    @Test
    public void should_ThrowException_When_InputPricesIsNull() {
        // given
        int[] prices = null;
        int rodLength = 0;

        // when
        Throwable exceptionCaught = catchThrowable(() -> {
            rodCutter.cutRod(prices, rodLength);
        });

        // then
        assertThat(exceptionCaught)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Price array cannot be null!");
    }

    @Test
    public void should_ThrowException_When_InputRodLenghtIsNegative() {
        // given
        int[] prices = {1, 5, 8, 9};
        int rodLength = -1;

        // when
        Throwable exceptionCaught = catchThrowable(() -> {
            rodCutter.cutRod(prices, rodLength);
        });

        // then
        assertThat(exceptionCaught)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Rod length cannot be negative!");
    }

    @Test
    public void should_ThrowException_When_InputRodLenghtIsBiggerThanSizeOfPriceArray() {
        // given
        int[] prices = {1, 5, 8, 9};
        int rodLength = 5;

        // when
        Throwable exceptionCaught = catchThrowable(() -> {
            rodCutter.cutRod(prices, rodLength);
        });

        // then
        assertThat(exceptionCaught)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("The size of the price array cannot be smaller than the length of the rod!");
    }

    /*
     * Based on: Cormen, Leiserson, Rivest, Stein
     */
    @ParameterizedTest
    @CsvSource({
        "1, 1",
        "2, 5",
        "3, 8",
        "10, 30"
    })
    void should_ReturnCorrectValue_When_NoCuttingIsNeeded(int rodLength, int expectedResult) {
        // given
        int[] prices = {1, 5, 8, 9, 10, 17, 17, 20, 24, 30};

        // when
        RodCutterResult result = rodCutter.cutRod(prices, rodLength);
        int maxSumOfMoney = result.getMaxSumResult();

        // then
        assertThat(maxSumOfMoney).isEqualTo(expectedResult);
    }

    /*
     * Based on: Cormen, Leiserson, Rivest, Stein
     */
    @ParameterizedTest
    @CsvSource({
        "4, 10",
        "5, 13",
        "7, 18",
        "8, 22",
        "9, 25"
    })
    void should_ReturnCorrectValue_When_CuttingIsNeeded(int rodLength, int expectedResult) {
        // given
        int[] prices = {1, 5, 8, 9, 10, 17, 17, 20, 24, 30};

        // when
        RodCutterResult result = rodCutter.cutRod(prices, rodLength);
        int maxSumOfMoney = result.getMaxSumResult();

        // then
        assertThat(maxSumOfMoney).isEqualTo(expectedResult);
    }

}
