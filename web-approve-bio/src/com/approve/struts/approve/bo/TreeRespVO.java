package com.approve.struts.approve.bo;

import java.util.ArrayList;
import java.util.List;

public class TreeRespVO {
	 private List<Item>  data = new ArrayList<Item>();  
	  
     public List<Item> getData()  
    {  
          return data ;  
    }  
  
     public void setData(List<Item> data )  
    {  
          this .data = data;  
    }  
}
