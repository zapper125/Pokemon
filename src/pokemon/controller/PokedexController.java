package pokemon.controller;

import javax.swing.JOptionPane;

import pokemon.model.Pokemon;
import pokemon.view.PokedexFrame;

public class PokedexController
{
	private PokedexFrame appFrame;

	public PokedexController()
	{
		appFrame = new PokedexFrame(this);
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
	String [] names =  new String [pokemonList.size()];
			
	for(int index = 0; index < pokemonList.size() index++);
			{
				names[index]= pokemonList.get(index).getName();
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
		}
		catch (NumberFormatException error)
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
		}
		catch (NumberFormatException error)
		{
			JOptionPane.showMessageDialog(null,  "Type in a decimal value aka a number with a . in it");
		}
		
		return isValid;
	
	}

}
