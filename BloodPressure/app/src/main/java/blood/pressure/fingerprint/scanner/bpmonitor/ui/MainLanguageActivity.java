package blood.pressure.fingerprint.scanner.bpmonitor.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tencent.mmkv.MMKV;
import com.zeugmasolutions.localehelper.LocaleAwareCompatActivity;
import com.zeugmasolutions.localehelper.LocaleHelper;
import com.zeugmasolutions.localehelper.LocaleHelperActivityDelegate;
import com.zeugmasolutions.localehelper.LocaleHelperActivityDelegateImpl;
import com.zeugmasolutions.localehelper.Locales;

import java.util.Locale;

import blood.pressure.fingerprint.scanner.bpmonitor.R;
import blood.pressure.fingerprint.scanner.bpmonitor.adapter.Adapter_language;
import blood.pressure.fingerprint.scanner.bpmonitor.interfaces.IAdapter;
import blood.pressure.fingerprint.scanner.bpmonitor.interfaces.LanguageCode;
import blood.pressure.fingerprint.scanner.bpmonitor.util.MyUtil;
import blood.pressure.fingerprint.scanner.bpmonitor.util.SharedPreferencesUtils;
import blood.pressure.fingerprint.scanner.bpmonitor.viewmodel.LanguageViewModel;

public class MainLanguageActivity extends LocaleAwareCompatActivity implements IAdapter {

    private final LocaleHelperActivityDelegate localeDelegate = new LocaleHelperActivityDelegateImpl();
    private LanguageViewModel viewModel;
    private Adapter_language adapter_language;
    private MMKV kv = MMKV.defaultMMKV();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_language);

        Log.d("vinhm", "onCreate");
        localeDelegate.onCreate(this);

        MyUtil.Companion.setStatusBar(this);

        viewModel = new ViewModelProvider(this).get(LanguageViewModel.class);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);

        adapter_language = new Adapter_language(LanguageCode.Companion.getLanguages(), getApplicationContext(), this);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);

        recyclerView.setAdapter(adapter_language);

        findViewById(R.id.bt_next).setOnClickListener(view -> {

            kv.encode(SharedPreferencesUtils.INSTALL_FOR_THE_FIRST_TIME, false);
            kv.encode(SharedPreferencesUtils.KEY_LOCALE_LANGUAGE, adapter_language.getLanguage());
            startActivity(new Intent(MainLanguageActivity.this, MainActivity.class));
        });
        kv.encode(SharedPreferencesUtils.KEY_LOCALE_LANGUAGE, LanguageCode.English);
    }

    @Override
    public void onItemClick(int position) {

    }

    @Override
    public void onItemClick2(@NonNull String value) {
        Log.d("vinhm", value + "/" + MyUtil.Companion.convertLanguage(value));
        viewModel.setLanguage(value);
        adapter_language.setLanguage(LanguageCode.Companion.getLanguageCode(value));
        updateLocale(MyUtil.Companion.convertLanguage(value));
    }

    @NonNull
    @Override
    public AppCompatDelegate getDelegate() {
        return super.getDelegate();
    }

    @Override
    protected void attachBaseContext(@NonNull Context newBase) {
        super.attachBaseContext(localeDelegate.attachBaseContext(newBase));
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onResume() {
        Log.d("vinhm", "onResume");
        super.onResume();
        localeDelegate.onResumed(this);
        adapter_language.setSelected(LanguageCode.Companion.getLanguages().indexOf(viewModel.getLanguage()));
        adapter_language.notifyDataSetChanged();
        if (viewModel.getFirst()) {
            updateLocale(Locales.INSTANCE.getEnglish());
            viewModel.setFirst(false);
        }
    }

    @Override
    protected void onPause() {
        Log.d("vinhm", "onPause");
        super.onPause();
        localeDelegate.onPaused();
    }

    @NonNull
    @Override
    public Context createConfigurationContext(@NonNull Configuration overrideConfiguration) {
        Context context = super.createConfigurationContext(overrideConfiguration);
        return LocaleHelper.INSTANCE.onAttach(context);
    }

    @NonNull
    @Override
    public Context getApplicationContext() {
        return localeDelegate.getApplicationContext(super.getApplicationContext());
    }

    @Override
    public void updateLocale(@NonNull Locale locale) {
        localeDelegate.setLocale(this, locale);
    }
}