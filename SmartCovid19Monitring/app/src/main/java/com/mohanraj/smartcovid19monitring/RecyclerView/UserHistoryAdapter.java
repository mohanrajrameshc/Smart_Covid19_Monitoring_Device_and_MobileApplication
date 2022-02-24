package com.mohanraj.smartcovid19monitring.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import com.mohanraj.smartcovid19monitring.Bean.UserHistoryAdapterBean;
import com.mohanraj.smartcovid19monitring.R;

public class UserHistoryAdapter extends FirebaseRecyclerAdapter<UserHistoryAdapterBean,UserHistoryAdapter.myviewholder>
        {
public UserHistoryAdapter(@NonNull FirebaseRecyclerOptions<UserHistoryAdapterBean> options) {
        super(options);
        }

@Override
protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull UserHistoryAdapterBean model) {
        holder.bind(getItem(position));

        }



@NonNull
@Override
public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)

        {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.user_history_list,parent,false);

        return new myviewholder(view);
        }

class myviewholder extends RecyclerView.ViewHolder
{

    TextView time,date,celsius;
    public myviewholder(@NonNull View itemView)
    {
        super(itemView);

        time=(TextView) itemView.findViewById(R.id.time);
        date=(TextView)itemView.findViewById(R.id.date);
        celsius=(TextView)itemView.findViewById(R.id.celsius);

    }
    public void bind(final UserHistoryAdapterBean bean)
    {
        time.setText(bean.getTIME());
        date.setText(bean.getDATE());
        celsius.setText(bean.getCELSIUS().toString());
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent i=new Intent(itemView.getContext(), AddQuranitine.class);
               // i.putExtra("id",bean.getTIME());
                //i.putExtra("date",bean.getDATE());
                //itemView.getContext().startActivity(i);
            }
        });
    }
}
}
