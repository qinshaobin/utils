package com.bin.joda.test;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Hours;
import org.joda.time.LocalDate;
import org.joda.time.Minutes;
import org.joda.time.Months;
import org.joda.time.Period;
import org.joda.time.PeriodType;
import org.joda.time.Seconds;
import org.joda.time.Years;
import org.joda.time.format.DateTimeFormat;
import org.junit.Test;

/**
 * joda日期工具类
 * LocalDate不带时分秒
 */
public class LocalDateTest {
	
	private LocalDate now = LocalDate.now();
	
	private static final String DEFAUL_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	private static final String DATE_FORMAT = "yyyy-MM-dd";
	
	@Test
	public void testFormat()
	{
		System.out.println(now.toString(DEFAUL_FORMAT));
		System.out.println(now.toString(DateTimeFormat.forPattern(DEFAUL_FORMAT)));
	}
	
	@Test
	public void testPlus()
	{
		System.out.println(now.plusYears(1).toString(DEFAUL_FORMAT));
		System.out.println(now.plusMonths(1).toString(DEFAUL_FORMAT));
		System.out.println(now.plusDays(1).toString(DEFAUL_FORMAT));
		
		System.out.println(now.plusYears(-1).toString(DEFAUL_FORMAT));
		System.out.println(now.plusMonths(-1).toString(DEFAUL_FORMAT));
		System.out.println(now.plusDays(-1).toString(DEFAUL_FORMAT));
	}
	
	@Test
	public void testMinus()
	{
		System.out.println(now.minusYears(1).toString(DEFAUL_FORMAT));
		System.out.println(now.minusMonths(1).toString(DEFAUL_FORMAT));
		System.out.println(now.minusDays(1).toString(DEFAUL_FORMAT));
		                            
		System.out.println(now.minusYears(-1).toString(DEFAUL_FORMAT));
		System.out.println(now.minusMonths(-1).toString(DEFAUL_FORMAT));
		System.out.println(now.minusDays(-1).toString(DEFAUL_FORMAT));
	}
	
	@Test
	public void testParse()
	{
		String dateStr = "2016-05-09 17:30:30";
		now = DateTimeFormat.forPattern(DEFAUL_FORMAT).parseLocalDate(dateStr);
		System.out.println(now.toString(DEFAUL_FORMAT));
	}

	/**
	 * 计算日期相差几天用LocalDate，如果用DateTime的话会带时分秒进行计算
	 * eg:2018-08-14 18:00:00和2018-08-15 17:59:59
	 * 用LocalDateL是相差1天，而DateTime是相差0天，因为少1秒
	 */
	@Test
	public void testBetween()
	{
		LocalDate birthDay = new DateTime(1992, 3, 15, 12, 24, 0).toLocalDate();
		
		System.out.println(Years.yearsBetween(now, birthDay).getYears());
		System.out.println(Months.monthsBetween(now, birthDay).getMonths());
		System.out.println(Days.daysBetween(now, birthDay).getDays());
		System.out.println(Hours.hoursBetween(now, birthDay).getHours());
		System.out.println(Minutes.minutesBetween(now, birthDay).getMinutes());
		System.out.println(Seconds.secondsBetween(now, birthDay).getSeconds());

		System.out.println(Years.yearsBetween(new LocalDate(1992, 10, 13), LocalDate.now()).getYears());
		System.out.println(Years.yearsBetween(new LocalDate(1992, 10, 12), LocalDate.now()).getYears());
		System.out.println(Years.yearsBetween(new LocalDate(1992, 10, 14), LocalDate.now()).getYears());
		System.out.println(Period.fieldDifference(new LocalDate(1992, 10, 13), LocalDate.now()).getYears());
		System.out.println(Period.fieldDifference(new LocalDate(1992, 10, 12), LocalDate.now()).getYears());
		System.out.println(Period.fieldDifference(new LocalDate(1992, 10, 14), LocalDate.now()).getYears());
		System.out.println(Period.fieldDifference(new LocalDate(1992, 1, 14), LocalDate.now()).getYears());
		System.out.println(Period.fieldDifference(new LocalDate(1992, 11, 14), LocalDate.now()).getYears());

		System.out.println(Months.monthsBetween(new LocalDate(2017, 10, 14), now).getMonths());
		System.out.println(Months.monthsBetween(now, new LocalDate(2017, 11, 13)).getMonths());
	}

	@Test
	public void testSpecialDate()
	{
		System.out.println("获取月末日期:" + new LocalDate().dayOfMonth().withMaximumValue().toString(DATE_FORMAT));
		
		System.out.println("获得今天是周几:" + new LocalDate().dayOfWeek().getAsString());
		
		System.out.println("90天后那周的周一 的日期:" + new LocalDate().plusDays(90).dayOfWeek().withMinimumValue().toString(DATE_FORMAT));
	}
	
	@Test
	public void testCompare()
	{
		LocalDate start = new LocalDate("1992-03-15");
		LocalDate end = new LocalDate("1992-06-13");
		
		System.out.println(start.isAfter(end));
		System.out.println(start.isBefore(end));
		System.out.println(start.isEqual(end));
		
	}
	
	@Test
	public void testInterval()
	{
		LocalDate start = new LocalDate("1992-03-15");
		LocalDate end = new LocalDate("1992-06-13");
		
		Period period = new Period(start, end, PeriodType.days());
		System.out.println(period.getDays());

	}
}
