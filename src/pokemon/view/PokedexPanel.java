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
			appLayout.putConstraint(SpringLayout.EAST, nameField, -54, SpringLayout.EAST, this);
			evolveField = new JTextField("false");
			evolveField.setEnabled(false);
			appLayout.putConstraint(SpringLayout.EAST, evolveField, -54, SpringLayout.EAST, this);
			attackField = new JTextField("0");
			appLayout.putConstraint(SpringLayout.NORTH, attackField, 345, SpringLayout.NORTH, this);
			appLayout.putConstraint(SpringLayout.EAST, attackField, -54, SpringLayout.EAST, this);
			appLayout.putConstraint(SpringLayout.WEST, numberField, 0, SpringLayout.WEST, attackField);
			enhancementField = new JTextField("0");
			appLayout.putConstraint(SpringLayout.EAST, enhancementField, -54, SpringLayout.EAST, this);
			healthField = new JTextField("0");
			appLayout.putConstraint(SpringLayout.EAST, healthField, -54, SpringLayout.EAST, this);

			
			nameLabel = new JLabel("My name is");
			appLayout.putConstraint(SpringLayout.NORTH, nameLabel, 5, SpringLayout.NORTH, nameField);
			healthLabel = new JLabel("This pokemans health is");
			appLayout.putConstraint(SpringLayout.SOUTH, healthLabel, -493, SpringLayout.SOUTH, this);
			appLayout.putConstraint(SpringLayout.NORTH, healthField, -3, SpringLayout.NORTH, healthLabel);
			numberLabel = new JLabel("This pokemans number is");
			appLayout.putConstraint(SpringLayout.WEST, nameLabel, 0, SpringLayout.WEST, numberLabel);
			appLayout.putConstraint(SpringLayout.NORTH, numberField, -5, SpringLayout.NORTH, numberLabel);
			evolveLabel = new JLabel("This pokeman can evolve");
			appLayout.putConstraint(SpringLayout.SOUTH, numberLabel, -34, SpringLayout.NORTH, evolveLabel);
			appLayout.putConstraint(SpringLayout.NORTH, evolveField, -5, SpringLayout.NORTH, evolveLabel);
			attackLabel = new JLabel("This pokemans attack is");
			appLayout.putConstraint(SpringLayout.NORTH, attackLabel, 350, SpringLayout.NORTH, this);
			enhanceLabel = new JLabel("This pokeman can be enhanced");
			appLayout.putConstraint(SpringLayout.SOUTH, enhanceLabel, -281, SpringLayout.SOUTH, this);
			appLayout.putConstraint(SpringLayout.EAST, enhanceLabel, -45, SpringLayout.WEST, enhancementField);
			appLayout.putConstraint(SpringLayout.SOUTH, evolveLabel, -37, SpringLayout.NORTH, enhanceLabel);
			appLayout.putConstraint(SpringLayout.NORTH, enhancementField, -5, SpringLayout.NORTH, enhanceLabel);
			imageLabel = new JLabel("", pokemonIcon, JLabel.CENTER);
			appLayout.putConstraint(SpringLayout.SOUTH, nameField, 0, SpringLayout.SOUTH, imageLabel);
			appLayout.putConstraint(SpringLayout.WEST, attackLabel, 59, SpringLayout.EAST, imageLabel);
			appLayout.putConstraint(SpringLayout.WEST, evolveLabel, 59, SpringLayout.EAST, imageLabel);
			appLayout.putConstraint(SpringLayout.WEST, numberLabel, 59, SpringLayout.EAST, imageLabel);
			appLayout.putConstraint(SpringLayout.WEST, healthLabel, 59, SpringLayout.EAST, imageLabel);
			
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
