import java.util.ArrayList;


public class SentenceAnalyser {
		String[] substr;
		public static final String SENTENCE_SEPARATORS = "[.?!]"; //  句子分割符
		public static final String PHRASE_SEPARATORS = "[,;:]";    // 短语分割符
		public static final String WORD_SEPARATORS = "[ .?!,;:]";  // 单词分割符
		
		public static final String PUNCTUATION = " !\"',;:.-_?)([]<>*#\n\t\r";//所有的标点符号

		public static ArrayList<String> getWordsFromSentence(String sentence)
		{
			String[] substr=sentence.split(WORD_SEPARATORS); 
		
			ArrayList<String>arrList=new ArrayList<String>();
			for(int i=0;i<substr.length;i++)
			{
				if(substr[i].hashCode()!=0 &&substr[i].hashCode()!=45)
					arrList.add(cleanWord(substr[i].toLowerCase()));
				
			}
			return arrList;
		}

//		public static void main(String[] args)
//		{
//			ArrayList<String> filelines=new ArrayList<String>();
////			filelines.add("This is the very,very,");
////			filelines.add("fist sentence. Isn't");
////			filelines.add("it?Yes!!! This");
////			filelines.add("");
////			filelines.add("");
////			filelines.add("last bit  is also a sentence ;but");
////			filelines.add("without a terminatat: other than the end of the file");
	//	
//			ArrayList<String> sentences=
//					sentence.convertFileLinesTosentences(filelines);
//				
//			for(String str1:sentences)
//			{
//				ArrayList<String> getWords=getWordsFromSentence(str1);
//				for(String str2:getWords)
//					System.out.println(str2);		
//			}
//			
//		}
		static ArrayList<String> convertFileLinesTosentences(ArrayList<String> filelines) 
		{
			ArrayList<String> newArrList=new ArrayList<String>();
			String strTemp="";
			char c1;
			for(int i=0;i<filelines.size();i++)
			{
				if(filelines.get(i).length()>0)
				{
					c1=filelines.get(i).charAt(filelines.get(i).length()-1);
					if(c1==' '||c1=='?'||c1=='.'||c1=='!')
					{
						strTemp=strTemp.concat(filelines.get(i));
						String[] substr=strTemp.split(SENTENCE_SEPARATORS);
						if(substr.length>1)
						{
							int j;
							for(j=0;j<substr.length-1;j++)
							{
								if(substr[j].hashCode()!=0&&substr[j].hashCode()!=32)
									newArrList.add(substr[j].trim());
								
							}
							strTemp=substr[j];
						}
					}
					else
					{
						strTemp=strTemp.concat(filelines.get(i)+" ");
						String[] substr=strTemp.split(SENTENCE_SEPARATORS);
						if(substr.length>1)
						{
							int j;
							for(j=0;j<substr.length-1;j++)
							{
								if(substr[j].hashCode()!=0 && substr[j].hashCode()!=32)
									newArrList.add(substr[j].trim());
								
							}
							strTemp=substr[j];
						}
					}
				}
			}

			if(strTemp.hashCode()!=0 && strTemp.hashCode()!=32)
				newArrList.add(strTemp.trim());
			return newArrList;
		}

		private static String cleanWord(String word) 
		{
			char[] cs= word.toCharArray();
			char[] dest=new char[cs.length];
			int index=0;
			for(char c:cs)
			{
				if((c<='Z'&&c>='A')||(c<='z'&&c>='a')||(c=='-')||(c=='\''))
				{
					dest[index ++]=c;
				}
			}
			return new String (dest,0,index).toLowerCase().trim();
			
		}
	}


