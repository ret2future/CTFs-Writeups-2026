package defpackage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class c7 {
    static {
        List listAsList = Arrays.asList(nd.class, yd.class, us.class, ce.class, de.class, ee.class, fe.class, ge.class, he.class, ie.class, od.class, pd.class, qd.class, rd.class, sd.class, td.class, ud.class, vd.class, wd.class, xd.class, zd.class, ae.class, be.class);
        listAsList.getClass();
        ArrayList arrayList = new ArrayList(listAsList.size());
        int i = 0;
        for (Object obj : listAsList) {
            int i2 = i + 1;
            if (i < 0) {
                throw new ArithmeticException("Index overflow has happened.");
            }
            arrayList.add(new jk((Class) obj, Integer.valueOf(i)));
            i = i2;
        }
        ci.f0(arrayList);
    }

    public final boolean equals(Object obj) {
        return (obj instanceof c7) && hb.D(this).equals(hb.D((c7) obj));
    }

    public final int hashCode() {
        return hb.D(this).hashCode();
    }

    public final String toString() {
        return tn.class + " (Kotlin reflection is not available)";
    }
}
