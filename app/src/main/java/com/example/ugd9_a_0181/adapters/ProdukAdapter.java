package com.example.ugd9_a_0181.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ugd9_a_0181.AddEditActivity;
import com.example.ugd9_a_0181.MainActivity;
import com.example.ugd9_a_0181.R;
import com.example.ugd9_a_0181.models.Produk;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ProdukAdapter extends RecyclerView.Adapter<ProdukAdapter.ViewHolder>
        implements Filterable {

    private List<Produk> produkList, filteredProdukList;
    private Context context;

    public ProdukAdapter(List<Produk> produkList, Context context) {
        this.produkList = produkList;
        filteredProdukList = new ArrayList<>(produkList);
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_produk, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Produk produk = filteredProdukList.get(position);

        holder.tvNama.setText(produk.getNama());
        holder.tvStok.setText("Stok " + produk.getStok());

        DecimalFormat rupiahFormat = (DecimalFormat) DecimalFormat
                .getCurrencyInstance(new Locale("in", "ID"));
        holder.tvHarga.setText(rupiahFormat.format(produk.getHarga()));

        Glide.with(context)
                .load(produk.getGambar())
                .placeholder(R.drawable.no_image)
                .into(holder.ivGambar);

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MaterialAlertDialogBuilder materialAlertDialogBuilder =
                        new MaterialAlertDialogBuilder(context);
                materialAlertDialogBuilder.setTitle("Konfirmasi")
                        .setMessage("Apakah anda yakin ingin menghapus data produk ini?")
                        .setNegativeButton("Batal", null)
                        .setPositiveButton("Hapus", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if (context instanceof MainActivity)
                                    ((MainActivity) context).deleteProduk(produk.getId());
                            }
                        })
                        .show();
            }
        });

        holder.cvProduk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, AddEditActivity.class);
                i.putExtra("id", produk.getId());

                if (context instanceof MainActivity)
                    ((MainActivity) context).startActivityForResult(i,
                            MainActivity.LAUNCH_ADD_ACTIVITY);
            }
        });
    }

    @Override
    public int getItemCount() {
        return filteredProdukList.size();
    }


    public void setProdukList(List<Produk> produkList) {
        this.produkList = produkList;
        filteredProdukList = new ArrayList<>(produkList);
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charSequenceString = charSequence.toString();
                List<Produk> filtered = new ArrayList<>();

                if (charSequenceString.isEmpty()) {
                    filtered.addAll(produkList);
                } else {
                    for (Produk produk : produkList) {
                        if (produk.getNama().toLowerCase()
                                .contains(charSequenceString.toLowerCase()))
                            filtered.add(produk);
                    }
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = filtered;

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                filteredProdukList.clear();
                filteredProdukList.addAll((List<Produk>) filterResults.values);
                notifyDataSetChanged();
            }
        };
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvNama, tvHarga, tvStok;
        ImageView ivGambar;
        ImageButton btnDelete;
        CardView cvProduk;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvHarga = itemView.findViewById(R.id.tv_harga);
            tvNama = itemView.findViewById(R.id.tv_nama);
            tvStok = itemView.findViewById(R.id.tv_stok);
            ivGambar = itemView.findViewById(R.id.iv_gambar);
            btnDelete = itemView.findViewById(R.id.btn_delete);
            cvProduk = itemView.findViewById(R.id.cv_produk);
        }
    }
}
