package defpackage;

import android.R;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import androidx.appcompat.app.AlertController$RecycleListView;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class g2 {
    public final int a;
    public final Object b;

    public g2(Context context) {
        int iG = h2.g(context, 0);
        this.b = new c2(new ContextThemeWrapper(context, h2.g(context, iG)));
        this.a = iG;
    }

    public h2 a() {
        c2 c2Var = (c2) this.b;
        h2 h2Var = new h2(c2Var.a, this.a);
        View view = c2Var.e;
        f2 f2Var = h2Var.f;
        if (view != null) {
            f2Var.u = view;
        } else {
            CharSequence charSequence = c2Var.d;
            if (charSequence != null) {
                f2Var.d = charSequence;
                TextView textView = f2Var.s;
                if (textView != null) {
                    textView.setText(charSequence);
                }
            }
            Drawable drawable = c2Var.c;
            if (drawable != null) {
                f2Var.q = drawable;
                ImageView imageView = f2Var.r;
                if (imageView != null) {
                    imageView.setVisibility(0);
                    f2Var.r.setImageDrawable(drawable);
                }
            }
        }
        String str = c2Var.f;
        if (str != null) {
            f2Var.e = str;
            TextView textView2 = f2Var.t;
            if (textView2 != null) {
                textView2.setText(str);
            }
        }
        CharSequence charSequence2 = c2Var.g;
        if (charSequence2 != null) {
            f2Var.b(-1, charSequence2, c2Var.h);
        }
        String str2 = c2Var.i;
        if (str2 != null) {
            f2Var.b(-2, str2, c2Var.j);
        }
        if (c2Var.m != null) {
            AlertController$RecycleListView alertController$RecycleListView = (AlertController$RecycleListView) c2Var.b.inflate(f2Var.y, (ViewGroup) null);
            int i = c2Var.o ? f2Var.z : f2Var.A;
            ListAdapter e2Var = c2Var.m;
            if (e2Var == null) {
                e2Var = new e2(c2Var.a, i, R.id.text1, null);
            }
            f2Var.v = e2Var;
            f2Var.w = c2Var.p;
            if (c2Var.n != null) {
                alertController$RecycleListView.setOnItemClickListener(new b2(c2Var, f2Var));
            }
            if (c2Var.o) {
                alertController$RecycleListView.setChoiceMode(1);
            }
            f2Var.f = alertController$RecycleListView;
        }
        h2Var.setCancelable(c2Var.k);
        if (c2Var.k) {
            h2Var.setCanceledOnTouchOutside(true);
        }
        h2Var.setOnCancelListener(null);
        h2Var.setOnDismissListener(null);
        hi hiVar = c2Var.l;
        if (hiVar != null) {
            h2Var.setOnKeyListener(hiVar);
        }
        return h2Var;
    }

    public g2(int i, mc[] mcVarArr) {
        this.a = i;
        this.b = mcVarArr;
    }
}
