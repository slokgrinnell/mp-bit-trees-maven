package edu.grinnell.csc207.util;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.IOException;

/**
 * Utility class for conversions between Braille, ASCII, and Unicode.
 * Contains static methods and translation tables for encoding and decoding.
 *
 * @author Slok Rajbhandari
 * @author Samuel A. Rebelsky
 */
public class BrailleAsciiTables {
  // +-----------+---------------------------------------------------
  // | Constants |
  // +-----------+

  /**
   * Conversions from ASCII to Braille bit strings.
   */
  static final String a2b = 
      "01000001,100000\n"
      + "01000010,110000\n"
      + "01000011,100100\n"
      + "01000100,100110\n"
      + "01000101,100010\n"
      + "01000110,110100\n"
      + "01000111,110110\n"
      + "01001000,110010\n"
      + "01001001,010100\n"
      + "01001010,010110\n"
      + "01001011,101000\n"
      + "01001100,111000\n"
      + "01001101,101100\n"
      + "01001110,101110\n"
      + "01001111,101010\n"
      + "01010000,111100\n"
      + "01010001,111110\n"
      + "01010010,111010\n"
      + "01010011,011100\n"
      + "01010100,011110\n"
      + "01010101,101001\n"
      + "01010110,111001\n"
      + "01010111,010111\n"
      + "01011000,101101\n"
      + "01011001,101111\n"
      + "01011010,101011\n";

  /**
   * Conversions from Braille bit strings to ASCII characters.
   */
  static final String b2a =
      "100000,A\n"
      + "110000,B\n"
      + "100100,C\n"
      + "100110,D\n"
      + "100010,E\n"
      + "110100,F\n"
      + "110110,G\n"
      + "110010,H\n"
      + "010100,I\n"
      + "010110,J\n"
      + "101000,K\n"
      + "111000,L\n"
      + "101100,M\n"
      + "101110,N\n"
      + "101010,O\n"
      + "111100,P\n"
      + "111110,Q\n"
      + "111010,R\n"
      + "011100,S\n"
      + "011110,T\n"
      + "101001,U\n"
      + "111001,V\n"
      + "101101,X\n"
      + "101111,Y\n"
      + "101011,Z\n";

  /**
   * Conversions from Braille bit strings to Unicode characters.
   */
  static final String b2u =
      "100000,2801\n"
      + "110000,2803\n"
      + "100100,2809\n"
      + "100110,2819\n"
      + "100010,2811\n"
      + "110100,280B\n"
      + "110110,281B\n"
      + "110010,2813\n"
      + "010100,280A\n"
      + "010110,281A\n";

  // +---------------+-----------------------------------------------
  // | Static fields |
  // +---------------+

  /**
   * The tree for ASCII-to-Braille conversions.
   */
  static BitTree a2bTree = null;

  /**
   * The tree for Braille-to-ASCII conversions.
   */
  static BitTree b2aTree = null;

  /**
   * The tree for Braille-to-Unicode conversions.
   */
  static BitTree b2uTree = null;

  // +----------------+----------------------------------------------
  // | Static methods |
  // +----------------+

  /**
   * Convert an ASCII character to its corresponding Braille bit string.
   *
   * @param letter
   *   The ASCII character to convert.
   * @return
   *   The Braille bit string corresponding to the ASCII character.
   */
  public static String toBraille(char letter) {
    if (a2bTree == null) {
      a2bTree = new BitTree(8);
      InputStream stream = new ByteArrayInputStream(a2b.getBytes());
      a2bTree.load(stream);
      try {
        stream.close();
      } catch (IOException e) {
        // Ignore closing errors
      }
    }
    String bits = Integer.toBinaryString(letter);
    while (bits.length() < 8) {
      bits = "0" + bits; // Ensure 8-bit encoding
    }
    return a2bTree.get(bits);
  } // toBraille(char)

  /**
   * Convert a Braille bit string to its corresponding ASCII character.
   *
   * @param bits
   *   The Braille bit string to convert.
   * @return
   *   The ASCII character corresponding to the Braille bit string.
   */
  public static String toAscii(String bits) {
    if (b2aTree == null) {
      b2aTree = new BitTree(6);
      InputStream stream = new ByteArrayInputStream(b2a.getBytes());
      b2aTree.load(stream);
      try {
        stream.close();
      } catch (IOException e) {
        // Ignore closing errors
      }
    }
    return b2aTree.get(bits);
  } // toAscii(String)

  /**
   * Convert a Braille bit string to its corresponding Unicode character.
   *
   * @param bits
   *   The Braille bit string to convert.
   * @return
   *   The Unicode character corresponding to the Braille bit string.
   */
  public static String toUnicode(String bits) {
    if (b2uTree == null) {
      b2uTree = new BitTree(6);
      InputStream stream = new ByteArrayInputStream(b2u.getBytes());
      b2uTree.load(stream);
      try {
        stream.close();
      } catch (IOException e) {
        // Ignore closing errors
      }
    }
    String unicodeHex = b2uTree.get(bits);
    int unicodeValue = Integer.parseInt(unicodeHex, 16);
    return String.valueOf((char) unicodeValue);
  } // toUnicode(String)
} // class BrailleAsciiTables
