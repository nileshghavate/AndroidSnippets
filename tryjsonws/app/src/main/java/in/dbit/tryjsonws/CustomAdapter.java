package in.dbit.tryjsonws;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    ArrayList<String> personNames;
    ArrayList<String> emailIds;
    ArrayList<String> mobileNumbers;
    Context context;

    public CustomAdapter(Context context, ArrayList<String> personNames, ArrayList<String> emailIds, ArrayList<String> mobileNumbers) {
        this.context = context;
        this.personNames = personNames;
        this.emailIds = emailIds;
        this.mobileNumbers = mobileNumbers;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // infalte the item Layout
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowlayout, parent, false);
        // get the reference of item view's

        // pass the view to View Holder
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, email, mobileNo;// init the item view's

        public MyViewHolder(View itemView) {
            super(itemView);

            // get the reference of item view's
            name = (TextView) itemView.findViewById(R.id.name);
            email = (TextView) itemView.findViewById(R.id.email);
            mobileNo = (TextView) itemView.findViewById(R.id.mobileNo);

        }
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder,final int position) {

        // set the data in items
        holder.name.setText(personNames.get(position));
        holder.email.setText(emailIds.get(position));
        holder.mobileNo.setText(mobileNumbers.get(position));

        // implement setOnClickListener event on item view.
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // display a toast with person name on item click
                Toast.makeText(context, personNames.get(position), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public int getItemCount() {
        return personNames.size();
    }

}
