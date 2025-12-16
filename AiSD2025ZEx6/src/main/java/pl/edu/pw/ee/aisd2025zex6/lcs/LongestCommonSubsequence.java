package pl.edu.pw.ee.aisd2025zex6.lcs;

public class LongestCommonSubsequence {

    public String findLcs(String top, String left) {
        if (top == null || left == null) {
            return "";
        }

        int n = top.length();
        int m = left.length();

        int[][] dp = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (top.charAt(i - 1) == left.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        StringBuilder lcsBuilder = new StringBuilder();
        int i = n;
        int j = m;

        while (i > 0 && j > 0) {
            if (top.charAt(i - 1) == left.charAt(j - 1)) {
                lcsBuilder.append(top.charAt(i - 1));
                i--;
                j--;
            } 
            else if (dp[i - 1][j] >= dp[i][j - 1]) {
                i--;
            } else {
                j--;
            }
        }

        return lcsBuilder.reverse().toString();
    }
}