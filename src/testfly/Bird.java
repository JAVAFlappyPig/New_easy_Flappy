package testfly;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

/** ������, x,y�����������ĵ�λ�� */
public class Bird{
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
    //BufferedImage[] images;
    BufferedImage image1;
    //�Ƕ���֡����Ԫ�ص��±�λ��
    int index;
    
	public Bird() throws Exception {
		image=ImageIO.read(
			new File("I:\\eclipse-workplace\\FF\\Image\\bird0.png"));
		width = image.getWidth();
		height = image.getHeight();
		x = 132;
		y = 280;
		size = 10;
		g = 1;
		v0 = 10;
		t = 0.4;
		speed = v0;
		s = 0;
		alpha=0;
		image1 = ImageIO.read(
			  new File("I:\\eclipse-workplace\\FF\\Image\\bird0.png"));
	}
	//��Bird����ӷ���(fly)�Ĵ���
	public void fly(){
		index++;
		image=image1;
	}
	//��Bird���������ƶ�����
	public void step(){
		double v0 = speed;
		s = v0*t + g*t*t/2;//���������˶�λ��
		y = y-(int)s;//�����������λ��
		double v = v0 - g*t;//�����´ε��ٶ�
		speed = v;
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
   if  (x>column.x-column.width/2-size/2&&x<column.x+column.width/2+size/2){
	   if(y<column.y+column.gap/2-size/2){
	   return false;
	   		}
	   return true;
   		}
   return false;
	}
}