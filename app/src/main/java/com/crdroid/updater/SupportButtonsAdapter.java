package com.crdroid.updater;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class SupportButtonsAdapter extends RecyclerView.Adapter<SupportButtonsAdapter.ViewHolder> {

    private final List<SupportButton> supportButtons;
    private final Context context;

    public static class SupportButton {
        public final String text;
        public final int drawableLeft;
        public final String url;

        public SupportButton(String text, int drawableLeft, String url) {
            this.text = text;
            this.drawableLeft = drawableLeft;
            this.url = url;
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public Button button;

        public ViewHolder(View itemView) {
            super(itemView);
            button = itemView.findViewById(R.id.support_button);
        }
    }

    public SupportButtonsAdapter(Context context, List<SupportButton> supportButtons) {
        this.context = context;
        this.supportButtons = supportButtons;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_support_button, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SupportButton supportButton = supportButtons.get(position);
        holder.button.setText(supportButton.text);
        holder.button.setCompoundDrawablesWithIntrinsicBounds(supportButton.drawableLeft, 0, 0, 0);

        if (supportButton.url == null || supportButton.url.isEmpty()) {
            holder.button.setVisibility(View.GONE);
        } else {
            holder.button.setVisibility(View.VISIBLE);
            holder.button.setOnClickListener(v -> {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse(supportButton.url));
                context.startActivity(intent);
            });
        }
    }

    @Override
    public int getItemCount() {
        return supportButtons.size();
    }
}