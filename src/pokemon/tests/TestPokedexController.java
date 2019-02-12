package pokemon.tests;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import pokemon.model.Pokemon;
import pokemon.view.PokedexFrame;
import pokemon.controller.PokedexController;

class TestPokedexController
{
	PokedexController tested;
	@BeforeEach
	void setUp() throws Exception
	{
		tested = new PokedexController();
	}

	@AfterEach
	void tearDown() throws Exception
	{
		tested = null;
	}

	@Test
	void testPokemonInPokedex()
	{
		int typeCount = 0;
		int evolveCount = 0;
		for (Pokemon currentPokemon : tested.getPokemonList())
		{
			assertNotNull( currentPokemon , "Cant pass if the Pokemon don't exist");
			assertFalse(currentPokemon.toString().contains("@"), "The toString method must be implemented");
			
			assertTrue(currentPokemon.getPokemonTypes().length >= 1, "All Pokemon must implement at least one Interface");
			if(currentPokemon.getPokemonTypes().length >= 1)
			{
				typeCount++;
			}
			if(currentPokemon.isCanEvolve())
			{
				evolveCount++;
			}
		}
		assertTrue(typeCount > 0, "At least one Pokemon must implement two different interfaces");
		assertTrue(evolveCount > 0, "At least one Pokemon must be evolveable");
	}
	
	@Test
	void testValidationMethods()
	{
		assertFalse(tested.isDouble(""), "Empty text is not a number");
		assertFalse(tested.isDouble("text"), "Text is not a number");
		assertTrue(tested.isDouble("9"), "9 is a number");
		assertTrue(tested.isDouble("9.09"), "9 is a number");
		
		assertFalse(tested.isInt(""), "Empty text is not a number");
		assertFalse(tested.isInt("text"), "Text is not a number");
		assertFalse(tested.isInt("3.2"), "Doubles are not ints");
		assertTrue(tested.isInt("9"), "9 is an integer number");
	}
	
	@Test
	void testFrame()
	{
		assertNotNull(tested.getFrame(), "Your app needs a GUI frame");
		assertTrue(tested.getFrame() instanceof PokedexFrame, "Your app needs a PokedexFrame");
	}

}
