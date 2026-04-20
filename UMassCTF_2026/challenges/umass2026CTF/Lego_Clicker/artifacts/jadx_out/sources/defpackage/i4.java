package defpackage;

import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.ListAdapter;
import androidx.appcompat.app.AlertController$RecycleListView;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class i4 implements o4, DialogInterface.OnClickListener {
    public h2 a;
    public j4 b;
    public CharSequence c;
    public final /* synthetic */ p4 d;

    public i4(p4 p4Var) {
        this.d = p4Var;
    }

    @Override // defpackage.o4
    public final boolean b() {
        h2 h2Var = this.a;
        if (h2Var != null) {
            return h2Var.isShowing();
        }
        return false;
    }

    @Override // defpackage.o4
    public final void c(int i) {
        Log.e("AppCompatSpinner", "Cannot set horizontal offset for MODE_DIALOG, ignoring");
    }

    @Override // defpackage.o4
    public final int d() {
        return 0;
    }

    @Override // defpackage.o4
    public final void dismiss() {
        h2 h2Var = this.a;
        if (h2Var != null) {
            h2Var.dismiss();
            this.a = null;
        }
    }

    @Override // defpackage.o4
    public final void e(int i, int i2) {
        if (this.b == null) {
            return;
        }
        p4 p4Var = this.d;
        g2 g2Var = new g2(p4Var.getPopupContext());
        c2 c2Var = (c2) g2Var.b;
        CharSequence charSequence = this.c;
        if (charSequence != null) {
            c2Var.d = charSequence;
        }
        j4 j4Var = this.b;
        int selectedItemPosition = p4Var.getSelectedItemPosition();
        c2Var.m = j4Var;
        c2Var.n = this;
        c2Var.p = selectedItemPosition;
        c2Var.o = true;
        h2 h2VarA = g2Var.a();
        this.a = h2VarA;
        AlertController$RecycleListView alertController$RecycleListView = h2VarA.f.f;
        alertController$RecycleListView.setTextDirection(i);
        alertController$RecycleListView.setTextAlignment(i2);
        this.a.show();
    }

    @Override // defpackage.o4
    public final int g() {
        return 0;
    }

    @Override // defpackage.o4
    public final Drawable h() {
        return null;
    }

    @Override // defpackage.o4
    public final CharSequence i() {
        return this.c;
    }

    @Override // defpackage.o4
    public final void k(CharSequence charSequence) {
        this.c = charSequence;
    }

    @Override // defpackage.o4
    public final void l(Drawable drawable) {
        Log.e("AppCompatSpinner", "Cannot set popup background for MODE_DIALOG, ignoring");
    }

    @Override // defpackage.o4
    public final void m(int i) {
        Log.e("AppCompatSpinner", "Cannot set vertical offset for MODE_DIALOG, ignoring");
    }

    @Override // defpackage.o4
    public final void n(ListAdapter listAdapter) {
        this.b = (j4) listAdapter;
    }

    @Override // defpackage.o4
    public final void o(int i) {
        Log.e("AppCompatSpinner", "Cannot set horizontal (original) offset for MODE_DIALOG, ignoring");
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        p4 p4Var = this.d;
        p4Var.setSelection(i);
        if (p4Var.getOnItemClickListener() != null) {
            p4Var.performItemClick(null, i, this.b.getItemId(i));
        }
        dismiss();
    }
}
