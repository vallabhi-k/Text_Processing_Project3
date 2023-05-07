package project3;
import java.io.*;

public class Bitap {
   
    public int match(String t, String p) {
        char[] text = t.toCharArray();
        char[] pattern = p.toCharArray();
        int index = bitap_search(text, pattern);
        if (index == -1) 
           return -1;
            return index;
        
    }
    private int bitap_search(char[] text, char[] pattern) {
        int len = pattern.length;
        long pattern_mask[] = new long[Character.MAX_VALUE + 1];
        long R = ~1;
        if (len == 0) {
            return -1;
        }
        if (len > 63) {
            return -1;
        }
        for (int i = 0; i <= Character.MAX_VALUE; ++i) {
            pattern_mask[i] = ~0;
        }
        for (int i = 0; i < len; ++i) {
            pattern_mask[pattern[i]] &= ~(1L << i);
        }
        for (int i = 0; i < text.length; ++i) {
            R |= pattern_mask[text[i]];
            R <<= 1;
            if ((R & (1L << len)) == 0) {
                return i - len + 1;
            }
        }
        return -1;
    }
}
