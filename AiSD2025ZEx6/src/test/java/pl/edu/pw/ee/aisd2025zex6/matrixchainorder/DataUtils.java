package pl.edu.pw.ee.aisd2025zex6.matrixchainorder;

public class DataUtils {

    public int[] prepareArrayWithTheSameValue(int size, int value) {
        int[] data = new int[size];

        for (int i = 0; i < size; i++) {
            data[i] = value;
        }

        return data;
    }

}
