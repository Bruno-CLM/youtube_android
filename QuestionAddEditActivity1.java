//seq-junho-005

package com.example.agendiario;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class QuestionAddEditActivity1 extends AppCompatActivity {

    public static final String EXTRA_ID = "id";
    public static final String EXTRA_DESCRIPTION = "descricao";
    public static final String EXTRA_STATUS = "-1";
    public static final String EXTRA_CREATION = "10/06/2020";

    private EditText mEditDescription;
    private Switch mSwitchStatus;

    private void saveQuestion(){

        Intent replyIntent = new Intent();

        if(TextUtils.isEmpty(mEditDescription.getText()) ){
            Toast.makeText(this, R.string.empty_question_description, Toast.LENGTH_SHORT).show();
            return;
        } else {

            int id = getIntent().getIntExtra(EXTRA_ID, -1);
            if( id != -1 ){
                replyIntent.putExtra(EXTRA_ID, id);

            }

            String description = mEditDescription.getText().toString().trim();
            replyIntent.putExtra(EXTRA_DESCRIPTION, description);

            int status = getIntent().getIntExtra(EXTRA_STATUS,0);
            if( mSwitchStatus.isChecked() ){
                status = 1;
            } else {
                status = 0;
            }
            replyIntent.putExtra(EXTRA_STATUS, status);

            String creationDate = getIntent().getStringExtra(EXTRA_CREATION);
            replyIntent.putExtra(EXTRA_CREATION, creationDate);

            setResult(RESULT_OK, replyIntent);

        }


        finish();

    }

    @Override
    public void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_new_question);
        mEditDescription = findViewById(R.id.edit_question_description);
        mSwitchStatus = findViewById(R.id.switch_recycler_status_question);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);

        Intent intent = getIntent();

        if( intent.hasExtra(EXTRA_ID) ) {
            setTitle(R.string.edit_question);
            mEditDescription.setText(intent.getStringExtra(EXTRA_DESCRIPTION));

            int status = intent.getIntExtra(EXTRA_STATUS,0);

            if( status == 1 ){
                mSwitchStatus.setChecked(true);
            } else {
                mSwitchStatus.setChecked(false);
            }

        } else {
            setTitle(R.string.add_new_question);
        }

        final Button button = findViewById(R.id.button_save_question);
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                saveQuestion();
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_book_menu, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.save_note:
                 saveQuestion();
                 return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
