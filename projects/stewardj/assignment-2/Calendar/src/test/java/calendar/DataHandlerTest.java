
/** A JUnit test class to test the class DataHandler. */


package calendar;

import org.junit.Test;
import static org.junit.Assert.*;
import calendar.Appt;
import calendar.CalDay;
import calendar.DataHandler;

import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;


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
		data0.saveAppt(appt0);
		check = data0.deleteAppt(appt0);
		assertTrue(check);
	}
	
	@Test
	public void test03() throws IOException{
		boolean check;
		DataHandler data0 = new DataHandler("newFile3",false);
		Appt appt0 = new Appt(2, 8, 14, 9, 2018, "A", "B", "C");
		appt0.setValid();
		data0.saveAppt(appt0);
		check = data0.deleteAppt(appt0);
		assertTrue(check);
		check = data0.save();
		assertTrue(check);
	}
	
	@Test
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
		assertEquals(list,list);
	}
	
	@Test
	public void test05() throws IOException, DateOutOfRangeException{
		boolean check;
		int myArray[]= {2,3};
		List<CalDay> list;
		DataHandler data0 = new DataHandler();
		Appt appt0 = new Appt(2, 8, 14, 9, 2018, "A", "B", "C");
		Appt appt1 = new Appt(3, 37, 14, 9, 2018, "A", "B", "C");
		GregorianCalendar strDay = new GregorianCalendar(2018,8,17);
		GregorianCalendar endDay = new GregorianCalendar(2018,10,17);
		appt0.setValid();
		appt1.setValid();
		appt0.setRecurrence(myArray,1,3,4);
		check = data0.saveAppt(appt0);
		assertTrue(check);
		check = data0.saveAppt(appt1);
		assertTrue(check);
		list = data0.getApptRange(strDay, endDay);
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
	
	@Test
	public void test07() throws IOException, DateOutOfRangeException{
		boolean check;
		List<CalDay> list;
		DataHandler data0 = new DataHandler();
		Appt appt0 = new Appt(2, 8, 14, 9, 2018, "A", "B", "C");
		Appt appt1 = new Appt(3, 37, 14, 9, 2018, "A", "B", "C");
		GregorianCalendar strDay = new GregorianCalendar(2018,8,17);
		GregorianCalendar endDay = new GregorianCalendar(2018,10,17);
		appt0.setValid();
		appt1.setValid();
		appt0.setRecurrence(null,2,2,4);
		check = data0.saveAppt(appt0);
		assertTrue(check);
		check = data0.saveAppt(appt1);
		assertTrue(check);
		list = data0.getApptRange(strDay, endDay);
	}
	@Test
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
}
