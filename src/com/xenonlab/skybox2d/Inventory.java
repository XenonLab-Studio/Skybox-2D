package com.xenonlab.skybox2d;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;


public class Inventory implements MouseWheelListener {

	//INVENTORY
	private final int INVENTORY_COLS = 8;
	private final int INVENTORY_ROWS = 4;
	private final int INVENTORY_SPACE = 2;
	private final int INVENTORY_MARGIN = 16;
	private Cell[][] inventory;
	private boolean open;
	
	//TOOLBAR
	private final int TOOLBAR_SLOTS = 8;
	private final int TOOLBAR_SPACE = 2;
	private final int TOOLBAR_MARGIN = 10;
	private final int CELL_SIZE = 16;
	private Cell[] toolbar;
	private int selected = 0;
	
	public Inventory() {
		GamePanel.mouse.addMouseWheelListener(this);
		
		toolbar = new Cell[TOOLBAR_SLOTS];
		for(int i = 0; i < toolbar.length; i++) {
			toolbar[i] = new Cell(getToolbarX() + i * (CELL_SIZE+TOOLBAR_SPACE), getToolbarY(), CELL_SIZE, CELL_SIZE);
		}
		
		inventory = new Cell[INVENTORY_ROWS][INVENTORY_COLS];
		for(int row = 0; row < INVENTORY_ROWS; row++) {
			for(int col = 0; col < INVENTORY_COLS; col++) {
				inventory[row][col] = new Cell(getInventoryX() + col * (CELL_SIZE+INVENTORY_SPACE), 
										getInventoryY() + row * ((CELL_SIZE+INVENTORY_SPACE)), CELL_SIZE, CELL_SIZE);
			}
		}
	}
	
	public void render(Graphics2D g) {
		for(int i = 0; i < toolbar.length; i++) {
			toolbar[i].render(g);
			if(toolbar[i].getItem() != null) {
				g.drawImage(toolbar[i].getItem().getImage(), (int)toolbar[i].getX(), (int)toolbar[i].getY(), null);
				g.setColor(Color.WHITE);
				g.drawString(String.valueOf(toolbar[i].getItemAmount()), (int)toolbar[i].getX() + 2, (int)toolbar[i].getY() + 12);
			}
		}
		
		if(open) {
			for(int row = 0; row < INVENTORY_ROWS; row++) {
				for(int col = 0; col < INVENTORY_COLS; col++) {
					inventory[row][col].render(g);
					if(inventory[row][col].getItem() != null) {
						g.drawImage(inventory[row][col].getItem().getImage(), (int)inventory[row][col].getX(), (int)inventory[row][col].getX(), null);
					}
				}
			}
		}
		
		Cell selectedCell = toolbar[selected];
		g.drawImage(Cell.SELECTED_TEXTURE, (int)selectedCell.getX(), (int)selectedCell.getY(), null);
	}
	
	
	public void update() {
		for(int i = 0; i < toolbar.length; i++) {
			toolbar[i].setLocation(getToolbarX() + i * (CELL_SIZE+TOOLBAR_SPACE), getToolbarY());
		}
	}
	
	
	
	//ADDING ITEMS TO INVENTORY --- FIRST ADD ITEM TO TOOLBAR, IF IS FULL TO INVENTORY
	public void addItem(Item item) {
		for(int i = 0; i < toolbar.length; i++) {
			if(toolbar[i].getItem() == null) {
				toolbar[i].addItem(item);
				return;
			}
		}
		
		for(int row = 0; row < INVENTORY_ROWS; row++) {
			for(int col = 0; col < INVENTORY_COLS; col++) {
				if(inventory[row][col].getItem() == null) {
					inventory[row][col].addItem(item);
					return;
				}
				
			}
		}
	}
	
	
	//SCROLLING THROUGH TOOLBAR
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		if(e.getWheelRotation() < 0) {
			selected--;
		} else if(e.getWheelRotation() > 0) {
			selected++;
		}
		
		if(selected >= toolbar.length) {
			selected = 0;
		} else if(selected < 0) {
			selected = toolbar.length - 1;
		}
	}
	
	public Item getSelectedItem() {
		return toolbar[selected].getItem();
	}
	
	public int getToolbarY() {
		return GamePanel.height / GamePanel.SCALE  - (TOOLBAR_MARGIN + CELL_SIZE);
	}
	
	public int getToolbarX() {
		return GamePanel.width / 2 / GamePanel.SCALE - (toolbar.length * (CELL_SIZE + TOOLBAR_SPACE)) / 2;
	}
	
	public int getInventoryX() {
		return getToolbarX();
	}
	
	public int getInventoryY() {
		return getToolbarY() - INVENTORY_MARGIN - (INVENTORY_ROWS*(CELL_SIZE + TOOLBAR_SPACE));
	}
}
