AndroidHSVColourSelectionDialog
===============================

A Dialog for selecting a HSV Colour in android, contains a basic example XML layout, the dialog class, and a Listener.
From an activity:   

HSVColourPickerDialog = colourDialog = new HSVColourPickerDialog(this,180,180,180); 
colourDialog.setColourPickerDoneListener(new ColourPickerDoneListener() {	
	public void onDone(int color) { 			
		//Extract Colour 			
    	} 			
});  
colourDialog.show();
