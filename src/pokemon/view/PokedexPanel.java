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
		private JLabel healthLabel_1;
		private JLabel imageLabel;
		
		public PokedexPanel(PokedexController appController)
		{
			super();
			this.appController = appController;
			appLayout = new SpringLayout();
			
			numberField = new JTextField("0");
			nameField = new JTextField("My pokename");

			evolveField = new JTextField("false");

			attackField = new JTextField("0");
			enhancementField = new JTextField("0");
			healthField = new JTextField("0");
			appLayout.putConstraint(SpringLayout.WEST, healthField, 0, SpringLayout.WEST, numberField);

			
			
			
			healthLabel = new JLabel("This pokemans health is");
			numberLabel = new JLabel("This pokmans number is");

			
			evolveLabel = new JLabel("this pokeman can evolve");

			attackLabel = new JLabel("this pokemans attack is");
			
			enhanceLabel = new JLabel("this pokeman can be enhanced");
			
			healthLabel_1 = new JLabel("this pokemans health");
			appLayout.putConstraint(SpringLayout.NORTH, healthField, -5, SpringLayout.NORTH, healthLabel_1);
			imageLabel = new JLabel();
			changeButton = new JButton("Click here to change the pokevalues");
			pokedexDropdown = new JComboBox<String>();
			appLayout.putConstraint(SpringLayout.NORTH, pokedexDropdown, 83, SpringLayout.NORTH, this);
			appLayout.putConstraint(SpringLayout.EAST, pokedexDropdown, -288, SpringLayout.EAST, this);

			
			setupDropdown();
			setupScrollPane();
			setupPanel();
			setupLayout();
			setupListeners();	
			
		}
		
		private void setupDropdown()
		{
			DefaultComboBoxModel<String> temp = new DefaultComboBoxModel<String>(appLayout.buildPokedexText());
			pokedexDropdown.setModel(temp);
		}
		
		private void setupLayout()
		{
			appLayout.putConstraint(SpringLayout.NORTH, nameField, 29, SpringLayout.NORTH, this);
			appLayout.putConstraint(SpringLayout.WEST, nameField, 313, SpringLayout.WEST, this);
			
			appLayout.putConstraint(SpringLayout.EAST, numberField, 0, SpringLayout.EAST, evolveField);
			
			appLayout.putConstraint(SpringLayout.NORTH, numberField, 15, SpringLayout.NORTH, numberLabel);
			appLayout.putConstraint(SpringLayout.NORTH, numberLabel, 114, SpringLayout.SOUTH, nameField);
			appLayout.putConstraint(SpringLayout.EAST, numberLabel, 0, SpringLayout.EAST, nameField);
			
			appLayout.putConstraint(SpringLayout.NORTH, evolveField, -5, SpringLayout.NORTH, evolveLabel);
			appLayout.putConstraint(SpringLayout.WEST, evolveField, 142, SpringLayout.EAST, evolveLabel);
			appLayout.putConstraint(SpringLayout.NORTH, evolveLabel, 204, SpringLayout.SOUTH, nameField);
			appLayout.putConstraint(SpringLayout.EAST, evolveLabel, 0, SpringLayout.EAST, nameField);
			appLayout.putConstraint(SpringLayout.SOUTH, numberLabel, -33, SpringLayout.NORTH, evolveLabel);
			
			appLayout.putConstraint(SpringLayout.NORTH, attackField, -5, SpringLayout.NORTH, attackLabel);
			appLayout.putConstraint(SpringLayout.WEST, attackField, 164, SpringLayout.EAST, attackLabel);
			appLayout.putConstraint(SpringLayout.EAST, attackLabel, 0, SpringLayout.EAST, nameField);
			appLayout.putConstraint(SpringLayout.NORTH, attackLabel, 74, SpringLayout.SOUTH, evolveLabel);
			
			appLayout.putConstraint(SpringLayout.NORTH, enhancementField, -5, SpringLayout.NORTH, enhanceLabel);
			appLayout.putConstraint(SpringLayout.WEST, enhancementField, 164, SpringLayout.EAST, enhanceLabel);
			appLayout.putConstraint(SpringLayout.EAST, enhanceLabel, 0, SpringLayout.EAST, nameField);
			appLayout.putConstraint(SpringLayout.NORTH, enhanceLabel, 74, SpringLayout.SOUTH, attackLabel);
			
			appLayout.putConstraint(SpringLayout.SOUTH, healthLabel_1, -23, SpringLayout.NORTH, numberLabel);
			appLayout.putConstraint(SpringLayout.EAST, healthLabel_1, 0, SpringLayout.EAST, nameField);
			
			appLayout.putConstraint(SpringLayout.NORTH, imageLabel, 164, SpringLayout.NORTH, this);
			appLayout.putConstraint(SpringLayout.WEST, imageLabel, 0, SpringLayout.WEST, this);
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
			this.add(healthLabel_1);
			this.add(numberLabel);	
			this.add(evolveLabel);
			this.add(attackLabel);
			this.add(enhanceLabel);
			this.add(healthLabel_1);
			this.add(imageLabel);
			this.add(pokedexDropdown);
			
		}
	}