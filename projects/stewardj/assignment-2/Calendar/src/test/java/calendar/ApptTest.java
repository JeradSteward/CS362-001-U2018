/** A JUnit test class to test the class ApptTest. */

package calendar;

import org.junit.Test;
import static org.junit.Assert.*;
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
		assertEquals("",str);
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
		Appt appt0 = new Appt(-1, 65, 35, 13, -2, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
		appt0.setValid(); // month too high
		appt0.toString();
		assertFalse(appt0.getValid());
		appt0.setStartMonth(0);
		appt0.setValid(); // month too low
		assertFalse(appt0.getValid());
		appt0.setStartMonth(2);
		appt0.setValid(); // hour too low
		assertFalse(appt0.getValid());
		appt0.setStartHour(26);
		appt0.setValid(); // hour too high
		assertFalse(appt0.getValid());
		appt0.setStartHour(5);
		appt0.setValid(); // minutes too high
		assertFalse(appt0.getValid());
		appt0.setStartMinute(-2);
		appt0.setValid(); // minutes too low
		assertFalse(appt0.getValid());
		appt0.setStartMinute(10);
		appt0.setValid(); // year too low
		assertFalse(appt0.getValid());
		appt0.setStartYear(100);
		appt0.setValid(); // day too high
		assertFalse(appt0.getValid());
		appt0.setStartDay(0);
		appt0.setValid(); // day too low
		assertFalse(appt0.getValid());
		appt0.setStartDay(7);
		appt0.setValid(); // all valid
		assertTrue(appt0.getValid());
	}
	@Test
	public void test03(){
		String str;
		boolean check;
		Appt appt0 = new Appt(15, 30, 14, 9, 2018, "Party", "This", "@gmail.com");
		str = appt0.toString();
		assertEquals("	9/14/2018 at 3:30pm ,, This"+"\n",str);
		appt0.setStartHour(0);
		str = appt0.toString();
		assertEquals("	9/14/2018 at 12:30am ,, This"+"\n",str);
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
		appt0.getXmlElement();
	}
}
