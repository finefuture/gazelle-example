package org.gra4j.gazelleExample.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.Entity;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class ClassCache {
	/**
	 * table 对象的 class 缓存
	 */
	private static final Map<String, List<Field>> modelFieldMap = new HashMap<>();
	
	private static final Map<String, Class<?>> modelEntityMap = new HashMap<>();

	private static final Map<String, Map<String, Field>> modelSqlFieldMap = new HashMap<>();

	private static final Map<String, List<Field>> modelJsonbField = new HashMap<>();

	public static void init(){
		log.info("init class size--------------------"+ ClassCache.modelEntityMap.size());
		log.info("init field size--------------------"+ ClassCache.modelFieldMap.size());
	}
	public static void setValueByClass(Class clz){
		AnnotationScanUtils.newFillClassField(clz, modelFieldMap, modelJsonbField, Entity.class);
		AnnotationScanUtils.newFillClass(clz, modelEntityMap, Entity.class);
	}
	
	/**根据table名获取对应的class*/
	public static Class<?> getClassByClassName(String name) {
		return modelEntityMap.get(StringUtils.lowerCase(name));
	}
	/** 查询出hql对应的field类型 */
	public static Field getClassFieldByClassName(String name, int index) {
		return modelFieldMap.get(StringUtils.lowerCase(name)).get(index - 1);
	}

	/** 查询出sql对应的field类型 */
	public static Field getClassFieldByClassName(String name, String sqlField) {
		sqlField = sqlField.replaceAll("_", "");
		if (modelSqlFieldMap.get(StringUtils.lowerCase(name)) != null) {
		} else {
			if (modelFieldMap.get(StringUtils.lowerCase(name)) != null) {
				List<Field> list = modelFieldMap.get(StringUtils.lowerCase(name));
				Map<String, Field> map = new HashMap<>();
				for (Field field : list) {
					map.put(StringUtils.lowerCase(field.getName()), field);
				}
				modelSqlFieldMap.put(StringUtils.lowerCase(name), map);
			} else {
				return null;
			}
		}

		return modelSqlFieldMap.get(StringUtils.lowerCase(name)).get(StringUtils.lowerCase(sqlField));
	}

	public static List<Field> getClassFieldName (String className) {
		return modelFieldMap.get(className);
	}

	public static List<Field> getJsonbField (String className) {
		return modelJsonbField.get(className);
	}
}
