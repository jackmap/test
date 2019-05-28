package com.example.until;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


public class FileUntil {
	 public void copyFile(String src,String dest) throws IOException{
         FileInputStream in=new FileInputStream(src);
         File file=new File(dest);
         if(!file.exists()){
        	 System.out.println("创建目录:"+file);
             file.mkdirs();
         }
         FileOutputStream out=new FileOutputStream(file,true);        
         for(int i=0;i<10000;i++){
             StringBuffer sb=new StringBuffer();
             sb.append("这是第"+i+"行:前面介绍的各种方法都不关用,为什么总是奇怪的问题 ");
             out.write(sb.toString().getBytes("utf-8"));
         }        
         in.close();
         out.close();
     }
	 private static void copyFile1(String srcPathStr, String desPathStr) {
         //1.获取源文件的名称
         String newFileName = srcPathStr.substring(srcPathStr.lastIndexOf("\\")+1); //目标文件地址
         System.out.println(newFileName);
         System.out.println(System.currentTimeMillis());
         desPathStr = desPathStr + File.separator +System.currentTimeMillis()+newFileName; //源文件地址
         System.out.println(desPathStr);
         try{
             //2.创建输入输出流对象
             FileInputStream fis = new FileInputStream(srcPathStr);
             FileOutputStream fos = new FileOutputStream(desPathStr);                

             //创建搬运工具
             byte datas[] = new byte[1024*8];
             //创建长度
             int len = 0;
             //循环读取数据
             while((len = fis.read(datas))!=-1){
                 fos.write(datas,0,len);
             }
             //3.释放资源
             fis.close();
             fis.close();
         }catch (Exception e){
             e.printStackTrace();
         }
     }
	
	public String traverseFolder(String path) {
        File file = new File(path);
        String filepath="";
        if (file.exists()) {
            File[] files = file.listFiles();
            if (null == files || files.length == 0) {
                System.out.println("文件夹是空的!");
                return  "文件夹是空的!";
            } else {
            	 try {
	                for (File file2 : files) {
	                    if (file2.isDirectory()) {
	                        System.out.println("文件夹:" + file2.getAbsolutePath());
	                        traverseFolder(file2.getAbsolutePath());
	                    } else {
	                        System.out.println("文件:" + file2.getAbsolutePath());
	                        filepath=file2.getAbsolutePath();
	                    }
	                }
            	} catch (Exception e) {
					e.printStackTrace();
				}
            }
        } else {
            System.out.println("文件不存在!");
        }
		return filepath;
    }
	 public void changeDirectory(String filename,String oldpath,String newpath,boolean cover){
         if(!oldpath.equals(newpath)){
             File oldfile=new File(oldpath+"/"+filename);
             File newfile=new File(newpath+"/"+filename);
             if(newfile.exists()){//若在待转移目录下，已经存在待转移文件
                 if(cover){//覆盖
                     oldfile.renameTo(newfile);
             }else {
                  System.out.println("在新目录下已经存在："+filename);
                    oldfile.renameTo(newfile);
             }   
          }
         }
     }
	public static void main(String[] args) {
		FileUntil file=new FileUntil();
		String desc="E:\\test4";
		String srcpath=file.traverseFolder("E:\\test\\");
		System.out.println(srcpath);
		file.copyFile1(srcpath,desc);
		String filepath=file.traverseFolder("E:\\test4");
		System.out.println(filepath);
	}
}

