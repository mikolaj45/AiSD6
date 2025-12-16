package pl.edu.pw.ee.aisd2025zex6.matrixchainorder;

public class MatrixChainOrderExtendedResult extends MatrixChainOrderResult {

    private final int[][] solutions;

    public MatrixChainOrderExtendedResult(int minMultiplyCost, int[][] solutions) {
        super(minMultiplyCost);
        this.solutions = solutions;
    }

    public String reconstructOptimalSolutions() {
        if (solutions == null || solutions.length == 0) {
            return "";
        }
        
        int n = solutions[0].length - 1;
        
        return buildSolutionString(1, n);
    }

    private String buildSolutionString(int i, int j) {
        if (i == j) {
            return "A" + i;
        } else {
            int k = solutions[i][j];
            
            String left = buildSolutionString(i, k);
            String right = buildSolutionString(k + 1, j);
            
            return "(" + left + " * " + right + ")";
        }
    }
}