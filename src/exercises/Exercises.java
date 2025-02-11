package exercises;

import java.util.*;

public final class Exercises {
  // EXERCISE A
  // -------------------------------------------------------------------------
  //
  // the method below computes some cute little formula
  //
  // the body of the method contains an expression that appears in both the
  // "if" part and the "else" part of a conditional
  //
  // can you refactor the method so that this expression is not repeated?
  //
  public static int calculateLittleFormula(int a, int b, int c, boolean positive) {
    if (positive) {
      int z = c * a + 2 * b + 42;
      return z;
    } else {
      int z = c * a + 2 * b + 42;
      return -z;
    }
  }


  // EXERCISE B
  // -------------------------------------------------------------------------
  //
  // the method below performs an access check
  //
  // as always, the implementation is correct, but the method urgently needs
  // some refactoring!
  //
  // can you refactor the method to make it cleaner? note that there is only
  // one combination of inputs that returns TRUE
  //
  public static boolean mayAccess(boolean hasAccount, boolean hasCorrectPassword, boolean hasBeenDenied) {
    if (!hasAccount) {
      return false;
    }

    if (hasBeenDenied) {
      return false;
    }

    if (hasAccount) {
      if (hasCorrectPassword && !hasBeenDenied) {
        return true;
      }
    }

    if (!hasCorrectPassword) {
      return false;
    }

    return true;
  }


  // EXERCISE C
  // -------------------------------------------------------------------------
  //
  // the method below creates a set of all the numbers that correspond to years
  // were you are not considered an adult under Norwegian law.
  //
  // often, creating a small collection "manually" in the fashion we do here
  // might be okay, but when we're adding 18 elements, it gets quite tedious...
  //
  // refactor the method to make use of a for loop to add elements to the set
  //
  // (...or, choose some other nice and readable way to refactor the method,
  // instead of a for loop, if you prefer :-)
  //
  public static Set<Integer> getMinorYears() {
    Set<Integer> minorYears = new TreeSet<>();

    minorYears.add(0);
    minorYears.add(1);
    minorYears.add(2);
    minorYears.add(3);
    minorYears.add(4);
    minorYears.add(5);
    minorYears.add(6);
    minorYears.add(7);
    minorYears.add(8);
    minorYears.add(9);
    minorYears.add(10);
    minorYears.add(11);
    minorYears.add(12);
    minorYears.add(13);
    minorYears.add(14);
    minorYears.add(15);
    minorYears.add(16);
    minorYears.add(17);

    return minorYears;
  }


  // EXERCISE D
  // -------------------------------------------------------------------------
  //
  // the method below is used to describe a number
  // (I really have no use for such a method, but let's ignore that for now...)
  //
  // the implementation is correct, but the problem is that the code style
  // in the body is all over the place
  //
  // refactor the code so everything is nice and consistent.
  // you're welcome to let the editor help you along, if you like.
  //
  public static String describeNumber(int n) {
    if (n >= 20_000  ) {
        return "HUGE!";
    }

if (n >= 10_000) return "very big!";

if(n <= -20000) {
return "TINY!";
}

      if ( n <= -10 * 1000)
      {
        return "very small!";
      }
      return "decently sized";
  }


  // EXERCISE E
  // -------------------------------------------------------------------------
  //
  // the method below is used to determine whether four integers make up a
  // (strictly) increasing sequence
  //
  // identify what's going on, and rewrite the method to a simpler and more
  // readable format.
  //
  // your refactored code should come out as a single readable line of code,
  // with no if statements AND no new variables...
  //
  public static boolean isIncreasingSequence(int a, int b, int c, int d) {
    if (a >= b || a >= c || a >= d) {
      return false;
    }

    if (b >= c || b >= d) {
      return false;
    }

    if (c >= d) {
      return false;
    }

    return true;
  }


  // EXERCISE F
  // -------------------------------------------------------------------------
  //
  // the method below extracts query parameters from an URI string.
  // for example, for the URI string
  //    https://gugle.no/?keyword1=sonny&keyword2=cher&maxage=2049
  // it returns a Map with the entries
  //    "keyword1" -> "sonny"
  //    "keyword2" -> "cher"
  //    "maxage" -> "2049"
  //
  // the method is correct, but the implementation is sloppy and hard to read.  
  //
  // apply the following refactorings to improve the quality of the code:
  // - introduce better variables names
  // - fix the comment
  // - extract the repeated expression (uriString.indexOf("?")), and store it
  //   in a variable with a good name
  //
  // if you want to, you may also introduce constants for the magic string
  // literals, ?, &, and =
  //
  public static Map<String, String> extractQueryParameters(String uriString) {
    Map<String, String> foo = new HashMap<>();

    // TODO: add comment explaining this return
    if (uriString.indexOf("?") == -1 || uriString.indexOf("?") == uriString.length()-1) {
      return foo;
    } else {
      for (String p : uriString.substring(uriString.indexOf("?")+1).split("&")) {
        String[] v = p.split("=");
        foo.put(v[0], v[1]);
      }
      return foo;
    }
  }


  // EXERCISE G
  // -------------------------------------------------------------------------
  //
  // the method below has a good name, but the variables names in its body
  // are a disgrace, and most of the comments and exception messages
  // also need some urgent care
  //
  // fix all these issues:
  // - variable names,
  // - comments,
  // - exception messages
  //
  public static int flooredRoot(double x) {
    // TODO: explain check
    if (x < 0) {
      throw new IllegalArgumentException("TODO: proper message");
    }

    double tmp1 = Math.sqrt(x);
    // TODO: xeplain
    if (tmp1 > (double) Long.MAX_VALUE || (long) tmp1 > Integer.MAX_VALUE) {
      throw new IllegalArgumentException("square root too large to store as an int");
    }

    int tmp2 = (int) tmp1;

    return tmp2;
  }


  // EXERCISE H
  // -------------------------------------------------------------------------
  //
  // the method below is used to format an URL string. it will throw
  // IllegalArgumentException if any of its input arguments are null,
  // or if the argument scheme is not "http" or "https".
  //
  // the method body is written in a very complicated manner, which makes it
  // hard to see what's going on.
  //
  // refactor the method so you start by dealing with all the errors that
  // should cause IllegalArgumentException at the beginning of the method.
  // make it so that you don't use the else keyword anywhere.
  //
  public static String formatURL(String scheme, String hostname, String path) {
    String res = "";

    if (scheme != null) {
      if (!scheme.equals("http") && !scheme.equals("https")) {
        throw new IllegalArgumentException("scheme must be 'http' or 'https'");
      }

      res += scheme;
      res += "://";

      if (hostname != null) {
        res += hostname;
        res += "/";

        if (path != null) {
          res += path;
        } else {
          throw new IllegalArgumentException("path is null");
        }
      } else {
        throw new IllegalArgumentException("hostname is null");
      }
    } else {
      throw new IllegalArgumentException("scheme is null");
    }

    return res;
  }



}
