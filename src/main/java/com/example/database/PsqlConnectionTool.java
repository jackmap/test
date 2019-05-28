package com.example.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;


@Configuration
public class PsqlConnectionTool {
	final static Logger log = LoggerFactory.getLogger(PsqlConnectionTool.class);
	@Value("${spring.datasource.url}")
	private String url="jdbc:postgresql://117.158.175.178:5432/weiku";
	@Value("${spring.datasource.username}")
	private String username="postgres";
	@Value("${spring.datasource.password}")
	private String password="W19k4u21798de";
    private Connection connection = null;
   
    public Connection getConn() {
        try {
            Class.forName("org.postgresql.Driver").newInstance();
            connection = DriverManager.getConnection(url, username, password);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public ResultSet query(Connection conn, String sql) {
        PreparedStatement pStatement = null;
        ResultSet rs = null;
        try {
            pStatement = conn.prepareStatement(sql);
            rs = pStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        return rs;
    }

    public boolean queryUpdate(Connection conn, String sql) {
        PreparedStatement pStatement = null;
        int rs = 0;
        try {
            pStatement = conn.prepareStatement(sql);
            rs = pStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (rs > 0) {
            return true;
        }
        return false;
    }
    
    
	public static void main(String[] args) throws SQLException {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<Orderhead>  list= selectDataByHour();
        Calendar c = Calendar.getInstance();
        c.set(Calendar.MINUTE,0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.YEAR, 2018);
        c.set(Calendar.DATE,20);
        for(int i=8;i<23;i++) {
        c.set(Calendar.HOUR_OF_DAY, i);
        Date starttime=c.getTime();
        System.out.println(sf.format(starttime)+"   "+starttime.getTime());
        c.set(Calendar.HOUR_OF_DAY, i+1);
        Date endtime=c.getTime();
        System.out.println(sf.format(endtime)+"   "+endtime.getTime());
        List<Orderhead>  result =list.stream()
        		.filter(o -> o.getAddtime().getTime() > starttime.getTime() 
        				&&
        				o.getAddtime().getTime() < endtime.getTime()).collect(Collectors.toList());
        System.out.println(result.size());
        }
	}
	
	private static   List<Orderhead>  selectDataByHour() throws SQLException {
		PsqlConnectionTool pgtool = new PsqlConnectionTool();
        Connection myconn = pgtool.getConn();
        String sql="SELECT * FROM orderhead WHERE CAST(addtime AS DATE)=DATE '2018-05-20'; ";
        System.out.println(sql);
        ResultSet rs = pgtool.query(myconn, sql);
        List<Orderhead> list=new ArrayList<Orderhead>();
        while(rs.next()) {
        	Orderhead o=new Orderhead();
        	o.setAddtime(rs.getDate("addtime"));
        	list.add(o);
        }
        System.out.println(list);
		return list;
	}
	
	
	public static   List<Orderhead>  selectDataByYear(int year) throws SQLException {
		PsqlConnectionTool pgtool = new PsqlConnectionTool();
        Connection myconn = pgtool.getConn();
        String sql="SELECT * FROM orderhead WHERE to_char(addtime, 'YYYY')= '"
        		+ year
        		+ "' ";
        System.out.println(sql);
        ResultSet rs = pgtool.query(myconn, sql);
        List<Orderhead> list=new ArrayList<Orderhead>();
        while(rs.next()) {
        	Orderhead o=new Orderhead();
        	o.setAddtime(rs.getDate("addtime"));
        	list.add(o);
        }
        System.out.println(list);
		return list;
	}
}