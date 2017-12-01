package com.khilman.www.heksavoucher.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.khilman.www.heksavoucher.MainActivity;
import com.khilman.www.heksavoucher.R;
import com.khilman.www.heksavoucher.UbahVoucherActivity;
import com.khilman.www.heksavoucher.database.DatabaseHelper;
import com.khilman.www.heksavoucher.helper.Helper;
import com.khilman.www.heksavoucher.model.VoucherModel;
import com.khilman.www.heksavoucher.view.DetailVoucherActivity;

import java.util.List;

/**
 * Created by root on 11/13/17.
 */

public class VoucherAdapter extends RecyclerView.Adapter<VoucherAdapter.MyHolder> {
    Context context;
    List<VoucherModel> data;

    public VoucherAdapter(Context context, List<VoucherModel> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.voucher_item, parent, false);

        MyHolder holder = new MyHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        final VoucherModel voucher = data.get(position);

        String tgl_mulai = Helper.readDate(voucher.getTgl_mulai());
        String tgl_akhir = Helper.readDate(voucher.getTgl_akhir());
        holder.tvNoPolis.setText("NO POLIS : " + voucher.getNo_polis());
        holder.tv_tgl_awal_akhir.setText(tgl_mulai + " - " + tgl_akhir);

        holder.tvNamaUser.setText(voucher.getNama_u());
        holder.tvNoKtp.setText("NO.KTP : " + voucher.getKtp_u());
        holder.tvUsia.setText("Usia : " + voucher.getUsia_u() + " tahun");
        holder.tvNilaiPremi.setText("Premi Up : Rp. " + voucher.getNilai_premi());
        holder.tvStatus.setText("Status : unknown");


        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(context);
                //alert.setMessage("HAPUS\nEDIT\nLAINNYA");
                final DatabaseHelper database = new DatabaseHelper(context);
                alert.setPositiveButton("Hapus", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AlertDialog.Builder alert = new AlertDialog.Builder(context);
                        alert.setMessage("Hapus voucher ini?");
                        alert.setPositiveButton("Hapus", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (database.deleteVoucher(voucher.get_id())){
                                    Toast.makeText(context, "Berhasil dihapus", Toast.LENGTH_SHORT).show();
                                    context.startActivity(new Intent(context, MainActivity.class));
                                }
                            }
                        });
                        alert.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });

                        alert.show();
                    }
                });
                alert.setNegativeButton("Edit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(context, UbahVoucherActivity.class);

                        intent.putExtra("vc_id", voucher.get_id());
                        intent.putExtra("no_ps", voucher.getNo_polis());
                        intent.putExtra("premi", voucher.getNilai_premi());
                        intent.putExtra("uang_pertanggungan", voucher.getNilai_pertanggungan());
                        intent.putExtra("tgl_mulai", voucher.getTgl_mulai());
                        intent.putExtra("tgl_selesai", voucher.getTgl_akhir());
                        intent.putExtra("nama", voucher.getNama_u());
                        intent.putExtra("jk", voucher.getJk_u());
                        intent.putExtra("tgl_lahir", voucher.getTgl_lahir_u());
                        intent.putExtra("usia", voucher.getUsia_u());
                        intent.putExtra("no_ktp", voucher.getKtp_u());
                        intent.putExtra("no_telp", voucher.getTelp_u());
                        intent.putExtra("ahli_waris", voucher.getAhli_waris_u());
                        intent.putExtra("hubungan_tertangung", voucher.getHubungan_u());
                        intent.putExtra("kd_ref", voucher.getReferal_u());
                        intent.putExtra("kd_pos", voucher.getKode_pos_u());
                        intent.putExtra("kd_prov", voucher.getProv_u());
                        intent.putExtra("kd_kab", voucher.getKab_u());
                        intent.putExtra("kd_kec", voucher.getKec_u());
                        intent.putExtra("ft_nas", voucher.getFoto_ns());
                        intent.putExtra("ft_ktp", voucher.getFoto_ktp());
                        intent.putExtra("ft_tk_dp", voucher.getFoto_tkd());
                        intent.putExtra("ft_tk_bl", voucher.getFoto_tkb());

                        context.startActivity(intent);
                    }
                });
                alert.show();
                return true;
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailVoucherActivity.class);
                intent.putExtra("no_ps", voucher.getNo_polis());
                intent.putExtra("premi", voucher.getNilai_premi());
                intent.putExtra("uang_pertanggungan", voucher.getNilai_pertanggungan());
                intent.putExtra("tgl_mulai", voucher.getTgl_mulai());
                intent.putExtra("tgl_selesai", voucher.getTgl_akhir());
                intent.putExtra("nama", voucher.getNama_u());
                intent.putExtra("jk", voucher.getJk_u());
                intent.putExtra("tgl_lahir", voucher.getTgl_lahir_u());
                intent.putExtra("usia", voucher.getUsia_u());
                intent.putExtra("no_ktp", voucher.getKtp_u());
                intent.putExtra("no_telp", voucher.getTelp_u());
                intent.putExtra("ahli_waris", voucher.getAhli_waris_u());
                intent.putExtra("hubungan_tertangung", voucher.getHubungan_u());
                intent.putExtra("kd_ref", voucher.getReferal_u());
                intent.putExtra("kd_pos", voucher.getKode_pos_u());
                intent.putExtra("kd_prov", voucher.getProv_u());
                intent.putExtra("kd_kab", voucher.getKab_u());
                intent.putExtra("kd_kec", voucher.getKec_u());
                intent.putExtra("ft_nas", voucher.getFoto_ns());
                intent.putExtra("ft_ktp", voucher.getFoto_ktp());
                intent.putExtra("ft_tk_dp", voucher.getFoto_tkd());
                intent.putExtra("ft_tk_bl", voucher.getFoto_tkb());

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView tvNoPolis, tv_tgl_awal_akhir, userInfo, tvNamaUser, tvNoKtp, tvUsia, tvNilaiPremi, tvStatus;
        public MyHolder(View itemView) {
            super(itemView);
            tvNoPolis = (TextView) itemView.findViewById(R.id.tvNoPolis);
            tv_tgl_awal_akhir = (TextView) itemView.findViewById(R.id.tv_tgl_awal_akhir);

            tvNamaUser = (TextView) itemView.findViewById(R.id.tvNamaUser);
            tvNoKtp = (TextView) itemView.findViewById(R.id.tvNoKtp);
            tvUsia = (TextView) itemView.findViewById(R.id.tvUsia);
            tvNilaiPremi = (TextView) itemView.findViewById(R.id.tvNilaiPremi);
            tvStatus = (TextView) itemView.findViewById(R.id.tvStatus);
        }
    }
}
