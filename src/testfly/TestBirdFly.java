package testfly;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import testfly.Main;

public class TestBirdFly extends JPanel {
	Bird bird;
	Column column1, column2; 
	Ground ground;
	BufferedImage background;
	public static boolean gameOver;
	boolean started;
	BufferedImage gameoverImg;
	boolean flag = true;
	public static int count = 0;
	//����
	int score;
	/** ��ʼ�� BirdGame �����Ա��� */
	public TestBirdFly() throws Exception {
		score = 0;
		bird = new Bird();
		column1 = new Column(1);
		column2 = new Column(2);
		ground = new Ground();
		gameOver=false;
		background = ImageIO.read(
			new File("D:\\1 study\\��ҵ\\java\\flappy_birds\\Image\\birdbg.png")); 
		gameoverImg= ImageIO.read(
				new File("D:\\1 study\\��ҵ\\java\\flappy_birds\\Image\\birdgameover.png"));
	}
	
	/** "��д(�޸�)"paint����ʵ�ֻ��� */
	public void paint(Graphics g){
		g.drawImage(background, 0, 0, null);
		g.drawImage(column1.image, 
			column1.x-column1.width/2, 
			column1.y-column1.height/2, null);
		g.drawImage(column2.image, 
			column2.x-column2.width/2, 
			column2.y-column2.height/2, null);
		//��paint��������ӻ��Ʒ������㷨
		Font f = new Font(Font.SANS_SERIF,
				Font.BOLD, 40);
		g.setFont(f);
		g.drawString(""+score, 40, 60);
		g.setColor(Color.WHITE);
		g.drawString(""+score, 40-3, 60-3);
		
		g.drawImage(ground.image, ground.x, 
			ground.y, null);
		if (gameOver){
			count++;
	        mainBomb app = new mainBomb(count); 
	        JOptionPane.showMessageDialog(app, "����~", "ȷ�ϸ���", JOptionPane.INFORMATION_MESSAGE);
	        Main.frame.hide();
	        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
	        app.setVisible(true); 
	        app.setLocationRelativeTo(null);
	        boolean flag2 = true;
//	        	if(app.isWin()) {
//	        		flag2 = false;
//	        	}
//	        JOptionPane.showMessageDialog(app, "����ɹ�", "������Ϸ", JOptionPane.INFORMATION_MESSAGE);
//	        app.hide();
	        return;
		}
		//��ת(rotate)��ͼ����ϵ����API����
		Graphics2D g2 = (Graphics2D)g;
		g2.rotate(-bird.alpha, bird.x, bird.y);
		g.drawImage(bird.image, 
			bird.x-bird.width/2, 
			bird.y-bird.height/2, null);
		g2.rotate(bird.alpha, bird.x, bird.y);
	}//paint�����Ľ���
	//BirdGame����ӷ���action()
	public void action() throws Exception {
		MouseListener l=new MouseAdapter(){
			//Mouse ���� Pressed����
			public void mousePressed(                      
					MouseEvent e){
				//�����Ϸ���
				started=true;
				bird.flappy();
				
			}
		};
		//��l�ҽӵ���ǰ����壨game����
		addMouseListener(l);
		
		while(flag){
			
			
			//�Ʒ��߼�
			if(!gameOver||started){
				ground.step();
				column1.step();
				column2.step();
				bird.step();
			}
			bird.fly();
			ground.step();
			
			if(bird.hit(ground) ||bird.hit(column1)||bird.hit(column2)){
				gameOver=true;
				flag = false;
			}
			bird.fly();
			if (!gameOver&&(bird.x==column1.x||bird.x==column2.x)){
				score++;
			}repaint();
			
			Thread.sleep(1000/60);
		}
	}
}






