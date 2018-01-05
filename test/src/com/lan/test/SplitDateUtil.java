package com.lan.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSONObject;

public class SplitDateUtil {

	public static void main(String[] args) {
//		List<KeyValueForDate> list = SplitDateUtil.getKeyValueForDate("2016-2-12","2016-12-06");
//        System.out.println("开始日期--------------结束日期");
//        for(KeyValueForDate date : list){
//            try {
//				System.out.println(date.getStartDate()+"-----"+date.getEndDate());
//			} catch (ParseException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//        }
        
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		try {
			Date startDate = simpleDateFormat.parse("2016-3-22 01-04-44");
			Date endDate = simpleDateFormat.parse("2017-2-14 01-23-44");
			List<JSONObject> list = SplitDateUtil.getSplitForDate(startDate, endDate);
			System.out.println("开始日期--------------结束日期");
			for (JSONObject date : list) {
				System.out.println(date.get("startDate")+"-----"+date.get("endDate"));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	}

    /**
     * 根据一段时间区间，按月份拆分成多个时间段
     * @param startDate 开始日期
     * @param endDate  结束日期
     * @return
     */
    @SuppressWarnings("deprecation")
    public static List<KeyValueForDate> getKeyValueForDate(String startDate,String endDate) {
        List<KeyValueForDate> list = null;
        try {
            list = new ArrayList<KeyValueForDate>();

            String firstDay = "";
            String lastDay = "";
            Date d1 = new SimpleDateFormat("yyyy-MM-dd").parse(startDate);// 定义起始日期

            Date d2 = new SimpleDateFormat("yyyy-MM-dd").parse(endDate);// 定义结束日期

            Calendar dd = Calendar.getInstance();// 定义日期实例
            dd.setTime(d1);// 设置日期起始时间
            Calendar cale = Calendar.getInstance();

            Calendar c = Calendar.getInstance();
            c.setTime(d2);

            int startDay = dd.get(Calendar.DAY_OF_MONTH);
            int endDay = c.get(Calendar.DAY_OF_MONTH);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            KeyValueForDate keyValueForDate = null;

            while (dd.getTime().before(d2)) {// 判断是否到结束日期
                keyValueForDate = new KeyValueForDate();
                cale.setTime(dd.getTime());

                if(dd.getTime().equals(d1)){
                	if(dd.get(Calendar.MONTH) == c.get(Calendar.MONTH) && dd.get(Calendar.YEAR) == c.get(Calendar.YEAR)){
                        keyValueForDate.setStartDate(sdf.format(d1));
                        keyValueForDate.setEndDate(sdf.format(d2));

                    }else {
	                    cale.set(Calendar.DAY_OF_MONTH, dd
	                            .getActualMaximum(Calendar.DAY_OF_MONTH));
	                    cale.add(Calendar.DAY_OF_MONTH, 1);
	                    
	                    lastDay = sdf.format(cale.getTime());
	                    keyValueForDate.setStartDate(sdf.format(d1));
	                    keyValueForDate.setEndDate(lastDay);
                    }
                }else if(dd.get(Calendar.MONTH) == c.get(Calendar.MONTH) && dd.get(Calendar.YEAR) == c.get(Calendar.YEAR)){
                    cale.set(Calendar.DAY_OF_MONTH,1);//取第一天
                    firstDay = sdf.format(cale.getTime());

                    keyValueForDate.setStartDate(firstDay);
                    keyValueForDate.setEndDate(sdf.format(d2));

                }else {
                    cale.set(Calendar.DAY_OF_MONTH,1);//取第一天
                    firstDay = sdf.format(cale.getTime());

                    cale.set(Calendar.DAY_OF_MONTH, dd
                            .getActualMaximum(Calendar.DAY_OF_MONTH));
                    cale.add(Calendar.DAY_OF_MONTH, 1);
                    
                    lastDay = sdf.format(cale.getTime());

                    keyValueForDate.setStartDate(firstDay);
                    keyValueForDate.setEndDate(lastDay);

                }
                list.add(keyValueForDate);
                dd.add(Calendar.MONTH, 1);// 进行当前日期月份加1

            }

            if(endDay<startDay){
                keyValueForDate = new KeyValueForDate();

                cale.setTime(d2);
                cale.set(Calendar.DAY_OF_MONTH,1);//取第一天
                firstDay = sdf.format(cale.getTime());

                keyValueForDate.setStartDate(firstDay);
                keyValueForDate.setEndDate(sdf.format(d2));
                list.add(keyValueForDate);
            }
        } catch (ParseException e) {
            return null;
        }

        return list;
    }
    
	public static List<JSONObject> getSplitForDate(Date startDate, Date endDate) {
		List<JSONObject> list = new ArrayList<JSONObject>();
		Date d1 = startDate;// 定义起始日期
		Date d2 = endDate;// 定义结束日期

		Calendar dd = Calendar.getInstance();
		dd.setTime(d1);
		Calendar cale = Calendar.getInstance();
		Calendar c = Calendar.getInstance();
		c.setTime(d2);

		int startDay = dd.get(Calendar.DAY_OF_MONTH);
		int endDay = c.get(Calendar.DAY_OF_MONTH);

		JSONObject keyValueForDate = null;

		while (dd.getTime().before(d2)) {// 判断是否到结束日期
			keyValueForDate = new JSONObject();
			cale.setTime(dd.getTime());

			if (dd.getTime().equals(d1)) {
				if (dd.get(Calendar.MONTH) == c.get(Calendar.MONTH) && dd.get(Calendar.YEAR) == c.get(Calendar.YEAR)) {
					keyValueForDate.put("startDate", d1);
					keyValueForDate.put("endDate", d2);

				} else {
					cale.set(Calendar.DAY_OF_MONTH, dd.getActualMaximum(Calendar.DAY_OF_MONTH));
					cale.set(Calendar.HOUR_OF_DAY, 0);
					cale.set(Calendar.MINUTE, 0);
					cale.set(Calendar.SECOND, 0);
					cale.add(Calendar.DAY_OF_MONTH, 1);// 取下个月第一天

					keyValueForDate.put("startDate", d1);
					keyValueForDate.put("endDate", cale.getTime());

				}
			} else if (dd.get(Calendar.MONTH) == c.get(Calendar.MONTH)
					&& dd.get(Calendar.YEAR) == c.get(Calendar.YEAR)) {
				cale.set(Calendar.DAY_OF_MONTH, 1);// 取第一天
				cale.set(Calendar.HOUR_OF_DAY, 0);
				cale.set(Calendar.MINUTE, 0);
				cale.set(Calendar.SECOND, 0);
				keyValueForDate.put("startDate", cale.getTime());
				keyValueForDate.put("endDate", d2);

			} else {
				cale.set(Calendar.DAY_OF_MONTH, 1);// 取第一天
				cale.set(Calendar.HOUR_OF_DAY, 0);
				cale.set(Calendar.MINUTE, 0);
				cale.set(Calendar.SECOND, 0);

				keyValueForDate.put("startDate", cale.getTime());

				cale.set(Calendar.DAY_OF_MONTH, dd.getActualMaximum(Calendar.DAY_OF_MONTH));
				cale.add(Calendar.DAY_OF_MONTH, 1);// 取下个月第一天

				keyValueForDate.put("endDate", cale.getTime());

			}
			list.add(keyValueForDate);
			dd.add(Calendar.MONTH, 1);// 进行当前日期月份加1

		}

		if (endDay < startDay) {
			keyValueForDate = new JSONObject();

			cale.setTime(d2);
			cale.set(Calendar.DAY_OF_MONTH, 1);// 取第一天
			cale.set(Calendar.HOUR_OF_DAY, 0);
			cale.set(Calendar.MINUTE, 0);
			cale.set(Calendar.SECOND, 0);
			keyValueForDate.put("startDate", cale.getTime());
			keyValueForDate.put("endDate", d2);
			list.add(keyValueForDate);
			// System.out.println(queryMap.getDate("createTimeBegin")+"----------------"+queryMap.getDate("createTimeEnd"));

			// updateSearchBeginAndEndTime(queryMap, simpleDateFormat);
			// findEarliestRealtimeDataByIndexByQueryMap(index, queryMap, realTimeInfos);
			// findLatestRealtimeDataByIndexByQueryMap(index, queryMap, realTimeInfos);
		}
		return list;
	}



}

class KeyValueForDate{
    private String startDate;
    private String endDate;
    public String getStartDate() throws ParseException {
//        return new SimpleDateFormat("yyyy-MM-dd").parse(startDate);
        return startDate;
    }
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    public String getEndDate() throws ParseException {
//    	return new SimpleDateFormat("yyyy-MM-dd").parse(endDate);
    	return endDate;
    }
    public void setEndDate(String endDate) {
        this.endDate = endDate;
        
    }
}

