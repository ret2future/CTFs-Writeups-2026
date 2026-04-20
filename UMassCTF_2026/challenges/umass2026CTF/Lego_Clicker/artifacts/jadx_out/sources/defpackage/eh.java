package defpackage;

import android.database.DataSetObserver;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class eh extends DataSetObserver {
    public final /* synthetic */ hh a;

    public eh(hh hhVar) {
        this.a = hhVar;
    }

    @Override // android.database.DataSetObserver
    public final void onChanged() {
        hh hhVar = this.a;
        if (hhVar.y.isShowing()) {
            hhVar.f();
        }
    }

    @Override // android.database.DataSetObserver
    public final void onInvalidated() {
        this.a.dismiss();
    }
}
