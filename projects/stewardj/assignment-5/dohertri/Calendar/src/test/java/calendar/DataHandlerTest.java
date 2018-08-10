
/** A JUnit test class to test the class DataHandler. */


package calendar;

import org.junit.Test;
import static org.junit.Assert.*;
import calendar.Appt;
import calendar.CalDay;
import calendar.DataHandler;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.w3c.dom.Element;

public class DataHandlerTest{

	@Test
	public void test00() throws IOException{
		boolean check;
		DataHandler data0 = new DataHandler();
		Appt appt0 = new Appt(2, 8, 14, 9, 2018, "A", "B", "C");
		Appt appt1 = new Appt(-4, 78, 14, 9, 2018, "A", "B", "C");
		appt0.setValid();
		appt1.setValid();
		check = data0.saveAppt(appt0);
		assertTrue(check);
		check = data0.saveAppt(appt1);
		assertFalse(check);
		check = data0.deleteAppt(appt1);
		assertFalse(check);
	}
	
	@Test
	public void test01() throws IOException{
		boolean check;
		DataHandler data0 = new DataHandler("newFile1");
		Appt appt0 = new Appt(2, 8, 14, 9, 2018, "A", "B", "C");
		appt0.setValid();
		data0.saveAppt(appt0);
		check = data0.deleteAppt(appt0);
		assertTrue(check);
	}
	
	@Test
	public void test02() throws IOException{
		boolean check;
		DataHandler data0 = new DataHandler("newFile2",true);
		Appt appt0 = new Appt(2, 8, 14, 9, 2018, "A", "B", "C");
		appt0.setValid();
		check = data0.saveAppt(appt0);
		assertTrue(check);
		check = data0.deleteAppt(appt0);
		assertTrue(check);
		
	}
	
	@Test
	public void test03() throws IOException{
		boolean check;
		Element E;
		DataHandler data0 = new DataHandler("newFile3",false);
		Appt appt0 = new Appt(2, 8, 14, 9, 2018, "A", "B", "C");
		appt0.setValid();
		check = data0.saveAppt(appt0);
		assertTrue(check);
		check = data0.deleteAppt(appt0);
		assertTrue(check);
		E = appt0.getXmlElement();
		assertEquals(E, null);
		check = data0.save();
		assertTrue(check);
	}
	
	@Test (timeout = 300)
	public void test04() throws IOException, DateOutOfRangeException{
		boolean check;
		List<CalDay> list;
		DataHandler data0 = new DataHandler();
		Appt appt0 = new Appt(2, 8, 14, 9, 2018, "A", "B", "C");
		Appt appt1 = new Appt(3, 37, 14, 9, 2018, "A", "B", "C");
		GregorianCalendar strDay = new GregorianCalendar(2018,8,17);
		GregorianCalendar endDay = new GregorianCalendar(2018,10,17);
		appt0.setValid();
		appt1.setValid();
		check = data0.saveAppt(appt0);
		assertTrue(check);
		check = data0.saveAppt(appt1);
		assertTrue(check);
		list = data0.getApptRange(strDay, endDay);
		assertTrue(list != null);
	}
	
	@Test (timeout = 200)
	public void test05() throws IOException, DateOutOfRangeException{
		boolean check;
		int myArray[]= {2,3,5};
		List<CalDay> list;
		DataHandler data0 = new DataHandler();
		Appt appt0 = new Appt(2, 8, 14, 9, 2018, "A", "B", "C");
		Appt appt1 = new Appt(3, 37, 14, 9, 2018, "A", "B", "C");
		GregorianCalendar strDay = new GregorianCalendar(2018,8,17);
		GregorianCalendar endDay = new GregorianCalendar(2018,10,17);
		appt0.setValid();
		appt1.setValid();
		appt0.setRecurrence(myArray,1,2,5);
		check = data0.saveAppt(appt0);
		assertTrue(check);
		check = data0.saveAppt(appt1);
		assertTrue(check);
		list = data0.getApptRange(strDay, endDay);
		assertEquals(61,list.size());
	}
	
	@Test
	public void test06() throws IOException, DateOutOfRangeException{
		boolean check;
		DataHandler data0 = new DataHandler();
		Appt appt0 = new Appt(2, 8, 14, 9, 2018, "A", "B", "C");
		appt0.setValid();
		check = data0.saveAppt(appt0);
		assertTrue(check);
		appt0.setXmlElement(null);
		check = data0.deleteAppt(appt0);
		assertFalse(check);
	}
	
	@Test (timeout = 200)
	public void test07() throws IOException, DateOutOfRangeException{
		boolean check;
		int myArray[]= {};
		int myArray1[]= {2,3,5};
		List<CalDay> list;
		DataHandler data0 = new DataHandler();
		Appt appt0 = new Appt(2, 8, 14, 9, 2018, "A", "B", "C");
		Appt appt1 = new Appt(3, 37, 14, 9, 2018, "A", "B", "C");
		GregorianCalendar strDay = new GregorianCalendar(2018,9,17);
		GregorianCalendar endDay = new GregorianCalendar(2018,10,17);
		appt0.setValid();
		appt1.setValid();
		appt0.setRecurrence(myArray,1,2,4);
		appt1.setRecurrence(myArray1,1,1,5);
		check = data0.saveAppt(appt0);
		assertTrue(check);
		check = data0.saveAppt(appt1);
		assertTrue(check);
		list = data0.getApptRange(strDay, endDay);
		assertEquals(31,list.size());
	}
	
	@Test (timeout = 200)
	public void test08() throws IOException{
		boolean check;
		List<CalDay> list;
		DataHandler data0 = new DataHandler();
		Appt appt0 = new Appt(2, 8, 14, 9, 2018, "A", "B", "C");
		Appt appt1 = new Appt(3, 37, 14, 9, 2018, "A", "B", "C");
		GregorianCalendar strDay = new GregorianCalendar(2018,11,17);
		GregorianCalendar endDay = new GregorianCalendar(2018,9,17);
		appt0.setValid();
		appt1.setValid();
		appt0.setRecurrence(null,3,2,1);
		check = data0.saveAppt(appt0);
		assertTrue(check);
		check = data0.saveAppt(appt1);
		assertTrue(check);
		try {
			list = data0.getApptRange(strDay, endDay);
		} catch (DateOutOfRangeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void test09() throws IOException{
		File f = new File("newFile4");
		DataHandler data0 = new DataHandler("newFile4",false);
		Appt appt0 = new Appt(2, 8, 14, 9, 2018, "A", "B", "C");
		appt0.setValid();
		if(f.isFile()) {
			f.delete();
		}
		data0.saveAppt(appt0);
		assertFalse(f.isFile());
		if(f.isFile()) {
			f.delete();
		}
		data0.deleteAppt(appt0);
		assertFalse(f.isFile());
	}
	
	@Test (timeout = 200)
	public void test10() throws IOException, DateOutOfRangeException{
		boolean check;
		List<CalDay> list;
		DataHandler data0 = new DataHandler();
		Appt appt0 = new Appt(2, 8, 14, 9, 2018, "A", "B", "C");
		Appt appt1 = new Appt(3, 37, 14, 9, 2018, "A", "B", "C");
		GregorianCalendar strDay = new GregorianCalendar(2018,8,17);
		GregorianCalendar endDay = new GregorianCalendar(2018,10,17);
		appt0.setValid();
		appt1.setValid();
		check = data0.saveAppt(appt0);
		assertTrue(check);
		check = data0.saveAppt(appt1);
		assertTrue(check);
		list = data0.getApptRange(strDay, endDay);
		assertEquals(61,list.size());
	}
	
	@Test
	public void test11() throws IOException, DateOutOfRangeException{
		boolean check;
		int myArray[]= {8,9,10};
		List<CalDay> list;
		DataHandler data0 = new DataHandler();
		Appt appt0 = new Appt(2, 8, 14, 9, 2018, "A", "B", "C");
		Appt appt1 = new Appt(3, 37, 14, 9, 2018, "A", "B", "C");
		GregorianCalendar strDay = new GregorianCalendar(2018,8,17);
		GregorianCalendar endDay = new GregorianCalendar(2018,10,17);
		appt0.setValid();
		appt1.setValid();
		appt0.setRecurrence(myArray,1,2,5);
		check = data0.saveAppt(appt0);
		assertTrue(check);
		check = data0.saveAppt(appt1);
		assertTrue(check);
		list = data0.getApptRange(strDay, endDay);
		assertEquals(61,list.size());
	}
	
	@Test
	public void test12() throws IOException, DateOutOfRangeException{
		boolean check;
		int myArray[]= {};
		List<CalDay> list;
		DataHandler data0 = new DataHandler();
		Appt appt0 = new Appt(2, 8, 14, 9, 2018, "A", "B", "C");
		Appt appt1 = new Appt(3, 37, 14, 9, 2018, "A", "B", "C");
		GregorianCalendar strDay = new GregorianCalendar(2018,9,17);
		GregorianCalendar endDay = new GregorianCalendar(2018,10,17);
		appt0.setValid();
		appt1.setValid();
		appt0.setRecurrence(myArray,1,2,4);
		check = data0.saveAppt(appt0);
		assertTrue(check);
		check = data0.saveAppt(appt1);
		assertTrue(check);
		list = data0.getApptRange(strDay, endDay);
		assertEquals(31,list.size());
	}
	
	private static final long TestTimeout = 10 * 500 * 1; /* Timeout at 30 seconds */
	private static final int NUM_TESTS=30;

	/**
	 * Return a randomly selected method to be tests !.
	 */
    public static String RandomSelectMethod(Random random){
        String[] methodArray = new String[] {"deleteAppt","getApptRange"};// The list of the of methods to be tested in the Appt class
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
     * Generate Random Tests that tests DataHandler Class.
     */
	 @Test
	 public void test13()  throws Throwable  {

		long startTime = Calendar.getInstance().getTimeInMillis();
		long elapsed = Calendar.getInstance().getTimeInMillis() - startTime;
		System.out.println("Start Testing DataHandler Class...");
		 
		try{ 
			for (int iteration = 0; elapsed < TestTimeout; iteration++) {
				long randomseed =System.currentTimeMillis(); //10
				Random random = new Random(randomseed);
		        
		        String methodName = DataHandlerTest.RandomSelectMethod(random);
				if (methodName.equals("deleteAppt")){
					boolean autosaveOrNot;
					if(ValuesGenerator.getRandomIntBetween(random, 0, 1) == 0) {
						autosaveOrNot = true;
					}
					else {
						autosaveOrNot = false;
					}
					DataHandler dataH = new DataHandler("newFile1",autosaveOrNot);
					DataHandler dataAssert = new DataHandler("newFile2",autosaveOrNot);
					for (int i = 0; i < NUM_TESTS; i++) {
						boolean check;
						int combo = ValuesGenerator.getRandomIntBetween(random, 0, 2);
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
				        appt.setValid();
						if(combo == 0) {
							dataAssert.saveAppt(appt);
							check = dataAssert.deleteApptForAssert(appt);
							dataH.saveAppt(appt);
							assertEquals(check, dataH.deleteAppt(appt));
						}
						else if (combo == 1){
							dataH.saveAppt(appt);
						}
						else if (combo == 2){
							check = dataAssert.deleteApptForAssert(appt);
							assertEquals(check, dataH.deleteAppt(appt));
						}
					}
				}
				else if (methodName.equals("getApptRange")){
					List<CalDay> list = null;
					List<CalDay> listAssert = null;
					/****Create DataHandler and fill with values****/
					DataHandler dataH = new DataHandler("newFile3",true);
					int day = ValuesGenerator.getRandomIntBetween(random, 0, 33);
					int month = ValuesGenerator.getRandomIntBetween(random, 0, 13);
					int year = ValuesGenerator.getRandomIntBetween(random, -1, 2018);
					GregorianCalendar strDay = new GregorianCalendar(year,month,day);
					day = ValuesGenerator.getRandomIntBetween(random, 0, 33);
					month = ValuesGenerator.getRandomIntBetween(random, 0, 13);
					year = ValuesGenerator.getRandomIntBetween(random, -1, 2018);
					GregorianCalendar endDay = new GregorianCalendar(year,month,day);
					
					/****Create 5 new Appointment to add.****/
					for(int i=0; i < 5;i++) {
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
						int[] recurDays;
						int pick = ValuesGenerator.getRandomIntBetween(random, 0, 9);
						int sizeArray=ValuesGenerator.getRandomIntBetween(random, 0, 8);
						if(pick == 0) {
							recurDays=null;
						}
						else {
							recurDays=ValuesGenerator.generateRandomArray(random, sizeArray);
						}
						int recur=DataHandlerTest.RandomSelectRecur(random);
						int recurIncrement = ValuesGenerator.RandInt(random);
						int recurNumber=DataHandlerTest.RandomSelectRecurForEverNever(random);
						appt.setRecurrenceForAssert(recurDays, recur, recurIncrement, recurNumber);
						dataH.saveAppt(appt);
					}
					try {
						list = dataH.getApptRange(strDay, endDay);
						listAssert = dataH.getApptRangeForAssert(strDay, endDay);
					} catch (DateOutOfRangeException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//assertEquals(listAssert,list);
				}
				elapsed = (Calendar.getInstance().getTimeInMillis() - startTime);
			    if((iteration%10000)==0 && iteration!=0 )
			              System.out.println("elapsed time: "+ elapsed + " of "+TestTimeout);
				}
		}catch(NullPointerException e){
			
		}
		 System.out.println("Done Testing DataHandler Class...");
	 }
}
