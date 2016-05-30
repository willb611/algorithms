package algorithms;

public class Preconditions {
  public static void checkNotNull(Object o) {
    if (o == null) {
      throw new NullPointerException();
    }
  }
}
