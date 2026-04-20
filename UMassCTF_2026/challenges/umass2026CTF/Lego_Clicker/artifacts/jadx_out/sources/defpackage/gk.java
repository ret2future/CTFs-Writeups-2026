package defpackage;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class gk extends ra {
    public final /* synthetic */ int d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ gk(ql qlVar, int i) {
        super(qlVar);
        this.d = i;
    }

    @Override // defpackage.ra
    public final int b(View view) {
        int right;
        int i;
        int i2 = this.d;
        Object obj = this.b;
        switch (i2) {
            case 0:
                rl rlVar = (rl) view.getLayoutParams();
                ((ql) obj).getClass();
                right = view.getRight() + ((rl) view.getLayoutParams()).b.right;
                i = ((ViewGroup.MarginLayoutParams) rlVar).rightMargin;
                break;
            default:
                rl rlVar2 = (rl) view.getLayoutParams();
                ((ql) obj).getClass();
                right = view.getBottom() + ((rl) view.getLayoutParams()).b.bottom;
                i = ((ViewGroup.MarginLayoutParams) rlVar2).bottomMargin;
                break;
        }
        return right + i;
    }

    @Override // defpackage.ra
    public final int c(View view) {
        int measuredWidth;
        int i;
        int i2 = this.d;
        Object obj = this.b;
        switch (i2) {
            case 0:
                rl rlVar = (rl) view.getLayoutParams();
                ((ql) obj).getClass();
                Rect rect = ((rl) view.getLayoutParams()).b;
                measuredWidth = view.getMeasuredWidth() + rect.left + rect.right + ((ViewGroup.MarginLayoutParams) rlVar).leftMargin;
                i = ((ViewGroup.MarginLayoutParams) rlVar).rightMargin;
                break;
            default:
                rl rlVar2 = (rl) view.getLayoutParams();
                ((ql) obj).getClass();
                Rect rect2 = ((rl) view.getLayoutParams()).b;
                measuredWidth = view.getMeasuredHeight() + rect2.top + rect2.bottom + ((ViewGroup.MarginLayoutParams) rlVar2).topMargin;
                i = ((ViewGroup.MarginLayoutParams) rlVar2).bottomMargin;
                break;
        }
        return measuredWidth + i;
    }

    @Override // defpackage.ra
    public final int d(View view) {
        int measuredHeight;
        int i;
        int i2 = this.d;
        Object obj = this.b;
        switch (i2) {
            case 0:
                rl rlVar = (rl) view.getLayoutParams();
                ((ql) obj).getClass();
                Rect rect = ((rl) view.getLayoutParams()).b;
                measuredHeight = view.getMeasuredHeight() + rect.top + rect.bottom + ((ViewGroup.MarginLayoutParams) rlVar).topMargin;
                i = ((ViewGroup.MarginLayoutParams) rlVar).bottomMargin;
                break;
            default:
                rl rlVar2 = (rl) view.getLayoutParams();
                ((ql) obj).getClass();
                Rect rect2 = ((rl) view.getLayoutParams()).b;
                measuredHeight = view.getMeasuredWidth() + rect2.left + rect2.right + ((ViewGroup.MarginLayoutParams) rlVar2).leftMargin;
                i = ((ViewGroup.MarginLayoutParams) rlVar2).rightMargin;
                break;
        }
        return measuredHeight + i;
    }

    @Override // defpackage.ra
    public final int e(View view) {
        int left;
        int i;
        int i2 = this.d;
        Object obj = this.b;
        switch (i2) {
            case 0:
                rl rlVar = (rl) view.getLayoutParams();
                ((ql) obj).getClass();
                left = view.getLeft() - ((rl) view.getLayoutParams()).b.left;
                i = ((ViewGroup.MarginLayoutParams) rlVar).leftMargin;
                break;
            default:
                rl rlVar2 = (rl) view.getLayoutParams();
                ((ql) obj).getClass();
                left = view.getTop() - ((rl) view.getLayoutParams()).b.top;
                i = ((ViewGroup.MarginLayoutParams) rlVar2).topMargin;
                break;
        }
        return left - i;
    }

    @Override // defpackage.ra
    public final int f() {
        switch (this.d) {
            case 0:
                return ((ql) this.b).m;
            default:
                return ((ql) this.b).n;
        }
    }

    @Override // defpackage.ra
    public final int g() {
        int i;
        int iA;
        int i2 = this.d;
        Object obj = this.b;
        switch (i2) {
            case 0:
                ql qlVar = (ql) obj;
                i = qlVar.m;
                iA = qlVar.A();
                break;
            default:
                ql qlVar2 = (ql) obj;
                i = qlVar2.n;
                iA = qlVar2.y();
                break;
        }
        return i - iA;
    }

    @Override // defpackage.ra
    public final int h() {
        switch (this.d) {
            case 0:
                return ((ql) this.b).A();
            default:
                return ((ql) this.b).y();
        }
    }

    @Override // defpackage.ra
    public final int i() {
        switch (this.d) {
            case 0:
                return ((ql) this.b).k;
            default:
                return ((ql) this.b).l;
        }
    }

    @Override // defpackage.ra
    public final int j() {
        switch (this.d) {
            case 0:
                return ((ql) this.b).l;
            default:
                return ((ql) this.b).k;
        }
    }

    @Override // defpackage.ra
    public final int k() {
        switch (this.d) {
            case 0:
                return ((ql) this.b).z();
            default:
                return ((ql) this.b).B();
        }
    }

    @Override // defpackage.ra
    public final int l() {
        int iZ;
        int iA;
        int i = this.d;
        Object obj = this.b;
        switch (i) {
            case 0:
                ql qlVar = (ql) obj;
                iZ = qlVar.m - qlVar.z();
                iA = qlVar.A();
                break;
            default:
                ql qlVar2 = (ql) obj;
                iZ = qlVar2.n - qlVar2.B();
                iA = qlVar2.y();
                break;
        }
        return iZ - iA;
    }

    @Override // defpackage.ra
    public final int m(View view) {
        int i = this.d;
        Object obj = this.c;
        Object obj2 = this.b;
        switch (i) {
            case 0:
                Rect rect = (Rect) obj;
                ((ql) obj2).F(view, rect);
                return rect.right;
            default:
                Rect rect2 = (Rect) obj;
                ((ql) obj2).F(view, rect2);
                return rect2.bottom;
        }
    }

    @Override // defpackage.ra
    public final int n(View view) {
        int i = this.d;
        Object obj = this.c;
        Object obj2 = this.b;
        switch (i) {
            case 0:
                Rect rect = (Rect) obj;
                ((ql) obj2).F(view, rect);
                return rect.left;
            default:
                Rect rect2 = (Rect) obj;
                ((ql) obj2).F(view, rect2);
                return rect2.top;
        }
    }

    @Override // defpackage.ra
    public final void o(int i) {
        switch (this.d) {
            case 0:
                ((ql) this.b).J(i);
                break;
            default:
                ((ql) this.b).K(i);
                break;
        }
    }
}
