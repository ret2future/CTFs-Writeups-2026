package defpackage;

import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.view.View;
import java.util.ArrayList;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class p9 extends nl {
    public static TimeInterpolator s;
    public boolean g;
    public ArrayList h;
    public ArrayList i;
    public ArrayList j;
    public ArrayList k;
    public ArrayList l;
    public ArrayList m;
    public ArrayList n;
    public ArrayList o;
    public ArrayList p;
    public ArrayList q;
    public ArrayList r;

    public static void h(ArrayList arrayList) {
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            ((em) arrayList.get(size)).a.animate().cancel();
        }
    }

    @Override // defpackage.nl
    public final boolean a(em emVar, em emVar2, tj tjVar, tj tjVar2) {
        int i;
        int i2;
        int i3 = tjVar.a;
        int i4 = tjVar.b;
        if (emVar2.n()) {
            int i5 = tjVar.a;
            i2 = tjVar.b;
            i = i5;
        } else {
            i = tjVar2.a;
            i2 = tjVar2.b;
        }
        if (emVar == emVar2) {
            return g(emVar, i3, i4, i, i2);
        }
        View view = emVar.a;
        float translationX = view.getTranslationX();
        float translationY = view.getTranslationY();
        float alpha = view.getAlpha();
        l(emVar);
        view.setTranslationX(translationX);
        view.setTranslationY(translationY);
        view.setAlpha(alpha);
        View view2 = emVar2.a;
        l(emVar2);
        view2.setTranslationX(-((int) ((i - i3) - translationX)));
        view2.setTranslationY(-((int) ((i2 - i4) - translationY)));
        view2.setAlpha(0.0f);
        ArrayList arrayList = this.k;
        n9 n9Var = new n9();
        n9Var.a = emVar;
        n9Var.b = emVar2;
        n9Var.c = i3;
        n9Var.d = i4;
        n9Var.e = i;
        n9Var.f = i2;
        arrayList.add(n9Var);
        return true;
    }

    @Override // defpackage.nl
    public final void d(em emVar) {
        ArrayList arrayList = this.l;
        ArrayList arrayList2 = this.m;
        ArrayList arrayList3 = this.n;
        View view = emVar.a;
        view.animate().cancel();
        ArrayList arrayList4 = this.j;
        int size = arrayList4.size();
        while (true) {
            size--;
            if (size < 0) {
                break;
            }
            if (((o9) arrayList4.get(size)).a == emVar) {
                view.setTranslationY(0.0f);
                view.setTranslationX(0.0f);
                c(emVar);
                arrayList4.remove(size);
            }
        }
        j(this.k, emVar);
        if (this.h.remove(emVar)) {
            view.setAlpha(1.0f);
            c(emVar);
        }
        if (this.i.remove(emVar)) {
            view.setAlpha(1.0f);
            c(emVar);
        }
        for (int size2 = arrayList3.size() - 1; size2 >= 0; size2--) {
            ArrayList arrayList5 = (ArrayList) arrayList3.get(size2);
            j(arrayList5, emVar);
            if (arrayList5.isEmpty()) {
                arrayList3.remove(size2);
            }
        }
        for (int size3 = arrayList2.size() - 1; size3 >= 0; size3--) {
            ArrayList arrayList6 = (ArrayList) arrayList2.get(size3);
            int size4 = arrayList6.size() - 1;
            while (true) {
                if (size4 < 0) {
                    break;
                }
                if (((o9) arrayList6.get(size4)).a == emVar) {
                    view.setTranslationY(0.0f);
                    view.setTranslationX(0.0f);
                    c(emVar);
                    arrayList6.remove(size4);
                    if (arrayList6.isEmpty()) {
                        arrayList2.remove(size3);
                    }
                } else {
                    size4--;
                }
            }
        }
        for (int size5 = arrayList.size() - 1; size5 >= 0; size5--) {
            ArrayList arrayList7 = (ArrayList) arrayList.get(size5);
            if (arrayList7.remove(emVar)) {
                view.setAlpha(1.0f);
                c(emVar);
                if (arrayList7.isEmpty()) {
                    arrayList.remove(size5);
                }
            }
        }
        this.q.remove(emVar);
        this.o.remove(emVar);
        this.r.remove(emVar);
        this.p.remove(emVar);
        i();
    }

    @Override // defpackage.nl
    public final void e() {
        ArrayList arrayList = this.k;
        ArrayList arrayList2 = this.n;
        ArrayList arrayList3 = this.l;
        ArrayList arrayList4 = this.m;
        ArrayList arrayList5 = this.i;
        ArrayList arrayList6 = this.h;
        ArrayList arrayList7 = this.j;
        int size = arrayList7.size();
        while (true) {
            size--;
            if (size < 0) {
                break;
            }
            o9 o9Var = (o9) arrayList7.get(size);
            View view = o9Var.a.a;
            view.setTranslationY(0.0f);
            view.setTranslationX(0.0f);
            c(o9Var.a);
            arrayList7.remove(size);
        }
        for (int size2 = arrayList6.size() - 1; size2 >= 0; size2--) {
            c((em) arrayList6.get(size2));
            arrayList6.remove(size2);
        }
        int size3 = arrayList5.size();
        while (true) {
            size3--;
            if (size3 < 0) {
                break;
            }
            em emVar = (em) arrayList5.get(size3);
            emVar.a.setAlpha(1.0f);
            c(emVar);
            arrayList5.remove(size3);
        }
        for (int size4 = arrayList.size() - 1; size4 >= 0; size4--) {
            n9 n9Var = (n9) arrayList.get(size4);
            em emVar2 = n9Var.a;
            if (emVar2 != null) {
                k(n9Var, emVar2);
            }
            em emVar3 = n9Var.b;
            if (emVar3 != null) {
                k(n9Var, emVar3);
            }
        }
        arrayList.clear();
        if (f()) {
            for (int size5 = arrayList4.size() - 1; size5 >= 0; size5--) {
                ArrayList arrayList8 = (ArrayList) arrayList4.get(size5);
                for (int size6 = arrayList8.size() - 1; size6 >= 0; size6--) {
                    o9 o9Var2 = (o9) arrayList8.get(size6);
                    View view2 = o9Var2.a.a;
                    view2.setTranslationY(0.0f);
                    view2.setTranslationX(0.0f);
                    c(o9Var2.a);
                    arrayList8.remove(size6);
                    if (arrayList8.isEmpty()) {
                        arrayList4.remove(arrayList8);
                    }
                }
            }
            for (int size7 = arrayList3.size() - 1; size7 >= 0; size7--) {
                ArrayList arrayList9 = (ArrayList) arrayList3.get(size7);
                for (int size8 = arrayList9.size() - 1; size8 >= 0; size8--) {
                    em emVar4 = (em) arrayList9.get(size8);
                    emVar4.a.setAlpha(1.0f);
                    c(emVar4);
                    arrayList9.remove(size8);
                    if (arrayList9.isEmpty()) {
                        arrayList3.remove(arrayList9);
                    }
                }
            }
            for (int size9 = arrayList2.size() - 1; size9 >= 0; size9--) {
                ArrayList arrayList10 = (ArrayList) arrayList2.get(size9);
                for (int size10 = arrayList10.size() - 1; size10 >= 0; size10--) {
                    n9 n9Var2 = (n9) arrayList10.get(size10);
                    em emVar5 = n9Var2.a;
                    if (emVar5 != null) {
                        k(n9Var2, emVar5);
                    }
                    em emVar6 = n9Var2.b;
                    if (emVar6 != null) {
                        k(n9Var2, emVar6);
                    }
                    if (arrayList10.isEmpty()) {
                        arrayList2.remove(arrayList10);
                    }
                }
            }
            h(this.q);
            h(this.p);
            h(this.o);
            h(this.r);
            ArrayList arrayList11 = this.b;
            if (arrayList11.size() <= 0) {
                arrayList11.clear();
            } else {
                arrayList11.get(0).getClass();
                z6.a();
            }
        }
    }

    @Override // defpackage.nl
    public final boolean f() {
        return (this.i.isEmpty() && this.k.isEmpty() && this.j.isEmpty() && this.h.isEmpty() && this.p.isEmpty() && this.q.isEmpty() && this.o.isEmpty() && this.r.isEmpty() && this.m.isEmpty() && this.l.isEmpty() && this.n.isEmpty()) ? false : true;
    }

    public final boolean g(em emVar, int i, int i2, int i3, int i4) {
        View view = emVar.a;
        int translationX = i + ((int) view.getTranslationX());
        int translationY = i2 + ((int) emVar.a.getTranslationY());
        l(emVar);
        int i5 = i3 - translationX;
        int i6 = i4 - translationY;
        if (i5 == 0 && i6 == 0) {
            c(emVar);
            return false;
        }
        if (i5 != 0) {
            view.setTranslationX(-i5);
        }
        if (i6 != 0) {
            view.setTranslationY(-i6);
        }
        ArrayList arrayList = this.j;
        o9 o9Var = new o9();
        o9Var.a = emVar;
        o9Var.b = translationX;
        o9Var.c = translationY;
        o9Var.d = i3;
        o9Var.e = i4;
        arrayList.add(o9Var);
        return true;
    }

    public final void i() {
        if (f()) {
            return;
        }
        ArrayList arrayList = this.b;
        if (arrayList.size() <= 0) {
            arrayList.clear();
        } else {
            arrayList.get(0).getClass();
            z6.a();
        }
    }

    public final void j(ArrayList arrayList, em emVar) {
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            n9 n9Var = (n9) arrayList.get(size);
            if (k(n9Var, emVar) && n9Var.a == null && n9Var.b == null) {
                arrayList.remove(n9Var);
            }
        }
    }

    public final boolean k(n9 n9Var, em emVar) {
        if (n9Var.b == emVar) {
            n9Var.b = null;
        } else {
            if (n9Var.a != emVar) {
                return false;
            }
            n9Var.a = null;
        }
        View view = emVar.a;
        View view2 = emVar.a;
        view.setAlpha(1.0f);
        view2.setTranslationX(0.0f);
        view2.setTranslationY(0.0f);
        c(emVar);
        return true;
    }

    public final void l(em emVar) {
        if (s == null) {
            s = new ValueAnimator().getInterpolator();
        }
        emVar.a.animate().setInterpolator(s);
        d(emVar);
    }
}
