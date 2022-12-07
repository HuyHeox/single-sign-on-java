package com.ks.sso.exception.common;



import com.google.common.collect.Maps;

import java.util.Collections;
import java.util.LinkedHashMap;

public class BusinessException extends ServiceException {

	private static final long serialVersionUID = -8595251514481960326L;

	protected BusinessException(ServiceError err, Throwable ex, LinkedHashMap<String, Object> params) {
        super(err, ex, params);
    }

    protected static LinkedHashMap<String, Object> buildSingleParamMaps(String key, String value) {
        return Maps.newLinkedHashMap(Collections.singletonMap(key, value));
    }
}