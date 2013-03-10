package com.elayne.satelliteimage;

import android.os.Bundle;
import android.app.Activity;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
 
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class Home extends Activity {

	EditText imageUrl;
    private ProgressBar progressBar;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(ProgressBar.INVISIBLE);
        imageUrl = (EditText)findViewById(R.id.editTextUrl);
        Button getImageButton = (Button)findViewById(R.id.buttonGetImage);
        getImageButton.setOnClickListener( new View.OnClickListener() {
        	
    	public void onClick(View v) {
        	progressBar.setVisibility(ProgressBar.VISIBLE);   		
		     Context context = v.getContext();
		     Editable ed = imageUrl.getText();
		     Drawable image = ImageOperations(context,ed.toString(),"image.jpg");
		     ImageView imgView = new ImageView(context);
		     imgView = (ImageView)findViewById(R.id.imageView);
		     imgView.setImageDrawable(image);
	           progressBar.setVisibility(ProgressBar.GONE);
		     }
    	});
    }
    
    private Drawable ImageOperations(Context ctx, String url, String saveFilename) {
    	try {
		   URL imageUrl = new URL(url);
		   InputStream is = (InputStream) imageUrl.getContent();
		   Drawable d = Drawable.createFromStream(is, "src");

		   return d;
    	} catch (MalformedURLException e) {
    		e.printStackTrace();
    		return null;
    	} catch (IOException e) {
    		e.printStackTrace();
    		return null;
    	}
    }
}
