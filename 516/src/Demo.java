import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class Demo extends JFrame {
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		String openFilePath;
		String openFileName;	
		String title = "ERROR MESSAGE";
		int type = JOptionPane.ERROR_MESSAGE;
		
		ArrayList<String> context = new ArrayList<String>();
		public static HashMap<String,Integer> dictionary = new HashMap<String,Integer>();	
				
		public Demo()
		{
			super("�ļ�����");
			Container c=getContentPane();
			c.setLayout(new BoxLayout(c,BoxLayout.Y_AXIS));	
			
			final JPanel panel = new JPanel();	
			panel.setLayout(new GridLayout(1, 1));
			
			final JPanel panel1 = new JPanel();	
			panel1.setLayout(new GridLayout(1, 1));
			
			final JTextArea text = new JTextArea();
			final JTextArea text1 = new JTextArea();	
			final JTextArea text2 = new JTextArea();
			final JTextArea text3 = new JTextArea();
			final JTextArea text4 = new JTextArea();
			final JButton JB1=new JButton("��");
			final JButton JB2=new JButton("����");
			final JButton JB3=new JButton("����");			
			panel.add(new JScrollPane(text));
			panel.add(JB1);
			panel1.add(new JScrollPane(text1));
			panel1.add(new JScrollPane(text2));
			panel1.add(new JScrollPane(text3));
			panel1.add(new JScrollPane(text4));
			panel1.add(JB2);
			panel1.add(JB3);
			c.add(JB1);
	        c.add(panel);
	        c.add(JB2);
	        c.add(panel1);
		     c.add(JB3);		
			
			JB1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JFileChooser openfile = new JFileChooser();
					openfile.setDialogTitle("���ļ�");
					openfile.setApproveButtonText("��");
					openfile.showOpenDialog(panel);
					File filename = openfile.getSelectedFile();
					
					context.addAll(TextFileManager.readFile(filename.getAbsolutePath()));
					
					StringBuffer strBF = new StringBuffer();
					String error_message = "Error";
					FileInputStream inputfile = null;
					try {
						char buffer[] = new char[1024];
						inputfile = new FileInputStream(filename);
						int len = 0;

						FileReader in = new FileReader(filename.getAbsoluteFile());
						while ((len = in.read(buffer)) != -1) {
							strBF.append(buffer, 0, len);
							}

						inputfile.close();
						in.close();

						text.setText(strBF.toString());
						String openfilename = filename.getName();
						setTitle(openfilename); } 
					catch (IOException ioEX) {
							JOptionPane.showMessageDialog(panel, error_message, title,type);
							} }
				});
			
			JB2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e){
					
//					a="�����зǿյ��ʵļ���";
//					for(int i=0;i<k.length;i++)
//					{
//						a+=(" "+k[i]);
//						}
//					  
//						a=("ƽ��������"+TextMetrics.averageSentenceLength(context)+"���е��ʵ�ƽ������:"+TextMetrics.averageWordLength(dictionary)
//								+"���е��ʵ�ƽ����Ƶ:"+TextMetrics.typeTokenRatio(dictionary));
//						text1.setText(a);
					ArrayList<String> sentences=
							SentenceAnalyser.convertFileLinesTosentences(TextFileManager.lines);
					//�����·ָ�ɾ���
					String getlines = null;  
					int i=0;
			        getlines = sentences.get(i);
					ArrayList<String> words = SentenceAnalyser.getWordsFromSentence(getlines);
					// words Ϊ���ӷָ�ɵ���
					for (i = 0; i < sentences.size(); i++)
					{
						getlines = sentences.get(i);
						words.addAll(SentenceAnalyser.getWordsFromSentence(getlines));
					}
					Signature.makeDictionary(words,"dictionary.txt");//����dictionary �� words ����
					//Signature.makeDictionary(context,"dictionary.txt");

					text1.setText("������"+TextMetrics.numberOfWords(words));//������;
					//ƽ�����ʳ���
					text2.setText("���е��ʵ�ƽ������:"+TextMetrics.averageWordLength(dictionary));
					//ƽ����Ƶ
					text3.setText("���е��ʵ�ƽ����Ƶ:"+TextMetrics.typeTokenRatio(dictionary));
					//ÿһ�仰��ƽ��������
					text4.setText("ÿһ�仰��ƽ��������"+TextMetrics.averageSentenceLength(words));
					//TextFileManager.writeFile("/context.txt", context);
				
				}
			});
			
			JB3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e){
					Signature.makeDictionary(context,"dictionary.txt");
					TextFileManager.writeFile("/context.txt", context);
				}
			});
	
		}

	
	public static void main(String[] args){
		Demo app=new Demo();
		app.setSize(640,500);
		app.setVisible(true);
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		}

}
