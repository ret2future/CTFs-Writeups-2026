package defpackage;

import android.content.Context;
import android.view.View;
import android.view.Window;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class eq implements View.OnClickListener {
    public final x0 a;
    public final /* synthetic */ gq b;

    public eq(gq gqVar) {
        this.b = gqVar;
        Context context = gqVar.a.getContext();
        CharSequence charSequence = gqVar.h;
        x0 x0Var = new x0();
        x0Var.e = 4096;
        x0Var.g = 4096;
        x0Var.l = null;
        x0Var.m = null;
        x0Var.n = false;
        x0Var.o = false;
        x0Var.p = 16;
        x0Var.i = context;
        x0Var.a = charSequence;
        this.a = x0Var;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        gq gqVar = this.b;
        Window.Callback callback = gqVar.k;
        if (callback == null || !gqVar.l) {
            return;
        }
        callback.onMenuItemSelected(0, this.a);
    }
}
