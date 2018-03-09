import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;


public class TextMetrics {
	public static final String PUNCTUATION = " !\"',;:.-_?)([]<>*#\n\t\r";//���еı�����

	static String wordlen;
	static String ratio;
	static String wordnum;
	static String sentencelen;
	//�����ֵ������е��ʵ�ƽ������
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
	


	//�����ֵ������е��ʵ�ƽ����Ƶ�����ظ��������ֳ��ܵ�������
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

	//���ؾ����зǿյ��ʵļ�����
	public static int numberOfWords(ArrayList<String> words){
		int num=words.size();
		wordnum=""+num;//"������"
		return num;
}


	//����������ÿһ�仰��ƽ����������
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
