package pl.edu.pw.ee.aisd2025zex6.benchmark;

import org.junit.jupiter.api.Test;
import pl.edu.pw.ee.aisd2025zex6.matrixchainorder.*;
import pl.edu.pw.ee.aisd2025zex6.rodcuttingproblem.*;

import java.util.Random;

public class BenchmarkRunner {

    private static final long TIME_LIMIT_MS = 4000; // 4 sekundy

    @Test
    public void runBenchmarks() {
        System.out.println("Szukamy N, dla którego czas wykonania ~= " + TIME_LIMIT_MS + " ms\n");

        runRodCuttingBenchmarks();
        System.out.println("--------------------------------------------------");
        runMatrixChainBenchmarks();
    }

    // --- ROD CUTTING (CIĘCIE PRĘTA) ---

    private void runRodCuttingBenchmarks() {
        System.out.println(">>> Problem Cięcia Pręta (Rod Cutting) <<<");

        measureRodCutter("Rekurencyjna", new RodCutterRecursive(), 10, 1);

        measureRodCutter("Top-Down", new RodCutterTopDown(), 1000, 500);

        measureRodCutter("Bottom-Up", new RodCutterBottomUp(), 1000, 500);
    }

    private void measureRodCutter(String name, RodCutter algorithm, int startN, int step) {
        System.out.print("Testowanie " + name + "... ");
        int n = startN;
        long elapsedTime = 0;
        int[] prices = generatePrices(500000);

        while (true) {
            if (n > prices.length) {
                prices = generatePrices(n * 2);
            }

            long start = System.nanoTime();
            try {
                algorithm.cutRod(prices, n);
            } catch (StackOverflowError e) {
                System.out.println("\n   [!] BŁĄD: Przepełnienie stosu (StackOverflow) przy N = " + n);
                System.out.println("       (Limit pamięci rekurencji osiągnięty przed limitem czasu)");
                break;
            }
            long end = System.nanoTime();

            elapsedTime = (end - start) / 1_000_000;

            if (elapsedTime >= TIME_LIMIT_MS) {
                System.out.println("Znaleziono N = " + n + " (Czas: " + elapsedTime + " ms)");
                break;
            }

            if (elapsedTime < 100 && step > 1) {
                n += step * 2;
            } else {
                n += step;
            }
        }
    }

    private int[] generatePrices(int size) {
        Random rand = new Random();
        int[] prices = new int[size];
        for (int i = 0; i < size; i++) {
            prices[i] = rand.nextInt(100) + 1;
        }
        return prices;
    }

    private void runMatrixChainBenchmarks() {
        System.out.println(">>> Problem Mnożenia Macierzy (Matrix Chain Order) <<<");

        measureMatrixChain("Rekurencyjna", new MatrixChainOrderRecursive(), 5, 1);

        measureMatrixChain("Top-Down", new MatrixChainOrderTopDown(), 100, 50);

        measureMatrixChain("Bottom-Up", new MatrixChainOrderBottomUp(), 100, 50);
    }

    private void measureMatrixChain(String name, MatrixChainOrder algorithm, int startN, int step) {
        System.out.print("Testowanie " + name + "... ");
        int n = startN;
        long elapsedTime = 0;

        while (true) {
            int[] matrixSizes = generateMatrixSizes(n);

            long start = System.nanoTime();
            try {
                algorithm.findOptimalOrder(matrixSizes);
            } catch (StackOverflowError e) {
                System.out.println("\n   [!] BŁĄD: Przepełnienie stosu (StackOverflow) przy N = " + n);
                System.out.println("       (Limit pamięci rekurencji osiągnięty przed limitem czasu)");
                break;
            }
            long end = System.nanoTime();

            elapsedTime = (end - start) / 1_000_000; // ms

            if (elapsedTime >= TIME_LIMIT_MS) {
                System.out.println("Znaleziono N = " + n + " (Czas: " + elapsedTime + " ms)");
                break;
            }

            if (elapsedTime < 100 && step > 1) {
                n += step * 2;
            } else {
                n += step;
            }
        }
    }

    private int[] generateMatrixSizes(int nOfMatrices) {
        Random rand = new Random();
        int[] sizes = new int[nOfMatrices + 1];
        for (int i = 0; i < sizes.length; i++) {
            sizes[i] = rand.nextInt(100) + 1;
        }
        return sizes;
    }
}