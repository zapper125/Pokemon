package pokemon.view;

import java.awt.Container;
import javax.swing.JFrame;
import pokemon.controller.PokedexController;

public class PokedexFrame extends JFrame
{
	private PokedexController appController;
	private PokedexPanel appPanel;
	
	public PokedexFrame(PokedexController appController)
	{
		super();
		this.appController = appController;
		this.appPanel = new PokedexPanel(appController);
		setupFrame();
		
	}
	
	private void setupFrame()
	{
		this.setContentPane(appPanel);
		this.setSize(800, 800);
		this.setTitle("banana");
		this.setResizable(true);
		this.setVisible(true);
	}
}