package defpackage;

import android.os.Build;
import androidx.activity.c;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class bk implements s6 {
    public final bd a;
    public final /* synthetic */ c b;

    public bk(c cVar, bd bdVar) {
        bdVar.getClass();
        this.b = cVar;
        this.a = bdVar;
    }

    @Override // defpackage.s6
    public final void cancel() {
        c cVar = this.b;
        q5 q5Var = cVar.b;
        bd bdVar = this.a;
        q5Var.remove(bdVar);
        bdVar.getClass();
        bdVar.b.remove(this);
        if (Build.VERSION.SDK_INT >= 33) {
            bdVar.c = null;
            cVar.c();
        }
    }
}
