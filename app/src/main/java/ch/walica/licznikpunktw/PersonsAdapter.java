package ch.walica.licznikpunktw;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

public class PersonsAdapter extends RecyclerView.Adapter<PersonsAdapter.MyViewHolder> {

    private List<Person> persons = new ArrayList<>();
    private OnCountClickListener onCountClickListener;

    public PersonsAdapter(List<Person> persons, OnCountClickListener onCountClickListener) {
        this.persons = persons;
        this.onCountClickListener = onCountClickListener;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.person_card, parent, false);
        return new MyViewHolder(view, onCountClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonsAdapter.MyViewHolder holder, int position) {
        holder.bind(persons.get(position));
    }

    @Override
    public int getItemCount() {
        return persons.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tvName, tvCount;
        EditText etCredit;
        ImageView btnAddCredit;
        ImageView btnSubCredit;
        OnCountClickListener countClickListener;


        public MyViewHolder(View itemView, OnCountClickListener countClickListener) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvCount = itemView.findViewById(R.id.tvCount);
            etCredit = itemView.findViewById(R.id.etCredit);
            btnAddCredit = itemView.findViewById(R.id.btnAddCredit);
            btnSubCredit = itemView.findViewById(R.id.btnSubCredit);

            this.countClickListener = countClickListener;

            btnAddCredit.setOnClickListener(this);
            btnSubCredit.setOnClickListener(this);


        }

        void bind(Person person) {
            String count = Integer.toString(person.getCount());
            tvName.setText(person.getName());
            if(person.getCount() > 49) {
                tvCount.setTextColor(0xFF00A323);
            }
            tvCount.setText(count);


        }

        @Override
        public void onClick(View v) {
            String creditStr = etCredit.getText().toString();

            if(!creditStr.isEmpty()) {
                if(v == btnAddCredit) {
                    onCountClickListener.addCredit(Integer.parseInt(creditStr), getAdapterPosition());
                } else {
                    onCountClickListener.addCredit(-(Integer.parseInt(creditStr)) , getAdapterPosition());
                }
                etCredit.getText().clear();
            }

        }
    }

    interface OnCountClickListener {
        void addCredit(int credit, int position);
    }
}
