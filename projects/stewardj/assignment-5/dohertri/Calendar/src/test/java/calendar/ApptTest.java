/** A JUnit test class to test the class ApptTest. */

package calendar;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Random;

import calendar.Appt;
import calendar.CalendarUtil;

public class ApptTest  {
	
	@Test
	public void test00(){
		int val;
		String str;
		Appt appt0 = new Appt(15, 30, 14, 9, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
		appt0.setValid();
		val = appt0.getStartHour();
		assertEquals(15, val);
		val = appt0.getStartMinute();
		assertEquals(30, val);
		val = appt0.getStartMonth();
		assertEquals(9, val);
		val = appt0.getStartDay();
		assertEquals(14, val);
		val = appt0.getStartYear();
		assertEquals(2018, val);
		str = appt0.getTitle();
		assertEquals(str,"Birthday Party");
		str = appt0.getDescription();
		assertEquals("This is my birthday party",str);
		str = appt0.getEmailAddress();
		assertEquals("xyz@gmail.com",str);
	}
	
	@Test
	public void test01(){
		int myArray[]= {2};
		int val;
		boolean check;
		Appt appt0 = new Appt(14, 9, 2018, "Homework", "CS362", "xyz@gmail.com");
		appt0.setValid();
		assertFalse(appt0.getValid());
		check = appt0.isRecurring();
		assertFalse(check);
		appt0.setRecurrence(myArray,2,3,4);
		assertEquals(appt0.getRecurDays(),myArray);
		val = appt0.getRecurBy();
		assertEquals(2,val);
		val = appt0.getRecurIncrement();
		assertEquals(3,val);
		val = appt0.getRecurNumber();
		assertEquals(4,val);
		check = appt0.isRecurring();
		assertTrue(check);
	}
	
	@Test
	public void test02(){
		Appt appt0 = new Appt(0, 0, 1, 1, 1, "A", "B", "C");
		appt0.setValid(); // month too low
		appt0.toString();
		assertTrue(appt0.getValid());
		appt0.setStartMonth(12);
		appt0.setValid();
		assertTrue(appt0.getValid());
		appt0.setStartMonth(0);
		appt0.setValid(); // month too low
		assertFalse(appt0.getValid());
		appt0.setStartMonth(13);
		appt0.setValid(); // month too high
		appt0.setStartMonth(12);
		appt0.setStartHour(23);
		appt0.setValid();
		assertTrue(appt0.getValid());
		appt0.setStartHour(-1);
		appt0.setValid(); // hour too low
		assertFalse(appt0.getValid());
		appt0.setStartHour(24);
		appt0.setValid(); // hour too high
		assertFalse(appt0.getValid());
		appt0.setStartHour(23);
		appt0.setStartMinute(59);
		appt0.setValid();
		assertTrue(appt0.getValid());
		appt0.setStartMinute(-1);
		appt0.setValid(); // minutes too low
		assertFalse(appt0.getValid());
		appt0.setStartMinute(61);
		appt0.setValid(); // minutes too high
		assertFalse(appt0.getValid());
		appt0.setStartMinute(59);
		appt0.setStartYear(0);
		appt0.setValid(); // year too low
		assertFalse(appt0.getValid());
		appt0.setStartYear(100);
		appt0.setStartDay(29);
		appt0.setValid();
		assertTrue(appt0.getValid());
		appt0.setStartDay(33);
		appt0.setValid(); // day too high
		assertFalse(appt0.getValid());
		appt0.setStartDay(0);
		appt0.setValid(); // day too low
		assertFalse(appt0.getValid());
		appt0.setStartDay(30);
		appt0.setValid(); // all valid
		assertTrue(appt0.getValid());
	}
	
	@Test
	public void test03(){
		String str;
		boolean check;
		Appt appt0 = new Appt(15, 30, 14, 9, 2018, "Party", "This", "@gmail.com");
		str = appt0.toString();
		assertEquals("	9/14/2018 at 3:30pm ,Party, This"+"\n",str);
		appt0.setStartHour(0);
		str = appt0.toString();
		assertEquals("	9/14/2018 at 12:30am ,Party, This"+"\n",str);
		check = appt0.isOn(14,9,2018);
		assertTrue(check);
		check = appt0.isOn(14,8,2018);
		assertFalse(check);
	}
	
	@Test
	public void test04(){
		boolean check;
		Appt appt0 = new Appt(14, 9, 2018, "Party", "This", "@gmail.com");
		Appt appt1 = new Appt(15, 30, 14, 9, 2018, "Party", "This", "@gmail.com");
		check = appt0.hasTimeSet();
		assertFalse(check);
		check = appt1.hasTimeSet();
		assertTrue(check);
	}
	
	@Test
	public void test05(){
		Appt appt0 = new Appt(14, 9, 2018,null,null,null);
		appt0.setRecurrence(null,2,3,4);
		assertEquals("",appt0.getDescription());
		assertEquals("",appt0.getTitle());
		assertEquals("",appt0.getEmailAddress());
		assertEquals(appt0.getXmlElement(), null);
	}
	
	private static final long TestTimeout = 10 * 500 * 1; /* Timeout at 30 seconds*/
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
   /**
     * Generate Random Tests that tests Appt Class.
     */
	 @Test
	 public void test06()  throws Throwable  {

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
					String methodName = ApptTest.RandomSelectMethod(random);
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
						   int recur=ApptTest.RandomSelectRecur(random);
						   int recurIncrement = ValuesGenerator.RandInt(random);
						   int recurNumber=ApptTest.RandomSelectRecurForEverNever(random);
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
