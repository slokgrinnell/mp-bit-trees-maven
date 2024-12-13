package edu.grinnell.csc207.main;

import edu.grinnell.csc207.util.BrailleAsciiTables;

import java.io.PrintWriter;

/**
 * Command-line utility for Braille and ASCII conversions.
 * Allows users to convert between ASCII, Braille, and Unicode representations.
 *
 * @author Slok Rajbhandari
 */
public class BrailleASCII {
  // +------+--------------------------------------------------------
  // | Main |
  // +------+

  /**
   * The main entry point for the BrailleASCII utility. Takes two
   * command-line parameters: the target encoding and the source
   * text or bit string. Converts the source into the target encoding.
   *
   * @param args
   *   Command-line arguments.
   *   args[0] specifies the target encoding ("braille", "ascii", or "unicode").
   *   args[1] specifies the source text or bit string to convert.
   */
  public static void main(String[] args) {
    // Ensure correct number of arguments
    if (args.length != 2) {
      System.err.println("Use it like this: java BrailleASCII target string ");
      System.exit(1);
    } // end of if loop

    String target = args[0]; // Target encoding
    String source = args[1]; // Source text or bit string
    PrintWriter pen = new PrintWriter(System.out, true);

    try {
      // Perform conversion based on the target encoding
      switch (target.toLowerCase()) {
        case "braille":
          // Convert each character in the source text to Braille
          for (char c : source.toCharArray()) {
            pen.print(BrailleAsciiTables.toBraille(c));
          } // end of for loop
          pen.println();
          break;

        case "ascii":
          // Convert the Braille bit string to ASCII
          pen.println(BrailleAsciiTables.toAscii(source));
          break;

        case "unicode":
          String brailleBits;
          for (char c : source.toCharArray()) {
            brailleBits = BrailleAsciiTables.toBraille(c);

            // Convert the Braille bit string to Unicode
            pen.print(BrailleAsciiTables.toUnicode(brailleBits));
          } // end of for loop
          pen.println();
          break;

        default:
          // Handle unknown target encoding
          System.err.println("Unknown target: " + target);
          System.exit(1);
      } // end of the switch statements
    } catch (Exception e) {
      // Handle and report any errors encountered during conversion
      pen.println("Error: " + e.getMessage());
    } // catched exception

    pen.close(); // Close the output stream
  } // main(String[])
} // class BrailleASCII
