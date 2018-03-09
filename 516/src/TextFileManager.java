import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class TextFileManager {
	
	static ArrayList<String> lines=new ArrayList<String>();
	public static ArrayList<String> readFile(String filename){
		//读取文件内容。
	    
		//File file = new File(url);
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			for(String line =br.readLine();line!=null;line = br.readLine()){
				System.out.println(line);
				lines.add(line);
				
				}
			br.close();
		} 
		catch(IOException e)
		{
		System.err.println("发生异常："+e);
		e.printStackTrace();
		}
		return lines;
		}

	public static void writeFile(String filename, ArrayList<String> text){
		 String[] s1 = {};
		   s1 = new String[text.size()];
		   StringBuffer sb1 = new StringBuffer();
			   for (int i=0;i<text.size();i++) {
				    s1[i] = text.get(i);
				    sb1.append(s1[i]).append("\r\n");}
		  String newpath1 = "out.txt";
		try{
			BufferedWriter bw = new BufferedWriter(new FileWriter(newpath1));
		   bw.write(sb1.toString());
		   bw.close();
		} catch (IOException e) {
			   e.printStackTrace();
		  }
	}
//	public static void main(String[] args)
//	{
//		  new TextFileManager();
//
//		}
//	

}
