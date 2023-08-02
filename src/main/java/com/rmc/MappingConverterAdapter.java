package com.rmc;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;

@Configuration
public class MappingConverterAdapter {
	
	@Bean
	public Converter<String, LocalDateTime> localDateTimeConvert() {
	    return new Converter<String, LocalDateTime>() {
	        public LocalDateTime convert(String source) {
	            DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	            if (source.endsWith("AM") || source.endsWith("PM")) {
	            	df = DateTimeFormatter.ofPattern("MM/dd/yyyy h:mm a");
	            }
	            
	            LocalDateTime dateTime = null;
	            try {
	                //2020-01-01 00:00:00
	                switch (source.length()){
	                    case 10:
//	                        logger.debug("传过来的是日期格式：{}",source);
	                        source=source+" 00:00:00";
	                        break;
	                    case 13:
//	                        logger.debug("传过来的是日期 小时格式：{}",source);
	                        source=source+":00:00";
	                        break;
	                    case 16:
//	                        logger.debug("传过来的是日期 小时:分钟格式：{}",source);
	                        source=source+":00";
	                        break;
	                }
	                dateTime = LocalDateTime.parse(source, df);
	            } catch (Exception e) {
//	               logger.error(e.getMessage(),e);
	            }
	            return dateTime;
	        }
	    };
	}
}