package ch.walica.licznikpunktw;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements PersonsAdapter.OnCountClickListener {

    RecyclerView recyclerView;
    List<Person> persons = new ArrayList<>();
    PersonsAdapter adapter;

    TextInputEditText etName;
    Button btnAddPerson;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        etName = findViewById(R.id.etName);
        btnAddPerson = findViewById(R.id.btnAddPerson);

        btnAddPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addPerson();
            }
        });

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        adapter = new PersonsAdapter(persons, this);
        recyclerView.setAdapter(adapter);

    }

    private void addPerson() {
        persons.add(new Person(etName.getText().toString()));
        etName.getText().clear();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void addCredit(int credit, int position) {
        persons.get(position).setCount(persons.get(position).getCount() + credit);
        adapter.notifyItemChanged(position);
    }
}