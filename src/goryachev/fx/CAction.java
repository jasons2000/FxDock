// Copyright (c) 2016 Andy Goryachev <andy@goryachev.com>
package goryachev.fx;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleButton;


/**
 * CAction - an AbstractAction equivalent for FX.
 */
public abstract class CAction
    implements EventHandler<ActionEvent>
{
	/** simple action event */
	protected abstract void action();

	/** override to obtain the actual event */
	public void handle(ActionEvent ev) { action(); }
	
	//
	
	private final BooleanProperty selectedProperty = new SimpleBooleanProperty(this, "selected");
	private final BooleanProperty disabledProperty = new SimpleBooleanProperty(this, "disabled");

	
	public CAction()
	{
	}


	public void attach(ButtonBase b)
	{
		b.setOnAction(this);
		b.disableProperty().bind(disabledProperty());

		if(b instanceof ToggleButton)
		{
			((ToggleButton)b).selectedProperty().bindBidirectional(selectedProperty());
		}
	}


	public void attach(MenuItem m)
	{
		m.setOnAction(this);
		m.disableProperty().bind(disabledProperty());

		if(m instanceof CheckMenuItem)
		{
			((CheckMenuItem)m).selectedProperty().bindBidirectional(selectedProperty());
		}
		else if(m instanceof RadioMenuItem)
		{
			((RadioMenuItem)m).selectedProperty().bindBidirectional(selectedProperty());
		}
	}


	public final BooleanProperty selectedProperty()
	{
		return selectedProperty;
	}


	public final boolean isSelected()
	{
		return selectedProperty.get();
	}


	public final void setSelected(boolean on)
	{
		selectedProperty.set(on);
	}


	public final BooleanProperty disabledProperty()
	{
		return disabledProperty;
	}


	public final boolean isDisabled()
	{
		return disabledProperty.get();
	}


	public final void setDisabled(boolean on)
	{
		disabledProperty.set(on);
	}
	
	
	public final boolean isEnabled()
	{
		return !isDisabled();
	}
	
	
	public final void setEnabled(boolean on)
	{
		disabledProperty.set(!on);
	}
}
