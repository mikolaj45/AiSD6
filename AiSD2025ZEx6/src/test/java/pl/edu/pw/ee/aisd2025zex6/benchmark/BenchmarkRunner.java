package pl.edu.pw.ee.aisd2025zex6.benchmark;

import org.junit.jupiter.api.Test;
import pl.edu.pw.ee.aisd2025zex6.matrixchainorder.*;
import pl.edu.pw.ee.aisd2025zex6.rodcuttingproblem.*;

import java.util.Random;

public class BenchmarkRunner {

    private static final long TIME_LIMIT_MS = 4000; // 4 sekundy

    @Test
    public void runBenchmarks() {
        System.out.println("=== Rozpoczynam pomiary (Zadanie 1) ===");
        System.out.println("Szukamy N, dla którego czas wykonania ~= " + TIME_LIMIT_MS + " ms\n");

        runRodCuttingBenchmarks();
        System.out.println("--------------------------------------------------");
        runMatrixChainBenchmarks();
    }

    // --- ROD CUTTING (CIĘCIE PRĘTA) ---

    private void runRodCuttingBenchmarks() {
        System.out.println(">>> Problem Cięcia Pręta (Rod Cutting) <<<");

        // 1. Rekurencyjna
        measureRodCutter("Rekurencyjna", new RodCutterRecursive(), 10, 1);

        // 2. Zstępująca (Top-Down)
        measureRodCutter("Top-Down", new RodCutterTopDown(), 1000, 500);

        // 3. Wstępująca (Bottom-Up)
        measureRodCutter("Bottom-Up", new RodCutterBottomUp(), 1000, 500);
    }

    private void measureRodCutter(String name, RodCutter algorithm, int startN, int step) {
        System.out.print("Testowanie " + name + "... ");
        int n = startN;
        long elapsedTime = 0;
        // Duży bufor cen, aby uniknąć błędów indeksowania przy dużych N
        int[] prices = generatePrices(500000);

        while (true) {
            // Zabezpieczenie: jeśli N przekroczy rozmiar tablicy, generujemy nową większą
            if (n > prices.length) {
                prices = generatePrices(n * 2);
            }

            long start = System.nanoTime();
            try {
                // Próba uruchomienia algorytmu
                algorithm.cutRod(prices, n);
            } catch (StackOverflowError e) {
                // Obsługa błędu przepełnienia stosu
                System.out.println("\n   [!] BŁĄD: Przepełnienie stosu (StackOverflow) przy N = " + n);
                System.out.println("       (Limit pamięci rekurencji osiągnięty przed limitem czasu)");
                break; // Przerywamy pętlę dla tego algorytmu i idziemy do następnego
            }
            long end = System.nanoTime();

            elapsedTime = (end - start) / 1_000_000; // ms

            // Sprawdzenie, czy przekroczyliśmy czas
            if (elapsedTime >= TIME_LIMIT_MS) {
                System.out.println("Znaleziono N = " + n + " (Czas: " + elapsedTime + " ms)");
                break;
            }

            // Heurystyka przyspieszająca szukanie
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

    // --- MATRIX CHAIN ORDER (MNOŻENIE MACIERZY) ---

    private void runMatrixChainBenchmarks() {
        System.out.println(">>> Problem Mnożenia Macierzy (Matrix Chain Order) <<<");

        // 1. Rekurencyjna
        measureMatrixChain("Rekurencyjna", new MatrixChainOrderRecursive(), 5, 1);

        // 2. Zstępująca (Top-Down)
        // Upewnij się, że masz klasę MatrixChainOrderTopDown w projekcie!
        measureMatrixChain("Top-Down", new MatrixChainOrderTopDown(), 100, 50);

        // 3. Wstępująca (Bottom-Up)
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
                // Próba uruchomienia algorytmu
                algorithm.findOptimalOrder(matrixSizes);
            } catch (StackOverflowError e) {
                // Obsługa błędu przepełnienia stosu
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