package harderExercises;

import org.junit.Test;
import java.util.List;
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
  public void testGetTopPaidActiveHarderExercises() {
    List<HarderExercises.Employee> employees = List.of(
        new HarderExercises.Employee("Alice", 60000, true, "1"),
        new HarderExercises.Employee("Bob", 55000, true, "1"),
        new HarderExercises.Employee("Charlie", 70000, false, "1"), // Inactive
        new HarderExercises.Employee("Dave", 80000, true, "1"),
        new HarderExercises.Employee("Eve", 90000, true, "1"),
        new HarderExercises.Employee("Frank", 75000, true, "1"),
        new HarderExercises.Employee("Grace", 65000, true, "1")
    );

    List<String> result = HarderExercises.getTopEmployees(employees);
    assertEquals(List.of("EVE", "DAVE", "FRANK",  "GRACE","ALICE"), result);
  }

  @Test
  public void testHandlesNullAndDuplicates() {
    List<HarderExercises.Employee> employees = List.of(
        new HarderExercises.Employee("Alice", 60000, true, "1"),
        new HarderExercises.Employee("Bob", 55000, true, "1"),
        new HarderExercises.Employee("Alice", 60000, true, "1"), // Duplicate name
        new HarderExercises.Employee(null, 70000, true, "1"), // Null name
        new HarderExercises.Employee("Eve", 90000, true, "1")
    );

    List<String> result = HarderExercises.getTopEmployees(employees);
    assertEquals(List.of("EVE", "ALICE", "BOB"), result);
  }

  @Test
  public void validUserReturnsTrue() {
    assertTrue(HarderExercises.isValidUser("alice", "password123"));
  }

  @Test
  public void invalidUserReturnsFalse() {
    assertFalse(HarderExercises.isValidUser("charlie", "randompass"));
  }

  /*
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
  }*/


  @Test
  public void testCalculateReceiptValidInputs() {
    // Basic case
    assertEquals("Basic calculation failed",
        "3.5,3,Orange", HarderExercises.calculateReceipt(
            new String[]{"Apple", "Banana", "Orange"},
            new double[]{1.0, 0.5, 2.0},
            new int[]{1, 1, 1}
        )
    );

    // With bulk discount (3 or more items)
    assertEquals("Bulk discount calculation failed",
        "3.7,5,Apple",
        HarderExercises.calculateReceipt(
            new String[]{"Apple", "Banana"},
            new double[]{1.0, 0.5},
            new int[]{3, 2}
        )
    );
  }

  @Test
  public void testCalculateReceiptEdgeCases() {
    // Empty arrays
    assertEquals("0.0,0,",
        HarderExercises.calculateReceipt(
            new String[]{},
            new double[]{},
            new int[]{}
        )
    );

    // Null and mismatched arrays
    assertEquals("ERROR",
        HarderExercises.calculateReceipt(null, new double[]{}, new int[]{}));
    assertEquals("ERROR",
        HarderExercises.calculateReceipt(
            new String[]{"Apple"},
            new double[]{1.0},
            new int[]{1, 2}
        )
    );
  }

  @Test
  public void testCalculateReceiptComplexCase() {
    // Multiple items with different quantities and discounts
    String result = HarderExercises.calculateReceipt(
        new String[]{"Cheap", "Medium", "Expensive"},
        new double[]{1.0, 5.0, 10.0},
        new int[]{4, 3, 2}
    );

    // Verifies total price, total count, and highest value item
    String[] parts = result.split(",");

    // Check total price (with discounts)
    double totalPrice = Double.parseDouble(parts[0]);
    assertTrue( "Total price should be positive", totalPrice > 0);

    // Check total item count
    int totalCount = Integer.parseInt(parts[1]);
    assertEquals("Total item count should match", 9, totalCount);
    // Check highest value item
    String maxItem = parts[2];
    assertEquals("Should be the most expensive item", maxItem, "Expensive");
  }

  @Test
  public void goldMemberOnHolidayGets20Percent() {
    assertEquals(20.0, HarderExercises.calculateDiscount("Gold", true), 0.00001);
  }

  @Test
  public void silverMemberNotOnHolidayGets5Percent() {
    assertEquals(5.0, HarderExercises.calculateDiscount("Silver", false), 0.00001);
  }

  @Test
  public void bronzeMemberOnHolidayGets7Percent() {
    assertEquals(8.0, HarderExercises.calculateDiscount("Bronze", true), 0.00001);
  }

  @Test
  public void regularCustomerOnHolidayGets5Percent() {
    assertEquals(5.0, HarderExercises.calculateDiscount("Regular", true), 0.00001);
  }

  @Test
  public void bronzeMemberNotOnHolidayGets3Percent() {
    assertEquals(3.0, HarderExercises.calculateDiscount("Bronze", false), 0.00001);
  }

  @Test
  public void testIsLeapYear() {
    assertTrue(HarderExercises.isLeapYear(420));
    assertTrue(HarderExercises.isLeapYear(2000));
    assertFalse(HarderExercises.isLeapYear(2001));
    assertTrue(HarderExercises.isLeapYear(2004));
    assertFalse(HarderExercises.isLeapYear(2100));
    assertFalse(HarderExercises.isLeapYear(2200));
    assertFalse(HarderExercises.isLeapYear(2300));
    assertTrue(HarderExercises.isLeapYear(2400));
  }
}
