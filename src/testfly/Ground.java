package testfly;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

/** ���� */
public class Ground{
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