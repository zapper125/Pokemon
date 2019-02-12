package pokemon.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import pokemon.controller.PokedexController;
import pokemon.view.PokedexPanel;

class PanelTest
{
	private PokedexPanel testedPanel;

	@BeforeEach
	void setUp() throws Exception
	{
		testedPanel = new PokedexPanel(new PokedexController());
	}

	@AfterEach
	void tearDown() throws Exception
	{
		testedPanel = null;
	}

	@Test
	void testPokedexPanel()
	{
		assertNotNull(testedPanel.getComponents(), "Your panel must have components");
		assertTrue(testedPanel.getComponents().length > 10, "You need way more than 5 components in this app" );
		
	}
	
	@Test
	void testSetupPanel()
	{
		assertTrue(testedPanel.getLayout() instanceof SpringLayout, "You should be using a SpringLayout");
		int enabledCount = 0;
		int disabledCount = 0;
		int fieldCount = 0;
		for(Component examined : testedPanel.getComponents())
		{
			if (examined instanceof JTextField)
			{
				if (((JTextField)examined).isEnabled())
				{
					enabledCount++;
				}
				else
				{
					disabledCount++;
				}
				fieldCount++;
			}
			
		}
		assertTrue(fieldCount > 5, "You need a textfield for each of the data members");
		assertTrue(enabledCount > 4, "At least four of the textfields need to be editable");
		assertTrue(disabledCount == 1, "Exactly ONE of the textfields need to be non-editable");
	}
	
	@Test
	void testSetupListeners()
	{
		for(Component examined : testedPanel.getComponents())
		{
			if (examined instanceof JButton)
			{
				assertTrue( ((JButton)examined).getActionListeners().length == 1, "Your button must have a listener");
			}
			
			if (examined instanceof JComboBox)
			{
				assertTrue( ((JComboBox)examined).getItemListeners().length == 1, "Your JComboBox must have a listener");
			}
			
		}
	}

}
