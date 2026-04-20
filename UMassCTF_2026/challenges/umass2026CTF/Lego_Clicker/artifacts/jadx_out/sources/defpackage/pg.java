package defpackage;

import android.view.View;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class pg {
    public ra a;
    public int b;
    public int c;
    public boolean d;
    public boolean e;

    public pg() {
        c();
    }

    public final void a() {
        boolean z = this.d;
        ra raVar = this.a;
        this.c = z ? raVar.g() : raVar.k();
    }

    public final void b(View view, int i) {
        ra raVar = this.a;
        int iL = Integer.MIN_VALUE == raVar.a ? 0 : raVar.l() - raVar.a;
        if (iL >= 0) {
            boolean z = this.d;
            ra raVar2 = this.a;
            if (z) {
                int iB = raVar2.b(view);
                ra raVar3 = this.a;
                this.c = (Integer.MIN_VALUE != raVar3.a ? raVar3.l() - raVar3.a : 0) + iB;
            } else {
                this.c = raVar2.e(view);
            }
            this.b = i;
            return;
        }
        this.b = i;
        boolean z2 = this.d;
        ra raVar4 = this.a;
        if (!z2) {
            int iE = raVar4.e(view);
            int iK = iE - this.a.k();
            this.c = iE;
            if (iK > 0) {
                int iG = (this.a.g() - Math.min(0, (this.a.g() - iL) - this.a.b(view))) - (this.a.c(view) + iE);
                if (iG < 0) {
                    this.c -= Math.min(iK, -iG);
                    return;
                }
                return;
            }
            return;
        }
        int iG2 = (raVar4.g() - iL) - this.a.b(view);
        this.c = this.a.g() - iG2;
        if (iG2 > 0) {
            int iC = this.c - this.a.c(view);
            int iK2 = this.a.k();
            int iMin = iC - (Math.min(this.a.e(view) - iK2, 0) + iK2);
            if (iMin < 0) {
                this.c = Math.min(iG2, -iMin) + this.c;
            }
        }
    }

    public final void c() {
        this.b = -1;
        this.c = Integer.MIN_VALUE;
        this.d = false;
        this.e = false;
    }

    public final String toString() {
        return "AnchorInfo{mPosition=" + this.b + ", mCoordinate=" + this.c + ", mLayoutFromEnd=" + this.d + ", mValid=" + this.e + '}';
    }
}
