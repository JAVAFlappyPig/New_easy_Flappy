package testfly;

import java.awt.Color;
import java.awt.Font;
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
import javax.swing.JPanel;
public class TestBirdFly extends JPanel {
	Bird bird;
	Column column1, column2; 
	Ground ground;
	BufferedImage background;
	boolean gameOver;
	boolean started;
	BufferedImage gameoverImg;
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
			new File("D:\\1 study\\��ҵ\\java\\flappy_birds\\src\\testfly\\bg.png")); 
		gameoverImg= ImageIO.read(
				new File("D:\\1 study\\��ҵ\\java\\flappy_birds\\src\\testfly\\gameover.png"));
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
		g.drawImage(gameoverImg,0,0,null);
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
		
		while(true){
			
			
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
			}
			bird.fly();
			if (bird.x==column1.x||bird.x==column2.x){
				score++;
			}repaint();
			
			Thread.sleep(1000/60);
		}
	}
	
	/** ��������ķ��� */
	public static void main(String[] args)
		throws Exception {
		JFrame frame = new JFrame();
		TestBirdFly game = new TestBirdFly();
		frame.add(game);
		frame.setSize(440, 670);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(
				JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		game.action();
	}
}
/** ���� */
class Ground{
	BufferedImage image;
	int x, y;
	int width;
	int height;
	public Ground() throws Exception {
		image = ImageIO.read(
		  new File("D:\\1 study\\��ҵ\\java\\flappy_birds\\src\\testfly\\ground.png"));
		width = image.getWidth();
		height = image.getHeight();
		x = 0;
		y = 500;
	}//����Ĺ���������
	//�����������,��ӷ����������ƶ�һ��
	public void step(){
		x--;
		if(x==-109){
			x = 0;
		}
	}
}//������Ľ���
/** �������ͣ�x,y�����ӵ����ĵ��λ�� */
class Column{
	BufferedImage image;
	int x,y;
	int width, height;
	/** �����м�ķ�϶ */
	int gap;
	int distance;//���룬��������֮��ľ���
	Random random = new Random();
	/** ����������ʼ�����ݣ�n����ڼ������� */
	public Column(int n) throws Exception {
		image=ImageIO.read(
		  new File("D:\\1 study\\��ҵ\\java\\flappy_birds\\src\\testfly\\column.png"));
		width = image.getWidth();
		height = image.getHeight();
		gap=144;
		distance = 245;
		x = 550+(n-1)*distance;
		y = random.nextInt(218)+132;
	}
	//��Column����ӷ��� step����action���ô˷���
	public void step(){
		x--;
		if(x==-width/2){
			x = distance * 2 - width/2;
			y = random.nextInt(218)+132;
		}
	}
}//Column��Ľ���
/** ������, x,y�����������ĵ�λ�� */
class Bird{
	BufferedImage image;
	int x,y;
	int width, height;
	int size;//��Ĵ�С��������ײ���
	
	//��Bird�����������ԣ����ڼ������λ��
    double g;//  �������ٶ�
    double t;//  ����λ�õļ��ʱ��
    double v0;// ��ʼ�����ٶ�
    double speed;// �ǵ�ǰ�������ٶ�
    double s;//     �Ǿ���ʱ��t�Ժ��λ��
    double alpha;// �������� ���ȵ�λ
    //��Bird���ж���
    //����һ�飨���飩ͼƬ������Ķ���֡
    BufferedImage[] images;
    //�Ƕ���֡����Ԫ�ص��±�λ��
    int index;
    
	public Bird() throws Exception {
		image=ImageIO.read(
			new File("D:\\1 study\\��ҵ\\java\\flappy_birds\\src\\testfly\\0.png"));
		width = image.getWidth();
		height = image.getHeight();
		x = 132;
		y = 280;
		size = 10;
		g = 1;
		v0 = 10;
		t = 0.25;
		speed = v0;
		s = 0;
		alpha=0;
		//��������,����8��Ԫ�ص�����
		//��8����λ�ã�û��ͼƬ����
		//8��λ�õ����: 0 1 2 3 4 5 6 7
		images = new BufferedImage[8];
		for(int i=0; i<8; i++){
			//i = 0 1 2 3 4 5 6 7 
			images[i] = ImageIO.read(
  			  new File("D:\\1 study\\��ҵ\\java\\flappy_birds\\src\\testfly\\"+i+".png"));
		}
		index = 0;
	}
	//��Bird����ӷ���(fly)�Ĵ���
	public void fly(){
		index++;
		image = images[(index/12) % 8];
	}
	//��Bird���������ƶ�����
	public void step(){
		double v0 = speed;
		s = v0*t + g*t*t/2;//���������˶�λ��
		y = y-(int)s;//�����������λ��
		double v = v0 - g*t;//�����´ε��ٶ�
		speed = v;
//		if(y>=500){//���������棬����������
//			y = 280;
//			speed = 35;
//		}
		//����Java API�ṩ�ķ����к������������
		alpha = Math.atan(s/8);
	}
	//��Bird����ӷ���
	public void flappy(){
		//�������ó�ʼ�ٶȣ��������Ϸ�
		speed = v0;
	}
//��������ӷ���hit
	// ��⵱ǰ���Ƿ���������ground
	//�������true��ʾ������ײ
	//���򷵻�false��ʾû����ײ

	
public boolean hit (Ground ground){
	boolean hit =y+size/2>ground.y;
	if(hit){
		y=ground.y-size/2;
		
	}
	return hit;
}
//��⵱ǰ���Ƿ�ײ������
public boolean hit(Column column){
	//�ȼ���Ƿ������ӵķ�Χ����
   if  (x>column.x-column.width/2-size/2&&x<column
		   .x+column.width/2+size/2){
	   if(y>column.y-column.gap/2+size/2&&y<column.y+column.gap/2-size/2){
	   return false;
	   
  
   
	   		}
	   return true;
	
   		}
   return false;
	}
}





