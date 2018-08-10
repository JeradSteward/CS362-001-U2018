/** A JUnit test class to test the class CalDay. */

package calendar;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

import calendar.Appt;
import calendar.CalDay;


public class CalDayTest{
	
	@Test
	public void test00(){
		String str;
		Iterator itr;
		CalDay day0 = new CalDay();
		assertFalse(day0.isValid());
		itr = day0.iterator();
		assertEquals(null,itr);
		str = day0.toString();
		assertEquals("",str);
	}
	
	@Test
	public void test01(){
		int val;
		GregorianCalendar cal = new GregorianCalendar(2018,5,17);
		CalDay day0 = new CalDay(cal);
		assertTrue(day0.isValid());
		val = day0.getDay();
		assertEquals(17,val);
		val = day0.getMonth();
		assertEquals(6,val);
		val = day0.getYear();
		assertEquals(2018,val);
	}
	
	@Test
	public void test03(){
		int val;
		String str;
		GregorianCalendar cal = new GregorianCalendar(2018,5,17);
		CalDay day0 = new CalDay(cal);
		Appt appt0 = new Appt(15, 30, 14, 9, 2018, "A", "B", "C");
		day0.addAppt(appt0);
		val = day0.getSizeAppts();
		assertEquals(1,val);
		str = day0.getFullInfomrationApp(day0);
		assertEquals("6-17-2018 "+"\n\t"+"3:30PM A B ",str);
	}
	
	@Test
	public void test04(){
		int val;
		String str;
		GregorianCalendar cal0 = new GregorianCalendar(2018,5,17);
		GregorianCalendar cal1 = new GregorianCalendar(2018,6,17);
		CalDay day0 = new CalDay(cal0);
		CalDay day1 = new CalDay(cal1);
		Appt appt0 = new Appt(12, 9, 14, 9, 2018, "A", "B", "C");
		Appt appt1 = new Appt(13, 10, 14, 9, 2018, "A", "B", "C");
		day0.addAppt(appt0);
		day1.addAppt(appt1);
		val = day0.getSizeAppts();
		assertEquals(1,val);
		str = day0.getFullInfomrationApp(day0);
		assertEquals("6-17-2018 "+"\n\t"+"0:09AM A B ",str);
		str = day1.getFullInfomrationApp(day1);
		assertEquals("7-17-2018 "+"\n\t"+"1:10PM A B ",str);
	}
	
	@Test
	public void test05(){
		int val;
		String str;
		char c;
		GregorianCalendar cal = new GregorianCalendar(2018,5,17);
		CalDay day0 = new CalDay(cal);
		Appt appt0 = new Appt(2, 10, 14, 9, 2018, "A", "B14", "C");
		Appt appt1 = new Appt(4, 8, 14, 9, 2018, "A", "B", "C");
		Appt appt2 = new Appt(14, 9, 2018, "A", "B", "C");
		Appt appt3 = new Appt(-4, 78, 14, 9, 2018, "A", "B", "C");
		Appt appt4 = new Appt(3, 9, 14, 9, 2018, "A", "B3", "C");
		day0.addAppt(appt0);
		day0.addAppt(appt1);		
		day0.addAppt(appt4);
		day0.addAppt(appt2);
		appt3.setValid();
		day0.addAppt(appt3);
		val = day0.getSizeAppts();
		assertEquals(4,val);
		str = day0.getFullInfomrationApp(day0);
		c = str.charAt(44);
		assertEquals(' ',c);
	}
	
	@Test
	public void test06(){
		int val;
		String str;
		GregorianCalendar cal = new GregorianCalendar(2018,5,17);
		CalDay day0 = new CalDay(cal);
		Appt appt0 = new Appt(0, 8, 14, 9, 2018, "A", "B", "C");
		day0.addAppt(appt0);
		str = day0.toString();
		val = str.length();
		assertEquals(95,val);
	}
	
	private static final long TestTimeout = 10 * 500 * 1; /* Timeout at 15 seconds*/
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
	 public void test07()  throws Throwable  {

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
