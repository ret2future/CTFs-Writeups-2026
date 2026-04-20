package defpackage;

import java.util.Locale;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public abstract class mh {
    public static final Locale[] a = {new Locale("en", "XA"), new Locale("ar", "XB")};

    public static Locale a(String str) {
        return Locale.forLanguageTag(str);
    }

    public static boolean b(Locale locale, Locale locale2) {
        if (locale.equals(locale2)) {
            return true;
        }
        if (locale.getLanguage().equals(locale2.getLanguage())) {
            Locale[] localeArr = a;
            int length = localeArr.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    int length2 = localeArr.length;
                    int i2 = 0;
                    while (true) {
                        if (i2 >= length2) {
                            String strC = ye.c(ye.a(ye.b(locale)));
                            if (!strC.isEmpty()) {
                                return strC.equals(ye.c(ye.a(ye.b(locale2))));
                            }
                            String country = locale.getCountry();
                            if (country.isEmpty() || country.equals(locale2.getCountry())) {
                                return true;
                            }
                        } else {
                            if (localeArr[i2].equals(locale2)) {
                                break;
                            }
                            i2++;
                        }
                    }
                } else {
                    if (localeArr[i].equals(locale)) {
                        break;
                    }
                    i++;
                }
            }
        }
        return false;
    }
}
