package net.bitacademy.java41.controls;

import java.util.Map;

public interface PageControl {
	String execute(Map<String,Object> model) throws Exception;
}
