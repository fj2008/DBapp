package com.korea.dbapp.util;

public class Script {
	
	//오버로딩
	public static String href(String uri,String msg) {
		StringBuilder sb = new StringBuilder();
		
		sb.append("<script>");
		sb.append("alert('"+msg+"');");
		sb.append("location.href='"+uri+"';'' ");
		sb.append("</script>");
		return sb.toString();
	}
	
	
	public static String href(String uri) {
		StringBuilder sb = new StringBuilder();
		
		sb.append("<script>");
		sb.append("location.href='"+uri+"';'' ");//제사용 할 수 있게 변수로 uri를 받아서 사용할 수 있도록한다
		sb.append("</script>");
		return sb.toString();
	}
	
	
	public static String back(String msg) {
		StringBuilder sb = new StringBuilder();
		sb.append("<script>");
		sb.append("alert('"+msg+"');");
		sb.append("history.back();");//js의 객체를 사용해서 뒤로 돌린다  
		sb.append("</script>");
		return sb.toString();
	}
}
