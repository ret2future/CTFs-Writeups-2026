package defpackage;

import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class y5 extends pq {
    public static final x5 d = new x5(0);
    public final /* synthetic */ int a = 1;
    public final Object b;
    public final Object c;

    public y5(re reVar, pq pqVar, Class cls) {
        this.b = new bi(reVar, pqVar, cls);
        this.c = cls;
    }

    @Override // defpackage.pq
    public final Object a(sf sfVar) {
        int i = this.a;
        Collection collection = null;
        Object obj = this.b;
        Object obj2 = this.c;
        switch (i) {
            case 0:
                Class cls = (Class) obj2;
                if (sfVar.v() == 9) {
                    sfVar.r();
                    return null;
                }
                ArrayList arrayList = new ArrayList();
                sfVar.a();
                while (sfVar.i()) {
                    arrayList.add(((pq) ((bi) obj).c).a(sfVar));
                }
                sfVar.e();
                int size = arrayList.size();
                if (!cls.isPrimitive()) {
                    return arrayList.toArray((Object[]) Array.newInstance((Class<?>) cls, size));
                }
                Object objNewInstance = Array.newInstance((Class<?>) cls, size);
                for (int i2 = 0; i2 < size; i2++) {
                    Array.set(objNewInstance, i2, arrayList.get(i2));
                }
                return objNewInstance;
            case 1:
                if (sfVar.v() == 9) {
                    sfVar.r();
                } else {
                    collection = (Collection) ((wj) obj2).q();
                    sfVar.a();
                    while (sfVar.i()) {
                        collection.add(((pq) ((bi) obj).c).a(sfVar));
                    }
                    sfVar.e();
                }
                return collection;
            default:
                Class cls2 = (Class) obj2;
                Object objA = ((pq) ((rq) obj).c).a(sfVar);
                if (objA == null || cls2.isInstance(objA)) {
                    return objA;
                }
                throw new of("Expected a " + cls2.getName() + " but was " + objA.getClass().getName() + "; at path " + sfVar.h(true));
        }
    }

    @Override // defpackage.pq
    public final void b(tf tfVar, Object obj) throws IOException {
        int i = this.a;
        Object obj2 = this.b;
        switch (i) {
            case 0:
                if (obj == null) {
                    tfVar.i();
                } else {
                    tfVar.b();
                    int length = Array.getLength(obj);
                    for (int i2 = 0; i2 < length; i2++) {
                        ((bi) obj2).b(tfVar, Array.get(obj, i2));
                    }
                    tfVar.e();
                }
                break;
            case 1:
                Collection collection = (Collection) obj;
                if (collection == null) {
                    tfVar.i();
                } else {
                    tfVar.b();
                    Iterator it = collection.iterator();
                    while (it.hasNext()) {
                        ((bi) obj2).b(tfVar, it.next());
                    }
                    tfVar.e();
                }
                break;
            default:
                ((pq) ((rq) obj2).c).b(tfVar, obj);
                break;
        }
    }

    public y5(re reVar, Type type, pq pqVar, wj wjVar) {
        this.b = new bi(reVar, pqVar, type);
        this.c = wjVar;
    }

    public y5(rq rqVar, Class cls) {
        this.b = rqVar;
        this.c = cls;
    }
}
