package dailysuvichar.ds_admin.view.adapters;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import cn.pedant.SweetAlert.SweetAlertDialog;
import dailysuvichar.ds_admin.R;
import dailysuvichar.ds_admin.model.Guru;
import dailysuvichar.ds_admin.view.holders.PendingGuruVH;

public class RVPendingGurus extends RecyclerView.Adapter<PendingGuruVH> {

    ArrayList<Guru> gurus;
    Context context, pContext;
    private int mPosition;

    public RVPendingGurus(Context context, ArrayList<Guru> list){
        this.gurus=list;
        this.context=context;
    }
    @Override
    public PendingGuruVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_guru, parent, false);
        pContext = parent.getContext();
        return new PendingGuruVH(view);
    }

    @Override
    public void onBindViewHolder(final PendingGuruVH holder, final int position) {
        mPosition = position;
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                String sDob = "DOB: " +gurus.get(mPosition).getDOB(),
                        sAge = "AGE: "+String.valueOf(gurus.get(mPosition).getAge()),
                        sName = "NAME: "+gurus.get(mPosition).getName(),
                        sEmail = "EMAIL: "+gurus.get(mPosition).getEmail(),
                        sUid = "UID: " +gurus.get(mPosition).getUid();
                holder.dob.setText(sDob);
                holder.age.setText(sAge);
                holder.name.setText(sName);
                holder.email.setText(sEmail);
                holder.uid.setText(sUid);
                holder.setGOV(context, gurus.get(mPosition).getImgGov());
                holder.setSpec(context, gurus.get(mPosition).getImgSpec());
                holder.imgGOV.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        holder.fullScreenIntent(1);
                    }
                });
                holder.imgSPEC.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        holder.fullScreenIntent(2);
                    }
                });
                holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {
                        PopupMenu popup = new PopupMenu(pContext, holder.cardView);
                        //inflating menu from xml resource
                        popup.inflate(R.menu.card_menu);
                        //adding click listener
                        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem item) {
                                switch (item.getItemId()) {
                                    case R.id.accept:{
                                        new SweetAlertDialog(pContext, SweetAlertDialog.WARNING_TYPE)
                                                .setTitleText("Accept as Guru?")
                                                .setContentText("The user will be converted to Guru in the parent app.")
                                                .setConfirmText("Yes,accept!")
                                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                                    @Override
                                                    public void onClick(SweetAlertDialog sDialog) {
                                                        sDialog
                                                                .setTitleText("Accepted!")
                                                                .setContentText("This person has been set as a Guru!")
                                                                .setConfirmText("OK")
                                                                .showCancelButton(false)
                                                                .setConfirmClickListener(null)
                                                                .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                                                    }
                                                })
                                                .showCancelButton(true)
                                                .setCancelText("Sorry, No!")
                                                .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                                    @Override
                                                    public void onClick(SweetAlertDialog sDialog) {
                                                        sDialog
                                                                .setTitleText("Dismissed!")
                                                                .setContentText("No Action was taken!")
                                                                .showCancelButton(false)
                                                                .setConfirmText("OK")
                                                                .setCancelClickListener(null)
                                                                .setConfirmClickListener(null)
                                                                .changeAlertType(SweetAlertDialog.ERROR_TYPE);
                                                    }
                                                })
                                                .show();
                                        break;
                                    }
                                    case R.id.reject:{

                                        break;
                                    }
                                }
                                return false;
                            }
                        });
                        //displaying the popup
                        popup.show();
                        setPosition(holder.getAdapterPosition());
                        return false;
                    }
                });
            }
        });
    }

    private void setPosition(int position) {
        this.mPosition = position;
    }

    @Override
    public int getItemCount() {
        return gurus.size();
    }
}
