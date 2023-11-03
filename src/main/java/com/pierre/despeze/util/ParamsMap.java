package com.pierre.despeze.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

import com.pierre.despeze.configuration.DataConfiguration;

public class ParamsMap extends HashMap<String, Object>{
	private static final long serialVersionUID = 1L;

	@Override
	public Object put(String key, Object value) {
		
		if ( value != null && value instanceof LocalDate ) {
			String dataFormatada = 
				( (LocalDate) value)
				.format( 
					DateTimeFormatter.ofPattern( DataConfiguration.PADRAO_DATA_BANCO ) 
				);
			
			return super.put(key, dataFormatada);
			
		} else {
			return super.put(key, value);
		}

    }

}