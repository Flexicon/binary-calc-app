package net.netne.flexicon.binarycalculator;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.support.v4.app.NavUtils;
import android.annotation.TargetApi;
import android.os.Build;

public class ConverterActivity extends Activity implements View.OnClickListener {
	// Variables
	EditText binaryInput, hexInput, decInput;
	String binary, hex, dec;
	Button buttonConvertBinary, buttonConvertHex, buttonConvertDec;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_converter);
		retrieve();
		// Set listeners
		buttonConvertBinary.setOnClickListener(this);
		buttonConvertHex.setOnClickListener(this);
		buttonConvertDec.setOnClickListener(this);
		
		// Show the Up button in the action bar.
		setupActionBar();
	}
	
	@Override
	public void onClick(View v) {
		switch(v.getId()) {
		case R.id.button_convert_binary:
			// Binary button
			binary = binaryInput.getText().toString();
			// Check for correct input otherwise output error message
			if(!binary.contentEquals("")) {
				if(!binary.matches("^[0-1]+$")) {
					binaryInput.setError("Binary number must be made up of 1's and 0'z only");
				} else {
					convertBinary(binary);
					decInput.setText(dec);
					hexInput.setText(hex);
				}
			}
			else {
				binaryInput.setError("Cannot be blank");
			}
			break;
		case R.id.button_convert_hex:
			// Hex button
			hex = hexInput.getText().toString();
			// Check for correct input otherwise output error message
			if(!hex.contentEquals("")){
				if(!hex.matches("^[a-fA-F0-9]+$")) {
					hexInput.setError("Hexadecimal number must be made up of digits [0-9]" +
							" and letters [a-f] only.");
				}
				else {
					convertHex(hex);
					binaryInput.setText(binary);
					decInput.setText(dec);
				}
			}
			else {
				hexInput.setError("Cannot be blank.");
			}
			break;
		case R.id.button_convert_dec:
			// Decimal button
			dec = decInput.getText().toString();
			// Check for correct input otherwise output error message
			if(!dec.contentEquals("")) {
				int rawNum = Integer.parseInt(dec);
				if(rawNum > 255) {
					decInput.setError("Decimal must be less than or equals to 255. (8 bits)");
				}
				else {
					convertDec(rawNum);
					binaryInput.setText(binary);
					hexInput.setText(hex);
				}
			}
			else {
				decInput.setError("Cannot be blank.");
			}
			break;
		}
		
	}
	
	protected void retrieve() {
		// Retrieve Views from XML
		binaryInput = (EditText) findViewById(R.id.binary_input);
		hexInput = (EditText) findViewById(R.id.hex_input);
		decInput = (EditText) findViewById(R.id.dec_input);
		buttonConvertBinary = (Button) findViewById(R.id.button_convert_binary);
		buttonConvertHex = (Button) findViewById(R.id.button_convert_hex);
		buttonConvertDec = (Button) findViewById(R.id.button_convert_dec);
	}
	
	// Functions to handle individual conversion
	protected void convertBinary(String num) {
		int newNum = Integer.parseInt(num, 2);
		dec = Integer.toString(newNum);
		hex = Integer.toHexString(newNum);
	}
	
	protected void convertHex(String num) {
		int newNum = Integer.parseInt(num, 16);
		dec = Integer.toString(newNum);
		binary = Integer.toBinaryString(newNum);
	}
	
	protected void convertDec(int rawNum) {
		// Convert to Binary and set it
		binary = Integer.toBinaryString(rawNum);
		// Convert to hex and set it
		hex = Integer.toHexString(Integer.parseInt(binary, 2));
	}

	
	// Everything below this line controls the Home/Up button and Action Bar
	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.binary, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
