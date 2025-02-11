package harderExercises;

import org.junit.Test;

import exercises.Exercises;

import java.util.Map;

import static org.junit.Assert.*;

public class HarderExercisesTest {

  @Test
  public void testHaversine() {
    assertEquals(2887.2599506071106, HarderExercises.haversine(36.12, -86.67, 33.94, -118.40), 1E-6);
  }
  @Test
  public void testFormatURL2Basics() {
    assertEquals("http://gv.no/nyheter", HarderExercises.formatURL2("http", "gv.no", 80, "nyheter"));
    assertEquals("https://lantega.com/", HarderExercises.formatURL2("https", "lantega.com", 443, ""));
    assertEquals("https://lantega.com:4242/", HarderExercises.formatURL2("https", "lantega.com", 4242, ""));
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
            HarderExercises.formatURL2(scheme, hostname, standardPort, path)
          );

          assertEquals(
            String.format("%s://%s:%d/%s", scheme, hostname, nonStandardPort, path),
            HarderExercises.formatURL2(scheme, hostname, nonStandardPort, path)
          );
        }
      }
    }
  }

  @Test
  public void testFormatURL2ThrowsIllegalArgumentExceptionForUnsupportedSchemes() {
    assertThrows(
      IllegalArgumentException.class,
      () -> HarderExercises.formatURL2("not-supported-scheme", "foo", 4242, "bar")
    );

    assertThrows(
      IllegalArgumentException.class,
      () -> HarderExercises.formatURL2("", "foo", 4242, "bar")
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
                () -> HarderExercises.formatURL2(
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

  @Test
  public void testIsLeapYear() {
      assertTrue(HarderExercises.isLeapYear(420));
      assertTrue( HarderExercises.isLeapYear(2000));
      assertFalse( HarderExercises.isLeapYear(2001));
      assertTrue( HarderExercises.isLeapYear(2004));
      assertFalse( HarderExercises.isLeapYear(2100));
      assertFalse( HarderExercises.isLeapYear(2200));
      assertFalse( HarderExercises.isLeapYear(2300));
      assertTrue( HarderExercises.isLeapYear(2400));
  }
}
