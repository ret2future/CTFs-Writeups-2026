package defpackage;

import android.view.View;
import android.view.ViewGroup;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class ol {
    public final /* synthetic */ int a;
    public final /* synthetic */ ql b;

    public /* synthetic */ ol(ql qlVar, int i) {
        this.a = i;
        this.b = qlVar;
    }

    public final int a(View view) {
        int right;
        int i;
        switch (this.a) {
            case 0:
                rl rlVar = (rl) view.getLayoutParams();
                right = view.getRight() + ((rl) view.getLayoutParams()).b.right;
                i = ((ViewGroup.MarginLayoutParams) rlVar).rightMargin;
                break;
            default:
                rl rlVar2 = (rl) view.getLayoutParams();
                right = view.getBottom() + ((rl) view.getLayoutParams()).b.bottom;
                i = ((ViewGroup.MarginLayoutParams) rlVar2).bottomMargin;
                break;
        }
        return right + i;
    }

    public final int b(View view) {
        int left;
        int i;
        switch (this.a) {
            case 0:
                rl rlVar = (rl) view.getLayoutParams();
                left = view.getLeft() - ((rl) view.getLayoutParams()).b.left;
                i = ((ViewGroup.MarginLayoutParams) rlVar).leftMargin;
                break;
            default:
                rl rlVar2 = (rl) view.getLayoutParams();
                left = view.getTop() - ((rl) view.getLayoutParams()).b.top;
                i = ((ViewGroup.MarginLayoutParams) rlVar2).topMargin;
                break;
        }
        return left - i;
    }

    public final int c() {
        int i;
        int iA;
        int i2 = this.a;
        ql qlVar = this.b;
        switch (i2) {
            case 0:
                i = qlVar.m;
                iA = qlVar.A();
                break;
            default:
                i = qlVar.n;
                iA = qlVar.y();
                break;
        }
        return i - iA;
    }

    public final int d() {
        int i = this.a;
        ql qlVar = this.b;
        switch (i) {
            case 0:
                return qlVar.z();
            default:
                return qlVar.B();
        }
    }
}
