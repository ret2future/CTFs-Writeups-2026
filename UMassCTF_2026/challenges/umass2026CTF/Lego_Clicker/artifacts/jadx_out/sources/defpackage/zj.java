package defpackage;

import androidx.activity.c;
import java.io.Serializable;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class zj implements nd, Serializable {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;

    public zj(int i, Object obj) {
        this.a = i;
        this.b = obj;
    }

    @Override // defpackage.nd
    public final Object a() {
        int i = this.a;
        Object obj = this.b;
        switch (i) {
            case 0:
                ((c) obj).c();
                return fr.c;
            case 1:
                ((c) obj).b();
                return fr.c;
            default:
                return d.B((vc) obj);
        }
    }

    public final String toString() {
        hm.a.getClass();
        String string = getClass().getGenericInterfaces()[0].toString();
        return string.startsWith("kotlin.jvm.functions.") ? string.substring(21) : string;
    }
}
