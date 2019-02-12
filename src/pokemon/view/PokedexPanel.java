package pokemon.view;

import java.awt.Color;
	import java.awt.Dimension;
	import javax.swing.*;

	import pokemon.controller.PokedexController;
	
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;


	public class PokedexPanel extends JPanel
	{

		private SpringLayout appLayout;
		private PokedexController appController;
		
		private JButton changeButton;
		private JComboBox pokedexDropdown;
		
		private JTextField numberField;
		private JTextField nameField;
		private JTextField evolveField;
		private JTextField attackField;
		private JTextField enhancementField;
		private JTextField healthField;
		
		private JLabel numberLabel;
		private JLabel nameLabel;
		private JLabel evolveLabel;
		private JLabel attackLabel;
		private JLabel enhanceLabel;
		private JLabel healthLabel;
		private JLabel imageLabel;
		
		public PokedexPanel(PokedexController appController)
		{
			super();
			this.appController = appController;
			appLayout = new SpringLayout();
			
			numberField = new JTextField("0");
			nameField = new JTextField("My pokename");
			appLayout.putConstraint(SpringLayout.NORTH, nameField, 29, SpringLayout.NORTH, this);
			appLayout.putConstraint(SpringLayout.WEST, nameField, 313, SpringLayout.WEST, this);
			evolveField = new JTextField("false");
			attackField = new JTextField("0");
			enhancementField = new JTextField("0");
			healthField = new JTextField("0");
			
			
			
			healthLabel = new JLabel("This pokemans health is");
			numberLabel = new JLabel("This pokmans number is");
			
			evolveLabel = new JLabel("this pokeman can evolve");
			attackLabel = new JLabel("this pokemans attack is");
			enhanceLabel = new JLabel("this pokeman can be enhanced");
			healthLabel = new JLabel("this pokemans health");
			imageLabel = new JLabel();
			
			
			setupScrollPane();
			setupPanel();
			setupLayout();
			setupListeners();	
			
		}
		
		
		
		private void setupLayout()
		{
		
			
		}
		
		private void setupListeners()
		{
			
		}
		
		private void setupScrollPane()
		{
			
		}
		
		private void setupPanel()
		{
			this.setLayout(appLayout);
			this.setPreferredSize(new Dimension(800, 600));
			this.setBackground(Color.GRAY);
			
			this.add(numberField);
			this.add(nameField);
			this.add(evolveField);
			this.add(attackField);
			this.add(enhancementField);
			this.add(healthField);
			
			
			
			this.add(healthLabel);
			this.add(numberLabel);
			
			this.add(evolveLabel);
			this.add(attackLabel);
			this.add(enhanceLabel);
			this.add(healthLabel);
			this.add(imageLabel);
			
		}
	}