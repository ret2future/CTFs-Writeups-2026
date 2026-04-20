package defpackage;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.recyclerview.widget.RecyclerView;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class fm extends r {
    public final gm d;
    public final WeakHashMap e = new WeakHashMap();

    public fm(gm gmVar) {
        this.d = gmVar;
    }

    @Override // defpackage.r
    public final boolean a(View view, AccessibilityEvent accessibilityEvent) {
        r rVar = (r) this.e.get(view);
        return rVar != null ? rVar.a(view, accessibilityEvent) : this.a.dispatchPopulateAccessibilityEvent(view, accessibilityEvent);
    }

    @Override // defpackage.r
    public final e0 b(View view) {
        r rVar = (r) this.e.get(view);
        return rVar != null ? rVar.b(view) : super.b(view);
    }

    @Override // defpackage.r
    public final void c(View view, AccessibilityEvent accessibilityEvent) {
        r rVar = (r) this.e.get(view);
        if (rVar != null) {
            rVar.c(view, accessibilityEvent);
        } else {
            super.c(view, accessibilityEvent);
        }
    }

    @Override // defpackage.r
    public final void d(View view, d0 d0Var) {
        AccessibilityNodeInfo accessibilityNodeInfo = d0Var.a;
        gm gmVar = this.d;
        RecyclerView recyclerView = gmVar.d;
        RecyclerView recyclerView2 = gmVar.d;
        boolean zJ = recyclerView.J();
        View.AccessibilityDelegate accessibilityDelegate = this.a;
        if (zJ || recyclerView2.getLayoutManager() == null) {
            accessibilityDelegate.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
            return;
        }
        recyclerView2.getLayoutManager().R(view, d0Var);
        r rVar = (r) this.e.get(view);
        if (rVar != null) {
            rVar.d(view, d0Var);
        } else {
            accessibilityDelegate.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
        }
    }

    @Override // defpackage.r
    public final void e(View view, AccessibilityEvent accessibilityEvent) {
        r rVar = (r) this.e.get(view);
        if (rVar != null) {
            rVar.e(view, accessibilityEvent);
        } else {
            super.e(view, accessibilityEvent);
        }
    }

    @Override // defpackage.r
    public final boolean f(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
        r rVar = (r) this.e.get(viewGroup);
        return rVar != null ? rVar.f(viewGroup, view, accessibilityEvent) : this.a.onRequestSendAccessibilityEvent(viewGroup, view, accessibilityEvent);
    }

    @Override // defpackage.r
    public final boolean g(View view, int i, Bundle bundle) {
        gm gmVar = this.d;
        RecyclerView recyclerView = gmVar.d;
        RecyclerView recyclerView2 = gmVar.d;
        if (recyclerView.J() || recyclerView2.getLayoutManager() == null) {
            return super.g(view, i, bundle);
        }
        r rVar = (r) this.e.get(view);
        if (rVar != null) {
            if (rVar.g(view, i, bundle)) {
                return true;
            }
        } else if (super.g(view, i, bundle)) {
            return true;
        }
        wl wlVar = recyclerView2.getLayoutManager().b.c;
        return false;
    }

    @Override // defpackage.r
    public final void h(View view, int i) {
        r rVar = (r) this.e.get(view);
        if (rVar != null) {
            rVar.h(view, i);
        } else {
            super.h(view, i);
        }
    }

    @Override // defpackage.r
    public final void i(View view, AccessibilityEvent accessibilityEvent) {
        r rVar = (r) this.e.get(view);
        if (rVar != null) {
            rVar.i(view, accessibilityEvent);
        } else {
            super.i(view, accessibilityEvent);
        }
    }
}
