package calendar;

import java.awt.Font;
import java.awt.event.MouseListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.*;

public class EditFrame extends JFrame{
	private int year;
	private int month;
	private int date;
	private JPanel panel;
	private JLabel dateLabel;
	private JLabel title;
	private JTextField titleText;
	private JLabel start;
	private NumberField startTextH;
	private NumberField startTextM;
	private JLabel end;
	private String[] optionsToChoose = {"Blue", "Red", "Green", "Puple", "Pink"};	// 설정할 수 있는 색상을 저장하는 배열
	private String[] colorArray = {"4C92B1","C8707E","5AA08D","AC99C1","E28FAD"};
	private JRadioButton alramOn;
	private JRadioButton alramOff;
	private JLabel alram;
	private int alramInt = 10;
	private JButton editButton;
	private JButton cancelButton;
	private JLabel startH;
	private JLabel startM;
	private NumberField endTextH;
	private JLabel endH;
	private NumberField endTextM;
	private JLabel endM;
	private JButton startHUp;
	private JButton startHDown;
	private JButton startMUp;
	private JButton startMDown;
	private Font font;
	private JButton endHUp;
	private JButton endHDown;
	private JButton endMUp;
	private JButton endMDown;
	private JButton pressed;	// 클릭된 버튼의 정보 저장할 변수
	private MyThread thread = new MyThread();	// 쓰레드
	private boolean tFlag=false;	// thread의 실행 상태 의미
	private JComboBox<String> jComboBox;
	private int edit;
	private String fileName;
	
	@SuppressWarnings("removal")
	EditFrame(int year, int month, int date, int edit) {	// edit: 0 -> 일정 생성을 의미 else 일정 수정을 의미
		fileName = "./" + Integer.toString(year*10000+month*100+date);
		this.edit = edit;
		thread.start();
		thread.suspend();
		this.year = year;
		this.month = month;
		this.date = date;
		setTitle("Priority Calendar");
		setSize(290,340);
		setVisible(true);
		this.getContentPane();
		panel = new JPanel();
		panel.setLayout(null);
		dateLabel = new JLabel('<'+Integer.toString(year*10000+month*100+date)+'>');
		font = new Font("고딕", Font.BOLD, 15);
		dateLabel.setFont(font);
		dateLabel.setBounds(15, 5, 100, 20);
		panel.add(dateLabel);
		title = new JLabel("title");
		titleText = new JTextField();
		title.setFont(font);
		title.setBounds(15, 35, 100, 20);
		titleText.setBounds(65, 40, 100, 20);
		panel.add(title);
		panel.add(titleText);
		start = new JLabel("start");
		start.setFont(font);
		startTextH = new NumberField("12");
		startTextH.setHorizontalAlignment(NumberField.CENTER);
		startH = new JLabel("h");
		startH.setFont(font);
		startHUp = new JButton("▲");
		startHUp.addMouseListener(new MyMouseListener());
		startHDown = new JButton("▼");
		startHDown.addMouseListener(new MyMouseListener());
		startTextM = new NumberField("0");
		startTextM.setHorizontalAlignment(NumberField.CENTER);
		startM = new JLabel("m");
		startM.setFont(font);
		startMUp = new JButton("▲");
		startMUp.addMouseListener(new MyMouseListener());
		startMDown = new JButton("▼");
		startMDown.addMouseListener(new MyMouseListener());
		start.setBounds(15, 90, 100, 20);
		startTextH.setBounds(65, 90, 46, 20);
		startH.setBounds(115, 90, 30, 20);
		startHUp.setBounds(65, 70, 45, 20);
		startHDown.setBounds(65, 108, 45, 20);
		startTextM.setBounds(145, 90, 46, 20);
		startM.setBounds(195, 90, 30, 20);
		startMUp.setBounds(145, 70, 45, 20);
		startMDown.setBounds(145, 108, 45, 20);
		panel.add(start);
		panel.add(startTextH);
		panel.add(startH);
		panel.add(startHUp);
		panel.add(startHDown);
		panel.add(startTextM);
		panel.add(startM);
		panel.add(startMUp);
		panel.add(startMDown);
		end = new JLabel("end");
		end.setFont(font);
		endTextH = new NumberField("12");
		endTextH.setHorizontalAlignment(NumberField.CENTER);
		endH = new JLabel("h");
		endH.setFont(font);
		endHUp = new JButton("▲");
		endHUp.addMouseListener(new MyMouseListener());
		endHDown = new JButton("▼");
		endHDown.addMouseListener(new MyMouseListener());
		endTextM = new NumberField("0");
		endTextM.setHorizontalAlignment(NumberField.CENTER);
		endM = new JLabel("m");
		endM.setFont(font);
		endMUp = new JButton("▲");
		endMUp.addMouseListener(new MyMouseListener());
		endMDown = new JButton("▼");
		endMDown.addMouseListener(new MyMouseListener());
		end.setBounds(15, 160, 100, 20);
		endTextH.setBounds(65, 160, 46, 20);
		endTextH.setHorizontalAlignment(NumberField.CENTER);
		endH.setBounds(115, 160, 30, 20);
		endHUp.setBounds(65, 140, 45, 20);
		endHDown.setBounds(65, 178, 45, 20);
		endTextM.setBounds(145, 160, 46, 20);
		endTextM.setHorizontalAlignment(NumberField.CENTER);
		endM.setBounds(195, 160, 30, 20);
		endMUp.setBounds(145, 140, 45, 20);
		endMDown.setBounds(145, 178, 45, 20);
		panel.add(end);
		panel.add(endTextH);
		panel.add(endH);
		panel.add(endHUp);
		panel.add(endHDown);
		panel.add(endTextM);
		panel.add(endM);
		panel.add(endMUp);
		panel.add(endMDown);
		jComboBox = new JComboBox<>(optionsToChoose);
		jComboBox.setBounds(15, 215, 70, 20);
		panel.add(jComboBox);
		alram = new JLabel("alram");
		alramOn = new JRadioButton("On");
        alramOff = new JRadioButton("Off");
        ButtonGroup bg = new ButtonGroup();
        bg.add(alramOn);
        bg.add(alramOff);
        alram.setBounds(110, 215, 45, 20);
        alram.setFont(font);
        alramOn.setBounds(160, 218, 45, 20);
        alramOff.setBounds(205, 218, 45, 20);
        alramOff.setSelected(true);
        panel.add(alram);
        panel.add(alramOn);
        panel.add(alramOff);
        String s;
        if(edit==0) s="add";
        else s="edit";
        editButton = new JButton(s);
        cancelButton = new JButton("cancel");
        editButton.addMouseListener(new MyMouseListener());
        cancelButton.addMouseListener(new MyMouseListener());
        editButton.setBounds(40,255,80,30);
        cancelButton.setBounds(140,255,80,30);
        panel.add(editButton);
        panel.add(cancelButton);
        if(edit!=0) {
			editInfo();
		}
        this.add(panel);
		this.setVisible(true);
		this.setResizable(false);
	}
	public class NumberField extends JTextField implements KeyListener {
		 private static final long serialVersionUID = 1;
		 
		public NumberField() {
			addKeyListener(this);
		}
		public NumberField(String s) {
			addKeyListener(this);
			this.setText(s);
		}

		public void keyPressed(KeyEvent e) {
		}

		public void keyReleased(KeyEvent e) {
		}
		
		public void keyTyped(KeyEvent e) {
		  // Get the current character you typed...
			char c = e.getKeyChar();
			JTextField text = (JTextField) e.getSource();
			if (!Character.isDigit(c)||text.getText().length()>1) {
				e.consume();
				return;
			}
			if (text.equals(startTextH)||text.equals(endTextH)) {
				
			}
			if (text.equals(startTextM)||text.equals(endTextM)) {
			}
		}
	}
	private void editInfo() {
		if(new File(fileName).exists()) {	// 파일이 존재한다면
			try {
				BufferedReader reader = new BufferedReader(new FileReader(fileName));
				String line;
				String[] newStr;
				for(int i=1;i<edit;i++) {	// 삭제하고자 하는 라인 이전까지 읽어오기
					line = reader.readLine();
				}
				line = reader.readLine();	// 수정하고자 하는 라인
				newStr = line.split("@%");
				// 수정 전에 가지고 있던 정보 가져오기
				titleText.setText(newStr[0]);
				for(int i=0;i<colorArray.length;i++) {	// 현재 색깔에 맞는 인덱스 값 구하여 선택되게 만들기
					if(newStr[1].equals(colorArray[i])) jComboBox.setSelectedIndex(i);
				}
				startTextH.setText(newStr[2]);
				startTextM.setText(newStr[3]);
				endTextH.setText(newStr[4]);
				endTextM.setText(newStr[5]);
				if(Integer.parseInt(newStr[6])==1) {
					alramOn.setSelected(true);
				}
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
													// 알람 1이면 켜기, 0이면 끄기
	void add(String title, String color, int startH, int startM, int endH, int endM, int alarm) {
		// 파일 입출력
		 try {
			 File file = new File(fileName);
			 if (!file.exists()) {
				 file.createNewFile();
			 }
			 FileWriter fw = new FileWriter(file,true);
			 //위와 같이 생성자의 2번째 파라미터를 true로 하면, 기존에 작성된 파일이 있을 경우, 그 뒤에 이어 붙여서 문자열이 써집니다.
			 BufferedWriter writer = new BufferedWriter(fw);
			 if(file.exists()) {
				 try {
					 BufferedReader reader = new BufferedReader(new FileReader(file));
					 if(reader.readLine()!=null) writer.write("\n"); 
					 writer.write(title+"@%"+color+"@%"+startH+"@%"+startM+"@%"+endH+"@%"+endM+"@%"+alarm);
					 writer.close();
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
			 }
			 
		 }
		 catch (IOException e) {            
			 e.printStackTrace();
		 }
	}
	// 쓰레드 설정
	public class MyThread extends Thread
	{
		public void run()
	    {
	        // 인터럽트 됬을때 예외처리
	        try
	        {
	            while(true) {
	                // 눌러진 버튼에 따라 처리
	            	if(pressed.equals(startHUp)) {
	                	startTextH.setText(String.valueOf(upHour(Integer.parseInt((startTextH.getText())))));
	                }
	                if(pressed.equals(startHDown)) {
	                	startTextH.setText(String.valueOf(downHour(Integer.parseInt((startTextH.getText())))));
	                }
	                if(pressed.equals(startMUp)) {
	                	startTextM.setText(String.valueOf(upMin(Integer.parseInt((startTextM.getText())))));
	                }
	                if(pressed.equals(startMDown)) {
	                	startTextM.setText(String.valueOf(downMin(Integer.parseInt((startTextM.getText())))));
	                }
	                if(pressed.equals(endHUp)) {
	                	endTextH.setText(String.valueOf(upHour(Integer.parseInt((endTextH.getText())))));
	                }
	                if(pressed.equals(endHDown)) {
	                	endTextH.setText(String.valueOf(downHour(Integer.parseInt((endTextH.getText())))));
	                }
	                if(pressed.equals(endMUp)) {
	                	endTextM.setText(String.valueOf(upMin(Integer.parseInt((endTextM.getText())))));
	                }
	                if(pressed.equals(endMDown)) {
	                	endTextM.setText(String.valueOf(downMin(Integer.parseInt((endTextM.getText())))));
	                }
	                Thread.sleep(200);	// 스레드 0.5초동안 대기
	            }
	        }catch(InterruptedException e)
	        {
	            e.printStackTrace();
	        }
	    }
	}
	private class MyMouseListener implements MouseListener {
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			JButton b = (JButton)e.getSource();
            if(b.getText().equals("cancel")) {
            	dispose();
            }
            if(b.getText().equals("add")) {
            	// 알람 라이디오 버튼 켜져있으면 1, 꺼져있으면 0
            	if(alramOn.isSelected()) alramInt = 1;
            	else alramInt = 0;
            	// 색깔 설정 필요
            	int startH = Integer.parseInt(startTextH.getText());
            	int startM = Integer.parseInt(startTextM.getText());
            	int endH = Integer.parseInt(endTextH.getText());
            	int endM = Integer.parseInt(endTextM.getText());
            	if(titleText.getText().equals("")) {	// 제목 입력을 하지 않았을 경우 경고 메세지 출력
            		JOptionPane.showMessageDialog(null, "제목을 입력하십시오.");
            		return;
            	}
            	if(startH<0||startH>23||startM<0||startM>59||endH<0||endH>23||endM<0||endM>59) {	// 입력된 시간이 잘못 됐을 경우 경고 메세지 출력
            		JOptionPane.showMessageDialog(null, "일정 시간을 다시 확인하십시오.");
            		return;
            	}
            	// 앞의 경고에 걸리지 않았으면 텍스트 파일에 추가 정보 저장
            	for(int i=0;i<5;i++) {	// 선택된 콤보박스에 맞는 rgb값 전달
            		if(jComboBox.getSelectedItem().toString().equals(optionsToChoose[i])) {
            			add(titleText.getText(),colorArray[i],startH,startM,endH,endM,alramInt);
            		}
            	}
            	dispose();
            }
            if(b.getText().equals("edit")) {
            	if(alramOn.isSelected()) alramInt = 1;
            	else alramInt = 0;
            	// 색깔 설정 필요
            	int startH = Integer.parseInt(startTextH.getText());
            	int startM = Integer.parseInt(startTextM.getText());
            	int endH = Integer.parseInt(endTextH.getText());
            	int endM = Integer.parseInt(endTextM.getText());
            	if(titleText.getText().equals("")) {	// 제목 입력을 하지 않았을 경우 경고 메세지 출력
            		JOptionPane.showMessageDialog(null, "제목을 입력하십시오.");
            		return;
            	}
            	if(startH<0||startH>23||startM<0||startM>59||endH<0||endH>23||endM<0||endM>59) {	// 입력된 시간이 잘못 됐을 경우 경고 메세지 출력
            		JOptionPane.showMessageDialog(null, "일정 시간을 다시 확인하십시오.");
            		return;
            	}
            	// 앞의 경고에 걸리지 않았으면 텍스트 파일에 추가 정보 저장
            	for(int i=0;i<5;i++) {	// 선택된 콤보박스에 맞는 rgb값 전달
            		if(jComboBox.getSelectedItem().toString().equals(optionsToChoose[i])) {
            			if(new File(fileName).exists()) {	// 파일이 존재한다면
                			String dummy = "";
        					try {
        						BufferedReader reader = new BufferedReader(new FileReader(fileName));
        						String line;
        						for(int p=1;p<i+edit;p++) {	// 삭제하고자 하는 라인 이전까지 읽어오기
        							line = reader.readLine();
        							dummy += (line + "\r\n");
        						}
        						reader.readLine();	// 수정하고자 하는 라인
        						dummy += (titleText.getText()+" "+colorArray[i]+" "+startH+" "+startM+" "+endH+" "+endM+" "+Integer.toString(alramInt)+"\r\n");
        						while((line=reader.readLine())!=null) {	// 삭제하고자 하는 라인 이후 읽어오기
        							dummy += (line + "\r\n");
        						}
        						dummy = dummy.trim();	// 맨 앞과 뒤 띄어쓰기 삭제
        						FileWriter fw = new FileWriter(fileName,false);
        						fw.write(dummy);
        						fw.close();
        					} catch (Exception er) {
        						er.printStackTrace();
        					}
                    	}
            		}
            	}
            	dispose();
            }
            // pressed를 통해 눌린 버튼을 저장하고 쓰레드를 재개함
            if(b.equals(startHUp)) {
            	pressed = startHUp;
            	threadResume();
            }
            if(b.equals(startHDown)) {
            	pressed = startHDown;
            	threadResume();
            }
            if(b.equals(startMUp)) {
            	pressed = startMUp;
            	threadResume();
            }
            if(b.equals(startMDown)) {
            	pressed = startMDown;
            	threadResume();
            }
            if(b.equals(endHUp)) {
            	pressed = endHUp;
            	threadResume();
            }
            if(b.equals(endHDown)) {
            	pressed = endHDown;
            	threadResume();
            }
            if(b.equals(endMUp)) {
            	pressed = endMUp;
            	threadResume();
            }
            if(b.equals(endMDown)) {
            	pressed = endMDown;
            	threadResume();
            }
		}

		@SuppressWarnings("removal")
		@Override
		public void mouseReleased(MouseEvent e) {
			// 마우스를 떼면 쓰레드를 정지 시킴
			if(tFlag==true) {
				thread.suspend();
				tFlag=false;	// 쓰레드가 꺼져있다는 걸 의미
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
    }
	// 쓰레드 재개 메소드
	@SuppressWarnings("removal")
	void threadResume() {
		if(tFlag==false) {
    		thread.resume();
    		tFlag = true;	// 쓰레드가 시작 됐다는걸 의미
    	}
	}
	// 시간과 분 처리 메소드
	int upHour(int h) {
		if(h>=23) return 0;
		if(h<0) return 23;
		return h+1;
	}
	int downHour(int h) {
		if(h>23) return 0;
		if(h<=0) return 23;
		return h-1;
	}
	int upMin(int m) {
		if(m>=59) return 0;
		if(m<0) return 59;
		return m+1;
	}
	int downMin(int m) {
		if(m>59) return 0;
		if(m<=0) return 59;
		return m-1;
	}
}