/*
 * Copyright (c) 2020
 * KUVEYT TÜRK PARTICIPATION BANK INC.
 *
 * Author: Fikri Aydemir
 *
 * Project: Java SDK for Accessing API Endpoints
 */

package tr.com.kuveytturk.api.client.sdk.model.misc;

import tr.com.kuveytturk.api.client.sdk.model.enums.ScopeType;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The utility class containing the list scopes
 *
 * @author      Fikri Aydemir
 * @version     1.0
 * @since       2020-01-12
 */
public class ScopeListBean implements java.io.Serializable{
    private List<String> backList;

    public ScopeListBean() {
        backList = new ArrayList<String>();
    }

    public ScopeListBean add(ScopeType theScopeType){
        if(!backList.contains(theScopeType.toString())){
            backList.add(theScopeType.toString());
        }
        return this;
    }

    public ScopeListBean remove(ScopeType theScopeType){
        if(backList.contains(theScopeType.toString())){
            backList.remove(theScopeType.toString());
        }
        return this;
    }

    public boolean isEmpty(){
        return backList.isEmpty();
    }

    public ScopeListBean clear(){
        backList.clear();
        return this;
    }

    @Override
    public String toString(){
        String s = backList.stream().map(Object::toString).collect(Collectors.joining(" "));
        return s;
    }
}
