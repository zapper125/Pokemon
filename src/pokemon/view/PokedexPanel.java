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
		private JButton saveButton;
		
		private ImageIcon pokemonIcon;
		
		public PokedexPanel(PokedexController appController)
		{
			super();
			this.appController = appController;
			this.appLayout = new SpringLayout();
			
			this.pokemonIcon = new ImageIcon(getClass().getResource("/pokemon/view/images/pokeball.jpg"));
			
			numberField = new JTextField("0");
			numberField.setEnabled(false);
			nameField = new JTextField("My pokename");
			appLayout.putConstraint(SpringLayout.EAST, nameField, -51, SpringLayout.EAST, this);
			evolveField = new JTextField("false");
			attackField = new JTextField("0");
			appLayout.putConstraint(SpringLayout.NORTH, attackField, 264, SpringLayout.NORTH, this);
			appLayout.putConstraint(SpringLayout.NORTH, nameField, 19, SpringLayout.SOUTH, attackField);
			appLayout.putConstraint(SpringLayout.WEST, numberField, 0, SpringLayout.WEST, attackField);
			enhancementField = new JTextField("0");
			appLayout.putConstraint(SpringLayout.SOUTH, evolveField, -22, SpringLayout.NORTH, enhancementField);
			appLayout.putConstraint(SpringLayout.WEST, enhancementField, 0, SpringLayout.WEST, numberField);
			healthField = new JTextField("0");
			appLayout.putConstraint(SpringLayout.WEST, healthField, 0, SpringLayout.WEST, numberField);

			
			nameLabel = new JLabel("My name is");
			appLayout.putConstraint(SpringLayout.NORTH, nameLabel, 3, SpringLayout.NORTH, nameField);
			healthLabel = new JLabel("This pokemans health is");
			appLayout.putConstraint(SpringLayout.SOUTH, healthLabel, -493, SpringLayout.SOUTH, this);
			appLayout.putConstraint(SpringLayout.NORTH, healthField, -3, SpringLayout.NORTH, healthLabel);
			numberLabel = new JLabel("This pokemans number is");
			appLayout.putConstraint(SpringLayout.WEST, nameLabel, 0, SpringLayout.WEST, numberLabel);
			appLayout.putConstraint(SpringLayout.NORTH, numberLabel, 21, SpringLayout.SOUTH, healthLabel);
			appLayout.putConstraint(SpringLayout.NORTH, numberField, -3, SpringLayout.NORTH, numberLabel);
			evolveLabel = new JLabel("This pokeman can evolve");
			appLayout.putConstraint(SpringLayout.NORTH, evolveLabel, 3, SpringLayout.NORTH, evolveField);
			appLayout.putConstraint(SpringLayout.WEST, evolveLabel, 0, SpringLayout.WEST, numberLabel);
			attackLabel = new JLabel("This pokemans attack is");
			appLayout.putConstraint(SpringLayout.NORTH, attackLabel, 267, SpringLayout.NORTH, this);
			appLayout.putConstraint(SpringLayout.WEST, attackLabel, 0, SpringLayout.WEST, numberLabel);
			enhanceLabel = new JLabel("This pokeman can be enhanced");
			appLayout.putConstraint(SpringLayout.SOUTH, enhanceLabel, -356, SpringLayout.SOUTH, this);
			appLayout.putConstraint(SpringLayout.NORTH, enhancementField, -3, SpringLayout.NORTH, enhanceLabel);
			appLayout.putConstraint(SpringLayout.WEST, enhanceLabel, 0, SpringLayout.WEST, nameLabel);
			imageLabel = new JLabel("", pokemonIcon, JLabel.CENTER);
			appLayout.putConstraint(SpringLayout.WEST, healthLabel, 59, SpringLayout.EAST, imageLabel);
			appLayout.putConstraint(SpringLayout.WEST, numberLabel, 59, SpringLayout.EAST, imageLabel);
			
			pokedexDropdown = new JComboBox<String>();
			appLayout.putConstraint(SpringLayout.WEST, pokedexDropdown, 99, SpringLayout.WEST, this);
			appLayout.putConstraint(SpringLayout.EAST, pokedexDropdown, -528, SpringLayout.EAST, this);

			changeButton = new JButton();
			changeButton.setText("Change");
			saveButton = new JButton();
			saveButton.setText("Save");
			
			setupDropdown();
			setupPanel();
			setupLayout();
			setupListeners();	
			
		}
		
		private void setupDropdown()
		{
			DefaultComboBoxModel<String> temp = new DefaultComboBoxModel<String>(appController.buildPokedexText());
			pokedexDropdown.setModel(temp);
			
		}
		
		
		private void setupLayout()
		{
			
			//attack field
			appLayout.putConstraint(SpringLayout.EAST, evolveField, 0, SpringLayout.EAST, attackField);
			appLayout.putConstraint(SpringLayout.EAST, attackField, -54, SpringLayout.EAST, this);
			
			//DropDown
			appLayout.putConstraint(SpringLayout.NORTH, pokedexDropdown, 6, SpringLayout.SOUTH, imageLabel);
			
			//change button 
			appLayout.putConstraint(SpringLayout.NORTH, changeButton, 19, SpringLayout.SOUTH, pokedexDropdown);
			appLayout.putConstraint(SpringLayout.WEST, changeButton, 148, SpringLayout.WEST, this);
		
			//save button
			appLayout.putConstraint(SpringLayout.NORTH, saveButton, 14, SpringLayout.SOUTH, changeButton);
			appLayout.putConstraint(SpringLayout.WEST, saveButton, 158, SpringLayout.WEST, this);
		}
		
		private void sendDataToController()
	    {
	        int index = pokedexDropdown.getSelectedIndex();
	        
	        if(appController.isInt(attackField.getText()) && appController.isDouble(enhancementField.getText()) && appController.isInt(healthField.getText()))
	        {
	            String [] data = new String[5];
	            data[0] = attackField.getText();
	            data[1] = enhancementField.getText();
	            data[2] = healthField.getText();
	            data[3] = nameField.getText();
	            data[4] = evolveField.getText();
	            
	            appController.updatePokemon(index, data);
	        }
	    }
		
		private void changeImageDisplay(String name)
		{
			String path = "/pokemon/view/images/";
			String defaultName = "pokeball";
			String extension = ".jpeg";
			try
			{
				pokemonIcon = new ImageIcon(getClass().getResource(path + name.toLowerCase() + extension));
			}
			catch (NullPointerException missingFile)
			{
				pokemonIcon = new ImageIcon(getClass().getResource(path + defaultName + extension));
			}
			imageLabel.setIcon(pokemonIcon);
			repaint();
		}
		
		private void updateFields(int index)
		{
			String [] data = appController.getPokeData(index);
			
			attackField.setText(data[0]);
			enhancementField.setText(data[1]);
			healthField.setText(data[2]);
			nameField.setText(data[3]);
			evolveField.setText(data[4]);
			numberField.setText(data[5]);
			
		}
		
		private void setupListeners()
		{
			changeButton.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent click)
				{
					sendDataToController();
				}
					});
			
			pokedexDropdown.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent selection)
				{
					String name = pokedexDropdown.getSelectedItem().toString();
					updateFields(pokedexDropdown.getSelectedIndex());
					changeImageDisplay(name);
				}
			});
			
			saveButton.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent click)
                {
                    appController.savePokedex();
                }
            });
			
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
			
			this.add(nameLabel);
			this.add(numberLabel);
			this.add(evolveLabel);
			this.add(attackLabel);
			this.add(enhanceLabel);
			this.add(healthLabel);
			this.add(imageLabel);
			
			this.add(pokedexDropdown);
			
			this.add(changeButton);
			this.add(saveButton);
			
			imageLabel.setVerticalTextPosition(JLabel.BOTTOM);
			imageLabel.setHorizontalTextPosition(JLabel.CENTER);
			
		}
	}
