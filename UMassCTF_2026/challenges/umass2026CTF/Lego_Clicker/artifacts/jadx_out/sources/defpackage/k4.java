package defpackage;

import android.view.View;
import android.widget.AdapterView;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class k4 implements AdapterView.OnItemClickListener {
    public final /* synthetic */ m4 a;

    public k4(m4 m4Var) {
        this.a = m4Var;
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public final void onItemClick(AdapterView adapterView, View view, int i, long j) {
        m4 m4Var = this.a;
        p4 p4Var = m4Var.F;
        p4Var.setSelection(i);
        if (p4Var.getOnItemClickListener() != null) {
            p4Var.performItemClick(view, i, m4Var.C.getItemId(i));
        }
        m4Var.dismiss();
    }
}
