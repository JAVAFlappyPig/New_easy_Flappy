package testfly;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import testfly.Bomb;
import testfly.TestBirdFly;


import java.io .*;
import java.applet.Applet;
import java.net .MalformedURLException;
import java.net .URL;

@SuppressWarnings({ "serial", "unused" })
 class mainBomb  extends JFrame  {
	//����ģʽ
	private final static int PRIMARY_ROW = 2;//��������  
    private final static int PRIMARY_COL = 2;//��������  
    private final static int PRIMARY_BOMB = 1;//��������  
     //�м�ģʽ 
    private final static int MEDIUM_ROW = 3;//�м�����  
    private final static int MEDIUM_COL = 3;//�м�����  
    private final static int MEDIUM_BOMB = 2;//�м�����  
    
    //�߼�ģʽ  
    private final static int SENIOR_ROW = 4;//�߼�����  
    private final static int SENIOR_COL = 4;//�߼�����  
    private final static int SENIOR_BOMB = 3;//�߼�����  
    
    
    //����ģʽ  
    private final static int SUPER_ROW = 5;//��������  
    private final static int SUPER_COL = 5;//��������  
    private final static int SUPER_BOMB = 5;//��������  
      
    private static int row;//����  
    private static int col;//����  
    private static int bombnum;//����  
    private static int blocknum = row * col;//����������  
    private static int leftblocknum = blocknum - bombnum;//ʣ�෽����  
    private static int weight = row * 10 + 60;//�߶�  
    private static int width = col * 10;//���  
	public static JFrame a;
	public int win=0;//Ӯ����ͨ������iswin�Ĵ���.
      
    JMenuBar mBar;//�˵���  
    JMenu gameMenu,gradeMenu;  
    JMenuItem startItem,exitItem;  
    JMenuItem primary,medium,senior,ssuper;//�ĸ�����  
      
    JPanel MenuPanel;//״̬���  
    JLabel noflagbombnum;//δ���������ǩ  
    private static int leftbombnum = bombnum;//δ�������  
    private int score;
      
    JPanel BombPanel;//�������  
    Bomb [][]bomb;//������������  
      
    ImageIcon iconbomb = new ImageIcon("Image/bomb.jpg");  
    ImageIcon iconbomb0 = new ImageIcon("Image/bomb0.jpg");  
    ImageIcon iconflag = new ImageIcon("Image/flag.jpg");  
    ImageIcon iconflag2 = new ImageIcon("Image/flag2.jpg");  
    ImageIcon icon1 = new ImageIcon("Image/1.jpg");  
    ImageIcon icon2 = new ImageIcon("Image/2.jpg");  
    ImageIcon icon3 = new ImageIcon("Image/3.jpg");  
    ImageIcon icon4 = new ImageIcon("Image/4.jpg");  
    ImageIcon icon5 = new ImageIcon("Image/5.jpg");  
    ImageIcon icon6 = new ImageIcon("Image/6.jpg");  
    ImageIcon icon7 = new ImageIcon("Image/7.jpg");  
    ImageIcon icon8 = new ImageIcon("Image/8.jpg");  
    ImageIcon icon0 = new ImageIcon("Image/0.jpg");  
    ImageIcon icons = new ImageIcon("Image/s.jpg");  
      
    public mainBomb(int n,int s)//���췽��  
    {  
        super("ɨ��");     
        score=s;  
        Container c = getContentPane();  
        //���״̬���  
        MenuPanel = new JPanel();  
        noflagbombnum = new JLabel();  
        MenuPanel.add(noflagbombnum);  
        c.add(MenuPanel,BorderLayout.NORTH);  
        int height = this.getHeight();
        int width = this.getWidth();
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenWidth = screenSize.width/2;
        int screenHeight = screenSize.height/2;
        c.setLocation(screenWidth-width/2, screenHeight-height/2);
                  
        //����������  
        BombPanel = new JPanel();  
        c.add(BombPanel,BorderLayout.CENTER);  
          
        setBomb(n);  
          
    }  
      
    public void setBomb(int n)//������׷���  
    {  
        //��ʼ������  
        BombPanel.removeAll();//�Ƴ������������  
        switch (n) {
        case 1:
        	this.row = PRIMARY_ROW;
        	this.col = PRIMARY_COL;
        	this.bombnum = this.PRIMARY_BOMB;
        	break;
        case 2:
        	this.row = this.MEDIUM_ROW;
        	this.col = this.MEDIUM_COL;
        	this.bombnum = this.MEDIUM_BOMB;
        	break;
        case 3:
        	this.row = this.SENIOR_ROW;
        	this.col = this.SENIOR_COL;
        	this.bombnum = this.SENIOR_BOMB;
        	break;
        case 4:
        	this.row = this.SUPER_ROW;
        	this.col = this.SUPER_COL;
        	this.bombnum = this.SUPER_BOMB;
        	break;
        }
        bomb = new Bomb[row][col];  
        BombPanel.setLayout(new GridLayout(row,col));  
        for(int i=0;i<row;i++)  
            for(int j=0;j<col;j++)  
            {  
                bomb[i][j] = new Bomb(i,j);
                bomb[i][j].addMouseListener(new MouseAdapter()  
                {  
                    public void mouseClicked(MouseEvent e)  
                    {  
                        Bomb ebomb = (Bomb)e.getSource();  
                        if(e.getButton() == MouseEvent.BUTTON1)  
                        {  
                            if(!ebomb.isClicked && !ebomb.isRight)  
                            {  
                                if(!ebomb.isBomb)  
                                {  
                                    open(ebomb);//�򿪷���  
                                    try {
										isWin();
									} catch (Exception e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}//�ж��Ƿ����  
                                }  
                                else  
                                {  
                                	for(int i=0;i<row;i++)  
                                        for(int j=0;j<col;j++)  
                                            if(bomb[i][j].isBomb)  
                                                bomb[i][j].setIcon(iconbomb);  
                                    ebomb.setIcon(icons);  
                                    ebomb.setIcon(iconbomb0);  
                                    JOptionPane.showMessageDialog(ebomb, "ʧ��~�����ﻻ��gameover��ͼƬ������ʾ������", "����ʧ�ܣ�", JOptionPane.INFORMATION_MESSAGE);
                                }  
                            }  
                        }  
                        else if(e.getButton() == MouseEvent.BUTTON3)  
                        {  
                            if (!ebomb.isClicked)   
                            {  
                                ebomb.Bombflag = (ebomb.Bombflag + 1) % 3;  
                                if (ebomb.Bombflag == 1)   //BombflagΪ̽�ױ��
                                {  
                                    if (leftbombnum > 0)   
                                    {  
                                        ebomb.setIcon(iconflag);  
                                        ebomb.isRight = true;  
                                        leftbombnum--;  
                                    }  
                                    else   
                                        ebomb.Bombflag = 0;  
                                }  
                                else if (ebomb.Bombflag == 2)  
                                {  
                                    leftbombnum++;  
                                    ebomb.setIcon(iconflag2);  
                                    ebomb.isRight = false;  
                                }  
                                else   
                                    ebomb.setIcon(icons);  
                                noflagbombnum.setText("δ������� ��"+leftbombnum);  
                                try {
									isWin();
								} catch (Exception e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}  
                            }  
                        }  
                    }  
                });  
                BombPanel.add(bomb[i][j]);  
            }  
        blocknum = row * col;//������  
        leftbombnum = bombnum;//δ��ǵ�����  
        leftblocknum = blocknum - bombnum;//δ�򿪷�����  
        noflagbombnum.setText("δ������� ��"+leftbombnum);  
        weight = row * 40 + 80;//���ڸ߶�  
        width = col * 40;//���ڿ��  
        setSize(width,weight);//�趨���ڴ�С  
        setResizable(false);//�趨���ɸı䴰�ڴ�С  
        //��ʼ������  
        for(int i=0;i<row;i++)  
            for(int j=0;j<col;j++)  
            {  
                bomb[i][j].BombRoundCount = 9;  
                bomb[i][j].Bombflag = 0;  
                bomb[i][j].isBomb = false;  
                bomb[i][j].isClicked = false;  
                bomb[i][j].isRight = false;  
                bomb[i][j].setIcon(icons);  
            }  
  
        //��ʼ�������  
        Random rand = new Random();  
        for(int i=0;i<bombnum;)  
        {  
            int x = rand.nextInt(row);  
            int y = rand.nextInt(col);  
            if(!bomb[x][y].isBomb)  
            {  
                bomb[x][y].isBomb = true;  
                i++;  
            }  
        }  
        calculateRoundBomb();  
    }  
      
    public void calculateRoundBomb()//������Χ��������  
    {  
        for(int i=0;i<row;i++)  
            for(int j=0;j<col;j++)  
            {  
                int count = 0;  
                if(!bomb[i][j].isBomb)  
                    for(int x=i-1;x<=i+1;x++)  
                        for(int y=j-1;y<=j+1;y++)  
                            if(x>=0&&y>=0&&x<row&&y<col&&bomb[x][y].isBomb)  
                                count++;  
                bomb[i][j].BombRoundCount = count;  
            }  
    }  
      
    public void isWin() throws Exception//�ж��Ƿ�������������  
    {  
    	//Frame a;
        if(leftblocknum == 0)  
        {  
        	
        	playMusic1();
            JOptionPane.showMessageDialog(this,"��ϲ��ȡ��ʤ��!","ʤ��!",JOptionPane.INFORMATION_MESSAGE);  
            win++;
            
			//System.out.println(win);

//            setBomb();
            //dispose();
   /*         a = new JFrame();
            TestBirdFly game1=new TestBirdFly(score);
            a.add(game1);
			a.setSize(440, 670);
			a.setLocationRelativeTo(null);           
            a.setVisible(true);
			game1.action();*/
            
        	
        }
		
    }  
    
    public void isNull(Bomb clickbomb)//�������Ϊ�գ�������Χ����  
    {  
        int x = clickbomb.bx;  
        int y = clickbomb.by;  
        for(int i=x-1;i<=x+1;i++)  
            for(int j=y-1;j<=y+1;j++)  
                if(i>=0&&j>=0&&i<row&&j<col)  
                    if(!bomb[i][j].isBomb&&!bomb[i][j].isClicked&&!bomb[i][j].isRight)  
                        open(bomb[i][j]);  
    }  
      
    public void open(Bomb clickbomb)//����򿪷���  
    {  
        clickbomb.isClicked = true;  
        leftblocknum--;  
        if(clickbomb.BombRoundCount > 0)  //��Χ����
        {  
            if(clickbomb.BombRoundCount == 1)  
                clickbomb.setIcon(icon1);  
            else if(clickbomb.BombRoundCount == 2)  
                clickbomb.setIcon(icon2);  
            else if(clickbomb.BombRoundCount == 3)  
                clickbomb.setIcon(icon3);  
            else if(clickbomb.BombRoundCount == 4)  
                clickbomb.setIcon(icon4);  
            else if(clickbomb.BombRoundCount == 5)  
                clickbomb.setIcon(icon5);  
            else if(clickbomb.BombRoundCount == 6)  
                clickbomb.setIcon(icon6);  
            else if(clickbomb.BombRoundCount == 7)  
                clickbomb.setIcon(icon7);  
            else if(clickbomb.BombRoundCount == 8)  
                clickbomb.setIcon(icon8);  
        }  
        else  
        {  
            clickbomb.setIcon(icon0);  
            isNull(clickbomb);  
        }  
    }  
    public static void playMusic1() {
		try {
			URL cb;
			File f = new File("1.wav");
			cb = f.toURL();
			java.applet.AudioClip aau;
			aau = Applet.newAudioClip(cb);
			aau.play();
			aau.loop();
		}catch(MalformedURLException e) {
			e.printStackTrace();
		}
	}
    public static void playMusic2() {
		try {
			URL cb;
			File f = new File("2.wav");
			cb = f.toURL();
			java.applet.AudioClip aau;
			aau = Applet.newAudioClip(cb);
			aau.play();
			aau.loop();
		}catch(MalformedURLException e) {
			e.printStackTrace();
		}
	}

}
