package com.hurricane.app.cartoon.pojo;

import java.util.List;

public class Element {
    private String name;  
    private String url;  
    //这里List中的泛型使用了？，可以匹配data不是Result的情况  
    private List<?> data;
}
