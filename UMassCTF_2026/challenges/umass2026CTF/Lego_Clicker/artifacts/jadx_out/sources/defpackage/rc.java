package defpackage;

import android.os.Bundle;
import android.util.Log;
import androidx.activity.b;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class rc implements wn {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;

    public /* synthetic */ rc(int i, Object obj) {
        this.a = i;
        this.b = obj;
    }

    @Override // defpackage.wn
    public final Bundle a() {
        ArrayList arrayList;
        e6[] e6VarArr;
        int size;
        int i = this.a;
        Object obj = this.b;
        switch (i) {
            case 0:
                l2 l2Var = (l2) obj;
                l2Var.markFragmentsCreated();
                l2Var.mFragmentLifecycleRegistry.e(dg.ON_STOP);
                return new Bundle();
            case 1:
                return b.b((vc) obj);
            case 2:
                fd fdVar = (fd) obj;
                fdVar.getClass();
                Bundle bundle = new Bundle();
                Iterator it = fdVar.b().iterator();
                while (it.hasNext()) {
                    ((qo) it.next()).getClass();
                }
                Iterator it2 = fdVar.b().iterator();
                if (it2.hasNext()) {
                    ((qo) it2.next()).a();
                    throw null;
                }
                fdVar.e(true);
                fdVar.y = true;
                fdVar.E.getClass();
                kd kdVar = fdVar.c;
                kdVar.getClass();
                HashMap map = (HashMap) kdVar.b;
                ArrayList arrayList2 = new ArrayList(map.size());
                Iterator it3 = map.values().iterator();
                while (it3.hasNext()) {
                    if (it3.next() != null) {
                        z6.a();
                        return null;
                    }
                }
                kd kdVar2 = fdVar.c;
                kdVar2.getClass();
                ArrayList arrayList3 = new ArrayList(((HashMap) kdVar2.c).values());
                if (arrayList3.isEmpty()) {
                    if (!fd.h(2)) {
                        return bundle;
                    }
                    Log.v("FragmentManager", "saveAllState: no fragments!");
                    return bundle;
                }
                kd kdVar3 = fdVar.c;
                synchronized (((ArrayList) kdVar3.a)) {
                    try {
                        if (((ArrayList) kdVar3.a).isEmpty()) {
                            arrayList = null;
                        } else {
                            arrayList = new ArrayList(((ArrayList) kdVar3.a).size());
                            Iterator it4 = ((ArrayList) kdVar3.a).iterator();
                            if (it4.hasNext()) {
                                if (it4.next() == null) {
                                    throw null;
                                }
                                throw new ClassCastException();
                            }
                        }
                    } finally {
                    }
                }
                ArrayList arrayList4 = fdVar.d;
                int i2 = 0;
                if (arrayList4 == null || (size = arrayList4.size()) <= 0) {
                    e6VarArr = null;
                } else {
                    e6VarArr = new e6[size];
                    for (int i3 = 0; i3 < size; i3++) {
                        e6VarArr[i3] = new e6((d6) fdVar.d.get(i3));
                        if (fd.h(2)) {
                            Log.v("FragmentManager", "saveAllState: adding back stack #" + i3 + ": " + fdVar.d.get(i3));
                        }
                    }
                }
                hd hdVar = new hd();
                hdVar.e = null;
                ArrayList arrayList5 = new ArrayList();
                hdVar.f = arrayList5;
                ArrayList arrayList6 = new ArrayList();
                hdVar.g = arrayList6;
                hdVar.a = arrayList2;
                hdVar.b = arrayList;
                hdVar.c = e6VarArr;
                hdVar.d = fdVar.h.get();
                arrayList5.addAll(fdVar.i.keySet());
                arrayList6.addAll(fdVar.i.values());
                hdVar.h = new ArrayList(fdVar.x);
                bundle.putParcelable("state", hdVar);
                for (String str : fdVar.j.keySet()) {
                    bundle.putBundle("result_" + str, (Bundle) fdVar.j.get(str));
                }
                int size2 = arrayList3.size();
                while (i2 < size2) {
                    Object obj2 = arrayList3.get(i2);
                    i2++;
                    jd jdVar = (jd) obj2;
                    Bundle bundle2 = new Bundle();
                    bundle2.putParcelable("state", jdVar);
                    bundle.putBundle("fragment_" + jdVar.b, bundle2);
                }
                return bundle;
            default:
                return qn.a((qn) obj);
        }
    }
}
