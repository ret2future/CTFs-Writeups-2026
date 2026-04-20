package defpackage;

import androidx.recyclerview.widget.RecyclerView;
import java.util.Arrays;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class le {
    public int a;
    public int b;
    public int[] c;
    public int d;

    public final void a(int i, int i2) {
        if (i < 0) {
            z6.f("Layout positions must be non-negative");
            return;
        }
        if (i2 < 0) {
            z6.f("Pixel distance must be non-negative");
            return;
        }
        int i3 = this.d;
        int i4 = i3 * 2;
        int[] iArr = this.c;
        if (iArr == null) {
            int[] iArr2 = new int[4];
            this.c = iArr2;
            Arrays.fill(iArr2, -1);
        } else if (i4 >= iArr.length) {
            int[] iArr3 = new int[i3 * 4];
            this.c = iArr3;
            System.arraycopy(iArr, 0, iArr3, 0, iArr.length);
        }
        int[] iArr4 = this.c;
        iArr4[i4] = i;
        iArr4[i4 + 1] = i2;
        this.d++;
    }

    public final void b(RecyclerView recyclerView, boolean z) {
        this.d = 0;
        int[] iArr = this.c;
        if (iArr != null) {
            Arrays.fill(iArr, -1);
        }
        ql qlVar = recyclerView.m;
        if (recyclerView.l == null || qlVar == null || !qlVar.h) {
            return;
        }
        if (z) {
            if (!recyclerView.e.f()) {
                qlVar.h(recyclerView.l.a(), this);
            }
        } else if (!recyclerView.J()) {
            qlVar.g(this.a, this.b, recyclerView.d0, this);
        }
        int i = this.d;
        if (i > qlVar.i) {
            qlVar.i = i;
            qlVar.j = z;
            recyclerView.c.m();
        }
    }
}
