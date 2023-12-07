package blood.pressure.fingerprint.scanner.bpmonitor.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.text.TextUtilsCompat;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import blood.pressure.fingerprint.scanner.bpmonitor.R;
import blood.pressure.fingerprint.scanner.bpmonitor.interfaces.LanguageCode;
import blood.pressure.fingerprint.scanner.bpmonitor.interfaces.IAdapter;
import blood.pressure.fingerprint.scanner.bpmonitor.util.MyUtil;

public class Adapter_language extends RecyclerView.Adapter<Adapter_language.ViewHolder> {

    private final List<String> list_language;
    private final Context context;
    private final IAdapter adapter;
    private int selected = 0;
    private String language;

    public Adapter_language(List<String> list_language, Context context, IAdapter adapter) {
        this.list_language = list_language;
        this.context = context;
        this.adapter = adapter;
        this.language = LanguageCode.English;
    }

    @SuppressLint("InflateParams")
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_item_language, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.txt_language.setText(list_language.get(position));

        if (selected == position) {
            holder.background.setBackground(ContextCompat.getDrawable(context, R.drawable.selected_bgr_item_language));
        } else {
            holder.background.setBackground(ContextCompat.getDrawable(context, R.drawable.shape_bgr_item_language));
        }
        if (TextUtilsCompat.getLayoutDirectionFromLocale(MyUtil.Companion.convertLanguage(list_language.get(position))) == ViewCompat.LAYOUT_DIRECTION_LTR) {
            holder.txt_language.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
        } else {
            holder.txt_language.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        }

        holder.background.setOnClickListener(view -> {
            if (selected != position) {
                selected = position;
                adapter.onItemClick2(list_language.get(position));
                notifyDataSetChanged();
            }

        });
    }

    @Override
    public int getItemCount() {
        return list_language.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView txt_language;
        LinearLayout background;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_language = itemView.findViewById(R.id.txt_language);
            background = itemView.findViewById(R.id.language_bg);
        }
    }

    public void setSelected(int selected) {
        this.selected = selected;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
