package pokemon.tests;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import pokemon.controller.PokedexController;
import pokemon.view.PokedexFrame;
import pokemon.view.PokedexPanel;

class FrameTest
{
	private PokedexFrame testedFrame;
	
	@BeforeEach
	void setUp() throws Exception
	{
		testedFrame = new PokedexFrame(new PokedexController());
	}

	@AfterEach
	void tearDown() throws Exception
	{
		testedFrame = null;
	}

	@Test
	void testPokedexFrame()
	{
		assertNotNull(testedFrame, "Your constructor should create an object, weird 😸");
		assertTrue(true, "This test should ALWAYS pass");
	}
	
	@Test
	public void testSetupFrame()
	{
		assertNotNull(testedFrame.getTitle(),"You need a title");
		assertTrue( testedFrame.getTitle().contains("Pokemon"), "Your title needs a reference to Pokemon");
		assertFalse(testedFrame.isResizable(), "Your Pokedex app should not be resizable");
		assertTrue( testedFrame.getContentPane() instanceof PokedexPanel, "You need a PokemonPanel inside the frame");
	}

}
