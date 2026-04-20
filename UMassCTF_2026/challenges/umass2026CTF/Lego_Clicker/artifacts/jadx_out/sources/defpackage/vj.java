package defpackage;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Calendar;
import java.util.Currency;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicIntegerArray;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class vj extends pq {
    public static final uj b = new uj(new vj(0), 0);
    public final /* synthetic */ int a;

    public /* synthetic */ vj(int i) {
        this.a = i;
    }

    public static nf c(sf sfVar, int i) {
        int iC = lo.c(i);
        if (iC == 5) {
            return new rf(sfVar.t());
        }
        if (iC == 6) {
            return new rf(new xf(sfVar.t()));
        }
        if (iC == 7) {
            return new rf(Boolean.valueOf(sfVar.l()));
        }
        if (iC == 8) {
            sfVar.r();
            return pf.a;
        }
        z6.o("Unexpected token: ".concat(lo.d(i)));
        return null;
    }

    public static void d(tf tfVar, nf nfVar) {
        if (nfVar == null || (nfVar instanceof pf)) {
            tfVar.i();
            return;
        }
        if (nfVar instanceof rf) {
            rf rfVar = (rf) nfVar;
            Serializable serializable = rfVar.a;
            if (serializable instanceof Number) {
                tfVar.n(rfVar.a());
                return;
            } else if (serializable instanceof Boolean) {
                tfVar.p(serializable instanceof Boolean ? ((Boolean) serializable).booleanValue() : Boolean.parseBoolean(rfVar.b()));
                return;
            } else {
                tfVar.o(rfVar.b());
                return;
            }
        }
        boolean z = nfVar instanceof mf;
        if (z) {
            tfVar.b();
            if (!z) {
                z6.q(nfVar, "Not a JSON Array: ");
                return;
            }
            ArrayList arrayList = ((mf) nfVar).a;
            int size = arrayList.size();
            int i = 0;
            while (i < size) {
                Object obj = arrayList.get(i);
                i++;
                d(tfVar, (nf) obj);
            }
            tfVar.e();
            return;
        }
        boolean z2 = nfVar instanceof qf;
        if (!z2) {
            throw new IllegalArgumentException("Couldn't write " + nfVar.getClass());
        }
        tfVar.c();
        if (!z2) {
            z6.q(nfVar, "Not a JSON Object: ");
            return;
        }
        Iterator it = ((vg) ((qf) nfVar).a.entrySet()).iterator();
        while (((ug) it).hasNext()) {
            wg wgVarB = ((ug) it).b();
            tfVar.g((String) wgVarB.getKey());
            d(tfVar, (nf) wgVarB.getValue());
        }
        tfVar.f();
    }

    @Override // defpackage.pq
    public final Object a(sf sfVar) {
        nf mfVar;
        nf mfVar2;
        boolean zL;
        switch (this.a) {
            case 0:
                int iV = sfVar.v();
                int iC = lo.c(iV);
                if (iC == 5 || iC == 6) {
                    return new xf(sfVar.t());
                }
                if (iC == 8) {
                    sfVar.r();
                    return null;
                }
                z6.p("Expecting number, got: ", lo.d(iV), "; at path ", sfVar.h(false));
                return null;
            case 1:
                if (sfVar.v() != 9) {
                    return Double.valueOf(sfVar.m());
                }
                sfVar.r();
                return null;
            case 2:
                if (sfVar.v() != 9) {
                    return Float.valueOf((float) sfVar.m());
                }
                sfVar.r();
                return null;
            case 3:
                ArrayList arrayList = new ArrayList();
                sfVar.a();
                while (sfVar.i()) {
                    try {
                        arrayList.add(Integer.valueOf(sfVar.n()));
                    } catch (NumberFormatException e) {
                        throw new of(e);
                    }
                }
                sfVar.e();
                int size = arrayList.size();
                AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(size);
                for (int i = 0; i < size; i++) {
                    atomicIntegerArray.set(i, ((Integer) arrayList.get(i)).intValue());
                }
                return atomicIntegerArray;
            case 4:
                if (sfVar.v() == 9) {
                    sfVar.r();
                    return null;
                }
                try {
                    return Long.valueOf(sfVar.o());
                } catch (NumberFormatException e2) {
                    throw new of(e2);
                }
            case 5:
                if (sfVar.v() != 9) {
                    return Float.valueOf((float) sfVar.m());
                }
                sfVar.r();
                return null;
            case 6:
                if (sfVar.v() != 9) {
                    return Double.valueOf(sfVar.m());
                }
                sfVar.r();
                return null;
            case 7:
                if (sfVar.v() == 9) {
                    sfVar.r();
                    return null;
                }
                String strT = sfVar.t();
                if (strT.length() == 1) {
                    return Character.valueOf(strT.charAt(0));
                }
                z6.p("Expecting character, got: ", strT, "; at ", sfVar.h(true));
                return null;
            case 8:
                int iV2 = sfVar.v();
                if (iV2 != 9) {
                    return iV2 == 8 ? Boolean.toString(sfVar.l()) : sfVar.t();
                }
                sfVar.r();
                return null;
            case 9:
                if (sfVar.v() == 9) {
                    sfVar.r();
                    return null;
                }
                String strT2 = sfVar.t();
                try {
                    return new BigDecimal(strT2);
                } catch (NumberFormatException e3) {
                    z6.b(strT2, "' as BigDecimal; at path ", sfVar.h(true), e3);
                    return null;
                }
            case 10:
                if (sfVar.v() == 9) {
                    sfVar.r();
                    return null;
                }
                String strT3 = sfVar.t();
                try {
                    return new BigInteger(strT3);
                } catch (NumberFormatException e4) {
                    z6.b(strT3, "' as BigInteger; at path ", sfVar.h(true), e4);
                    return null;
                }
            case 11:
                if (sfVar.v() != 9) {
                    return new xf(sfVar.t());
                }
                sfVar.r();
                return null;
            case 12:
                if (sfVar.v() != 9) {
                    return new StringBuilder(sfVar.t());
                }
                sfVar.r();
                return null;
            case 13:
                throw new UnsupportedOperationException("Attempted to deserialize a java.lang.Class. Forgot to register a type adapter?");
            case 14:
                if (sfVar.v() != 9) {
                    return new StringBuffer(sfVar.t());
                }
                sfVar.r();
                return null;
            case 15:
                if (sfVar.v() == 9) {
                    sfVar.r();
                    return null;
                }
                String strT4 = sfVar.t();
                if ("null".equals(strT4)) {
                    return null;
                }
                return new URL(strT4);
            case 16:
                if (sfVar.v() == 9) {
                    sfVar.r();
                    return null;
                }
                try {
                    String strT5 = sfVar.t();
                    if ("null".equals(strT5)) {
                        return null;
                    }
                    return new URI(strT5);
                } catch (URISyntaxException e5) {
                    throw new of(e5);
                }
            case 17:
                if (sfVar.v() != 9) {
                    return InetAddress.getByName(sfVar.t());
                }
                sfVar.r();
                return null;
            case 18:
                if (sfVar.v() == 9) {
                    sfVar.r();
                    return null;
                }
                String strT6 = sfVar.t();
                try {
                    return UUID.fromString(strT6);
                } catch (IllegalArgumentException e6) {
                    z6.b(strT6, "' as UUID; at path ", sfVar.h(true), e6);
                    return null;
                }
            case 19:
                String strT7 = sfVar.t();
                try {
                    return Currency.getInstance(strT7);
                } catch (IllegalArgumentException e7) {
                    z6.b(strT7, "' as Currency; at path ", sfVar.h(true), e7);
                    return null;
                }
            case 20:
                if (sfVar.v() == 9) {
                    sfVar.r();
                    return null;
                }
                sfVar.b();
                int i2 = 0;
                int i3 = 0;
                int i4 = 0;
                int i5 = 0;
                int i6 = 0;
                int i7 = 0;
                while (sfVar.v() != 4) {
                    String strP = sfVar.p();
                    int iN = sfVar.n();
                    if ("year".equals(strP)) {
                        i2 = iN;
                    } else if ("month".equals(strP)) {
                        i3 = iN;
                    } else if ("dayOfMonth".equals(strP)) {
                        i4 = iN;
                    } else if ("hourOfDay".equals(strP)) {
                        i5 = iN;
                    } else if ("minute".equals(strP)) {
                        i6 = iN;
                    } else if ("second".equals(strP)) {
                        i7 = iN;
                    }
                }
                sfVar.f();
                return new GregorianCalendar(i2, i3, i4, i5, i6, i7);
            case 21:
                if (sfVar.v() == 9) {
                    sfVar.r();
                    return null;
                }
                StringTokenizer stringTokenizer = new StringTokenizer(sfVar.t(), "_");
                String strNextToken = stringTokenizer.hasMoreElements() ? stringTokenizer.nextToken() : null;
                String strNextToken2 = stringTokenizer.hasMoreElements() ? stringTokenizer.nextToken() : null;
                String strNextToken3 = stringTokenizer.hasMoreElements() ? stringTokenizer.nextToken() : null;
                return (strNextToken2 == null && strNextToken3 == null) ? new Locale(strNextToken) : strNextToken3 == null ? new Locale(strNextToken, strNextToken2) : new Locale(strNextToken, strNextToken2, strNextToken3);
            case 22:
                int iV3 = sfVar.v();
                int iC2 = lo.c(iV3);
                if (iC2 == 0) {
                    sfVar.a();
                    mfVar = new mf();
                } else if (iC2 != 2) {
                    mfVar = null;
                } else {
                    sfVar.b();
                    mfVar = new qf();
                }
                if (mfVar == null) {
                    return c(sfVar, iV3);
                }
                ArrayDeque arrayDeque = new ArrayDeque();
                while (true) {
                    if (sfVar.i()) {
                        String strP2 = mfVar instanceof qf ? sfVar.p() : null;
                        int iV4 = sfVar.v();
                        int iC3 = lo.c(iV4);
                        if (iC3 == 0) {
                            sfVar.a();
                            mfVar2 = new mf();
                        } else if (iC3 != 2) {
                            mfVar2 = null;
                        } else {
                            sfVar.b();
                            mfVar2 = new qf();
                        }
                        boolean z = mfVar2 != null;
                        if (mfVar2 == null) {
                            mfVar2 = c(sfVar, iV4);
                        }
                        if (mfVar instanceof mf) {
                            ((mf) mfVar).a.add(mfVar2);
                        } else {
                            ((qf) mfVar).a.put(strP2, mfVar2);
                        }
                        if (z) {
                            arrayDeque.addLast(mfVar);
                            mfVar = mfVar2;
                        }
                    } else {
                        if (mfVar instanceof mf) {
                            sfVar.e();
                        } else {
                            sfVar.f();
                        }
                        if (arrayDeque.isEmpty()) {
                            return mfVar;
                        }
                        mfVar = (nf) arrayDeque.removeLast();
                    }
                }
                break;
            case 23:
                BitSet bitSet = new BitSet();
                sfVar.a();
                int iV5 = sfVar.v();
                int i8 = 0;
                while (iV5 != 2) {
                    int iC4 = lo.c(iV5);
                    if (iC4 == 5 || iC4 == 6) {
                        int iN2 = sfVar.n();
                        if (iN2 == 0) {
                            zL = false;
                        } else {
                            if (iN2 != 1) {
                                z6.h("Invalid bitset value ", iN2, ", expected 0 or 1; at path ", sfVar.h(true));
                                return null;
                            }
                            zL = true;
                        }
                    } else {
                        if (iC4 != 7) {
                            z6.p("Invalid bitset value type: ", lo.d(iV5), "; at path ", sfVar.h(false));
                            return null;
                        }
                        zL = sfVar.l();
                    }
                    if (zL) {
                        bitSet.set(i8);
                    }
                    i8++;
                    iV5 = sfVar.v();
                }
                sfVar.e();
                return bitSet;
            case 24:
                int iV6 = sfVar.v();
                if (iV6 != 9) {
                    return iV6 == 6 ? Boolean.valueOf(Boolean.parseBoolean(sfVar.t())) : Boolean.valueOf(sfVar.l());
                }
                sfVar.r();
                return null;
            case 25:
                if (sfVar.v() != 9) {
                    return Boolean.valueOf(sfVar.t());
                }
                sfVar.r();
                return null;
            case 26:
                if (sfVar.v() == 9) {
                    sfVar.r();
                    return null;
                }
                try {
                    int iN3 = sfVar.n();
                    if (iN3 <= 255 && iN3 >= -128) {
                        return Byte.valueOf((byte) iN3);
                    }
                    z6.h("Lossy conversion from ", iN3, " to byte; at path ", sfVar.h(true));
                    return null;
                } catch (NumberFormatException e8) {
                    throw new of(e8);
                }
            case 27:
                if (sfVar.v() == 9) {
                    sfVar.r();
                    return null;
                }
                try {
                    int iN4 = sfVar.n();
                    if (iN4 <= 65535 && iN4 >= -32768) {
                        return Short.valueOf((short) iN4);
                    }
                    z6.h("Lossy conversion from ", iN4, " to short; at path ", sfVar.h(true));
                    return null;
                } catch (NumberFormatException e9) {
                    throw new of(e9);
                }
            default:
                if (sfVar.v() == 9) {
                    sfVar.r();
                    return null;
                }
                try {
                    return Integer.valueOf(sfVar.n());
                } catch (NumberFormatException e10) {
                    throw new of(e10);
                }
        }
    }

    @Override // defpackage.pq
    public final void b(tf tfVar, Object obj) throws IOException {
        int i = 0;
        switch (this.a) {
            case 0:
                tfVar.n((Number) obj);
                return;
            case 1:
                Number number = (Number) obj;
                if (number == null) {
                    tfVar.i();
                    return;
                }
                double dDoubleValue = number.doubleValue();
                re.a(dDoubleValue);
                tfVar.l(dDoubleValue);
                return;
            case 2:
                Number numberValueOf = (Number) obj;
                if (numberValueOf == null) {
                    tfVar.i();
                    return;
                }
                float fFloatValue = numberValueOf.floatValue();
                re.a(fFloatValue);
                if (!(numberValueOf instanceof Float)) {
                    numberValueOf = Float.valueOf(fFloatValue);
                }
                tfVar.n(numberValueOf);
                return;
            case 3:
                tfVar.b();
                int length = ((AtomicIntegerArray) obj).length();
                while (i < length) {
                    tfVar.m(r5.get(i));
                    i++;
                }
                tfVar.e();
                return;
            case 4:
                Number number2 = (Number) obj;
                if (number2 == null) {
                    tfVar.i();
                    return;
                } else {
                    tfVar.m(number2.longValue());
                    return;
                }
            case 5:
                Number numberValueOf2 = (Number) obj;
                if (numberValueOf2 == null) {
                    tfVar.i();
                    return;
                }
                if (!(numberValueOf2 instanceof Float)) {
                    numberValueOf2 = Float.valueOf(numberValueOf2.floatValue());
                }
                tfVar.n(numberValueOf2);
                return;
            case 6:
                Number number3 = (Number) obj;
                if (number3 == null) {
                    tfVar.i();
                    return;
                } else {
                    tfVar.l(number3.doubleValue());
                    return;
                }
            case 7:
                Character ch = (Character) obj;
                tfVar.o(ch != null ? String.valueOf(ch) : null);
                return;
            case 8:
                tfVar.o((String) obj);
                return;
            case 9:
                tfVar.n((BigDecimal) obj);
                return;
            case 10:
                tfVar.n((BigInteger) obj);
                return;
            case 11:
                tfVar.n((xf) obj);
                return;
            case 12:
                StringBuilder sb = (StringBuilder) obj;
                tfVar.o(sb != null ? sb.toString() : null);
                return;
            case 13:
                throw new UnsupportedOperationException("Attempted to serialize java.lang.Class: " + ((Class) obj).getName() + ". Forgot to register a type adapter?");
            case 14:
                StringBuffer stringBuffer = (StringBuffer) obj;
                tfVar.o(stringBuffer != null ? stringBuffer.toString() : null);
                return;
            case 15:
                URL url = (URL) obj;
                tfVar.o(url != null ? url.toExternalForm() : null);
                return;
            case 16:
                URI uri = (URI) obj;
                tfVar.o(uri != null ? uri.toASCIIString() : null);
                return;
            case 17:
                InetAddress inetAddress = (InetAddress) obj;
                tfVar.o(inetAddress != null ? inetAddress.getHostAddress() : null);
                return;
            case 18:
                UUID uuid = (UUID) obj;
                tfVar.o(uuid != null ? uuid.toString() : null);
                return;
            case 19:
                tfVar.o(((Currency) obj).getCurrencyCode());
                return;
            case 20:
                if (((Calendar) obj) == null) {
                    tfVar.i();
                    return;
                }
                tfVar.c();
                tfVar.g("year");
                tfVar.m(r5.get(1));
                tfVar.g("month");
                tfVar.m(r5.get(2));
                tfVar.g("dayOfMonth");
                tfVar.m(r5.get(5));
                tfVar.g("hourOfDay");
                tfVar.m(r5.get(11));
                tfVar.g("minute");
                tfVar.m(r5.get(12));
                tfVar.g("second");
                tfVar.m(r5.get(13));
                tfVar.f();
                return;
            case 21:
                Locale locale = (Locale) obj;
                tfVar.o(locale != null ? locale.toString() : null);
                return;
            case 22:
                d(tfVar, (nf) obj);
                return;
            case 23:
                BitSet bitSet = (BitSet) obj;
                tfVar.b();
                int length2 = bitSet.length();
                while (i < length2) {
                    tfVar.m(bitSet.get(i) ? 1L : 0L);
                    i++;
                }
                tfVar.e();
                return;
            case 24:
                Boolean bool = (Boolean) obj;
                if (bool == null) {
                    tfVar.i();
                    return;
                }
                tfVar.q();
                tfVar.a();
                tfVar.a.write(bool.booleanValue() ? "true" : "false");
                return;
            case 25:
                Boolean bool2 = (Boolean) obj;
                tfVar.o(bool2 == null ? "null" : bool2.toString());
                return;
            case 26:
                if (((Number) obj) == null) {
                    tfVar.i();
                    return;
                } else {
                    tfVar.m(r5.byteValue());
                    return;
                }
            case 27:
                if (((Number) obj) == null) {
                    tfVar.i();
                    return;
                } else {
                    tfVar.m(r5.shortValue());
                    return;
                }
            default:
                if (((Number) obj) == null) {
                    tfVar.i();
                    return;
                } else {
                    tfVar.m(r5.intValue());
                    return;
                }
        }
    }
}
