package defpackage;

import java.util.Locale;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class oh {
    public static final oh b = new oh(new ph(nh.a(new Locale[0])));
    public final ph a;

    public oh(ph phVar) {
        this.a = phVar;
    }

    public static oh a(String str) {
        if (str == null || str.isEmpty()) {
            return b;
        }
        String[] strArrSplit = str.split(",", -1);
        int length = strArrSplit.length;
        Locale[] localeArr = new Locale[length];
        for (int i = 0; i < length; i++) {
            localeArr[i] = mh.a(strArrSplit[i]);
        }
        return new oh(new ph(nh.a(localeArr)));
    }

    public final boolean equals(Object obj) {
        if (obj instanceof oh) {
            return this.a.equals(((oh) obj).a);
        }
        return false;
    }

    public final int hashCode() {
        return this.a.a.hashCode();
    }

    public final String toString() {
        return this.a.a.toString();
    }
}
