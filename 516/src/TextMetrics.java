import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;


public class TextMetrics {
	public static final String PUNCTUATION = " !\"',;:.-_?)([]<>*#\n\t\r";//所有的标点符号

	static String wordlen;
	static String ratio;
	static String wordnum;
	static String sentencelen;
	//返回字典中所有单词的平均长度
	public static double averageWordLength(HashMap<String,Integer> dictionary){
		double ave=0;
		int lengsum=0;
		int wordnum=0;
		
	    Iterator<String> ha = dictionary.keySet().iterator();
	    
	    while(ha.hasNext()) {	    	
	    	String key = (String)ha.next();
	    	wordnum+=dictionary.get(key);
	    	lengsum+=key.length()*dictionary.get(key);
	    }
	    ave=(double)lengsum/(double)wordnum;
		return ave;
	}
	


	//返回字典中所有单词的平均词频（不重复单词数字除总单词数）
	public static double typeTokenRatio(HashMap<String,Integer> dictionary){
		double ratio=0;
		int sum=0;
		int norepeat=0;
		Iterator<String> ha1 = dictionary.keySet().iterator();
		
	    while(ha1.hasNext()) {
	    	norepeat++;
	    	String key = (String)ha1.next();	    	
	    	sum+=dictionary.get(key);
	    }
	    ratio=(double)norepeat/sum;
	    return ratio;
	}

	//返回句子中非空单词的计数。
	public static int numberOfWords(ArrayList<String> words){
		int num=words.size();
		wordnum=""+num;//"单词数"
		return num;
}


	//返回论文中每一句话的平均单词数。
	public static double averageSentenceLength(ArrayList<String> text){
		String[] strArr = text.toArray(new String[]{});
		int wordnum=0;
		int linenum=0;
		double average=0.0;
		for(int i=0;i<strArr.length;i++){
			linenum++;
			String[] substr=strArr[i].split(PUNCTUATION);
			wordnum+=substr.length;
		}
		average=(double)wordnum/(double)linenum;
		return average;
	}

}
