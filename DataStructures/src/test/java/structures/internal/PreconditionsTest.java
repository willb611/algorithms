package structures.internal;

import org.junit.Test;

import static structures.internal.Preconditions.checkNotNull;

public class PreconditionsTest {

  @Test(expected = NullPointerException.class)
  public void testCheckNotNull() throws Exception {
    checkNotNull(null);
  }
}