package com.bookstore.userportal.utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class USConstants {
	
	public final static String US = "US";
	
	public final static Map<String, String> mapOfUSStates = new HashMap<String, String>() {
		{
			put("Hà Nội", "Hà Nội");
			put("Tp. HCM", "TP. HCM");
			put("Hải Phòng", "Hải Phòng");
            put("Đã Nẵng", "Đà Nẵng");
            put("Nam Định", "Nam Đinh");
            put("Hà Nam", "Hà Nam");
            put("Ninh Bình", "Ninh Bình");
            put("Quảng Ninh", "Quảng Ninh");
		}
	};
	
	public final static List<String> listOfUSStatesCode = new ArrayList<>(mapOfUSStates.keySet());
	public final static List<String> listOfUSStatesName = new ArrayList<>(mapOfUSStates.values());

}
