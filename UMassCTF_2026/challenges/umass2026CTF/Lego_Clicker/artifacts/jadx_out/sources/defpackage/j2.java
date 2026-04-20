package defpackage;

import android.os.Bundle;
import java.util.ArrayList;
import java.util.LinkedHashSet;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class j2 implements wn {
    public final /* synthetic */ int a;
    public final Object b;

    public j2(xn xnVar) {
        this.a = 1;
        this.b = new LinkedHashSet();
        xnVar.c("androidx.savedstate.Restarter", this);
    }

    @Override // defpackage.wn
    public final Bundle a() {
        int i = this.a;
        Object obj = this.b;
        switch (i) {
            case 0:
                Bundle bundle = new Bundle();
                ((l2) obj).getDelegate().getClass();
                return bundle;
            default:
                Bundle bundle2 = new Bundle();
                bundle2.putStringArrayList("classes_to_restore", new ArrayList<>((LinkedHashSet) obj));
                return bundle2;
        }
    }

    public j2(l2 l2Var) {
        this.a = 0;
        this.b = l2Var;
    }
}
