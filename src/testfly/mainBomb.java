package testfly;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
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


import java.io .*;
import java.applet.Applet;
import java.net .MalformedURLException;
import java.net .URL;

@SuppressWarnings({ "serial", "unused" })
 class mainBomb  extends JFrame  {
	//����ģʽ
	private final static int PRIMARY_ROW = 10;//��������  
    private final static int PRIMARY_COL = 10;//��������  
    private final static int PRIMARY_BOMB = 10;//��������  
     //�м�ģʽ 
    private final static int MEDIUM_ROW = 15;//�м�����  
    private final static int MEDIUM_COL = 20;//�м�����  
    private final static int MEDIUM_BOMB = 40;//�м�����  
    
    //�߼�ģʽ  
    private final static int SENIOR_ROW = 20;//�߼�����  
    private final static int SENIOR_COL = 35;//�߼�����  
    private final static int SENIOR_BOMB = 120;//�߼�����  
    
    
    //����ģʽ  
    private final static int SUPER_ROW = 30;//��������  
    private final static int SUPER_COL = 50;//��������  
    private final static int SUPER_BOMB = 350;//��������  
      
    private static int row = PRIMARY_ROW;//����  
    private static int col = PRIMARY_COL;//����  
    private static int bombnum = PRIMARY_BOMB;//����  
    private static int blocknum = row * col;//����������  
    private static int leftblocknum = blocknum - bombnum;//ʣ�෽����  
    private static int weight = row * 10 + 60;//�߶�  
    private static int width = col * 10;//���  
      
    JMenuBar mBar;//�˵���  
    JMenu gameMenu,gradeMenu;  
    JMenuItem startItem,exitItem;  
    JMenuItem primary,medium,senior,ssuper;//�ĸ�����  
      
    JPanel MenuPanel;//״̬���  
    JLabel noflagbombnum;//δ���������ǩ  
    private static int leftbombnum = bombnum;//δ�������  
      
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
      
    public mainBomb()//���췽��  
    {  
        super("ɨ��");  
          
        //��Ӳ˵�  
        mBar = new JMenuBar();   
        gameMenu = new JMenu("��Ϸ�˵�");  
        startItem = new JMenuItem("����Ϸ");  
        gradeMenu = new JMenu("����");  
        exitItem = new JMenuItem("�˳�");  
        primary = new JMenuItem("����");  
        medium = new JMenuItem("�м�");  
        senior = new JMenuItem("�߼�");  
        ssuper = new JMenuItem("����");  
        mBar.add(gameMenu);   
        gameMenu.add(startItem);  
        gameMenu.add(gradeMenu);  
        gameMenu.add(exitItem);  
        gradeMenu.add(primary);  
        gradeMenu.add(medium);  
        gradeMenu.add(senior);  
        gradeMenu.add(ssuper);  
        setJMenuBar(mBar);  
          
        //��Ӳ˵���������  
        startItem.addActionListener(new ActionListener()  //����Ϸ
        {  
            public void actionPerformed(ActionEvent e)  
            {  
                setBomb();  
            }  
        });  
        primary.addActionListener(new ActionListener()  //����ģʽ
        {  
            public void actionPerformed(ActionEvent e)  
            {  
                row = PRIMARY_ROW;  //��
                col = PRIMARY_COL;  //��
                bombnum = PRIMARY_BOMB; //�������� 
                setBomb();  
            }  
        });  
        medium.addActionListener(new ActionListener()  //�м�ģʽ
        {  
            public void actionPerformed(ActionEvent e)  
            {  
                row = MEDIUM_ROW;  //��
                col = MEDIUM_COL;  //��
                bombnum = MEDIUM_BOMB; //���� 
                setBomb();  
            }  
        });  
        senior.addActionListener(new ActionListener()  //�߼�ģʽ
        {  
            public void actionPerformed(ActionEvent e)  
            {  
                row = SENIOR_ROW;  //��
                col = SENIOR_COL;  //��
                bombnum = SENIOR_BOMB;//����  
                setBomb();  
            }  
        });  
        ssuper.addActionListener(new ActionListener()  //������ģʽ
        {  
            public void actionPerformed(ActionEvent e)  
            {  
                row = SUPER_ROW;  //��
                col = SUPER_COL;  //��
                bombnum = SUPER_BOMB; //���� 
                setBomb();  
            }  
        });  
        exitItem.addActionListener(new ActionListener()  //�˳�
        {  
            public void actionPerformed(ActionEvent e)  
            {  
                System.exit(0);  
            }  
        });  
          
        Container c = getContentPane();  
        //���״̬���  
        MenuPanel = new JPanel();  
        noflagbombnum = new JLabel();  
        MenuPanel.add(noflagbombnum);  
        c.add(MenuPanel,BorderLayout.NORTH);  
                  
        //����������  
        BombPanel = new JPanel();  
        c.add(BombPanel,BorderLayout.CENTER);  
          
        setBomb();  
          
    }  
      
    public void setBomb()//������׷���  
    {  
        //��ʼ������  
        BombPanel.removeAll();//�Ƴ������������  
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
                                    isWin();//�ж��Ƿ����  
                                }  
                                else  
                                {  
                                    for(int i=0;i<row;i++)  
                                        for(int j=0;j<col;j++)  
                                            if(bomb[i][j].isBomb)  
                                                bomb[i][j].setIcon(iconbomb);  
                                    ebomb.setIcon(icons);  
                                    ebomb.setIcon(iconbomb0);  
                                    isLose();  
                                    setBomb();  
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
                                isWin();  
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
      
    public void isWin()//�ж��Ƿ�������������  
    {  
        if(leftblocknum == 0)  
        {  
        	playMusic1();
            JOptionPane.showMessageDialog(this,"��ϲ��ȡ��ʤ��!","ʤ��!",JOptionPane.INFORMATION_MESSAGE);  
            setBomb();  
        }  
    }  
      
    public void isLose()  
    {  
        noflagbombnum.setText("δ������� ��"+0);  
        playMusic2();
        JOptionPane.showMessageDialog(this,"��ȵ������ˣ���ȷ�����¿�ʼ!","ʧ��!",2);  
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
