package algorithms;

public class Preconditions {
  public static Object checkNotNull(Object object) {
    if (object == null) {
      throw new NullPointerException();
    } else {
      return object;
    }
  }
}