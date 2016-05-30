package algorithms;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.math.BigInteger;

public class FactorialFinderTest {
  @Rule
  public ExpectedException expectedException = ExpectedException.none();

  @Test
  public void testOutOfBoundsLower() {
    FactorialFinder factorialFinder = new FactorialFinder(5);
    expectedException.expect(IllegalArgumentException.class);
    factorialFinder.findFactorialOf(-1);
  }

  @Test
  public void testLowerBoundary() {
    FactorialFinder factorialFinder = new FactorialFinder(5);
    BigInteger expected = BigInteger.ONE;
    BigInteger actual = factorialFinder.findFactorialOf(0);
    assertEquals(expected, actual);
  }

  @Test
  public void testJustAboveLowerBoundary() {
    FactorialFinder factorialFinder = new FactorialFinder(5);
    BigInteger expected = BigInteger.ONE;
    BigInteger actual = factorialFinder.findFactorialOf(1);
    assertEquals(expected, actual);
  }

  @Test
  public void testSomeReasonableNumber() {
    FactorialFinder factorialFinder = new FactorialFinder(25);
    BigInteger expected = new BigInteger("1307674368000");
    BigInteger actual = factorialFinder.findFactorialOf(15);
    assertEquals(expected, actual);
  }

  @Test
  public void testSomeAnotherReasonableNumber() {
    FactorialFinder factorialFinder = new FactorialFinder(15);
    BigInteger expected = new BigInteger("5040");
    BigInteger actual = factorialFinder.findFactorialOf(7);
    assertEquals(expected, actual);
  }

  @Test
  public void testSomeMassiveNumber() {
    FactorialFinder factorialFinder = new FactorialFinder(500);
    String stringValue = "3182750543403027012336706156782913536145733016893641344126362765144026816950176483433708498817938232240803623626671799827608949952077222644959964479896392909891870215457426451709146369917636437691396355704735347375186355955486746692419516082334780844743248178690628678903075282420582144560170577403643367011886148316551412798791982372719612406291751889507353976687344796452748237331383357722881374421135344607466566365616430720688428282314786156182610130584455498842302880790439643833376285500555099702421385995385249384096711650952839664585148408940005298992813059018912575966834405498932039568784861886858419360205684508272535782584117522247661348434578786906430129061606611661267909297409661378018498501877267168740344838806302712262339284801793863104236638986340292915492599418063322154612336200191670497548687093313185330566187614892772404915990294490393845525190476795730740661296857081846091154291156935517402332012013293062659768320000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
    BigInteger expected = new BigInteger(stringValue);
    BigInteger actual = factorialFinder.findFactorialOf(470);
    assertEquals(expected, actual); 
  }
}