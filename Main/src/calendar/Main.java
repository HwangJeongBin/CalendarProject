package calendar;

import java.io.IOException;
import java.util.Calendar;

public class Main {

	public static void main(String[] args){
		try {
			Calendar cal = Calendar.getInstance();
			int year = cal.get(Calendar.YEAR);
			int month = cal.get(Calendar.MONTH) +1;
			CalendarFrame frame = new CalendarFrame(year, month);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
