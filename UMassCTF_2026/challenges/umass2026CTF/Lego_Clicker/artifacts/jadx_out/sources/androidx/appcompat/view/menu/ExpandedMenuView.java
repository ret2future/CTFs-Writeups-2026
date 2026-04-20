package androidx.appcompat.view.menu;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import defpackage.ej;
import defpackage.fi;
import defpackage.gi;
import defpackage.j5;
import defpackage.oi;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class ExpandedMenuView extends ListView implements fi, ej, AdapterView.OnItemClickListener {
    public static final int[] b = {R.attr.background, R.attr.divider};
    public gi a;

    public ExpandedMenuView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet);
        setOnItemClickListener(this);
        j5 j5VarQ = j5.q(context, attributeSet, b, i);
        TypedArray typedArray = (TypedArray) j5VarQ.a;
        if (typedArray.hasValue(0)) {
            setBackgroundDrawable(j5VarQ.k(0));
        }
        if (typedArray.hasValue(1)) {
            setDivider(j5VarQ.k(1));
        }
        j5VarQ.s();
    }

    @Override // defpackage.fi
    public final boolean a(oi oiVar) {
        return this.a.q(oiVar, null, 0);
    }

    @Override // defpackage.ej
    public final void b(gi giVar) {
        this.a = giVar;
    }

    public int getWindowAnimations() {
        return 0;
    }

    @Override // android.widget.ListView, android.widget.AbsListView, android.widget.AdapterView, android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        setChildrenDrawingCacheEnabled(false);
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public final void onItemClick(AdapterView adapterView, View view, int i, long j) {
        a((oi) getAdapter().getItem(i));
    }

    public ExpandedMenuView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.listViewStyle);
    }
}
