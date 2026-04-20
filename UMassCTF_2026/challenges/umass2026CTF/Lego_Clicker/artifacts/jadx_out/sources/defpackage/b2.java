package defpackage;

import android.content.DialogInterface;
import android.view.View;
import android.widget.AdapterView;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class b2 implements AdapterView.OnItemClickListener {
    public final /* synthetic */ f2 a;
    public final /* synthetic */ c2 b;

    public b2(c2 c2Var, f2 f2Var) {
        this.b = c2Var;
        this.a = f2Var;
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public final void onItemClick(AdapterView adapterView, View view, int i, long j) {
        c2 c2Var = this.b;
        DialogInterface.OnClickListener onClickListener = c2Var.n;
        f2 f2Var = this.a;
        onClickListener.onClick(f2Var.b, i);
        if (c2Var.o) {
            return;
        }
        f2Var.b.dismiss();
    }
}
