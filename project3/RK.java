package project3;

public class RK {
    
  private static final int PRIME = 101;
  
  public int match(String text, String pattern) {
      int m = pattern.length();
      int n = text.length();
      long patternHash = createHash(pattern, m - 1);
      long textHash = createHash(text, m - 1);
      
      for (int i = 1; i <= n - m + 1; i++) {
          if (patternHash == textHash && checkEqual(pattern, text, i - 1, i + m - 2)) {
              return (i - 1);
          }
          if (i < n - m + 1) {
              textHash = recalculateHash(text, i - 1, i + m - 1, textHash, m);
          }
      }
      return -1;
  }
  
  private static long createHash(String str, int end) {
      long hash = 0;
      for (int i = 0; i <= end; i++) {
          hash += str.charAt(i) * Math.pow(PRIME, i);
      }
      return hash;
  }
  
  private static boolean checkEqual(String pattern, String text, int start, int end) {
      if (pattern.length() != end - start + 1) {
          return false;
      }
      for (int i = start; i <= end; i++) {
          if (pattern.charAt(i - start) != text.charAt(i)) {
              return false;
          }
      }
      return true;
  }
  
  private static long recalculateHash(String str, int oldIndex, int newIndex, long oldHash, int patternLen) {
      long newHash = oldHash - str.charAt(oldIndex);
      newHash /= PRIME;
      newHash += str.charAt(newIndex) * Math.pow(PRIME, patternLen - 1);
      return newHash;
  }
}
