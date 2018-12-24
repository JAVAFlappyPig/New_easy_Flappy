package testfly;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;

import javax.imageio.ImageIO;

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
		  new File("I:\\eclipse-workplace\\FF\\Image\\birdcolumn.png"));
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