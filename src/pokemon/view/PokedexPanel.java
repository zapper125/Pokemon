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
			nameField = new JTextField("My pokename");
			appLayout.putConstraint(SpringLayout.EAST, nameField, -27, SpringLayout.EAST, this);
			appLayout.putConstraint(SpringLayout.EAST, numberField, 0, SpringLayout.EAST, nameField);
			evolveField = new JTextField("false");
			appLayout.putConstraint(SpringLayout.EAST, evolveField, -27, SpringLayout.EAST, this);
			attackField = new JTextField("0");
			appLayout.putConstraint(SpringLayout.EAST, attackField, -27, SpringLayout.EAST, this);
			enhancementField = new JTextField("0");
			appLayout.putConstraint(SpringLayout.EAST, enhancementField, -27, SpringLayout.EAST, this);
			healthField = new JTextField("0");
			appLayout.putConstraint(SpringLayout.WEST, healthField, 418, SpringLayout.WEST, this);
			
			nameLabel = new JLabel("my name is");
			appLayout.putConstraint(SpringLayout.NORTH, nameField, -5, SpringLayout.NORTH, nameLabel);
			appLayout.putConstraint(SpringLayout.SOUTH, nameLabel, -84, SpringLayout.SOUTH, this);
			
			healthLabel = new JLabel("This pokemans health is");
			numberLabel = new JLabel("This pokemans number is");
			appLayout.putConstraint(SpringLayout.NORTH, numberField, -5, SpringLayout.NORTH, numberLabel);
			appLayout.putConstraint(SpringLayout.WEST, numberLabel, 0, SpringLayout.WEST, nameLabel);
			
			evolveLabel = new JLabel("This pokeman can evolve");
			appLayout.putConstraint(SpringLayout.SOUTH, numberLabel, -48, SpringLayout.NORTH, evolveLabel);
			appLayout.putConstraint(SpringLayout.NORTH, evolveField, -5, SpringLayout.NORTH, evolveLabel);
			appLayout.putConstraint(SpringLayout.WEST, evolveLabel, 0, SpringLayout.WEST, nameLabel);
			attackLabel = new JLabel("this pokemans attack is");
			appLayout.putConstraint(SpringLayout.NORTH, attackField, -5, SpringLayout.NORTH, attackLabel);
			appLayout.putConstraint(SpringLayout.WEST, attackLabel, 0, SpringLayout.WEST, nameLabel);
			appLayout.putConstraint(SpringLayout.SOUTH, attackLabel, -39, SpringLayout.NORTH, nameField);
			enhanceLabel = new JLabel("This pokeman can be enhanced");
			appLayout.putConstraint(SpringLayout.SOUTH, evolveLabel, -44, SpringLayout.NORTH, enhanceLabel);
			appLayout.putConstraint(SpringLayout.NORTH, enhancementField, -5, SpringLayout.NORTH, enhanceLabel);
			appLayout.putConstraint(SpringLayout.WEST, enhanceLabel, 0, SpringLayout.WEST, nameLabel);
			appLayout.putConstraint(SpringLayout.SOUTH, enhanceLabel, -44, SpringLayout.NORTH, attackLabel);
			healthLabel_1 = new JLabel("this pokemans health");
			appLayout.putConstraint(SpringLayout.WEST, healthLabel_1, 188, SpringLayout.WEST, this);
			imageLabel = new JLabel("pokemon goes here", pokemonIcon, JLabel.CENTER);
			appLayout.putConstraint(SpringLayout.WEST, nameLabel, 0, SpringLayout.EAST, imageLabel);
			appLayout.putConstraint(SpringLayout.SOUTH, imageLabel, -66, SpringLayout.SOUTH, this);
			appLayout.putConstraint(SpringLayout.SOUTH, healthField, -6, SpringLayout.NORTH, imageLabel);
			appLayout.putConstraint(SpringLayout.SOUTH, healthLabel_1, -6, SpringLayout.NORTH, imageLabel);
			appLayout.putConstraint(SpringLayout.WEST, imageLabel, 102, SpringLayout.WEST, this);
			
			pokedexDropdown = new JComboBox<String>();
			appLayout.putConstraint(SpringLayout.NORTH, numberLabel, -9, SpringLayout.NORTH, pokedexDropdown);
			appLayout.putConstraint(SpringLayout.WEST, pokedexDropdown, 112, SpringLayout.WEST, this);
			appLayout.putConstraint(SpringLayout.EAST, pokedexDropdown, -515, SpringLayout.EAST, this);
			
			changeButton = new JButton();
			appLayout.putConstraint(SpringLayout.SOUTH, pokedexDropdown, -9, SpringLayout.NORTH, changeButton);
			changeButton.setText("Change");
			appLayout.putConstraint(SpringLayout.WEST, changeButton, 152, SpringLayout.WEST, this);
			
			saveButton = new JButton();
			appLayout.putConstraint(SpringLayout.NORTH, saveButton, 336, SpringLayout.NORTH, this);
			appLayout.putConstraint(SpringLayout.SOUTH, changeButton, -6, SpringLayout.NORTH, saveButton);
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
