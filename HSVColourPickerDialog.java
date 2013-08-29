package uk.co.iceroad.colourpickerexample.controller;

import uk.co.iceroad.R;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.view.WindowManager.LayoutParams;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

/**
 * A class which allows the user to choose a HSV colour by editing 
 * the progress of three SeekBars.
 * @author Rose Tucker: rosy533 GitHub/GMail
 * @since 2013
 */
public class HSVColourPickerDialog extends Dialog {

	/** The Hue SeekBar */
	private SeekBar hBar;
	/** The Saturation SeekBar */
	private SeekBar sBar;
	/** The Value SeekBar */
	private SeekBar vBar;
	/** An imageView showing the user a preview of their chose colour */
	private ImageView colourPreView;
	/** A listener to alert that the user has selected a colour */
	private ColourPickerDoneListener colourPickerDoneListener;

	/**
	 * Constructor for the Dialog, sets up the components and sets the 
	 * seekbars initial values
	 * @param context the parent activity
	 * @param h The Initial Hue
	 * @param s The Initial Saturation 
	 * @param v The Initial Value
	 */
	public HSVColourPickerDialog(Context context, int h, int s, int v) {
		super(context);

		//Set up Dialog Layout
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);   
		this.setContentView(R.layout.colour_picker_dialog_layout);
		this.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
		LayoutParams params = getWindow().getAttributes();
		params.width = LayoutParams.MATCH_PARENT; //Full Width
		getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);

		setUpSeekBars();
		setUpUIComponents(h,s,v);

	}

	/**
	 * Set up the buttons and the colour preview 
	 * @param h The Initial Hue
	 * @param s The Initial Saturation
	 * @param v The Initial Value
	 */
	private void setUpUIComponents(int h, int s, int v) {

		//Set up the Image View displaying a preview of the chosen colour
		colourPreView = (ImageView) findViewById(R.id.showColourView);
		setColour(h, s, v);

		//Set up the buttons
		Button cancelButton = (Button) findViewById(R.id.cancel);
		cancelButton.setOnClickListener(new OnCancelListener());
		Button doneButton = (Button) findViewById(R.id.done);
		doneButton.setOnClickListener(new OnDoneListener());
	}

	/**
	 * Sets up the HSV SeekBars by adding the listeners 
	 * and creating the SeekBar fields
	 */
	private void setUpSeekBars() {
		//Set up H bar
		hBar = (SeekBar) findViewById(R.id.hBar);
		hBar.setOnSeekBarChangeListener(new SeekBarListener());

		//Set up S Bar
		sBar = (SeekBar) findViewById(R.id.sBar);
		sBar.setOnSeekBarChangeListener(new SeekBarListener());

		//Set up V bar
		vBar = (SeekBar) findViewById(R.id.vBar);
		vBar.setOnSeekBarChangeListener(new SeekBarListener());
	}

	/**
	 * Sets the ColourPickerDone Listener for this instance, the on done method 
	 * of this listener will be called when the user clicks the done button, 
	 * passing the selected colour to the parent activity
	 * @param colourPickerDoneListener
	 */
	public void setColourPickerDoneListener(ColourPickerDoneListener 
			colourPickerDoneListener){
		this.colourPickerDoneListener = colourPickerDoneListener;
	}

	/**
	 * Combines the progress of the three SeekBars and returns the colour they 
	 * make
	 * @return The selected Colour
	 */
	public int getCurrentlySelectedColor(){
		return Color.HSVToColor(new float[]{hBar.getProgress(),(1f/360f) *(float)sBar.getProgress(), (1f/360f) *(float)vBar.getProgress()});
	}

	/**
	 * Set the currently selected colour from the three parameters,
	 * this includes updating the HSV SeekBars to match the colour
	 * @param h The Hue
	 * @param s The Saturation
	 * @param v The Value
	 */
	public void setColour(int h, int s, int v){
		//Set the Bar values
		vBar.setProgress(v);
		hBar.setProgress(h);
		sBar.setProgress(s);
	}

	/*************************************************************************/
	/**                          INNER CLASSES                              **/
	/*************************************************************************/

	/**
	 * A Listener which, when any SeekBars progress is altered, will update the 
	 * current colour and therefore the preview
	 */
	private class SeekBarListener implements OnSeekBarChangeListener{

		public void onStopTrackingTouch(SeekBar seekBar) {} //UNUSED
		public void onStartTrackingTouch(SeekBar seekBar){} //UNUSED

		/**
		 * The SeekBars Progress has been changed by the user, update the colour
		 */
		public void onProgressChanged(SeekBar seekBar, int progress,
				boolean fromUser) {
			colourPreView.setBackgroundColor(getCurrentlySelectedColor());
		}
	}

	/**
	 * Called when the user clicks Cancel, closes the dialog, does not update the 
	 * colour
	 */
	private class OnCancelListener implements android.view.View.OnClickListener{
		public void onClick(View v) {
			HSVColourPickerDialog.this.dismiss();			
		}
	}

	/**
	 * Called when the user clicks Done, calls the onDone method of the current 
	 * NamePickerDoneListener, passing the currently selected colour
	 */
	private class OnDoneListener implements android.view.View.OnClickListener{
		public void onClick(View v) {
			if(colourPickerDoneListener != null)
				colourPickerDoneListener.onDone(getCurrentlySelectedColor());	
		}
	}
}
