package net.bitacademy.java41.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/* 특징
- 필터는 서블릿 컨테이너가 관리한다.
- 필터 객체는 서블릿과 마찬가지로 하나만 생성된다.
- 웹 애플리케이션이 종료될 때까지 유지된다.
- 필터를 정의한 후 어느 요청에 대해 적용할 것인지 설정해야한다.
*/

//@WebFilter("/*")
public class CharSetFilter implements Filter {
	FilterConfig config;
	
	// 필터가 생성될 때 딱 한번 호출된다.
	// 필터가 자신의 일을 수행하는데 필요한 객체를 준비한다.
	// 준비할게 없다면 그냥 내비 둔다.
	@Override
	public void init(FilterConfig config) throws ServletException {
		//config 파라미터는 필터의 설정정보를 조회하는데 사용한다.
		//호출될 때 별도 저장할 필요가 있다.
		this.config = config;
	}
	
	// 매번 필터가 적용될 때 마다 호출된다.
	@Override
	public void doFilter(
			ServletRequest request, ServletResponse response,
			FilterChain next) throws IOException, ServletException {
		
		// 서블릿의 service() 호출전에 해야할 작업이 있다면 doFilter()호출전에 작성한다.
		//System.out.println("CharSetFilter.... before");
		request.setCharacterEncoding("UTF-8");
		
		next.doFilter(request, response);
		
		// 서블릿의 service() 호출 후에 해야할 작업이 있다면 doFilter() 호출 후에 작성한다.
		//System.out.println("CharSetFilter.... after");
		
		
	}
	
	// 웹 애플리케이션이 종료되어, 필터가 삭제되기 전에 호출된다.
	// init()에서 준비한 객체를 해제하는 작업을 수행한다.
	// 해제할게 없다면 그냥 내비둔다.
	@Override
	public void destroy() {}

	

	

}
