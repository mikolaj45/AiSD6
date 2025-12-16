package pl.edu.pw.ee.aisd2025zex6.lcs;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

public class LongestCommonSubsequenceTest {

    @Test
    public void should_FindCorrectLcs_ForStandardCases() {
        // given
        LongestCommonSubsequence lcsAlg = new LongestCommonSubsequence();

        // when & then
        
        assertThat(lcsAlg.findLcs("ABCDE", "ACE")).isEqualTo("ACE");

        assertThat(lcsAlg.findLcs("KONSTANTYNOPOL", "ANTY")).isEqualTo("ANTY");

        assertThat(lcsAlg.findLcs("ABC", "XYZ")).isEqualTo("");
        
        assertThat(lcsAlg.findLcs("AGGTAB", "GXTXAYB")).isEqualTo("GTAB");
    }

    @Test
    public void should_HandleEmptyInputs() {
        LongestCommonSubsequence lcsAlg = new LongestCommonSubsequence();

        assertThat(lcsAlg.findLcs("", "ABC")).isEqualTo("");
        assertThat(lcsAlg.findLcs("ABC", null)).isEqualTo("");
        assertThat(lcsAlg.findLcs("", "")).isEqualTo("");
    }
}