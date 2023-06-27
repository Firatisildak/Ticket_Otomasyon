package com.IsteUcus.Util;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;


public class UcusveritabaniUtil {
	static Connection conn=null;

	public static Connection Baglan() {
		try {
			//"jdbc:mysql://ServerIPAdresi/db_ismi","kullanici","sifre";
	     conn=DriverManager.getConnection("jdbc:mysql://localhost/projemdb","root","");
			return conn;
		}catch (Exception e) {
		System.out.println(e.getMessage().toString());
			return null;
		}
	 }
	public static String MDSifrele(String icerik) {
		try {
			MessageDigest md=MessageDigest.getInstance("MD5");
			//Byte olarak oku
			byte[] sifrelenmis=md.digest(icerik.getBytes());
			
			BigInteger no=new BigInteger(1, sifrelenmis);
			//Hex hesapla
			String hashIcerik=no.toString(16);
			while(hashIcerik.length()<32) {
				hashIcerik="0"+hashIcerik;
			}
			return hashIcerik;
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}
}
