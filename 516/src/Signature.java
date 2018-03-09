import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;


public class Signature {
	public static final String PUNCTUATION = "[ !\"',;:.-_?)(<>*#\n\t\r]"; //所有的标点符号
	
	public static void makeDictionary(ArrayList<String> sentencetext,String fileName){
		
		String[] strArr = sentencetext.toArray(new String[]{});
		for(int i=0;i<strArr.length;i++){			
			String[] substr=strArr[i].split(PUNCTUATION);
			for(int j=0;j<substr.length;j++){
				boolean exist = true;
				Iterator<String> it = Demo.dictionary.keySet().iterator();
			    while(it.hasNext()) {
			    	String key = (String)it.next();
			    	if(substr[j].equals(key)){
			    		exist = false;
			    		Demo.dictionary.put(key,(Demo.dictionary.get(key)+1));
			    	}
			    	  
			    }
			    if(exist){
			    	Demo.dictionary.put(substr[j],1);
			    }
			}
		}
		StringBuffer buf = new StringBuffer();
		BufferedWriter bw = null;  
		Iterator<String> it = Demo.dictionary.keySet().iterator();
	    while(it.hasNext()) {
	    	String key = (String)it.next();
	    	buf.append(key+","+Demo.dictionary.get(key));  
		 	buf.append(System.getProperty("line.separator"));	    		    	  
	    }
	    try {  
			bw = new BufferedWriter(new FileWriter(fileName));  
			bw.write(buf.toString());  
		} catch (Exception e) {  
	    e.printStackTrace();  
		} finally {  
	    if (bw != null) {  
				try {  
			    bw.close();  
				} catch (IOException e2) {  
			    bw = null;  
				}  
	 		}  
		}			
		}
		public HashMap<String,Integer> readDictionary(String fileName)	{
			HashMap<String,Integer> dictionary = new HashMap<String,Integer>();
			BufferedReader br = null;  
			String line = null;   			
			try {  
		    br = new BufferedReader(new FileReader(fileName));  		      
		    while ((line = br.readLine()) != null) {  
		    	String[] subline = line.split(",");
		    	dictionary.put(subline[0], Integer.parseInt(subline[1]));
		    } 
			} catch (Exception e1) {  
		    	e1.printStackTrace();  
				} finally {  
		    		if (br != null) {  
						try {  
				    	br.close();
						} catch (IOException e1) {  
				    	br = null;  
						}  
		    		}  
			}
			return dictionary;
		}


	}

