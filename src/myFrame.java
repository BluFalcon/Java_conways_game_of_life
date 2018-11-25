
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;


import javax.swing.JFrame;
import javax.swing.JPanel;


public class myFrame extends JFrame
{

	public static final int  Horizonal_boxes = 19;
	public static final int  Vertical_boxes = 19;

	/**
	 * 
	 */
	private int rounds = 100;
	private static final long serialVersionUID = 1L;
	private int Thesi_X = 450, Thesi_Y= 200; //thesi stin o8oni tou PC
	public static int size_x = 575, size_y= 575;

	//	public static int[][] game ; //line ~40 also
	public static int[][] game = new int[][]{
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0 },
		{ 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0 },
		{ 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },
		{ 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0 },
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0 },
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0 },
		{ 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0 },
		{ 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0 },
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0 },
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }
	};

	// constructor
	myFrame(  )
	{

		//game = new int[Horizonal_boxes+2][Vertical_boxes+2];


		setLocation(Thesi_X, Thesi_Y);
		setSize(size_x, size_y);
		setTitle("Conway's Game of Life");
		setResizable(false);
		setVisible(true);

		getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		setDefaultCloseOperation(EXIT_ON_CLOSE);




		DrawPanel ak  =  new DrawPanel();

		add(ak);

		for(int k=0; k<rounds;k++)
		{
			try {
				Thread.sleep(400);
				run_game();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			repaint();
		}

	}

	static int[][] getGame()
	{
		return game;
	}

	/**
	 * At each step in time, the following transitions occur:
	 * Any live cell with fewer than two live neighbors dies, as if by underpopulation.
	 * Any live cell with two or three live neighbors lives on to the next generation.
	 * Any live cell with more than three live neighbors dies, as if by overpopulation.
	 * Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
	 */
	static void run_game()
	{
		int[][] tmp_game = new int[Horizonal_boxes+2][Vertical_boxes+2]; ;//= game.clone();
		int sum;
		for(int i = 2; i < Horizonal_boxes-2; i++)
		{
			for(int j = 2; j < Vertical_boxes-2; j++)
			{

				sum = game[i-1][j-1] + game[i-1][j] + game[i-1][j+1] ;
				sum+=   game[i][j-1]        +           game[i][j+1] ;
				sum+= game[i+1][j-1] + game[i+1][j] + game[i+1][j+1] ;

				if(game[i][j]==0 && sum==3)
				{	
					tmp_game[i][j]=1;
				}


				if(game[i][j]==1)
				{	
					if(sum<2 || sum>3)
					{
						tmp_game[i][j]=0;
					}
					else
					{
						tmp_game[i][j]=1;
					}	
				}
			}//for j

		}//for i
		
		game =tmp_game.clone();

	}//run game end



}


class DrawPanel extends JPanel
{

	private static final long serialVersionUID = 1L;



	int box_size = 25;
	int NH = 500 ;
	int NV = 500 ;
	int BIAS = 20;

	public void paintComponent(Graphics g)
	{
		int[][] tmp = myFrame.getGame();


		for(int i = 0; i <= myFrame.Horizonal_boxes; i++)
		{
			for(int j = 0; j <= myFrame.Vertical_boxes; j++)
			{
				if(i!=0)
					g.drawString(String.valueOf(i), 0,15+ i * box_size);
				if(j!=0)
					g.drawString(String.valueOf(j),  j * box_size,15);

				g.drawLine(BIAS,BIAS + i * box_size,BIAS + NH, BIAS + i * box_size);
				g.drawLine(i * box_size + BIAS , BIAS,BIAS + i * box_size, BIAS+ NV);

				if(tmp[i][j]==1)
				{

					g.setColor(Color.black);

					g.fillRect((i*box_size)+BIAS,BIAS+ (j*box_size), box_size, box_size);

				}

			}

		}

	}
}