package testfly;

import javax.swing.JButton;

@SuppressWarnings("serial")
final class Bomb extends JButton {
	int bx,by;//��������λ��  
    int BombRoundCount;//��Χ����  
    int Bombflag;//̽�ױ��  
    boolean isBomb;//�Ƿ�����  
    boolean isClicked;//�Ƿ������  
    boolean isRight;//�Ƿ����Ҽ�  
    public static int blockopennum = 0;//�򿪷�����  
      
    public Bomb(int x,int y)//���췽��  
    {  
        bx = x;  
        by = y;  
        BombRoundCount = 9;  
        Bombflag = 0;  
        isBomb = false;  
        isClicked = false;  
        isRight = false;  
    }  
}

