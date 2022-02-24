package com.mohanraj.smartcovid19monitring.RecyclerView;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.mohanraj.smartcovid19monitring.Admin.AddQuranitine;
import com.mohanraj.smartcovid19monitring.Bean.AlertBean;
import com.mohanraj.smartcovid19monitring.R;

public class AlertAdaptar extends FirebaseRecyclerAdapter<AlertBean,AlertAdaptar.myviewholder>
{
    public AlertAdaptar(@NonNull FirebaseRecyclerOptions<AlertBean> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull AlertBean model) {
        holder.bind(getItem(position));

    }



    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)

    {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.alert_list_bean,parent,false);

        return new myviewholder(view);
    }

    class myviewholder extends RecyclerView.ViewHolder
    {

        TextView id,date,celsius;
        public myviewholder(@NonNull View itemView)
        {
            super(itemView);

            id=(TextView) itemView.findViewById(R.id.id);
            date=(TextView)itemView.findViewById(R.id.date);
            celsius=(TextView)itemView.findViewById(R.id.celsius);

        }
        public void bind(final AlertBean bean)
        {
            id.setText(bean.getUSER_ID());
            date.setText(bean.getDATE());
            celsius.setText(bean.getCELSIUS().toString());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i=new Intent(itemView.getContext(),AddQuranitine.class);
                    i.putExtra("id",bean.getUSER_ID());
                    i.putExtra("date",bean.getDATE());
                    itemView.getContext().startActivity(i);
                }
            });
        }
    }
}
