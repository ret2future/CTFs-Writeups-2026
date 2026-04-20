package defpackage;

import android.view.View;
import android.view.ViewPropertyAnimator;
import java.util.ArrayList;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class j9 implements Runnable {
    public final /* synthetic */ int a;
    public final /* synthetic */ ArrayList b;
    public final /* synthetic */ p9 c;

    public /* synthetic */ j9(p9 p9Var, ArrayList arrayList, int i) {
        this.a = i;
        this.c = p9Var;
        this.b = arrayList;
    }

    @Override // java.lang.Runnable
    public final void run() {
        char c;
        int i = this.a;
        int i2 = 0;
        ArrayList arrayList = this.b;
        switch (i) {
            case 0:
                int size = arrayList.size();
                while (true) {
                    p9 p9Var = this.c;
                    if (i2 >= size) {
                        arrayList.clear();
                        p9Var.m.remove(arrayList);
                    } else {
                        Object obj = arrayList.get(i2);
                        i2++;
                        o9 o9Var = (o9) obj;
                        em emVar = o9Var.a;
                        int i3 = o9Var.b;
                        int i4 = o9Var.c;
                        int i5 = o9Var.d;
                        int i6 = o9Var.e;
                        p9Var.getClass();
                        View view = emVar.a;
                        int i7 = i5 - i3;
                        int i8 = i6 - i4;
                        if (i7 != 0) {
                            view.animate().translationX(0.0f);
                        }
                        if (i8 != 0) {
                            view.animate().translationY(0.0f);
                        }
                        ViewPropertyAnimator viewPropertyAnimatorAnimate = view.animate();
                        p9Var.p.add(emVar);
                        viewPropertyAnimatorAnimate.setDuration(p9Var.e).setListener(new l9(p9Var, emVar, i7, view, i8, viewPropertyAnimatorAnimate)).start();
                    }
                    break;
                }
                break;
            case 1:
                int size2 = arrayList.size();
                while (true) {
                    p9 p9Var2 = this.c;
                    if (i2 >= size2) {
                        arrayList.clear();
                        p9Var2.n.remove(arrayList);
                        break;
                    } else {
                        Object obj2 = arrayList.get(i2);
                        i2++;
                        n9 n9Var = (n9) obj2;
                        ArrayList arrayList2 = p9Var2.r;
                        long j = p9Var2.f;
                        em emVar2 = n9Var.a;
                        View view2 = emVar2 == null ? null : emVar2.a;
                        em emVar3 = n9Var.b;
                        View view3 = emVar3 != null ? emVar3.a : null;
                        if (view2 != null) {
                            ViewPropertyAnimator duration = view2.animate().setDuration(j);
                            arrayList2.add(n9Var.a);
                            duration.translationX(n9Var.e - n9Var.c);
                            duration.translationY(n9Var.f - n9Var.d);
                            duration.alpha(0.0f).setListener(new m9(p9Var2, n9Var, duration, view2, 0)).start();
                        }
                        if (view3 != null) {
                            ViewPropertyAnimator viewPropertyAnimatorAnimate2 = view3.animate();
                            arrayList2.add(n9Var.b);
                            c = 0;
                            viewPropertyAnimatorAnimate2.translationX(0.0f).translationY(0.0f).setDuration(j).alpha(1.0f).setListener(new m9(p9Var2, n9Var, viewPropertyAnimatorAnimate2, view3, 1)).start();
                        } else {
                            c = 0;
                        }
                    }
                }
                break;
            default:
                int size3 = arrayList.size();
                while (true) {
                    p9 p9Var3 = this.c;
                    if (i2 >= size3) {
                        arrayList.clear();
                        p9Var3.l.remove(arrayList);
                    } else {
                        Object obj3 = arrayList.get(i2);
                        i2++;
                        em emVar4 = (em) obj3;
                        p9Var3.getClass();
                        View view4 = emVar4.a;
                        ViewPropertyAnimator viewPropertyAnimatorAnimate3 = view4.animate();
                        p9Var3.o.add(emVar4);
                        viewPropertyAnimatorAnimate3.alpha(1.0f).setDuration(p9Var3.c).setListener(new k9(p9Var3, emVar4, view4, viewPropertyAnimatorAnimate3)).start();
                    }
                    break;
                }
                break;
        }
    }
}
