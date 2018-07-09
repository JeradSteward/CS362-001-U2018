/** A JUnit test class to test the class CalDay. */

package calendar;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.GregorianCalendar;
import java.util.Iterator;

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
		assertEquals("6-17-2018 "+"\n\t"+"3:30PM  B ",str);
	}
	@Test
	public void test04(){
		int val;
		String str;
		GregorianCalendar cal = new GregorianCalendar(2018,5,17);
		CalDay day0 = new CalDay(cal);
		Appt appt0 = new Appt(0, 8, 14, 9, 2018, "A", "B", "C");
		day0.addAppt(appt0);
		val = day0.getSizeAppts();
		assertEquals(1,val);
		str = day0.getFullInfomrationApp(day0);
		assertEquals("6-17-2018 "+"\n\t"+"12:08AM  B ",str);
	}
	@Test
	public void test05(){
		int val;
		String str;
		GregorianCalendar cal = new GregorianCalendar(2018,5,17);
		CalDay day0 = new CalDay(cal);
		Appt appt0 = new Appt(0, 8, 14, 9, 2018, "A", "B", "C");
		Appt appt1 = new Appt(2, 8, 14, 9, 2018, "A", "B", "C");
		Appt appt2 = new Appt(14, 9, 2018, "A", "B", "C");
		Appt appt3 = new Appt(-4, 78, 14, 9, 2018, "A", "B", "C");
		day0.addAppt(appt0);
		day0.addAppt(appt1);
		day0.addAppt(appt2);
		appt3.setValid();
		day0.addAppt(appt3);
		val = day0.getSizeAppts();
		assertEquals(3,val);
		str = day0.toString();
	}
	
}
