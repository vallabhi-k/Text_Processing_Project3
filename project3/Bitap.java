package project3;
import java.io.*;

public class Bitap {
  public int match(String text, String pattern) {
    char[] textChars = text.toCharArray();
    char[] patternChars = pattern.toCharArray();
    int index = search(textChars, patternChars);
    if (index == -1) 
       return -1;
        return index;
    
  }
  public int search(char[] text, char[] pattern) {
    int len = pattern.length;
    long patternMask[] = new long[Character.MAX_VALUE + 1];
    long R = ~1;
    if (len == 0) {
        return -1;
    }
    if (len > 63) {
        return -1;
    }
    for (int i = 0; i <= Character.MAX_VALUE; ++i) {
        patternMask[i] = ~0;
    }
    for (int i = 0; i < len; ++i) {
        patternMask[pattern[i]] &= ~(1L << i);
    }
    for (int i = 0; i < text.length; ++i) {
        R |= patternMask[text[i]];
        R <<= 1;
        if ((R & (1L << len)) == 0) {
            return i - len + 1;
        }
    }
    return -1;
  }
}



