package algorithms;

import org.junit.Test;

import static algorithms.Preconditions.checkNotNull;

public class PreconditionsTest {

  @Test(expected = NullPointerException.class)
  public void testCheckNotNull() throws Exception {
    checkNotNull(null);
  }
}