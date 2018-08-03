package calendar;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;
import org.junit.Test;
import static org.junit.Assert.*;


/**
 * Random Test Generator  for CalDay class.
 */

public class CalDayRandomTest {
	
	private static final long TestTimeout = 30 * 500 * 1; /* Timeout at 15 seconds*/
	private static final int NUM_TESTS=20;

	/**
	 * Return a randomly selected method to be tests !.
	 */
    public static String RandomSelectMethod(Random random){
        String[] methodArray = new String[] {"addAppt"};// The list of the of methods to be tested in the Appt class
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
		
   /**
     * Generate Random Tests that tests CalDay Class.
     */
	 @Test
	 public void radnomtest()  throws Throwable  {

		long startTime = Calendar.getInstance().getTimeInMillis();
		long elapsed = Calendar.getInstance().getTimeInMillis() - startTime;
		System.out.println("Start Testing CalDay Class...");
		 
		try{ 
			for (int iteration = 0; elapsed < TestTimeout; iteration++) {
				long randomseed =System.currentTimeMillis(); //10
				Random random = new Random(randomseed);
				
				/****Construct a new Calendar Day object****/
				int day = ValuesGenerator.getRandomIntBetween(random, 0, 33);
				int month = ValuesGenerator.getRandomIntBetween(random, 0, 13);
				int year = ValuesGenerator.getRandomIntBetween(random, -1, 2018);
				GregorianCalendar cal = new GregorianCalendar(year,month,day);
				CalDay day0 = new CalDay(cal);
				CalDay day0Assert = new CalDay(cal);

			 for (int i = 0; i < NUM_TESTS; i++) {
					       int sizeForAssert;
					       /****Create new Appointment to add.****/
						   int startHour = ValuesGenerator.getRandomIntBetween(random, -10, 30);
						   int startMinute = ValuesGenerator.getRandomIntBetween(random, -20, 70);
						   int startDay = ValuesGenerator.getRandomIntBetween(random, 0, 32);
						   int startMonth = ValuesGenerator.getRandomIntBetween(random, 0, 13);
						   int startYear = ValuesGenerator.getRandomIntBetween(random, -1, 2018);
						   String title="Study";
						   String description="CS362";
						   String emailAddress="xyz@gmail.com";	 
					       Appt appt = new Appt(startHour,startMinute,startDay,startMonth,startYear,title,description,emailAddress);
					       appt.setValid();
						   day0.addAppt(appt);
						   day0Assert.addApptForAssert(appt);
						   /****Assertion****/
						   sizeForAssert = day0Assert.getSizeAppts();
						   assertEquals(sizeForAssert,day0.getSizeAppts());					   			
				}
				elapsed = (Calendar.getInstance().getTimeInMillis() - startTime);
			    if((iteration%10000)==0 && iteration!=0 )
			              System.out.println("elapsed time: "+ elapsed + " of "+TestTimeout);
			}
		}catch(NullPointerException e){
			
		}
		 System.out.println("Done Testing CalDay Class...");
	 }
}
