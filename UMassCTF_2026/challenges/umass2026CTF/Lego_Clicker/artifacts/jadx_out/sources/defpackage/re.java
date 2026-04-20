package defpackage;

import java.io.EOFException;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicLongArray;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class re {
    public final ThreadLocal a;
    public final ConcurrentHashMap b;
    public final fr c;
    public final h7 d;
    public final List e;
    public final boolean f;

    public re() {
        sb sbVar = sb.c;
        Map map = Collections.EMPTY_MAP;
        List list = Collections.EMPTY_LIST;
        this.a = new ThreadLocal();
        this.b = new ConcurrentHashMap();
        fr frVar = new fr(15);
        this.c = frVar;
        int i = 1;
        this.f = true;
        ArrayList arrayList = new ArrayList();
        arrayList.add(vq.z);
        arrayList.add(xj.b);
        arrayList.add(sbVar);
        arrayList.addAll(list);
        arrayList.add(vq.p);
        arrayList.add(vq.g);
        arrayList.add(vq.d);
        arrayList.add(vq.e);
        arrayList.add(vq.f);
        vj vjVar = vq.k;
        arrayList.add(new sq(Long.TYPE, Long.class, vjVar));
        arrayList.add(new sq(Double.TYPE, Double.class, new vj(i)));
        int i2 = 2;
        arrayList.add(new sq(Float.TYPE, Float.class, new vj(i2)));
        arrayList.add(vj.b);
        arrayList.add(vq.h);
        arrayList.add(vq.i);
        int i3 = 0;
        arrayList.add(new rq(AtomicLong.class, new so(new so(vjVar, 1), 3), i3));
        arrayList.add(new rq(AtomicLongArray.class, new so(new so(vjVar, 2), 3), i3));
        arrayList.add(vq.j);
        arrayList.add(vq.l);
        arrayList.add(vq.q);
        arrayList.add(vq.r);
        arrayList.add(new rq(BigDecimal.class, vq.m, i3));
        arrayList.add(new rq(BigInteger.class, vq.n, i3));
        arrayList.add(new rq(xf.class, vq.o, i3));
        arrayList.add(vq.s);
        arrayList.add(vq.t);
        arrayList.add(vq.v);
        arrayList.add(vq.w);
        arrayList.add(vq.y);
        arrayList.add(vq.u);
        arrayList.add(vq.b);
        arrayList.add(ro.e);
        arrayList.add(vq.x);
        if (to.a) {
            arrayList.add(to.c);
            arrayList.add(to.b);
            arrayList.add(to.d);
        }
        arrayList.add(y5.d);
        arrayList.add(vq.a);
        arrayList.add(new h7(frVar, i3));
        arrayList.add(new h7(frVar, i2));
        h7 h7Var = new h7(frVar, i);
        this.d = h7Var;
        arrayList.add(h7Var);
        arrayList.add(vq.A);
        arrayList.add(new rq(frVar, sbVar, h7Var));
        this.e = Collections.unmodifiableList(arrayList);
    }

    public static void a(double d) {
        if (Double.isNaN(d) || Double.isInfinite(d)) {
            throw new IllegalArgumentException(d + " is not a valid double value as per JSON specification. To override this behavior, use GsonBuilder.serializeSpecialFloatingPointValues() method.");
        }
    }

    public final Object b(String str, Class cls) {
        Object objA;
        wq wqVar = new wq(cls);
        sf sfVar = new sf(new StringReader(str));
        boolean z = true;
        sfVar.b = true;
        try {
            try {
                try {
                    try {
                        sfVar.v();
                    } catch (EOFException e) {
                        e = e;
                    }
                    try {
                        objA = c(wqVar).a(sfVar);
                    } catch (EOFException e2) {
                        e = e2;
                        z = false;
                        if (!z) {
                            throw new of(e);
                        }
                        sfVar.b = false;
                        objA = null;
                    }
                    if (objA != null) {
                        try {
                            if (sfVar.v() != 10) {
                                throw new of("JSON document was not fully consumed.");
                            }
                        } catch (xh e3) {
                            throw new of(e3);
                        } catch (IOException e4) {
                            throw new of(e4);
                        }
                    }
                    if (cls == Integer.TYPE) {
                        cls = Integer.class;
                    } else if (cls == Float.TYPE) {
                        cls = Float.class;
                    } else if (cls == Byte.TYPE) {
                        cls = Byte.class;
                    } else if (cls == Double.TYPE) {
                        cls = Double.class;
                    } else if (cls == Long.TYPE) {
                        cls = Long.class;
                    } else if (cls == Character.TYPE) {
                        cls = Character.class;
                    } else if (cls == Boolean.TYPE) {
                        cls = Boolean.class;
                    } else if (cls == Short.TYPE) {
                        cls = Short.class;
                    } else if (cls == Void.TYPE) {
                        cls = Void.class;
                    }
                    return cls.cast(objA);
                } catch (AssertionError e5) {
                    throw new AssertionError("AssertionError (GSON 2.10.1): " + e5.getMessage(), e5);
                }
            } catch (IOException e6) {
                throw new of(e6);
            } catch (IllegalStateException e7) {
                throw new of(e7);
            }
        } finally {
            sfVar.b = false;
        }
    }

    public final pq c(wq wqVar) {
        boolean z;
        ConcurrentHashMap concurrentHashMap = this.b;
        pq pqVar = (pq) concurrentHashMap.get(wqVar);
        if (pqVar != null) {
            return pqVar;
        }
        ThreadLocal threadLocal = this.a;
        Map map = (Map) threadLocal.get();
        if (map == null) {
            map = new HashMap();
            threadLocal.set(map);
            z = true;
        } else {
            pq pqVar2 = (pq) map.get(wqVar);
            if (pqVar2 != null) {
                return pqVar2;
            }
            z = false;
        }
        try {
            qe qeVar = new qe();
            qeVar.a = null;
            map.put(wqVar, qeVar);
            Iterator it = this.e.iterator();
            pq pqVarA = null;
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                pqVarA = ((qq) it.next()).a(this, wqVar);
                if (pqVarA != null) {
                    if (qeVar.a != null) {
                        throw new AssertionError("Delegate is already set");
                    }
                    qeVar.a = pqVarA;
                    map.put(wqVar, pqVarA);
                }
            }
            if (z) {
                threadLocal.remove();
            }
            if (pqVarA == null) {
                z6.n(wqVar, "GSON (2.10.1) cannot handle ");
                return null;
            }
            if (z) {
                concurrentHashMap.putAll(map);
            }
            return pqVarA;
        } catch (Throwable th) {
            if (z) {
                threadLocal.remove();
            }
            throw th;
        }
    }

    public final tf d(Writer writer) {
        tf tfVar = new tf(writer);
        tfVar.f = this.f;
        tfVar.e = false;
        tfVar.h = false;
        return tfVar;
    }

    public final String e(Object obj) {
        if (obj == null) {
            StringWriter stringWriter = new StringWriter();
            try {
                f(d(stringWriter));
                return stringWriter.toString();
            } catch (IOException e) {
                throw new of(e);
            }
        }
        Class cls = obj.getClass();
        StringWriter stringWriter2 = new StringWriter();
        try {
            g(obj, cls, d(stringWriter2));
            return stringWriter2.toString();
        } catch (IOException e2) {
            throw new of(e2);
        }
    }

    public final void f(tf tfVar) {
        pf pfVar = pf.a;
        boolean z = tfVar.e;
        tfVar.e = true;
        boolean z2 = tfVar.f;
        tfVar.f = this.f;
        boolean z3 = tfVar.h;
        tfVar.h = false;
        try {
            try {
                rq rqVar = vq.a;
                vj.d(tfVar, pfVar);
                tfVar.e = z;
                tfVar.f = z2;
                tfVar.h = z3;
            } catch (IOException e) {
                throw new of(e);
            } catch (AssertionError e2) {
                throw new AssertionError("AssertionError (GSON 2.10.1): " + e2.getMessage(), e2);
            }
        } catch (Throwable th) {
            tfVar.e = z;
            tfVar.f = z2;
            tfVar.h = z3;
            throw th;
        }
    }

    public final void g(Object obj, Class cls, tf tfVar) {
        pq pqVarC = c(new wq(cls));
        boolean z = tfVar.e;
        tfVar.e = true;
        boolean z2 = tfVar.f;
        tfVar.f = this.f;
        boolean z3 = tfVar.h;
        tfVar.h = false;
        try {
            try {
                try {
                    pqVarC.b(tfVar, obj);
                } catch (IOException e) {
                    throw new of(e);
                }
            } catch (AssertionError e2) {
                throw new AssertionError("AssertionError (GSON 2.10.1): " + e2.getMessage(), e2);
            }
        } finally {
            tfVar.e = z;
            tfVar.f = z2;
            tfVar.h = z3;
        }
    }

    public final String toString() {
        return "{serializeNulls:false,factories:" + this.e + ",instanceCreators:" + this.c + "}";
    }
}
