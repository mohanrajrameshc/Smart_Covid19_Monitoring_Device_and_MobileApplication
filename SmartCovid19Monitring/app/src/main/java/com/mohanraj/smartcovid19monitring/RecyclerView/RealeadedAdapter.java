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
import com.mohanraj.smartcovid19monitring.Bean.AlertBean;
import com.mohanraj.smartcovid19monitring.Bean.QuarantinedAdpBean;
import com.mohanraj.smartcovid19monitring.Bean.RealeaedAdpBean;
import com.mohanraj.smartcovid19monitring.R;

public class RealeadedAdapter extends FirebaseRecyclerAdapter<RealeaedAdpBean,RealeadedAdapter.myviewholder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public RealeadedAdapter(@NonNull FirebaseRecyclerOptions<RealeaedAdpBean> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull RealeaedAdpBean model) {
        holder.bind(getItem(position));

    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.released_list,parent,false);
        return new RealeadedAdapter.myviewholder(view);

    }

    public class myviewholder extends RecyclerView.ViewHolder {
        TextView uid,adate,qdate,place,releaseddate;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            uid=(TextView)itemView.findViewById(R.id.uid);
            adate=(TextView)itemView.findViewById(R.id.adate);
            qdate=(TextView)itemView.findViewById(R.id.qdate);
            place=(TextView)itemView.findViewById(R.id.Center);
            releaseddate=(TextView)itemView.findViewById(R.id.realeseddate);

        }
        public void bind(final RealeaedAdpBean bean)
        {
            uid.setText(bean.getUserid());
            adate.setText(bean.getAlerted_DATE());
            qdate.setText(bean.getQuarantined_DATE());
            place.setText(bean.getCenter());
            releaseddate.setText(bean.getRealese_DATE());

            /*itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i=new Intent(itemView.getContext(), AddReleasedList.class);
                    i.putExtra("uid",bean.getUserid());
                    i.putExtra("adate",bean.getAlerted_DATE());
                    i.putExtra("qdate",bean.getQuarantined_DATE());
                    i.putExtra("place",bean.getCenter());
                    i.putExtra("released",bean.getRealese_DATE());
                    itemView.getContext().startActivity(i);
                }
            });*/
        }
    }
}
