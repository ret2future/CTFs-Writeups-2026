package defpackage;

import android.content.res.Configuration;
import android.os.LocaleList;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public abstract class e3 {
    public static void a(Configuration configuration, Configuration configuration2, Configuration configuration3) {
        LocaleList locales = configuration.getLocales();
        LocaleList locales2 = configuration2.getLocales();
        if (locales.equals(locales2)) {
            return;
        }
        configuration3.setLocales(locales2);
        configuration3.locale = configuration2.locale;
    }

    public static oh b(Configuration configuration) {
        return oh.a(configuration.getLocales().toLanguageTags());
    }

    public static void c(oh ohVar) {
        LocaleList.setDefault(LocaleList.forLanguageTags(ohVar.a.a.toLanguageTags()));
    }

    public static void d(Configuration configuration, oh ohVar) {
        configuration.setLocales(LocaleList.forLanguageTags(ohVar.a.a.toLanguageTags()));
    }
}
