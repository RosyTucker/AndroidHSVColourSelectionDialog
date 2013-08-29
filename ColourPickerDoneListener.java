package uk.co.iceroad.colourpickerexample.controller;

/**
 * A Listener to respond to a HSVColourPickerDialog's Done button being pressed
 */
public interface ColourPickerDoneListener {

	/**
	 * The Done button was pressed, pass the selected colour
	 * @param color The Selected Colour
	 */
	public void onDone(int color);
}
