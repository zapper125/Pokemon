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
		appLayout = new SpringLayout();

		this.pokemonIcon = new ImageIcon(getClass().getResource("/pokemon/view/images/Psyduck.jpeg"));

		numberField = new JTextField("0");
		nameField = new JTextField("My pokename");
		evolveField = new JTextField("false");
		attackField = new JTextField("0");
		enhancementField = new JTextField("0");
		healthField = new JTextField("0");

		healthLabel = new JLabel("This pokemans health is");
		numberLabel = new JLabel("This pokmans number is");

		evolveLabel = new JLabel("this pokeman can evolve");
		attackLabel = new JLabel("this pokemans attack is");
		enhanceLabel = new JLabel("this pokeman can be enhanced");
		nameLabel = new JLabel("my name is");
		imageLabel = new JLabel("pokemon goes here", pokemonIcon, JLabel.CENTER);
		
				appLayout.putConstraint(SpringLayout.NORTH, imageLabel, 5, SpringLayout.NORTH, enhancementField);
		appLayout.putConstraint(SpringLayout.WEST, imageLabel, 25, SpringLayout.WEST, this);
		changeButton = new JButton("Clck here to change the pokevalues");
		pokedexDropdown = new JComboBox<String>();
		appLayout.putConstraint(SpringLayout.NORTH, pokedexDropdown, 10, SpringLayout.NORTH, this);
		appLayout.putConstraint(SpringLayout.WEST, pokedexDropdown, 10, SpringLayout.WEST, this);

		setupDropdown();
		setupScrollPane();
		setupPanel();
		setupLayout();
		setupListeners();

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
		this.add(numberLabel);
		this.add(evolveLabel);
		this.add(attackLabel);
		this.add(enhanceLabel);
		this.add(imageLabel);
		this.add(pokedexDropdown);
		this.add(saveButton);

		imageLabel.setVerticalTextPosition(JLabel.BOTTOM);
		imageLabel.setHorizontalTextPosition(JLabel.CENTER);

	}

	private void setupDropdown()
	{
		DefaultComboBoxModel<String> temp = new DefaultComboBoxModel<String>(appController.buildPokedexText());
		pokedexDropdown.setModel(temp);
	}

	private void sendDataToController()
	{
		int index = pokedexDropdown.getSelectedIndex();

		if (appController.validInt(attackField.getText()) && appController.validDouble(enhancementField.getText()) & appController.validInt(healthField.getText()))
		{
			String[] data = new String[5];

			// insert code here
			appController.updatePokemon(index, data);
		}
	}

	private void changeImageDisplay(String name)
	{
		String path = "/pokemon/view/images/";
		String defaultName = "ultraball";
		String extension = "png";
		try
		{
			pokemonIcon = new ImageIcon(getClass().getResource(path + name.toLowerCase() + extension));
		} catch (NullPointerException missingFile)
		{
			pokemonIcon = new ImageIcon(getClass().getResource(path + defaultName + extension));
		}
		imageLabel.setIcon(pokemonIcon);
		repaint();
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

	private void setupLayout()
	{
		appLayout.putConstraint(SpringLayout.WEST, healthField, 0, SpringLayout.WEST, numberField);

		appLayout.putConstraint(SpringLayout.NORTH, healthField, -5, SpringLayout.NORTH, healthLabel);
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

		appLayout.putConstraint(SpringLayout.SOUTH, healthLabel, -23, SpringLayout.NORTH, numberLabel);
		appLayout.putConstraint(SpringLayout.EAST, healthLabel, 0, SpringLayout.EAST, nameField);
	}

}