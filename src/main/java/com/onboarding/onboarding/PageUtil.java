package com.onboarding.onboarding;

import com.onboarding.onboarding.annotation.DotMapping;
import com.onboarding.onboarding.annotation.IgnoreMapping;

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
			if (method.isAnnotationPresent(IgnoreMapping.class)){
				continue;
			}

			String resultSegment;
			if (method.isAnnotationPresent(GetMapping.class)) {
				GetMapping mapping = method.getAnnotation(GetMapping.class);
				String addressString = mapping.value()[0];
				resultSegment = "\"" + addressString + "\": \"get\", ";
			} else if (method.isAnnotationPresent(PostMapping.class)) {
				PostMapping mapping = method.getAnnotation(PostMapping.class);
				String addressString = mapping.value()[0];
				resultSegment = "\"" + addressString + "\": \"post\", ";
			} else if (method.isAnnotationPresent(PutMapping.class)) {
				PutMapping mapping = method.getAnnotation(PutMapping.class);
				String addressString = mapping.value()[0];
				resultSegment = "\"" + addressString + "\": \"put\", ";
			} else if (method.isAnnotationPresent(DeleteMapping.class)) {
				DeleteMapping mapping = method.getAnnotation(DeleteMapping.class);
				String addressString = mapping.value()[0];
				resultSegment = "\"" + addressString + "\": \"delete\", ";
			} else {
				resultSegment = "";
			}

			if (method.isAnnotationPresent(DotMapping.class)){
				DotMapping mapping = method.getAnnotation(DotMapping.class);
				resultSegment = resultSegment.replaceAll("'", "." + mapping.extension());
			}

			result += resultSegment;
		} 
		result += "\"" + class_.getName() + "\": \"teapot#418\"}";
		return result;
	}
}
