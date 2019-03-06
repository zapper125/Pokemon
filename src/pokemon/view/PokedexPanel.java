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
		private JButton saveButton;
		
		private ImageIcon pokemonIcon;
		
		public PokedexPanel(PokedexController appController)
		{
			super();
			this.appController = appController;
			this.appLayout = new SpringLayout();
			
			this.pokemonIcon = new ImageIcon(getClass().getResource("/pokemon/view/images/pokeball.jpg"));
			
			numberField = new JTextField("0");
			appLayout.putConstraint(SpringLayout.EAST, numberField, -69, SpringLayout.EAST, this);
			nameField = new JTextField("My pokename");
			appLayout.putConstraint(SpringLayout.SOUTH, nameField, -273, SpringLayout.SOUTH, this);
			appLayout.putConstraint(SpringLayout.EAST, nameField, -28, SpringLayout.EAST, this);
			evolveField = new JTextField("false");
			attackField = new JTextField("0");
			appLayout.putConstraint(SpringLayout.EAST, evolveField, 0, SpringLayout.EAST, attackField);
			appLayout.putConstraint(SpringLayout.EAST, attackField, -54, SpringLayout.EAST, this);
			enhancementField = new JTextField("0");
			appLayout.putConstraint(SpringLayout.SOUTH, enhancementField, -350, SpringLayout.SOUTH, this);
			appLayout.putConstraint(SpringLayout.NORTH, attackField, 14, SpringLayout.SOUTH, enhancementField);
			appLayout.putConstraint(SpringLayout.WEST, enhancementField, 0, SpringLayout.WEST, attackField);
			healthField = new JTextField("0");
			appLayout.putConstraint(SpringLayout.WEST, healthField, 0, SpringLayout.WEST, numberField);
			appLayout.putConstraint(SpringLayout.SOUTH, healthField, -15, SpringLayout.NORTH, numberField);
			
			nameLabel = new JLabel("my name is");
			appLayout.putConstraint(SpringLayout.NORTH, nameLabel, 5, SpringLayout.NORTH, nameField);
			appLayout.putConstraint(SpringLayout.EAST, nameLabel, -56, SpringLayout.WEST, nameField);
			
			healthLabel = new JLabel("This pokemans health is");
			numberLabel = new JLabel("This pokemans number is");
			appLayout.putConstraint(SpringLayout.NORTH, numberLabel, 130, SpringLayout.NORTH, this);
			appLayout.putConstraint(SpringLayout.NORTH, numberField, -5, SpringLayout.NORTH, numberLabel);
			
			evolveLabel = new JLabel("this pokeman can evolve");
			appLayout.putConstraint(SpringLayout.EAST, numberLabel, 0, SpringLayout.EAST, evolveLabel);
			appLayout.putConstraint(SpringLayout.NORTH, evolveField, -5, SpringLayout.NORTH, evolveLabel);
			appLayout.putConstraint(SpringLayout.EAST, evolveLabel, -169, SpringLayout.EAST, this);
			attackLabel = new JLabel("this pokemans attack is");
			appLayout.putConstraint(SpringLayout.EAST, attackLabel, -179, SpringLayout.EAST, this);
			enhanceLabel = new JLabel("this pokeman can be enhanced");
			appLayout.putConstraint(SpringLayout.SOUTH, enhanceLabel, -355, SpringLayout.SOUTH, this);
			appLayout.putConstraint(SpringLayout.NORTH, attackLabel, 24, SpringLayout.SOUTH, enhanceLabel);
			appLayout.putConstraint(SpringLayout.SOUTH, evolveLabel, -27, SpringLayout.NORTH, enhanceLabel);
			appLayout.putConstraint(SpringLayout.WEST, enhanceLabel, 0, SpringLayout.WEST, attackLabel);
			healthLabel_1 = new JLabel("this pokemans health");
			appLayout.putConstraint(SpringLayout.NORTH, healthLabel_1, 5, SpringLayout.NORTH, healthField);
			appLayout.putConstraint(SpringLayout.EAST, healthLabel_1, 0, SpringLayout.EAST, nameLabel);
			imageLabel = new JLabel("pokemon goes here", pokemonIcon, JLabel.CENTER);
			appLayout.putConstraint(SpringLayout.NORTH, imageLabel, 5, SpringLayout.NORTH, healthField);
			appLayout.putConstraint(SpringLayout.WEST, imageLabel, 102, SpringLayout.WEST, this);
			
			pokedexDropdown = new JComboBox<String>();
			appLayout.putConstraint(SpringLayout.NORTH, pokedexDropdown, 1, SpringLayout.NORTH, attackField);
			appLayout.putConstraint(SpringLayout.WEST, pokedexDropdown, 10, SpringLayout.WEST, imageLabel);
			appLayout.putConstraint(SpringLayout.EAST, pokedexDropdown, 183, SpringLayout.WEST, imageLabel);
			
			changeButton = new JButton();
			changeButton.setText("Change");
			appLayout.putConstraint(SpringLayout.NORTH, changeButton, 0, SpringLayout.NORTH, nameField);
			appLayout.putConstraint(SpringLayout.WEST, changeButton, 152, SpringLayout.WEST, this);
			
			saveButton = new JButton();
			appLayout.putConstraint(SpringLayout.NORTH, saveButton, 6, SpringLayout.SOUTH, changeButton);
			appLayout.putConstraint(SpringLayout.WEST, saveButton, 10, SpringLayout.WEST, changeButton);
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
		
			
		}
		
		private void sendDataToController()
		{
			int index = pokedexDropdown.getSelectedIndex();
			
			if(appController.validInt(attackField.getText()) && appController.validDouble(enhancementField.getText()) && appController.validInt(healthField.getText()))
			{
				String [] data = new String[5];
				
				appController.updatePokemon(index, data);
			}                                  
		}
		
		private void changeImageDisplay(String name)
		{
			String path = "/pokemon/view/images/";
			String defaultName = "pokeball";
			String extension = ".jpg";
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
			
			
			this.add(healthLabel_1);
			this.add(numberLabel);
			
			this.add(evolveLabel);
			this.add(attackLabel);
			this.add(enhanceLabel);
			
			this.add(imageLabel);
			this.add(pokedexDropdown);
			
			this.add(changeButton);
			this.add(saveButton);
			
			imageLabel.setVerticalTextPosition(JLabel.BOTTOM);
			imageLabel.setHorizontalTextPosition(JLabel.CENTER);
			
		}
	}
