package project3;

public class Bitap {
  public int match(String text, String pattern) {
  
    int m = pattern.length();
        int n = text.length();

        if (m > 31) {
            throw new IllegalArgumentException("Pattern too long");
        }

        int[] R = new int[256];

        int pattern_mask = 1 << (m - 1);
        int i;

        for (i = 0; i < 256; i++) {
            R[i] = ~0;
        }

        for (i = 0; i < m; i++) {
            R[pattern.charAt(i)] &= ~(1 << i);
        }

        int state = ~0;

        for (i = 0; i < n - m + 1; i++) {
            state = (state << 1) | 1;
            state &= R[text.charAt(i)];

            if ((state & pattern_mask) != 0) {
                int j;
                for (j = 0; j < m; j++) {
                    if (pattern.charAt(j) != text.charAt(i + j)) {
                        break;
                    }
                }
                if (j == m) {
                    return i;
                }
            }
        }

        return -1;
  }
}
