package androidx.lifecycle;

import android.os.Looper;
import defpackage.bg;
import defpackage.dg;
import defpackage.eg;
import defpackage.fg;
import defpackage.ig;
import defpackage.jg;
import defpackage.kg;
import defpackage.kn;
import defpackage.lg;
import defpackage.mg;
import defpackage.mn;
import defpackage.o5;
import defpackage.oe;
import defpackage.q9;
import defpackage.ub;
import defpackage.z6;
import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.WeakHashMap;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class a extends fg {
    public final boolean a;
    public ub b;
    public eg c;
    public final WeakReference d;
    public int e;
    public boolean f;
    public boolean g;
    public final ArrayList h;

    public a(kg kgVar) {
        new AtomicReference();
        this.a = true;
        this.b = new ub();
        this.c = eg.b;
        this.h = new ArrayList();
        this.d = new WeakReference(kgVar);
    }

    @Override // defpackage.fg
    public final void a(jg jgVar) {
        ig reflectiveGenericLifecycleObserver;
        Object obj;
        kg kgVar;
        d("addObserver");
        eg egVar = this.c;
        eg egVar2 = eg.a;
        if (egVar != egVar2) {
            egVar2 = eg.b;
        }
        lg lgVar = new lg();
        HashMap map = mg.a;
        boolean z = jgVar instanceof ig;
        boolean z2 = jgVar instanceof q9;
        if (z && z2) {
            reflectiveGenericLifecycleObserver = new DefaultLifecycleObserverAdapter((q9) jgVar, (ig) jgVar);
        } else if (z2) {
            reflectiveGenericLifecycleObserver = new DefaultLifecycleObserverAdapter((q9) jgVar, null);
        } else if (z) {
            reflectiveGenericLifecycleObserver = (ig) jgVar;
        } else {
            Class<?> cls = jgVar.getClass();
            if (mg.c(cls) == 2) {
                Object obj2 = mg.b.get(cls);
                obj2.getClass();
                List list = (List) obj2;
                if (list.size() == 1) {
                    mg.a((Constructor) list.get(0), jgVar);
                    throw null;
                }
                int size = list.size();
                oe[] oeVarArr = new oe[size];
                if (size > 0) {
                    mg.a((Constructor) list.get(0), jgVar);
                    throw null;
                }
                reflectiveGenericLifecycleObserver = new CompositeGeneratedAdaptersObserver(oeVarArr);
            } else {
                reflectiveGenericLifecycleObserver = new ReflectiveGenericLifecycleObserver(jgVar);
            }
        }
        lgVar.b = reflectiveGenericLifecycleObserver;
        lgVar.a = egVar2;
        ub ubVar = this.b;
        kn knVarA = ubVar.a(jgVar);
        if (knVarA != null) {
            obj = knVarA.b;
        } else {
            HashMap map2 = ubVar.e;
            kn knVar = new kn(jgVar, lgVar);
            ubVar.d++;
            kn knVar2 = ubVar.b;
            if (knVar2 == null) {
                ubVar.a = knVar;
                ubVar.b = knVar;
            } else {
                knVar2.c = knVar;
                knVar.d = knVar2;
                ubVar.b = knVar;
            }
            map2.put(jgVar, knVar);
            obj = null;
        }
        if (((lg) obj) == null && (kgVar = (kg) this.d.get()) != null) {
            boolean z3 = this.e != 0 || this.f;
            eg egVarC = c(jgVar);
            this.e++;
            while (lgVar.a.compareTo(egVarC) < 0 && this.b.e.containsKey(jgVar)) {
                eg egVar3 = lgVar.a;
                ArrayList arrayList = this.h;
                arrayList.add(egVar3);
                bg bgVar = dg.Companion;
                eg egVar4 = lgVar.a;
                bgVar.getClass();
                egVar4.getClass();
                int iOrdinal = egVar4.ordinal();
                dg dgVar = iOrdinal != 1 ? iOrdinal != 2 ? iOrdinal != 3 ? null : dg.ON_RESUME : dg.ON_START : dg.ON_CREATE;
                if (dgVar == null) {
                    z6.r(lgVar.a, "no event up from ");
                    return;
                } else {
                    lgVar.a(kgVar, dgVar);
                    arrayList.remove(arrayList.size() - 1);
                    egVarC = c(jgVar);
                }
            }
            if (!z3) {
                g();
            }
            this.e--;
        }
    }

    @Override // defpackage.fg
    public final void b(jg jgVar) {
        jgVar.getClass();
        d("removeObserver");
        ub ubVar = this.b;
        WeakHashMap weakHashMap = ubVar.c;
        kn knVarA = ubVar.a(jgVar);
        if (knVarA != null) {
            ubVar.d--;
            if (!weakHashMap.isEmpty()) {
                Iterator it = weakHashMap.keySet().iterator();
                while (it.hasNext()) {
                    ((mn) it.next()).a(knVarA);
                }
            }
            kn knVar = knVarA.d;
            kn knVar2 = knVarA.c;
            if (knVar != null) {
                knVar.c = knVar2;
            } else {
                ubVar.a = knVar2;
            }
            kn knVar3 = knVarA.c;
            if (knVar3 != null) {
                knVar3.d = knVar;
            } else {
                ubVar.b = knVar;
            }
            knVarA.c = null;
            knVarA.d = null;
        }
        ubVar.e.remove(jgVar);
    }

    public final eg c(jg jgVar) {
        lg lgVar;
        HashMap map = this.b.e;
        kn knVar = map.containsKey(jgVar) ? ((kn) map.get(jgVar)).d : null;
        eg egVar = (knVar == null || (lgVar = (lg) knVar.b) == null) ? null : lgVar.a;
        ArrayList arrayList = this.h;
        eg egVar2 = arrayList.isEmpty() ? null : (eg) arrayList.get(arrayList.size() - 1);
        eg egVar3 = this.c;
        egVar3.getClass();
        if (egVar == null || egVar.compareTo(egVar3) >= 0) {
            egVar = egVar3;
        }
        return (egVar2 == null || egVar2.compareTo(egVar) >= 0) ? egVar : egVar2;
    }

    public final void d(String str) {
        o5 o5Var;
        if (this.a) {
            if (o5.p != null) {
                o5Var = o5.p;
            } else {
                synchronized (o5.class) {
                    try {
                        if (o5.p == null) {
                            o5.p = new o5(0);
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                }
                o5Var = o5.p;
            }
            ((o5) o5Var.o).getClass();
            if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
                return;
            }
            throw new IllegalStateException(("Method " + str + " must be called on the main thread").toString());
        }
    }

    public final void e(dg dgVar) {
        dgVar.getClass();
        d("handleLifecycleEvent");
        f(dgVar.a());
    }

    public final void f(eg egVar) {
        eg egVar2 = this.c;
        if (egVar2 == egVar) {
            return;
        }
        eg egVar3 = eg.b;
        eg egVar4 = eg.a;
        if (egVar2 == egVar3 && egVar == egVar4) {
            StringBuilder sb = new StringBuilder("no event down from ");
            sb.append(this.c);
            Object obj = this.d.get();
            sb.append(" in component ");
            sb.append(obj);
            throw new IllegalStateException(sb.toString().toString());
        }
        this.c = egVar;
        if (this.f || this.e != 0) {
            this.g = true;
            return;
        }
        this.f = true;
        g();
        this.f = false;
        if (this.c == egVar4) {
            this.b = new ub();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0030, code lost:
    
        r11.g = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0032, code lost:
    
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void g() {
        /*
            Method dump skipped, instruction units count: 369
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.lifecycle.a.g():void");
    }
}
