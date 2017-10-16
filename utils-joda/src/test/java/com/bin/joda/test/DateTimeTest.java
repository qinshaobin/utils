package com.bin.joda.test;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Duration;
import org.joda.time.Hours;
import org.joda.time.Interval;
import org.joda.time.Minutes;
import org.joda.time.Months;
import org.joda.time.Period;
import org.joda.time.PeriodType;
import org.joda.time.Seconds;
import org.joda.time.Years;
import org.joda.time.format.DateTimeFormat;
import org.junit.Test;

/**
 * joda时间工具类
 *
 */
public class DateTimeTest {
	
	private DateTime now = DateTime.now();
	
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
		System.out.println(now.plusHours(1).toString(DEFAUL_FORMAT));
		System.out.println(now.plusMinutes(1).toString(DEFAUL_FORMAT));
		System.out.println(now.plusSeconds(1).toString(DEFAUL_FORMAT));
		
		System.out.println(now.plusYears(-1).toString(DEFAUL_FORMAT));
		System.out.println(now.plusMonths(-1).toString(DEFAUL_FORMAT));
		System.out.println(now.plusDays(-1).toString(DEFAUL_FORMAT));
		System.out.println(now.plusHours(-1).toString(DEFAUL_FORMAT));
		System.out.println(now.plusMinutes(-1).toString(DEFAUL_FORMAT));
		System.out.println(now.plusSeconds(-1).toString(DEFAUL_FORMAT));
	}
	
	@Test
	public void testMinus()
	{
		System.out.println(now.minusYears(1).toString(DEFAUL_FORMAT));
		System.out.println(now.minusMonths(1).toString(DEFAUL_FORMAT));
		System.out.println(now.minusDays(1).toString(DEFAUL_FORMAT));
		System.out.println(now.minusHours(1).toString(DEFAUL_FORMAT));
		System.out.println(now.minusMinutes(1).toString(DEFAUL_FORMAT));
		System.out.println(now.minusSeconds(1).toString(DEFAUL_FORMAT));
		                            
		System.out.println(now.minusYears(-1).toString(DEFAUL_FORMAT));
		System.out.println(now.minusMonths(-1).toString(DEFAUL_FORMAT));
		System.out.println(now.minusDays(-1).toString(DEFAUL_FORMAT));
		System.out.println(now.minusHours(-1).toString(DEFAUL_FORMAT));
		System.out.println(now.minusMinutes(-1).toString(DEFAUL_FORMAT));
		System.out.println(now.minusSeconds(-1).toString(DEFAUL_FORMAT));
	}
	
	@Test
	public void testParse()
	{
		String dateStr = "2016-05-09 17:30:30";
		now = DateTimeFormat.forPattern(DEFAUL_FORMAT).parseDateTime(dateStr);
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
		DateTime bitthDay = new DateTime(1992, 3, 15, 12, 24, 0);
		
		System.out.println(Years.yearsBetween(now, bitthDay).getYears());
		System.out.println(Months.monthsBetween(now, bitthDay).getMonths());
		System.out.println(Days.daysBetween(now, bitthDay).getDays());
		System.out.println(Hours.hoursBetween(now, bitthDay).getHours());
		System.out.println(Minutes.minutesBetween(now, bitthDay).getMinutes());
		System.out.println(Seconds.secondsBetween(now, bitthDay).getSeconds());
	}

	@Test
	public void testSpecialDate()
	{
		System.out.println("获取月末日期:" + new DateTime().dayOfMonth().withMaximumValue().toString(DATE_FORMAT));
		
		System.out.println("获得今天是周几:" + new DateTime().dayOfWeek().getAsString());
		
		System.out.println("获取今天的结束时间:" + new DateTime().millisOfDay().withMaximumValue().toString(DEFAUL_FORMAT));
		
		System.out.println("90天后那周的周一 的日期:" + new DateTime().plusDays(90).dayOfWeek().withMinimumValue().toString(DATE_FORMAT));
	}
	
	@Test
	public void testCompare()
	{
		DateTime start = new DateTime("1992-03-15");
		DateTime end = new DateTime("1992-06-13");
		
		System.out.println(start.isAfter(end));
		System.out.println(start.isBefore(end));
		System.out.println(start.isEqual(end));
		
		System.out.println(start.isAfterNow());
		System.out.println(start.isBeforeNow());
		System.out.println(start.isEqualNow());
		
	}
	
	@Test
	public void testInterval()
	{
		DateTime start = new DateTime("1992-03-15");
		DateTime end = new DateTime("1992-06-13");
		
		Duration duration = new Duration(start, end);
		System.out.println(duration.getMillis());
		System.out.println(duration.getStandardDays());
		
		Period period = new Period(start, end, PeriodType.days());
		System.out.println(period.getDays());
		
		Interval interval = new Interval(start, end);
		System.out.println(interval.contains(new DateTime("1992-03-24")));
		System.out.println(interval.contains(new DateTime("1992-06-14")));
	}
}
