package internal;

public class Preconditions {
  public static <T> T checkNotNull(T object) {
    if (object == null) {
      throw new NullPointerException();
    }
    return object;
  }

  public static void checkThat(boolean b) {
    checkThat(b, "");
  }

  public static void checkThat(boolean b, String message) {
    if (!b) {
      throw new IllegalArgumentException("Checked condition was false. Msg: " + message);
    }
  }
}
