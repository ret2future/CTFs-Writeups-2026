package androidx.activity.result;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import androidx.activity.b;
import defpackage.dg;
import defpackage.eg;
import defpackage.fg;
import defpackage.ig;
import defpackage.kg;
import defpackage.q1;
import defpackage.r1;
import defpackage.s1;
import defpackage.v1;
import defpackage.w1;
import defpackage.x1;
import defpackage.y1;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public abstract class a {
    public Random a = new Random();
    public final HashMap b = new HashMap();
    public final HashMap c = new HashMap();
    public final HashMap d = new HashMap();
    public ArrayList e = new ArrayList();
    public final transient HashMap f = new HashMap();
    public final HashMap g = new HashMap();
    public final Bundle h = new Bundle();

    public final boolean a(int i, int i2, Intent intent) {
        r1 r1Var;
        String str = (String) this.b.get(Integer.valueOf(i));
        if (str == null) {
            return false;
        }
        x1 x1Var = (x1) this.f.get(str);
        if (x1Var == null || (r1Var = x1Var.a) == null || !this.e.contains(str)) {
            this.g.remove(str);
            this.h.putParcelable(str, new q1(intent, i2));
            return true;
        }
        r1Var.a(x1Var.b.a(intent, i2));
        this.e.remove(str);
        return true;
    }

    public final v1 b(final String str, b bVar, final s1 s1Var, final r1 r1Var) {
        fg lifecycle = bVar.getLifecycle();
        if (((androidx.lifecycle.a) lifecycle).c.compareTo(eg.d) >= 0) {
            StringBuilder sb = new StringBuilder("LifecycleOwner ");
            sb.append(bVar);
            eg egVar = ((androidx.lifecycle.a) lifecycle).c;
            sb.append(" is attempting to register while current state is ");
            sb.append(egVar);
            sb.append(". LifecycleOwners must call register before they are STARTED.");
            throw new IllegalStateException(sb.toString());
        }
        d(str);
        HashMap map = this.d;
        y1 y1Var = (y1) map.get(str);
        if (y1Var == null) {
            y1Var = new y1(lifecycle);
        }
        ig igVar = new ig() { // from class: androidx.activity.result.ActivityResultRegistry$1
            @Override // defpackage.ig
            public final void d(kg kgVar, dg dgVar) {
                boolean zEquals = dg.ON_START.equals(dgVar);
                String str2 = str;
                a aVar = this.d;
                if (!zEquals) {
                    if (dg.ON_STOP.equals(dgVar)) {
                        aVar.f.remove(str2);
                        return;
                    } else {
                        if (dg.ON_DESTROY.equals(dgVar)) {
                            aVar.e(str2);
                            return;
                        }
                        return;
                    }
                }
                HashMap map2 = aVar.f;
                Bundle bundle = aVar.h;
                HashMap map3 = aVar.g;
                s1 s1Var2 = s1Var;
                r1 r1Var2 = r1Var;
                map2.put(str2, new x1(s1Var2, r1Var2));
                if (map3.containsKey(str2)) {
                    Object obj = map3.get(str2);
                    map3.remove(str2);
                    r1Var2.a(obj);
                }
                q1 q1Var = (q1) bundle.getParcelable(str2);
                if (q1Var != null) {
                    bundle.remove(str2);
                    r1Var2.a(s1Var2.a(q1Var.b, q1Var.a));
                }
            }
        };
        y1Var.a.a(igVar);
        y1Var.b.add(igVar);
        map.put(str, y1Var);
        return new v1();
    }

    public final w1 c(String str, s1 s1Var, r1 r1Var) {
        d(str);
        this.f.put(str, new x1(s1Var, r1Var));
        HashMap map = this.g;
        if (map.containsKey(str)) {
            Object obj = map.get(str);
            map.remove(str);
            r1Var.a(obj);
        }
        Bundle bundle = this.h;
        q1 q1Var = (q1) bundle.getParcelable(str);
        if (q1Var != null) {
            bundle.remove(str);
            r1Var.a(s1Var.a(q1Var.b, q1Var.a));
        }
        return new w1(this, str);
    }

    public final void d(String str) {
        HashMap map = this.c;
        if (((Integer) map.get(str)) != null) {
            return;
        }
        int iNextInt = this.a.nextInt(2147418112);
        while (true) {
            int i = iNextInt + 65536;
            Integer numValueOf = Integer.valueOf(i);
            HashMap map2 = this.b;
            if (!map2.containsKey(numValueOf)) {
                map2.put(Integer.valueOf(i), str);
                map.put(str, Integer.valueOf(i));
                return;
            }
            iNextInt = this.a.nextInt(2147418112);
        }
    }

    public final void e(String str) {
        Integer num;
        if (!this.e.contains(str) && (num = (Integer) this.c.remove(str)) != null) {
            this.b.remove(num);
        }
        this.f.remove(str);
        HashMap map = this.g;
        if (map.containsKey(str)) {
            Log.w("ActivityResultRegistry", "Dropping pending result for request " + str + ": " + map.get(str));
            map.remove(str);
        }
        Bundle bundle = this.h;
        if (bundle.containsKey(str)) {
            Log.w("ActivityResultRegistry", "Dropping pending result for request " + str + ": " + bundle.getParcelable(str));
            bundle.remove(str);
        }
        HashMap map2 = this.d;
        y1 y1Var = (y1) map2.get(str);
        if (y1Var != null) {
            ArrayList arrayList = y1Var.b;
            int size = arrayList.size();
            int i = 0;
            while (i < size) {
                Object obj = arrayList.get(i);
                i++;
                y1Var.a.b((ig) obj);
            }
            arrayList.clear();
            map2.remove(str);
        }
    }
}
