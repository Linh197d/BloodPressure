package blood.pressure.fingerprint.scanner.bpmonitor.viewmodel;

import androidx.lifecycle.ViewModel;

import blood.pressure.fingerprint.scanner.bpmonitor.interfaces.LanguageCode;

public class LanguageViewModel extends ViewModel {

    private String language = LanguageCode.Companion.getLanguages().get(0);
    private Boolean first = true;

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Boolean getFirst() {
        return first;
    }

    public void setFirst(Boolean first) {
        this.first = first;
    }
}
