package exercises;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.Test;

public class ExercisesTest {
  @Test
  public void testCalculateLittleFormula() {
    assertEquals(42, Exercises.calculateLittleFormula(0, 0, 0, true));
    assertEquals(-42, Exercises.calculateLittleFormula(0, 0, 0, false));
    assertEquals(89, Exercises.calculateLittleFormula(3, 7, 11, true));
    assertEquals(-89, Exercises.calculateLittleFormula(3, 7, 11, false));
    assertEquals(125, Exercises.calculateLittleFormula(7, 3, 11, true));
    assertEquals(-125, Exercises.calculateLittleFormula(7, 3, 11, false));
    assertEquals(145, Exercises.calculateLittleFormula(11, 13, 7, true));
    assertEquals(-145, Exercises.calculateLittleFormula(11, 13, 7, false));
  }

  @Test
  public void testMayAccess() {
    assertFalse(Exercises.mayAccess(true, true, true));
    assertTrue(Exercises.mayAccess(true, true, false));
    assertFalse(Exercises.mayAccess(true, false, true));
    assertFalse(Exercises.mayAccess(true, false, false));
    assertFalse(Exercises.mayAccess(false, true, true));
    assertFalse(Exercises.mayAccess(false, true, false));
    assertFalse(Exercises.mayAccess(false, false, true));
    assertFalse(Exercises.mayAccess(false, false, false));
  }

  @Test
  public void testGetMinorYears() {
    assertEquals(
      IntStream.rangeClosed(0, 17).boxed().collect(Collectors.toSet()),
      Exercises.getMinorYears()
    );
  }

 @Test
  public void testDescribeNumber() {
    assertEquals("HUGE!", Exercises.describeNumber(20001));
    assertEquals("HUGE!", Exercises.describeNumber(20000));
    assertEquals("very big!", Exercises.describeNumber(19999));
    assertEquals("very big!", Exercises.describeNumber(10001));
    assertEquals("very big!", Exercises.describeNumber(10000));
    assertEquals("decently sized", Exercises.describeNumber(9999));
    assertEquals("decently sized", Exercises.describeNumber(0));
    assertEquals("decently sized", Exercises.describeNumber(-9999));
    assertEquals("very small!", Exercises.describeNumber(-10000));
    assertEquals("very small!", Exercises.describeNumber(-10001));
    assertEquals("very small!", Exercises.describeNumber(-19999));
    assertEquals("TINY!", Exercises.describeNumber(-20000));
    assertEquals("TINY!", Exercises.describeNumber(-20001));
  }

  @Test
  public void testIsIncreasingSequence() {
    // positive cases
    assertTrue(Exercises.isIncreasingSequence(-1, 0, 1, 2));
    assertTrue(Exercises.isIncreasingSequence(100, 200, 300, 400));
    assertTrue(Exercises.isIncreasingSequence(100, 101, 102, 103));

    // negative cases
    assertFalse(Exercises.isIncreasingSequence(0, 0, 0, 0));
    assertFalse(Exercises.isIncreasingSequence(0, 1, 2, 2));
    assertFalse(Exercises.isIncreasingSequence(0, -1, 2, 3));
    assertFalse(Exercises.isIncreasingSequence(1, 1, 2, 3));
    assertFalse(Exercises.isIncreasingSequence(201, 200, 300, 301));
  }

  @Test
  public void testExtractQueryParameters() {
    assertEquals(Map.of(), Exercises.extractQueryParameters("https://noogle.net/"));
    assertEquals(Map.of(), Exercises.extractQueryParameters("https://noogle.net/?"));

    assertEquals(
      Map.of("foo", "bar"),
      Exercises.extractQueryParameters("https://noogle.net/?foo=bar")
    );

    assertEquals(
      Map.of("a", "0", "b", "1", "c", "2"),
      Exercises.extractQueryParameters("https://noogle.net/?a=0&b=1&c=2")
    );

    assertEquals(
      Map.of("keyword1", "sonny", "keyword2", "cher", "maxage", "2049"),
      Exercises.extractQueryParameters("https://gugle.no/?keyword1=sonny&keyword2=cher&maxage=2049")
    );
  }

  @Test
  public void testFlooredRoot() {
    assertEquals(0, Exercises.flooredRoot(0.0));
    assertEquals(5, Exercises.flooredRoot(25.5));
    assertEquals(9, Exercises.flooredRoot(99.999));

    // test negative inputs
    assertThrows(IllegalArgumentException.class, () -> Exercises.flooredRoot(-1.0));

    // just above Integer.MAX_VALUE
    assertThrows(
        IllegalArgumentException.class,
        () -> Exercises.flooredRoot(4.6116860184273879E18)
    );

    // just at Integer.MAX_VALUE
    assertEquals(
        Integer.MAX_VALUE,
        Exercises.flooredRoot(4.6116860141324206E18)
    );
  }

  @Test
  public void testFormatURL() {
    assertEquals(
        "http://gv.no/nyheter",
        Exercises.formatURL("http", "gv.no", "nyheter")
    );

    assertEquals(
        "https://lantega.com/",
        Exercises.formatURL("https", "lantega.com", "")
    );

    assertThrows(IllegalArgumentException.class, () -> Exercises.formatURL("not-http-or-https", "foo", "bar"));
    assertThrows(IllegalArgumentException.class, () -> Exercises.formatURL("", "foo", "bar"));

    assertThrows(IllegalArgumentException.class, () -> Exercises.formatURL(null, "ok", "ok"));
    assertThrows(IllegalArgumentException.class, () -> Exercises.formatURL("https", null, "ok"));
    assertThrows(IllegalArgumentException.class, () -> Exercises.formatURL("https", "ok", null));
    assertThrows(IllegalArgumentException.class, () -> Exercises.formatURL(null, null, "ok"));
    assertThrows(IllegalArgumentException.class, () -> Exercises.formatURL(null, "ok", null));
    assertThrows(IllegalArgumentException.class, () -> Exercises.formatURL("https", null, null));
    assertThrows(IllegalArgumentException.class, () -> Exercises.formatURL(null, null, null));
  }

  @Test
  public void testHaversine() {
    assertEquals(2887.2599506071106, Exercises.haversine(36.12, -86.67, 33.94, -118.40), 1E-6);
  }

  @Test
  public void testFormatURL2Basics() {
    assertEquals("http://gv.no/nyheter", Exercises.formatURL2("http", "gv.no", 80, "nyheter"));
    assertEquals("https://lantega.com/", Exercises.formatURL2("https", "lantega.com", 443, ""));
    assertEquals("https://lantega.com:4242/", Exercises.formatURL2("https", "lantega.com", 4242, ""));
  }

  @Test
  public void testFormatURL2StandardHappyCases() {
    final Map<String, Integer> supportedSchemesAndStandardPortNumbers =
      Map.of(
        "ftp", 21,
        "sftp", 22,
        "irc", 6667,
        "http", 80,
        "https", 443,
        "ws", 80,
        "wss", 443
      );

    final int nonStandardPort = 34567;

    for (final Map.Entry<String, Integer> schemeAndPort : supportedSchemesAndStandardPortNumbers.entrySet()) {
      final String scheme = schemeAndPort.getKey();
      final int standardPort = schemeAndPort.getValue();

      for (final String hostname : new String[]{ "", "h.o.s.t.n.a.m.e.nu" }) {
        for (final String path : new String[]{ "", "path", "sub/path/index.php" }) {
          assertEquals(
            String.format("%s://%s/%s", scheme, hostname, path),
            Exercises.formatURL2(scheme, hostname, standardPort, path)
          );

          assertEquals(
            String.format("%s://%s:%d/%s", scheme, hostname, nonStandardPort, path),
            Exercises.formatURL2(scheme, hostname, nonStandardPort, path)
          );
        }
      }
    }
  }

  @Test
  public void testFormatURL2ThrowsIllegalArgumentExceptionForUnsupportedSchemes() {
    assertThrows(
      IllegalArgumentException.class,
      () -> Exercises.formatURL2("not-supported-scheme", "foo", 4242, "bar")
    );

    assertThrows(
      IllegalArgumentException.class,
      () -> Exercises.formatURL2("", "foo", 4242, "bar")
    );
  }

  @Test
  public void testFormatURL2ThrowsIllegalArgumentExceptionForNullInputs() {
    final String[] schemeInputs = new String[] { null, "https" };
    final String[] hostnameInputs = new String[] { null, "host.ru" };
    final Integer[] portNumInputs = new Integer[] { null, 4242 };
    final String[] pathInputs = new String[] { null, "jimmy.page.co.uk" };

    for (final String schemeOrNull : schemeInputs) {
      for (final String hostnameOrNull : hostnameInputs) {
        for (final Integer portNumOrNull : portNumInputs) {
          for (final String pathOrNull : pathInputs) {
            final boolean shouldThrowIllegalArgumentExceptionBecauseOfNullArgument = (
              schemeOrNull == null ||
              hostnameOrNull == null ||
              portNumOrNull == null ||
              pathOrNull == null
            );

            if (shouldThrowIllegalArgumentExceptionBecauseOfNullArgument) {
              assertThrows(
                IllegalArgumentException.class,
                () -> Exercises.formatURL2(
                  schemeOrNull,
                  hostnameOrNull,
                  portNumOrNull,
                  pathOrNull
                )
              );
            }
          }
        }
      }
    }
  }
}
