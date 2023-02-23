package com.qa.ngageplatform.utils;

import org.testng.Assert;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * This Class is used to provide methods related to Handling of Document, Date ,
 * Sorting etc
 *
 * @author Nahian Omar Faruqe
 * @version 1.0
 * @since 2022-09-28
 */
public class CommonUtil {

	/**
	 * This method is used to get File Path by taking FileName from
	 * .\test\resources\Documents
	 *
	 * @param fileName FileName in String format
	 * @return This will return the filepath according to fileName
	 */
	public String getResourceDocumentPath(String fileName) {
		String filePath = null;
		try {
			filePath = new File("./").getAbsolutePath().replace(".", "") + "src\\test\\resources\\Documents";
		} catch (Exception e) {
			AssertionUtil.fail("Fail while reading file " + fileName + "in src\\test\\resources\\Documents");
		}
		return filePath + "\\" + fileName;
	}

	/**
	 * This method is used to get Current DateTime in "ddMMyyyyHHmmss" Format [Can
	 * be use as Random unique String]
	 *
	 * @return This will return the DateTime in "ddMMyyyyHHmmss" Format
	 */
	public String getCurrentDateTime() {
		return this.getCurrentDateTime("ddMMyyyyHHmmss");
	}

	/**
	 * This method is used to get future/past DateTime in "MM-dd-yyyy hh:mm:ss a"
	 * Format depending on the provided Days
	 *
	 * @param Days Days value which user want example -1 for yesterday's date and 1
	 *             for tomorrow's date
	 * @return This will return the DateTime in "MM-dd-yyyy hh:mm:ss a" Format
	 */
	public String getDateTime(int Days) {
		return getDateTime(Days, "MM-dd-yyyy hh:mm:ss a");
	}

	/**
	 * This method is used to get future/past DateTime in user provided Format
	 * depending on the provided Days
	 *
	 * @param Days   Days value which user want example -1 for yesterday's date and
	 *               1 for tomorrow's date
	 * @param format User Specific Date Format
	 * @return This will return the DateTime in user provided Format
	 */
	public String getDateTime(int Days, String format) {
		LocalDateTime date = LocalDateTime.now().plusDays(Days);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
		String myFormattedDate = date.format(formatter);
		return myFormattedDate;

	}

	/**
	 * This method is used to get Current DateTime in user provided Format
	 *
	 * @param dateFormat User Specific Date Format
	 * @return This will return the DateTime in user provided Format
	 */
	public String getCurrentDateTime(String dateFormat) {
		String dateTime = null;
		try {
			SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
			Date date = new Date();
			dateTime = formatter.format(date);
		} catch (Exception e) {
			AssertionUtil.fail("Fail while getting Current Date Time");
		}
		return dateTime;
	}

	/**
	 * This method is used to compare two date [Return True if First Date 'date1' is
	 * Greater than Second Date 'date2' Otherwise Return False]
	 *
	 * @param date1  First Date in String Format
	 * @param date2  Second Date in String Format
	 * @param format User Specific Date Format
	 * @return This will return TRUE if date1 is greater than date2 else return
	 *         FALSE
	 */
	public boolean verifyFirstDateIsGreater(String date1, String date2, String format) {
		try {
			Date d1 = new SimpleDateFormat(format).parse(date1);
			Date d2 = new SimpleDateFormat(format).parse(date2);
			if (d1.compareTo(d2) == 1)
				return true;
			else
				return false;
		} catch (Exception e) {
			AssertionUtil.fail("Fail while comparing two dates");
		}
		return false;
	}

	/**
	 * This method is used to Sort the List in Specific Ascending Order Priority:
	 * Symbol, Number, Alphabet [Ignoring Case-Sensitivity] Example: <b>Raw List
	 * :</b> abc,ACC,adC,[abc],1 <b>Sorted List :</b> [abc],1,abc,ACC,adC
	 *
	 * @param list Raw List (unsorted List)
	 * @return This will return Sorted List
	 */
	public List<String> customiseSorting(List<String> list) {

		Collections.sort(list, new Comparator<String>() {
			private boolean isThereAnyNumber(String a, String b) {
				return isNumber(a) || isNumber(b);
			}

			private boolean isThereAnySymbol(String a, String b) {
				return isSymbol(a) || isSymbol(b);
			}

			private boolean isNumber(String s) {
				return s.matches("[-+]?\\d*\\.?\\d+");
			}

			private boolean isSymbol(String s) {
				return String.valueOf(s.charAt(0)).matches("[\\[\\(].*");
			}

			@Override
			public int compare(String a, String b) {
				return isThereAnySymbol(a, b) ? isSymbol(a) ? -1 : 1
						: isThereAnyNumber(a, b) ? isNumber(a) ? 1 : -1 : a.compareToIgnoreCase(b);
			}
		});
		return list;
	}

	/**
	 * This method is used to convert String to Number
	 *
	 * @param value Value in String Format which need to convert to Int Format
	 * @return This will return the value in Int Format
	 */
	public int convertStringToNumber(String value) {
		try {
			return Integer.parseInt(value);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			Assert.fail("Issue while converting String to number");
		}
		return 0;

	}

	/**
	 * This method is used to compare two date [Return True if First Date 'date1' is
	 * Greater or equal than Second Date 'date2' Otherwise Return False]
	 *
	 * @param date1  First Date in String Format
	 * @param date2  Second Date in String Format
	 * @param format User Specific Date Format
	 * @return This will return TRUE if date1 is greater or equal than date2 else
	 *         return FALSE
	 */
	public boolean verifyFirstDateIsGreaterOrEqual(String date1, String date2, String format) {
		try {
			Date d1 = new SimpleDateFormat(format).parse(date1);
			Date d2 = new SimpleDateFormat(format).parse(date2);
			if (d1.compareTo(d2) >= 0)
				return true;
			else
				return false;
		} catch (Exception e) {
			AssertionUtil.fail("Fail while comparing two dates");
		}
		return false;
	}

	/**
	 * This method is used to compare two date [Return True if First Date 'date1' is
	 * to Second Date 'date2' Otherwise Return False]
	 *
	 * @param date1  First Date in String Format
	 * @param date2  Second Date in String Format
	 * @param format User Specific Date Format
	 * @return This will return TRUE if date1 is equal to date2 else return FALSE
	 */
	public boolean verifyTwoDatesAreEqual(String date1, String date2, String format) {
		try {
			Date d1 = new SimpleDateFormat(format).parse(date1);
			Date d2 = new SimpleDateFormat(format).parse(date2);
			if (d1.compareTo(d2) == 0)
				return true;
			else
				return false;
		} catch (Exception e) {
			AssertionUtil.fail("Fail while comparing two dates");
		}
		return false;
	}

}
