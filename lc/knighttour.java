package knight;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

import java.awt.*;
import java.awt.event.WindowStateListener;

public class Test  {
	
	JFrame f;
	
	String[] columnsNames = { "A", "B", "C", "D", "E", "F", "G", "H" };

	static Object[][] data = { { "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "" },
			{ "", "", "", "", "", "", "", "" }, { "", "", "", "", "", "", "", "" } };
	/*		
	String[] columnsNames = { "A", "B", "C", "D", "E" };

	static Object[][] data = { 
			{ "", "", "", "", "" }, 
			{ "", "", "", "", "" }, 
			{ "", "", "", "", "" }, 
			{ "", "", "", "", "" }, 
			{ "", "", "", "", "" }
			};*/
	JTable table = new JTable(data, columnsNames);

	public Test() {

		f = new JFrame();
		f.setTitle(" Knight Tour ");
		
		
		table.setRowHeight(80);
		for(int i=0;i<data[0].length;i++) table.getColumnModel().getColumn(i).setMaxWidth(80);
//		table.setBounds(30, 40, 800, 300);
		JScrollPane sp = new JScrollPane(table);
        f.add(sp);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		f.setSize(700, 700);
		f.setVisible(true);
		
		table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {

	        @Override
	        public Component getTableCellRendererComponent(JTable table, 
	                Object value, boolean isSelected, boolean hasFocus,
	                int row, int column) {
	            Component c = super.getTableCellRendererComponent(table, 
	                value, isSelected, hasFocus, row, column);
	            c.setBackground((row+column)%2==0 ? Color.white : Color.gray);  
	            return c;
	        };
	    });
	}

    public static void main(String[] args) {
        Test t = new Test();
//        t.solve(0,0, chessmv,1); //this works
//        t.solveTwo(0,0, chessmv,1); //this works
        t.solveThree(0,0, chessmv,1); //this works
        System.out.println("Solve completed!");
    }
    
    static int R = data.length;
    static int C = data[0].length;
    static int[][] chessmv = new int[R][C];
    int[] delx = new int[] {-2,-1, 1, 2, 2, 1,-1,-2};
    int[] dely = new int[] { 1, 2, 2, 1,-1,-2,-2,-1};
    
    
    private boolean isValidPos(int x, int y, int[][] chess) {
    	if(x<0 || x>=chess.length || y<0 || y>=chess[0].length || chess[x][y] > 0) 
    		return false;
    	else 
    		return true;
    }
    
    private void sleep(int sec10) {
    	try {
			Thread.sleep(sec10*100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public boolean solve(int x, int y, int[][] chessmv, int cc) {
    	if(cc>=data.length*data[0].length) {
    		System.out.println(" found it");
    		data[x][y] = ""+cc;
        	((AbstractTableModel) table.getModel()).fireTableCellUpdated(x, y); // Repaint one cell.
    		return true;
    	}
    	
    	data[x][y] = cc;
    	((AbstractTableModel) table.getModel()).fireTableCellUpdated(x, y); // Repaint one cell.
		boolean res = false;
    	for(int i=0;i<delx.length;i++) {
    		int newx = x + delx[i];
    		int newy = y + dely[i];
    		if(isValidPos(newx, newy, chessmv)) {
    			chessmv[x][y] = cc;
    			res = solve(newx, newy, chessmv,cc+1);
    			if(res) return true;
    			else
    				chessmv[x][y] = 0;
    			
    		}
    	}
		
    	return res;
    }
    
    public void solveTwo(int x, int y, int[][] chessmv, int cc) {
    	if(cc>=data.length*data[0].length) {
    		System.out.println(" found it");
    		data[x][y] = ""+cc;
        	((AbstractTableModel) table.getModel()).fireTableCellUpdated(x, y); // Repaint one cell.
    		return ;
    	}
    	
    	data[x][y] = cc;
    	((AbstractTableModel) table.getModel()).fireTableCellUpdated(x, y); // Repaint one cell.
		for(int i=0;i<delx.length;i++) {
    		int newx = x + delx[i];
    		int newy = y + dely[i];
    		if(isValidPos(newx, newy, chessmv)) {
    			chessmv[x][y] = cc;
    			solve(newx, newy, chessmv,cc+1);
    			chessmv[x][y] = 0;
    		}
    	}
    }
    
    static boolean isFound = false;
    public void solveThree(int x, int y, int[][] chessmv, int cc) {
    	if(!isValidPos(x, y, chessmv)) {
    		return;
    	}
    	
    	else if(cc>=data.length*data[0].length) {
    		System.out.println(" found it");
    		isFound = true;
//    		data[x][y] = ""+cc;
//        	((AbstractTableModel) table.getModel()).fireTableCellUpdated(x, y); // Repaint one cell.
    		return ;
    	}
    	
    	chessmv[x][y] = cc;
    	data[x][y] = cc;
    	((AbstractTableModel) table.getModel()).fireTableCellUpdated(x, y); // Repaint one cell.
		for(int i=0;i<delx.length;i++) {
    		int newx = x + delx[i];
    		int newy = y + dely[i];
    		if(!isFound) {
//    			chessmv[x][y] = cc; // this also works
    			solveThree(newx, newy, chessmv,cc+1);
//    			chessmv[x][y] = 0;
    		}
    		else break;
    		
    	}
		chessmv[x][y] = 0;
    }
}
