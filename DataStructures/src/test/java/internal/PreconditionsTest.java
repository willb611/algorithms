package internal;

import org.junit.Test;

import static internal.Preconditions.checkNotNull;

public class PreconditionsTest {

  @Test(expected = NullPointerException.class)
  public void testCheckNotNull() throws Exception {
    checkNotNull(null);
  }
}