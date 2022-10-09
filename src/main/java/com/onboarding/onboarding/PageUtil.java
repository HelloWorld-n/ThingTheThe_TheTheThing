package com.onboarding.onboarding;

import java.lang.reflect.Method;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

public class PageUtil {
	public static String fetchAllPages(Class class_, ModelMap model){
		String result = "{";
		for (Method method : class_.getDeclaredMethods()) {
			if (method.isAnnotationPresent(GetMapping.class)) {
				GetMapping Mapping = method.getAnnotation(GetMapping.class);
				String addressString = Mapping.value()[0];
				result += "\"" + addressString + "\": \"get\", ";
			} else if (method.isAnnotationPresent(PostMapping.class)) {
				PostMapping Mapping = method.getAnnotation(PostMapping.class);
				String addressString = Mapping.value()[0];
				result += "\"" + addressString + "\": \"post\", ";
			} else if (method.isAnnotationPresent(PutMapping.class)) {
				PutMapping Mapping = method.getAnnotation(PutMapping.class);
				String addressString = Mapping.value()[0];
				result += "\"" + addressString + "\": \"put\", ";
			} else if (method.isAnnotationPresent(DeleteMapping.class)) {
				DeleteMapping Mapping = method.getAnnotation(DeleteMapping.class);
				String addressString = Mapping.value()[0];
				result += "\"" + addressString + "\": \"delete\", ";
			} else {
			}
		} 
		result += "\"" + class_.getName() + "\": \"teapot#418\"}";
		return result;
	}
}
