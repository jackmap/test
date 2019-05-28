package com.example.database;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class SelectDataByYear {
	
	public static void main(String[] args) throws SQLException {
	        
		List<Integer>  result =new ArrayList<Integer>();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        int year=2018;
        Date start=new Date();
	    System.out.println(sf.format(start));
	     
        List<Orderhead>  list= PsqlConnectionTool.selectDataByYear(year);
        System.out.println(list.size());
        Calendar c = Calendar.getInstance();
        c.set(Calendar.MINUTE,0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.DAY_OF_MONTH,0);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.YEAR, year);
        for(int i=1;i<12;i++) {
        c.set(Calendar.MONTH, i);
        Date starttime=c.getTime();
        System.out.println(sf.format(starttime)+"   "+starttime.getTime());
        c.set(Calendar.MONTH, i+1);
        Date endtime=c.getTime();
        System.out.println(sf.format(endtime)+"   "+endtime.getTime());
        List<Orderhead>  p =list.stream()
        		.filter(o -> o.getAddtime().getTime() > starttime.getTime() 
        				&&
        				o.getAddtime().getTime() < endtime.getTime()).collect(Collectors.toList());
        result.add(p.size());
        System.out.println(p.size());
        }
        System.out.println(result);
        Date end=new Date();
        System.out.println(sf.format(end));
        System.out.println(end.getTime()-start.getTime());
	}
	
}
