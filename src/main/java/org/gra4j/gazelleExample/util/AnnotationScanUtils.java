package org.gra4j.gazelleExample.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.Type;

import javax.persistence.Transient;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.*;

@Slf4j
public class AnnotationScanUtils {

	private static AnnotationScanUtils instance = new AnnotationScanUtils();

	private AnnotationScanUtils() {
	}

	public static void newFillClass(Class clz, Map<String, Class<?>> classes,
			Class<? extends Annotation> annotation) {
		try {
			if (annotation == null || clz.getAnnotation(annotation) != null) {
				String table = clz.getSimpleName();
				String key = StringUtils.lowerCase(table);
				
				if (classes.containsKey(key)) {
				}
				
				classes.put(key, clz);
			}
		} catch (Exception e) {
			log.error("class is error,className:"+clz.getName(),e);
		}
	}

	public static void newFillClassField(Class clz, Map<String, List<Field>> classes,
			 Map<String, List<Field>> jsonbField, Class<? extends Annotation> annotation) {
		try {
			if (annotation == null || clz.getAnnotation(annotation) != null) {
				String table = clz.getSimpleName();
				String key = StringUtils.lowerCase(table);
				
				if (classes.containsKey(key)) {
				}
				
				
				Field[] fields = clz.getDeclaredFields();
				List<Field> excludefields = Arrays.asList(clz.getFields());
				Field[] superFields = clz.getSuperclass().getDeclaredFields();
				List<Field> excludeSuperFields = Arrays.asList(clz.getSuperclass().getFields());
				List<String> fieldList = new ArrayList<>();
				List<Field> jsonb = new ArrayList<>();
				List<String> superFieldList = new ArrayList<>();

				toField(fields, excludefields, jsonb, fieldList);
                toField(superFields, excludeSuperFields, jsonb, superFieldList);
                jsonbField.put(key,jsonb);

				Collections.sort(fieldList);
				Collections.sort(superFieldList);
				superFieldList.addAll(fieldList);
				List<Field> fieldsList = new ArrayList<>();

                addField(clz, "id", fieldsList);
				for (String fieldName : superFieldList) {
					addField(clz, fieldName, fieldsList);
				}
				classes.put(key, fieldsList);
			}
		} catch (Exception e) {
			log.error("class is error,className:"+clz.getName(),e);
		}
	}

	/**
	 * 获取annotaionaScanUtil 对象
	 *
	 * @return AnnotationScanUtil
	 */
	public static AnnotationScanUtils getInstance() {
		return instance;
	}

	private static void toField (Field[] fields, List<Field> excludeFields,
                          List<Field> jsonb, List<String> fieldName) {
        for (Field field : fields) {
            Transient ts = field.getAnnotation(Transient.class);
            if (field.getName().equals("id") || excludeFields.contains(field) || null!=ts) {
                continue;
            }
            Type type = field.getAnnotation(Type.class);
            if (null!=type)
                jsonb.add(field);
            fieldName.add(field.getName());
        }
    }

    private static void addField (Class clz, String fieldName, List<Field> fieldsList) {
        Field field = null;
        try {
            field = clz.getDeclaredField(fieldName);
        } catch (Exception e) {
            try {
                field = clz.getSuperclass().getDeclaredField(fieldName);
            } catch (NoSuchFieldException e1) {
                e1.printStackTrace();
                e.printStackTrace();
            }
        }
        fieldsList.add(field);
    }

}
