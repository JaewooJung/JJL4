package net.bitacademy.java41.test;

import java.util.ArrayList;

public class WrapperTest01 {

	public static void main(String[] args) {
		int i = 20;
		String s = "30";
		
		int k = Integer.parseInt(s);
		
		/* Wrapper Class
		 * - 기본 데이터형의 값을 좀 더 잘 다룰 수 있는 메서드를 보유.
		 * - 기본 데이터형 당 하나의 랩퍼클래스가 존재
		 * - int <--> Integer
		 * - byte <--> Byte
		 * - short <--> Short
		 * - long <--> Long
		 * - char <--> Character
		 * - float <--> Float
		 * - double <--> Double
		 * - boolean <--> Boolean
		 * - 사용하는 이유?
		 * 	. Collection 객체에 보관할 수 있다.
		 *  . ArrayList list = new ArraryList();
		 *    list.add(객체주소); // 값을 저장할 수 없다.
		 *  . 기본 데이터를 객체 처럼 다루고자 하는 경우를 위해서.
		 *  . 좀 더 다양한 방식으로 다룰 수 있도록 도움을 주기 위해.
		 */
		
		int x1 = 10;
		Integer obj1 = new Integer(10);
		
		int x2 = x1;
		
		// Object -> 값으로 변환: auto-unboxing
		int x3 = obj1.intValue(); // 예전 방식
		int x4 = obj1; // auto-unboxing이 지원됨.
		
		// int -> Object 객체로 변환: auto-boxing
		Integer obj2 = new Integer(50); // 예전방식
		Integer obj3 = 50;
		
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(20); // 20 -> new Integer(20) : auto-boxing
		
	}

}









