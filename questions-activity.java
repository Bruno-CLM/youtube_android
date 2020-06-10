//seq-junho-004

package com.example.agendiario;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class QuestionsActivity1 extends AppCompatActivity {

    public static final int ADD_QUESTION_ACTIVITY_REQUEST_CODE = 1;

    public static final int EDIT_QUESTION_ACTIVITY_REQUEST_CODE = 2;

    public String TEXT_SEARCH_RESULT = "pesquisa";

    private QuestionViewModel mQuestionViewModel;

    private FloatingActionButton mFabQuestions;

    private QuestionAdapter adapter;

    public String getDate(){
        android.text.format.DateFormat df = new android.text.format.DateFormat();
        return df.format("yyyy-MM-dd hh:mm:ss a", new java.util.Date()).toString();
    }


    private void newQuestionClicked(){
        Intent intent = new Intent(QuestionsActivity1.this, QuestionAddEditActivity1.class);
        startActivityForResult(intent, ADD_QUESTION_ACTIVITY_REQUEST_CODE);
    }


    public class NewQuestionClick implements View.OnClickListener{
        @Override
        public void onClick(View view){
            newQuestionClicked();
        }
    }

    private void setupRecyclerView(){
        // falta programar
        RecyclerView recyclerView = findViewById(R.id.recycler_questions);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        adapter = new QuestionAdapter(this);
        recyclerView.setAdapter(adapter);

        mQuestionViewModel = ViewModelProviders.of(this).get(QuestionViewModel.class);

        mQuestionViewModel.getFullQuestions().observe(this, new Observer<List<Question>>() {
            @Override
            public void onChanged(List<Question> questions) {
                adapter.setQuestions(questions);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT |
                        ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                mQuestionViewModel.delete(adapter.getQuestionAt(viewHolder.getAdapterPosition()));
            }
        }).attachToRecyclerView(recyclerView);

        adapter.setOnItemClickListener(new QuestionAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Question question) {
                Intent intent = new Intent(QuestionsActivity1.this, QuestionAddEditActivity1.class);
                intent.putExtra(QuestionAddEditActivity1.EXTRA_ID, question.getId());
                intent.putExtra(QuestionAddEditActivity1.EXTRA_DESCRIPTION, question.getDescription());
                intent.putExtra(QuestionAddEditActivity1.EXTRA_STATUS, question.getStatus());
                intent.putExtra(QuestionAddEditActivity1.EXTRA_CREATION, question.getCreationDate());
                startActivityForResult(intent, EDIT_QUESTION_ACTIVITY_REQUEST_CODE);
            }
        });


    }


    @Override
    protected void onCreate(final Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_questions);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setupRecyclerView();

        mFabQuestions = findViewById(R.id.fab_questions);

        mFabQuestions.setOnClickListener( new NewQuestionClick() );

    }


    public void onActivityResult( int requestCode, int resultCode, Intent data ){
        super.onActivityResult( requestCode, resultCode, data);

        if(requestCode == ADD_QUESTION_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {

            String description = data.getStringExtra(QuestionAddEditActivity1.EXTRA_DESCRIPTION);
            int status = data.getIntExtra(QuestionAddEditActivity1.EXTRA_STATUS, -1);
            String creationDate = getDate();

            Question question = new Question(description, status, creationDate);

            mQuestionViewModel.insert(question);

            Toast.makeText(this, R.string.save_success, Toast.LENGTH_SHORT).show();


        } else if (requestCode == EDIT_QUESTION_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {

            int id = data.getIntExtra(QuestionAddEditActivity1.EXTRA_ID, -1);
            if( id == -1 ){
                Toast.makeText(this, R.string.update_fail, Toast.LENGTH_SHORT).show();
                return;
            }

            String description = data.getStringExtra(QuestionAddEditActivity1.EXTRA_DESCRIPTION);
            int status = data.getIntExtra(QuestionAddEditActivity1.EXTRA_STATUS, 0);
            String creationDate = data.getStringExtra(QuestionAddEditActivity1.EXTRA_CREATION);

            Question question = new Question(description, status, creationDate);
            question.setId(id);

            mQuestionViewModel.update(question);

            Toast.makeText(this, R.string.update_success, Toast.LENGTH_SHORT).show();


        } else {
            if( resultCode != RESULT_CANCELED){
                Toast.makeText(this, R.string.failure_has_occurred, Toast.LENGTH_SHORT).show();
            }
        }

    }


    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu_questions, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        final SearchView searchView = (SearchView) menu.findItem(R.id.action_search_questions).getActionView();

        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                TEXT_SEARCH_RESULT = query;
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch ( item.getItemId() ){
            case R.id.delete_all_questions:
                mQuestionViewModel.deleteAll();
                Toast.makeText(this, R.string.delete_all_item, Toast.LENGTH_SHORT).show();
                return true;

            case R.id.add_new_question:
                newQuestionClicked();
                return  true;

            default:
                return  super.onOptionsItemSelected(item);
        }
    }

}
