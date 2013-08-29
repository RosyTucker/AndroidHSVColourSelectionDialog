AndroidHSVColourSelectionDialog
===============================

A Dialog for Selecting a HSV Colour in Android, Contains a Basic Example XML Layout, the Dialog Class, and a Listener.
From an Activity:   

```java
HSVColourPickerDialog colourDialog = new HSVColourPickerDialog(this,180,180,180); //H,S,V are 0-360
colourDialog.setColourPickerDoneListener(new ColourPickerDoneListener() {	
	public void onDone(int color) { 			
		//Extract Colour 			
    	} 			
});
colourDialog.show();
```

