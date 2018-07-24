
/** A JUnit test class to test the class DataHandler. */


package calendar;

import org.junit.Test;
import static org.junit.Assert.*;
import calendar.Appt;
import calendar.CalDay;
import calendar.DataHandler;

import java.io.File;
import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
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
}
