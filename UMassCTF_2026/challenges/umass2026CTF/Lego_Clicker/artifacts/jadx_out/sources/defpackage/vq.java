package defpackage;

import java.net.InetAddress;
import java.net.URI;
import java.net.URL;
import java.util.BitSet;
import java.util.Currency;
import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public abstract class vq {
    public static final x5 A;
    public static final rq a = new rq(Class.class, new so(new vj(13), 3), 0);
    public static final rq b = new rq(BitSet.class, new so(new vj(23), 3), 0);
    public static final vj c;
    public static final sq d;
    public static final sq e;
    public static final sq f;
    public static final sq g;
    public static final rq h;
    public static final rq i;
    public static final rq j;
    public static final vj k;
    public static final sq l;
    public static final vj m;
    public static final vj n;
    public static final vj o;
    public static final rq p;
    public static final rq q;
    public static final rq r;
    public static final rq s;
    public static final rq t;
    public static final rq u;
    public static final rq v;
    public static final rq w;
    public static final uj x;
    public static final rq y;
    public static final rq z;

    static {
        vj vjVar = new vj(24);
        c = new vj(25);
        d = new sq(Boolean.TYPE, Boolean.class, vjVar);
        e = new sq(Byte.TYPE, Byte.class, new vj(26));
        f = new sq(Short.TYPE, Short.class, new vj(27));
        g = new sq(Integer.TYPE, Integer.class, new vj(28));
        h = new rq(AtomicInteger.class, new so(new tq(1), 3), 0);
        i = new rq(AtomicBoolean.class, new so(new tq(0), 3), 0);
        j = new rq(AtomicIntegerArray.class, new so(new vj(3), 3), 0);
        k = new vj(4);
        l = new sq(Character.TYPE, Character.class, new vj(7));
        vj vjVar2 = new vj(8);
        m = new vj(9);
        n = new vj(10);
        o = new vj(11);
        int i2 = 0;
        p = new rq(String.class, vjVar2, i2);
        q = new rq(StringBuilder.class, new vj(12), i2);
        r = new rq(StringBuffer.class, new vj(14), i2);
        s = new rq(URL.class, new vj(15), i2);
        t = new rq(URI.class, new vj(16), i2);
        u = new rq(InetAddress.class, new vj(17), 1);
        v = new rq(UUID.class, new vj(18), 0);
        w = new rq(Currency.class, new so(new vj(19), 3), 0);
        x = new uj(new vj(20), 1);
        y = new rq(Locale.class, new vj(21), 0);
        z = new rq(nf.class, new vj(22), 1);
        A = new x5(6);
    }
}
