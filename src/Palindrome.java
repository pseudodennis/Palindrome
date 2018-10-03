import support_structures.DoubleLinkedDeque;
import support_structures.LinkedStack;
import java.util.*;

/**
 * This class evaluates a date to determine if its numerical representation is a
 * palindrome, that is, reads the same way forward or backward. For example, May
 * 2, 2050 is palindromic: 05 02 2050. Single-digit month or day values are
 * considered both with and without leading zeroes. The date is stored in a Calendar,
 * which will throw an exception if an invalid date is attempted (i.e. February 31).
 * Since the palindrome-ness is evaluated when the date is set, all the instance
 * variables are updated by the setCalendar() method.
 */
public class Palindrome
{
	// --------------------
	// INSTANCE DATA
	// --------------------

	private Calendar calendar;
	private boolean isPalindrome;
	private String palSequence;

	private String longMonth;
	private String longDay;
	private String longYear;

	// --------------------
	// CONSTRUCTORS
	// --------------------

	public Palindrome(int[] userDate)
	{
		this.setCalendar(userDate);
	}

	public Palindrome(int year, int month, int day)
	{
		int[] date = {year, month, day};
		this.setCalendar(date);
	}

	public Palindrome()
	{
		int[] defaultCalendar = {2015, 8, 21};
		this.setCalendar(defaultCalendar);
	}

	// --------------------
	// GETTERS AND SETTER
	// --------------------

	public Calendar getCalendar()
	{
		return calendar;
	}
	public boolean isPalindrome()
	{
		return isPalindrome;
	}
	public String getPalSequence()
	{
		return palSequence;
	}
	public String getLongMonth()
	{
		return longMonth;
	}
	public String getLongDay()
	{
		return longDay;
	}
	public String getLongYear()
	{
		return longYear;
	}

	/**
	 * Sets the calendar date from an int[] containing the year, month, and day.
	 * (Since the month values in Calendar are zero-based, the value for month is
	 * subtracted by 1.) After setting the instance variables, the .test() method
	 * is called to encode multiple versions of the calendar and set isPalindrome
	 * accordingly.
	 * @param userDate
	 */
	public void setCalendar(int[] userDate)
	{
			int zeroMonth = userDate[1] - 1;
			this.calendar = new Calendar.Builder().setLenient(false).setDate(userDate[0], zeroMonth, userDate[2]).build();
			this.longMonth = this.calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.US);
			this.longDay = String.valueOf(this.calendar.get(Calendar.DAY_OF_MONTH));
			this.longYear = String.valueOf(this.calendar.get(Calendar.YEAR));
			test();
	}

	/**
	 * Returns the date entered into the Calendar constructor,
	 * whether or not if the date evaluates to a palindrome, and if so,
	 * the particular version of the date which does so.
	 * @return the contents of the Palindrome as a String
	 */
	@Override
	public String toString()
	{
		StringBuilder returnString = new StringBuilder();
		returnString.append("The date, ")
				.append(this.longMonth + " " + this.longDay + ", " + this.longYear + ", ");

		if (this.isPalindrome)
			returnString.append("is a palindrome as expressed in the sequence ")
					.append(this.palSequence + ".");
		else
			returnString.append("is not a palindrome.");

		return returnString.toString();
	}

	// --------------------
	// METHODS
	// --------------------

	/**
	 * Encodes the date set in the Calendar as a sequence of Characters, as a
	 * kind of preprocessor for the test() method. The Characters are stored in a
	 * DoubleLinkedDeque. Since dates can be conventionally serialized in multiple
	 * ways (i.e. May the 4th can be "5 4", "05 04", "5 04" or "05 4"), multiple
	 * versions of the dates are encoded into multiple deques, which are in turn
	 * all pushed into a LinkedStack.
	 * @return a LinkedStack of DoubleLinkedDeques of Characters
	 */
	private LinkedStack<DoubleLinkedDeque> encode()
	{
		LinkedStack<DoubleLinkedDeque> stackOfDeques = new LinkedStack<>();
		String calString =
				String.valueOf(this.calendar.get(Calendar.MONTH)+1) +			// +1 to offset the asinine zero-based numbering system
				String.valueOf(this.calendar.get(Calendar.DAY_OF_MONTH)) +
				String.valueOf(this.calendar.get(Calendar.YEAR))
				;

		// index values that specify where to insert a leading 0 if the month or day is a single-digit
		int indexPreMonth = 0;
		int indexPreDay = String.valueOf(this.calendar.get(Calendar.MONTH)+1).length();	// the place to insert the 0-prefix for the day is offset by whether the month is a dingle or double-digit

		// push the base deque onto the stack
		StringBuilder calStringBase = new StringBuilder(calString);
		stackOfDeques.push(dequify(calStringBase));

		// if single-digit month, push a version of the deque with a leading zero
		if ((this.calendar.get(Calendar.MONTH)+1) < 10)
		{
			StringBuilder calStringMonth = new StringBuilder(calString);
			stackOfDeques.push(dequify(calStringMonth.insert(indexPreMonth, '0')));
		}

		// if single-digit day, push a version of the deque with a leading zero
		if ((this.calendar.get(Calendar.DAY_OF_MONTH)) < 10)
		{
			StringBuilder calStringDay = new StringBuilder(calString);
			stackOfDeques.push(dequify(calStringDay.insert(indexPreDay, '0')));
		}

		// if both day and month are single-digits, push a version of the deque with both leading zeroes
		if ( ((this.calendar.get(Calendar.MONTH)+1) < 10) && ((this.calendar.get(Calendar.DAY_OF_MONTH)) < 10) )
		{
			StringBuilder calStringMonthDay = new StringBuilder(calString);
			calStringMonthDay.insert(indexPreDay, '0');
			calStringMonthDay.insert(indexPreMonth, '0');
			stackOfDeques.push(dequify(calStringMonthDay));
		}

		return stackOfDeques;
	}

	/**
	 * Converts a StringBuilder into a DoubleLinkedDeque of Characters
	 * @param stringBuilder
	 * @return a deque of Characters
	 */
	private static DoubleLinkedDeque<Character> dequify(StringBuilder stringBuilder)
	{
		DoubleLinkedDeque<Character> deque = new DoubleLinkedDeque<Character>();
		for (int i = 0; i < stringBuilder.length(); i++)
		{
			deque.enqueue(stringBuilder.charAt(i));
		}
		return deque;
	}

	/**
	 * Evaluates whether or not the serialized date in this object's calendar is
	 * a palindrome. It updates the isPalindrome instance variable and if true,
	 * also updates the palSequence with the specific version of the encoded
	 * date that meets palindrome requirements.
	 */
	private void test()
	{
		boolean isPalindrome = false;
		LinkedStack<DoubleLinkedDeque> stackOfDeques = this.encode();

		// pop each deque off the stack to evaluate if it's a palindrome
		while (!stackOfDeques.isEmpty() && !isPalindrome)
		{
			isPalindrome = true;
			StringBuilder palSeq = new StringBuilder();
			DoubleLinkedDeque<Character> deque = stackOfDeques.top();

			// transcribe the deque into a StringBuilder in order to update this.palSequence in the event the date is a palindrome
			for (int i=0; i<deque.size(); i++)
			{
				char c = deque.dequeue();
				palSeq.append(c);
				deque.enqueue(c);
			}

			// determine if the deque is a palindrome by comparing the values returned by dequeue() and dequeueRear()
			while (deque.size()>1)
			{
				if (Character.valueOf(deque.dequeue()) != Character.valueOf(deque.dequeueRear()) )
					isPalindrome = false;
			}

			// if the deque is a palindrome, update this.palSequence with the winning values
			if (isPalindrome)
				this.palSequence = palSeq.toString();
			else
				this.palSequence = null;

			stackOfDeques.pop();
		}

		this.isPalindrome = isPalindrome;
	} // end test()

} // end Palindrome