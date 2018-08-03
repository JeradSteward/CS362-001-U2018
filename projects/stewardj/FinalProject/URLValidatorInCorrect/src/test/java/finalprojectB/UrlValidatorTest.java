
package finalprojectB;

import java.util.Calendar;
import java.util.Random;
import org.junit.Test;
import static org.junit.Assert.*;



/****Unit tests for UrlValidator isValid method.****/
public class UrlValidatorTest {

	private static final long TestTimeout = 60 * 500 * 1; /* Timeout at 30 seconds*/
	private static final int NUM_TESTS=10;

	/**
	 * Return a randomly selected method to be tests !.
	 */
    public static String RandomSelectMethod(Random random){
        String[] methodArray = new String[] {"setValid","setRecurrence"};// The list of the of methods to be tested in the Appt class
    	int n = random.nextInt(methodArray.length);// get a random number between 0 (inclusive) and  methodArray.length (exclusive)            
        return methodArray[n] ; // return the method name 
     }
	/**
	 * Return a randomly selected appointments to recur Weekly,Monthly, or Yearly !.
	 */
    public static int RandomSelectRecur(Random random){
        int[] RecurArray = new int[] {Appt.RECUR_BY_WEEKLY,Appt.RECUR_BY_MONTHLY,Appt.RECUR_BY_YEARLY};// The list of the of setting appointments to recur Weekly,Monthly, or Yearly
    	int n = random.nextInt(RecurArray.length);// get a random number between 0 (inclusive) and  RecurArray.length (exclusive)
        return RecurArray[n] ; // return the value of the  appointments to recur 
    }	
	/**
	 * Return a randomly selected appointments to recur forever or Never recur  !.
	 */
    public static int RandomSelectRecurForEverNever(Random random){
        int[] RecurArray = new int[] {Appt.RECUR_NUMBER_FOREVER,Appt.RECUR_NUMBER_NEVER};// The list of the of setting appointments to recur RECUR_NUMBER_FOREVER, or RECUR_NUMBER_NEVER
    	int n = random.nextInt(RecurArray.length);// get a random number between 0 (inclusive) and  RecurArray.length (exclusive)
        return RecurArray[n] ; // return appointments to recur forever or Never recur 
    }
    
    /**Generate Random Tests that tests isValid method.****/
	@Test
	public void radnomtest()  throws Throwable  {

		long startTime = Calendar.getInstance().getTimeInMillis();
		long elapsed = Calendar.getInstance().getTimeInMillis() - startTime;
		System.out.println("Start Testing Appt Class...");
		 
		try{ 
			for (int iteration = 0; elapsed < TestTimeout; iteration++) {
				long randomseed =System.currentTimeMillis(); //10
				Random random = new Random(randomseed);
				
				int startHour = ValuesGenerator.getRandomIntBetween(random, -10, 30);
				int startMinute = ValuesGenerator.getRandomIntBetween(random, -20, 70);
				int startDay = ValuesGenerator.getRandomIntBetween(random, 0, 32);
				int startMonth = ValuesGenerator.getRandomIntBetween(random, 0, 13);
				int startYear = ValuesGenerator.getRandomIntBetween(random, -1, 2018);
				String title="Study";
				String description="CS362";
				String emailAddress="xyz@gmail.com";

				//Construct a new Appointment object with the initial data	 
		        Appt appt = new Appt(startHour,startMinute,startDay,startMonth,startYear,title,description,emailAddress);

			 for (int i = 0; i < NUM_TESTS; i++) {
					String methodName = UrlValidatorTest.RandomSelectMethod(random);
					   if (methodName.equals("setValid")){
						   /****Assertion****/
						   boolean checkForAssert;
						   appt.setValidForAssert();
						   checkForAssert = appt.getValid();
						   appt.setValid();
						   assertEquals(checkForAssert,appt.getValid());					   
						}
					   else if (methodName.equals("setRecurrence")){
						   int[] recurDays;
						   int pick = ValuesGenerator.getRandomIntBetween(random, 0, 9);
						   int sizeArray=ValuesGenerator.getRandomIntBetween(random, 0, 8);
						   if(pick == 0) {
							   recurDays=null;
						   }
						   else {
							   recurDays=ValuesGenerator.generateRandomArray(random, sizeArray);
						   }
						   int recur=UrlValidatorTest.RandomSelectRecur(random);
						   int recurIncrement = ValuesGenerator.RandInt(random);
						   int recurNumber=UrlValidatorTest.RandomSelectRecurForEverNever(random);
						   /****Assertion****/
						   appt.setRecurrenceForAssert(recurDays, recur, recurIncrement, recurNumber);
						   int assertRecB = appt.getRecurBy();
						   int assertRecI = appt.getRecurIncrement();
						   int assertRecN = appt.getRecurNumber();
						   appt.setRecurrence(recurDays, recur, recurIncrement, recurNumber);
						   assertEquals(assertRecB,appt.getRecurBy());
						   assertEquals(assertRecI,appt.getRecurIncrement());
						   assertEquals(assertRecN,appt.getRecurNumber());
						}				
				}
				elapsed = (Calendar.getInstance().getTimeInMillis() - startTime);
			    if((iteration%10000)==0 && iteration!=0 )
			              System.out.println("elapsed time: "+ elapsed + " of "+TestTimeout);
			}
		}catch(NullPointerException e){
			
		}
		 System.out.println("Done Testing Appt Class...");
	 }
}