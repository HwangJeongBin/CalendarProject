package calendar;

import java.awt.Color;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class DailyPlan{
	private int N=4;	// 한 화면에 보이는 스케줄 갯수
	private String title[] = new String[N];
	private String color[] = new String[N];
	private String etc = new String(". . .");	// 칸이 모두 차면 ...으로 더 많은 일정이 있다는 것을 나타냄
	private String fileName;
	private int y;
	private int m;
	private int d;
	
	DailyPlan(int y, int m, int d){
		this.y = y;
		this.m = m;
		this.d = d;
		readFile();
	}
	void readFile() {
		BufferedReader reader;
		fileName = "./" + Integer.toString(y*10000+m*100+d);
		if(new File(fileName).exists()) {
			try {
				reader = new BufferedReader(new FileReader(fileName));
				String s;
				String[] newStr;
				// 라인별로 일정의 제목과 설정 색상 불러오기 (readLine -> split)
				for(int i=0;i<N&&((s=reader.readLine())!=null);i++) {
					newStr = s.split("@%");
					title[i]=newStr[0];
					color[i]=newStr[1];
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	// 캘린더 매 칸마다 일정 출력 (빈 메모장이나 생성되지 않은 메모장 처리 필요)
	void paint(Graphics g, int X, int Y, int w, int h) {
		for(int i=0;i<N&&title[i]!=null;i++) {	// 배열에 저장된 정보가 없으면 for문 종료
			g.setColor(new Color(Integer.parseInt(color[i], 16))); // new Color(int rgb값)
			// rgb값은 16진수로 저장되어 있으므로 parseInt 메소드에 16을 매개변수로 설정
			g.drawRect(X, Y+(i*h/5), w, h/5);
			g.fillRect(X, Y+(i*h/5), w, h/5);
			g.setColor(Color.BLACK);
			g.drawString(title[i], X, Y+(i*h/5)+15);	// 창의 크기 조절 -> 칸의 크기 변화 -> 출력되는 title 글자 개수 조정 ******
			if(i==(N-1)) g.drawString(etc, X, Y+i*h/5+23);	// 더 이상 출력할 칸이 없으면 ...을 출력함으로써 표시
		}
	}
}