import java.util.Scanner;

/**
 * The PalindromeTest class contains a main() method to prompt the user to enter a date,
 * then passes that date as an int[] argument to the Palindrome class constructor.
 * It then handles any invalid input exceptions thrown by Palindrome,
 * asking the user to repeat the input if the date is invalid ("February 30", for example).
 * Finally, it displays the result of the palindrome evaluation using Palindrome.toString().
 */
public class PalindromeTest
{
	public static void main(String[] args)
	{
		boolean isValidInput;
		int[] userDate;
		Palindrome pal;

		do
		{
			try
			{
				userDate = initialPrompt();
				pal = new Palindrome(userDate);
				isValidInput = true;
			}
			catch (IllegalArgumentException e)
			{
				System.out.println("Please enter a valid " + e.getMessage().toLowerCase());
				System.out.println();

				isValidInput = false;
				pal = null;
			}
		} while (!isValidInput);

		System.out.println(pal.toString());

	} // end of main()

	/**
	 * Prompts the user to enter the month, day, and year as a series of ints.
	 * @return int[] specifying the year, month, and day.
	 */
	public static int[] initialPrompt()
	{
		int[] userDate = new int[3];
		Scanner scanner = new Scanner(System.in);

		System.out.print("Please enter the month:  ");
		userDate[1] = scanner.nextInt();

		System.out.print("Please enter the day:  ");
		userDate[2] = scanner.nextInt();

		System.out.print("Please enter the year:  ");
		userDate[0] = scanner.nextInt();

		return userDate;

	} // end of initialPrompt()

} // end of PalindromeTest