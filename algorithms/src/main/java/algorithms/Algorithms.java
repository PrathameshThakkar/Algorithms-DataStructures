package algorithms;

import java.util.HashMap;

public class Algorithms {
	public static void main(String[] args) {
		
		String s = " ";
int result = 0;
        
        if(s == null || s.length()==0) System.out.println(result);
        
        HashMap<Character,Integer> m = new HashMap<Character,Integer>();
        
        int left=0,right=0;
        
        while(left<s.length() && right< s.length()){
            
            if(m.containsKey(s.charAt(right)) && left<=m.get(s.charAt(right))){
                result = Math.max(result,right-left);
                left = Math.min(m.get(s.charAt(right))+1, s.length()-1);
                m.put(s.charAt(right),right);
            }else{
                m.put(s.charAt(right),right);
                
            }
            right++;
            
         }
               
        result = Math.max(result,right-left);
        System.out.println(result);   

	}
}