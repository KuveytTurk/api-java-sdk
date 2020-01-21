/*
 * Copyright (c) 2020
 * KUVEYT TÜRK PARTICIPATION BANK INC.
 *
 * Author: Fikri Aydemir
 *
 * Project: Java SDK for Accessing API Endpoints
 */

package tr.com.kuveytturk.api.client.sdk.model.misc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * The utility class containing the list of query parameters
 *
 * @author      Fikri Aydemir
 * @version     1.0
 * @since       2020-01-12
 */
public class QueryParameterListBean {
    private TreeMap<String, Object> backMap;

    public QueryParameterListBean() {
        this.backMap = new TreeMap<>();
    }

    public QueryParameterListBean add(QueryParameterBean bean) {
        if (bean != null) {
            backMap.put(bean.getParamName(), bean.getParamValue());
        }
        return this;
    }

    public QueryParameterListBean remove(String key) {
        backMap.remove(key);
        return this;
    }

    public QueryParameterListBean clear() {
        backMap.clear();
        return this;
    }

    public boolean isEmpty(){
        return backMap.isEmpty();
    }

    public Map<String, Object> toMap(){
        return backMap;
    }

    public List<QueryParameterBean> toList(){
        List<QueryParameterBean> theList = new ArrayList<>();
        backMap.forEach( (k,v)-> {
            theList.add(new QueryParameterBean(k, String.valueOf(v)));
        });
        return theList;
    }

}
