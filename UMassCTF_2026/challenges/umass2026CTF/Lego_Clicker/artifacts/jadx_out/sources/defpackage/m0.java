package defpackage;

import android.content.DialogInterface;
import com.example.LegoClicker.FCA;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class m0 implements DialogInterface.OnClickListener {
    public final /* synthetic */ int a;
    public final /* synthetic */ l2 b;

    public /* synthetic */ m0(l2 l2Var, int i) {
        this.a = i;
        this.b = l2Var;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        int i2 = this.a;
        l2 l2Var = this.b;
        switch (i2) {
            case 0:
                ((n0) l2Var).finish();
                break;
            default:
                int[] iArr = FCA.a;
                ((FCA) l2Var).finish();
                break;
        }
    }
}
