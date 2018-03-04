package com.example.user.databasetest;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText inputText;
    TextView outputText;
    MyDBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inputText = (EditText) findViewById(R.id.inputText);
        outputText = (TextView) findViewById(R.id.outputText);
        dbHandler = new MyDBHandler(this, null, null, 1);
        printDatabase();
    }

    //Add a product to the database
    public void addButtonClicked(View view){
        Products product = new Products(inputText.getText().toString());
        dbHandler.addProduct(product);
        printDatabase();
    }

    //Delete items pass in view as parameter else clicking wont affect it
    public void deleteButtonClicked(View view){
        String input = inputText.getText().toString();
        dbHandler.deleteProduct(input);
        printDatabase();
    }

    public void printDatabase(){
        String dbString = dbHandler.databaseToString();
        outputText.setText(dbString);
        inputText.setText("");
    }



}
