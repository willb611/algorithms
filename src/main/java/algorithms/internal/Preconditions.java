package algorithms.internal;

public class Preconditions {
  public static <T> T checkNotNull(T object) {
    if (object == null) {
      throw new NullPointerException();
    }
    return object;
  }
}
