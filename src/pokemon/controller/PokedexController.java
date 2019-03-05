package pokemon.controller;

import java.util.ArrayList;
import java.io.*; 

import javax.swing.JOptionPane;

import pokemon.model.Cyndaquil;
import pokemon.model.Magikarp;
import pokemon.model.Mudkip;
import pokemon.model.Pokemon;
import pokemon.model.Psyduck;
import pokemon.model.Squirtle;
import pokemon.view.PokedexFrame;

public class PokedexController
{
	private PokedexFrame appFrame;
	private ArrayList<Pokemon> pokemonList;
	private String saveFile = "backup.pokemon";
	
	public PokedexController()
	{
		pokemonList = new ArrayList<Pokemon>();
		addPokemon();
		appFrame = new PokedexFrame(this);
	}

	public void addPokemon()
	{
		pokemonList.add(new Psyduck());
		pokemonList.add(new Cyndaquil());
		pokemonList.add(new Mudkip());
		pokemonList.add(new Magikarp());
		pokemonList.add(new Squirtle());
	}

	public void start()
	{
		// TODO Auto-generated method stub

	}

	public void updatePokemon(int index, String[] data)
	{
		if (data.length == 5)
		{
			Pokemon current = pokemonList.get(index);
			current.setAttackPoints(Integer.parseInt(data[0]));
			current.setEnhancementModifier(Double.parseDouble(data[1]));
			current.setHealthPoints(Integer.parseInt(data[2]));
			current.setName(data[3]);
			current.setCanEvolve(Boolean.parseBoolean(data[4]));
		}
	}

	public String[] buildPokedexText()
	{
		String[] names = new String[pokemonList.size()];

		for (int index = 0; index < pokemonList.size(); index++)
		{
			names[index] = pokemonList.get(index).getName();
		}
		return names;
	}

	public boolean validInt(String maybeInt)
	{
		boolean isValid = false;

		try
		{
			Integer.parseInt(maybeInt);
			isValid = true;
		} catch (NumberFormatException error)
		{
			JOptionPane.showMessageDialog(null, "You need to type in a number :/");
		}
		return isValid;
	}

	public boolean validDouble(String mightbeDouble)
	{
		boolean isValid = false;

		try
		{
			Double.parseDouble(mightbeDouble);
			isValid = true;
		} catch (NumberFormatException error)
		{
			JOptionPane.showMessageDialog(null, "Type in a decimal value aka a number with a . in it");
		}

		return isValid;

	}
	
	public String [] getPokeData(int index)
	{
        String [] data = new String [6];
        Pokemon current = pokemonList.get(index);
        data[0] = current.getAttackPoints() + "";
        data[1] = current.getEnhancementModifier() + "";
        data[2] = current.getHealthPoints() + "";
        data[3] = current.getName() + "";
        data[4] = current.isCanEvolve() + "";
        data[5] = current.getNumber() + "";
        return data;
    }

	public void savePokedex()
	{
		try
		{
			FileOutputStream saveStream = new FileOutputStream(saveFile);
            ObjectOutputStream output = new ObjectOutputStream(saveStream);
            output.writeObject(pokemonList);
            output.close();
            saveStream.close();
		}
		catch(IOException error)
		{
			JOptionPane.showMessageDialog(appFrame, error.getMessage(), "File Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void loadPokedex()
	{
		try {
			ArrayList<Pokemon> saved = new ArrayList<Pokemon>();
			FileInputStream inputStream = new FileInputStream(saveFile);
			ObjectInputStream input = new ObjectInputStream(inputStream);
			saved = (ArrayList<Pokemon>) input.readObject();
			input.close();
			inputStream.close();
			pokemonList = saved;
		}
		catch(IOException error)
		{
			JOptionPane.showMessageDialog(appFrame, "No Save file", "Loading Pokemon", JOptionPane.INFORMATION_MESSAGE);
		}
		catch (ClassNotFoundException pokemonError)
		{
			JOptionPane.showMessageDialog(appFrame, pokemonError.getMessage(), "Type Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public Object getFrame()
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
	
	
}
