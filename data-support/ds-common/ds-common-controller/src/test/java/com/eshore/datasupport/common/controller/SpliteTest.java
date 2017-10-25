package com.eshore.datasupport.common.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class SpliteTest {

	@Test
	public void main(){
		String str = "wordgoodgoodgoodbestword";
		String check = "best";
//		System.out.println(fillTheBill(str,check));
//		char a = 'b';
//		char g = 'g';
//		System.out.println(a<g);
//		System.out.println(smallThenRet("best","good"));
		List<String> list = new ArrayList<String>();
		list.add("word");
		list.add("good");
		list.add("best");
		list.add("good");
		findLongestWord("wordgoodgoodgoodbestword",list);
	}
    public String findLongestWord(String s, List<String> d) {
        String ret = "";
        for(String str:d){
            if(fillTheBill(s,str)){
                if(str.length()>ret.length()){
                    ret=str;
                }else if(str.length()==ret.length()){
                    if(smallThenRet(str,ret)){
                        ret=str;
                    }
                }
            }
        }
        return ret;
    }
    
    private boolean smallThenRet(String compareStr,String retStr){
        for(int i=0;i<retStr.length();i++){
            if(compareStr.charAt(i)<retStr.charAt(i)){
                return true;
            }
        }
        return false;
    }
    
    private boolean fillTheBill(String baseStr,String checkStr){
        if(checkStr.length()>baseStr.length()){
            return false;
        }
        int position=0;
        for(int i=0;i<checkStr.length();i++){
            int positionRet = containChar(checkStr.charAt(i),baseStr,position);
            if(position==positionRet){
                return false;
            }
            position = positionRet;
        }
        return true;
    }
    
    private int containChar(char ch,String str,int start){
       if(str.indexOf(ch,start)!=-1){
           start=str.indexOf(ch,start)+1;
           return start;
       }
        return start;
    }
}
