package calendar;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.*;

import calendar.EditFrame.MyThread;

public class DailyFrame extends JFrame{
	private MyPanel panel;
	private JButton left;
	private JButton right;
	private JButton plus;
	private int year, month, date;
	private MyThread thread = new MyThread();	// 쓰레드
	private String fileName;
	private ArrayList<String> title = new ArrayList();
	private ArrayList<String> color = new ArrayList();
	private int N=6;	// 한 화면에 출력할 일정의 갯수
	private int page=1;	// 현재 있는 페이지
	private JButton pre;
	private JButton next;
	private JButton []edit;
	private JButton []delete;
	private int numOfPlan=0;	// 오늘의 일정 갯수
	private int x;
	private int y;
	private int w;
	private int h;
	private int gap;
	private BufferedReader reader;
	// CalendarFrame의 일별 칸을 누르면 세부 일정을 보여주는 DailyFrame 화면에 띄우기
	private EditFrame editFrame;
	private File file;
	private boolean leftFlag=false;		// true -> 달력 창도 달이 바뀜 
	private boolean rightFlag=false;
	private Calendar cal;
	
	DailyFrame(int year, int month, int date) {
		this.year = year;
		this.month = month;
		this.date = date;
		fileName = "./" + Integer.toString(year*10000+month*100+date);
		file = new File(fileName);
		 if (!file.exists()) {
			 try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
		setTitle("Priority Calendar");
		setBounds(650,50,600,600);
		setVisible(true);
		Container contentPane = getContentPane();
		panel = new MyPanel();
		contentPane.add(panel);
		panel.setBackground(new Color(0xeeffdd));
		panel.setLayout(null);
    	panel.setVisible(true);
    	panel.setPreferredSize(new Dimension(600,100));
    	left = new JButton("<-");
    	left.setBounds(70,20,80,20);
    	left.addActionListener(new MyActionListener());
    	plus = new JButton("+");
    	plus.addActionListener(new MyActionListener());
    	plus.setBounds(360,13,50,35);
    	right = new JButton("->");
    	right.setBounds(430,20,80,20);
    	right.addActionListener(new MyActionListener());
    	pre = new JButton("previous page");
    	pre.setBounds(20,580,120,20);
    	pre.addActionListener(new MyActionListener());
    	next = new JButton("next page");
    	next.setBounds(450,580,110,20);
    	next.addActionListener(new MyActionListener());
    	edit = new JButton[N];
    	delete = new JButton[N];
    	// 일정 바를 출력할 위치를 위한 요소들을 계산
    	x = 120;	// 그림을 그리는 시작점의 x값
        y = 80;	// 그림을 그리는 시작점의 y값
        w = this.getWidth()-2*x-80;	// 그림을 그리는 판넬의 너비 가져오기
        gap = 22;
        h = (this.getHeight()-(gap*(N-1))-2*y)/N;	// 출력할 부분의 높이 가져오기
        for(int i=0;i<N;i++) {
        	edit[i] = new JButton("✔");
        	delete[i] = new JButton("✘");
        	edit[i].setBounds(x+w+gap,y+(i*(h-1)+gap*(i-1))+10,50,50);
        	delete[i].setBounds(x+w+2*gap+50,y+(i*(h-1)+gap*(i-1))+10,50,50);
        	edit[i].addActionListener(new MyActionListener());
        	delete[i].addActionListener(new MyActionListener());
        	panel.add(edit[i]);
        	panel.add(delete[i]);
        }
        // 버튼에 아이콘 씌우기
    	panel.add(left);
    	panel.add(plus);
    	panel.add(right);
    	panel.add(pre);
    	panel.add(next);
    	thread.start();
    	cal = Calendar.getInstance();
		cal.set(cal.YEAR, year);	// 캘린더 클래스에 현재 년도를 세팅
    	cal.set(cal.MONTH, month-1); //1월이 0부터 시작하므로 월에서 -1
    	cal.set(Calendar.DAY_OF_MONTH,1); //DAY_OF_MONTH를 1로 설정 (월의 첫날)
	}
	boolean getLeftFlag() {
		return leftFlag;
	}
	boolean getRightFlag() {
		return rightFlag;
	}
	void setLeftFlag(boolean b) {
		leftFlag = b;
	}
	void setRightFlag(boolean b) {
		rightFlag = b;
	}
	// 일정 정보를 즉각적으로 업데이트 할 쓰레드 설정 ********* 쓰레드 쓸 필요 없이 가능할지도? (삭제는 가능) / 수정도 가능하려나? 객체 이 프레임에서 생성하고 결과값 여기서 받기 (액션 리스너 넘겨주기 / editFrame에서 버튼 받아오기)
	public class MyThread extends Thread
	{
		public void run()
		{
			// 인터럽트 됬을때 예외처리
			try
			{
			    while(true) {
			    	File file = new File(fileName);
					if(file.exists()) {
						try {
							reader = new BufferedReader(new FileReader(fileName));
							String s;
							String[] newStr;
							title.clear();
							color.clear();
							// 라인별로 일정의 제목과 설정 색상 불러오기 (readLine -> split) / ArrayList를 이용하여 일정 등록 갯수에 제한을 두지 않음
							while((s=reader.readLine())!=null) {
								newStr = s.split("@%");
								title.add(newStr[0]);
								color.add(newStr[1]);
							}
							numOfPlan = title.size();
						} catch (FileNotFoundException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						}
						if(numOfPlan>=6) {
							pre.setVisible(true);
				    		pre.setEnabled(true);
				    		next.setVisible(true);
				    		next.setEnabled(true);
						}
						if(page==1) {	// 첫번째 장에서의 수정 및 삭제 버튼 처리
				    		pre.setVisible(false);
				    		pre.setEnabled(false);
				    	}
				    	else {	// 아니면 생성
				    		pre.setVisible(true);
				    		pre.setEnabled(true);
				    	}
						if((page==(numOfPlan-1)/N+1)) {	// 마지막 장에서의 수정 및 삭제 버튼 처리
				    		next.setVisible(false);
				    		next.setEnabled(false);
				    		if(numOfPlan==0||numOfPlan%6!=0){	// 일정 갯수가 N으로 나누어 떨어질 떄 예외 처리
				    			for(int i=N-1;i>=title.size()%N;i--) {	// 일정이 없는 위치의 버튼 삭제
					    			edit[i].setVisible(false);
					    			edit[i].setEnabled(false);
					    			delete[i].setVisible(false);
					    			delete[i].setEnabled(false);
					    		}
				    		}
				    	}
						else {	// 아니면 생성
				    		next.setVisible(true);
				    		next.setEnabled(true);
				    		for(int i=0;i<N;i++) {
				    			edit[i].setVisible(true);
				    			edit[i].setEnabled(true);
				    			delete[i].setVisible(true);
				    			delete[i].setEnabled(true);
				    		}
				    	}	
						for(int i=0;i<N;i++) {
							if(page==(numOfPlan-1)/N+1) {	// 일정이 추가 되었을 때 수정 및 버튼 재생성
								if((i<title.size()%N||numOfPlan%N==0)&&numOfPlan!=0) {	// 일정 갯수가 N으로 나누어 떨어질 떄 예외 처리
									edit[i].setVisible(true);
					    			edit[i].setEnabled(true);
					    			delete[i].setVisible(true);
					    			delete[i].setEnabled(true);
								}
							}
						}
					} else
						try {
							file.createNewFile();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			        repaint();
			        Thread.sleep(200);	// 스레드 0.5초동안 대기
			    }
			}catch(InterruptedException e)
			{
			    System.out.println(e);
			}
		}
	}
	
	class MyPanel extends JPanel {
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setFont(new Font("맑은 고딕",Font.BOLD,30));
            g.drawString(Integer.toString(year*10000+month*100+date), 190, 40);
            g.setFont(new Font("맑은 고딕",Font.PLAIN,20));
            for(int i=0;i<N;i++) {	// 배열에 저장된 정보가 없으면 for문 종료
            	if((page==(numOfPlan-1)/N+1)&&(i>=title.size()%N)&&(numOfPlan%N!=0)||(numOfPlan==0)) {	// 간소화 가능할까?????????????????????????
            		return;
            	}
    			g.setColor(new Color(Integer.parseInt(color.get((page-1)*N+i), 16))); // new Color(int rgb값)
    			// rgb값은 16진수로 저장되어 있으므로 parseInt 메소드에 16을 매개변수로 설정
    			g.drawRect(x, y+(i*h+gap*(i-1)), w, h);
    			g.fillRect(x, y+(i*h+gap*(i-1)), w, h);
    			g.setColor(Color.BLACK);
    			g.drawString(title.get((page-1)*N+i), x+50, y+(i*h+gap*(i-1)+h/2));	// 창의 크기 조절 -> 칸의 크기 변화 -> 출력되는 title 글자 개수 조정 ******
    		}
        }
	}
	
	// 버튼 클릭 이벤트 설정
	private class MyActionListener implements ActionListener {
		@Override
        public void actionPerformed(ActionEvent e) {
            JButton b = (JButton)e.getSource();
            if(b.getText().equals("+")) {
            	EditFrame editFrame = new EditFrame(year, month, date, 0);
            }
            if(b.equals(pre)) {
            	page--;
            }
            if(b.equals(next)) {
            	page++;
            }
            if(b.equals(left)) {	// left 버튼을 누르면 하루 이전 날짜로 이동
            	if(date==1) {	// 현재 date가 1이면 이전달로 이동
            		if(month==1) {	// 현재 month가 1월일 경우
            			cal.set(cal.MONTH, 11);
            			year--;
            			month = 12;            			
            		}
            		else {
            			month--;
            			cal.set(cal.MONTH, month-1);
            		}
            		leftFlag = true;	// 달력 창의 월도 같이 이동하기 위하여 플래그 사용
            		date = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
            	}
            	else date--;
            	fileName = "./" + Integer.toString(year*10000+month*100+date);	// 파일명 업데이트
            }
            if(b.equals(right)) {	// right 버튼을 누르면 다음 날짜로 이동
            	if(date==cal.getActualMaximum(Calendar.DAY_OF_MONTH)) {	// 현재 date가 그 달의 마지막 날이면
            		if(month==12) {	// 현재 month가 12월일 경우
            			cal.set(cal.MONTH, 0);
            			year++;
            			month = 1;         			
            		}
            		else {
            			month++;
            			cal.set(cal.MONTH, month-1);
            		}
            		rightFlag = true;	// 달력 창의 월도 같이 이동하기 위하여 플래그 사용
            		date = 1;
            	}
            	else date++;
            	fileName = "./" + Integer.toString(year*10000+month*100+date);	// 파일명 업데이트
            }
            for(int i=0;i<N;i++) {	// 현재 클릭한 버튼은 i+(page-1)*N+1번 째에 해당하는 일정의 버튼이다.
            	if(b.equals(edit[i])) {
            		editFrame = new EditFrame(year, month, date, i+(page-1)*N+1);
            	}
            	if(b.equals(delete[i])) {	// 파일 삭제
            		if(new File(fileName).exists()) {	// 파일이 존재한다면
            			String dummy = "";
						try {
							reader = new BufferedReader(new FileReader(fileName));
							String line;
							for(int p=1;p<i+(page-1)*N+1;p++) {	// 삭제하고자 하는 라인 이전까지 읽어오기
								line = reader.readLine();
								dummy += (line + "\r\n");
							}
							reader.readLine();	// 삭제하고자 하는 라인 건너뛰기
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
						if((page==(numOfPlan-1)/N+1)&&page!=1&&(numOfPlan%N==1)) {	// 마지막 장의 마지막 일정이 삭제되면 마지막 페이지는 사라짐
							page--;
						}
					}
            	}
            }
        }
    }
}