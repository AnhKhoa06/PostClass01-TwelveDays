/*******************************************************************************
 * Companion code for the book "Introduction to Software Design with Java",
 * 3rd edition by Martin P. Robillard.
 *
 * Copyright (C) 2025 by Martin P. Robillard
 *
 * This code is licensed under a Creative Commons 
 * Attribution-NonCommercial-NoDerivatives 4.0 International License.
 * 
 * See http://creativecommons.org/licenses/by-nc-nd/4.0/
 * 
 *******************************************************************************/
package e3.chapter1;

import java.text.MessageFormat;
import java.util.ResourceBundle;

/**
 * Outputs the text of the poem "The Twelve Days of Christmas"
 * to the console. The code leverages the natural recursion in 
 * the structure of the poem. All strings are externalized in
 * TwelveDays.properties and loaded through a ResourceBundle, so
 * the program can be localized by adding another properties file
 * (e.g., TwelveDays_fr.properties) without touching this code.
 */
public class TwelveDays {

	// Loads TwelveDays.properties from the same package (e3.chapter1).
	private static final ResourceBundle STRINGS = ResourceBundle.getBundle("e3.chapter1.TwelveDays");

	public static void main(String[] args) {
		System.out.println(poem());
	}

	/*
     * Returns the name of the day (e.g., "first", "second"...) for
     * a given index, read from the properties file.
	 */
	static String dayName(int day) {
		return STRINGS.getString("day." + day);
	}

	/*
     * Returns the gift text for a given day, read from the
     * properties file.
	 */
	static String giftText(int day) {
		return STRINGS.getString("gift." + day);
	}

	/*
     * Returns the first line in the verse for a given day.
	 */
	static String firstLine(int day) {
	  return MessageFormat.format(STRINGS.getString("text.firstLine"), dayName(day));
	}

	/*
     * Returns a string that lists all the gifts received on a given
     * day.
	 */
	static String allGifts(int day) {
		if (day == 0) { 
			return STRINGS.getString("text.and") + giftText(0); 
		}
		else { 
			return giftText(day) + "\n" + allGifts(day-1);	
		}
	}

	/*
	 * Returns the text of the entire poem. 
	 */
	static String poem() {
		String poem = firstLine(0) + giftText(0) + "\n\n";
		for (int day = 1; day < 12; day++) { 
			poem += firstLine(day) + allGifts(day) + "\n\n"; 
		}
		return poem;
	}
}