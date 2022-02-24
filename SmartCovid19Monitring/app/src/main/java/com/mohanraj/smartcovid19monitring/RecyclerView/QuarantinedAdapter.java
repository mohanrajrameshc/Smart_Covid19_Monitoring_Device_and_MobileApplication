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
import com.mohanraj.smartcovid19monitring.Admin.AddReleasedList;
import com.mohanraj.smartcovid19monitring.Admin.Realizedlist;
import com.mohanraj.smartcovid19monitring.Bean.QuarantinedAdpBean;
import com.mohanraj.smartcovid19monitring.R;

public class QuarantinedAdapter  extends FirebaseRecyclerAdapter<QuarantinedAdpBean,QuarantinedAdapter.myviewholder> {

    public QuarantinedAdapter(@NonNull FirebaseRecyclerOptions<QuarantinedAdpBean> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull QuarantinedAdpBean model) {
        holder.bind(getItem(position));

    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.quarantine_list,parent,false);
        return new QuarantinedAdapter.myviewholder(view);
    }

    public class myviewholder extends RecyclerView.ViewHolder {
        TextView uid,adate,qdate,place;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            uid=(TextView)itemView.findViewById(R.id.uid);
            adate=(TextView)itemView.findViewById(R.id.adate);
            qdate=(TextView)itemView.findViewById(R.id.qdate);
            place=(TextView)itemView.findViewById(R.id.Center);
        }
        public void bind(final QuarantinedAdpBean bean)
        {
            uid.setText(bean.getUid());
            adate.setText(bean.getAlerteddate());
            qdate.setText(bean.getQuarantineddate());
            place.setText(bean.getQurantinedcenter());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i=new Intent(itemView.getContext(), AddReleasedList.class);
                    i.putExtra("uid",bean.getUid());
                    i.putExtra("adate",bean.getAlerteddate());
                    i.putExtra("qdate",bean.getQuarantineddate());
                    i.putExtra("place",bean.getQurantinedcenter());
                    itemView.getContext().startActivity(i);
                }
            });
        }
    }
}
