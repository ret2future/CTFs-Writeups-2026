package defpackage;

import android.text.InputFilter;
import android.widget.TextView;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class ib extends hb {
    public final gb o;

    public ib(TextView textView) {
        this.o = new gb(textView);
    }

    @Override // defpackage.hb
    public final InputFilter[] C(InputFilter[] inputFilterArr) {
        return !(va.k != null) ? inputFilterArr : this.o.C(inputFilterArr);
    }

    @Override // defpackage.hb
    public final void T(boolean z) {
        if (va.k != null) {
            this.o.T(z);
        }
    }

    @Override // defpackage.hb
    public final void U(boolean z) {
        boolean z2 = va.k != null;
        gb gbVar = this.o;
        if (z2) {
            gbVar.U(z);
        } else {
            gbVar.q = z;
        }
    }
}
