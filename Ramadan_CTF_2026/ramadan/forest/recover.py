import pickle
import numpy as np

with open('model.pkl', 'rb') as f:
    rf = pickle.load(f)

n = rf.n_features_in_
assign = {}
contr = []

for ti, est in enumerate(rf.estimators_):
    tr = est.tree_
    left = tr.children_left
    right = tr.children_right
    feat = tr.feature
    thr = tr.threshold

    parent = np.full(tr.node_count, -1, dtype=int)
    is_left = np.zeros(tr.node_count, dtype=bool)
    for p in range(tr.node_count):
        l = left[p]
        r = right[p]
        if l != -1:
            parent[l] = p
            is_left[l] = True
        if r != -1:
            parent[r] = p
            is_left[r] = False

    leaves = np.where(left == -1)[0]
    vals = tr.value[leaves, 0, :]
    p1 = vals[:, 1] / vals.sum(axis=1)
    li = leaves[np.argmax(p1)]
    if p1.max() < 0.5:
        continue

    node = li
    while parent[node] != -1:
        p = parent[node]
        fidx = feat[p]
        t = thr[p]
        go_left = is_left[node]

        if fidx >= 0:
            if abs(t - 0.5) < 1e-9:
                val = 0 if go_left else 1
            elif t < 0:
                val = 0 if go_left else 1
            elif t >= 1:
                val = 1 if go_left else 0
            else:
                val = None

            if val is not None:
                if fidx in assign and assign[fidx] != val:
                    contr.append((ti, fidx, assign[fidx], val, t, go_left))
                assign[fidx] = val
        node = p

print('deduced', len(assign), 'of', n, 'features')
print('contradictions', len(contr))
vec = np.array([assign.get(i, -1) for i in range(n)], dtype=int)
print('vector', ''.join('?' if x < 0 else str(x) for x in vec))
unknown = [i for i, v in enumerate(vec) if v < 0]
print('unknown', unknown)

best = []
for mask in range(1 << len(unknown)):
    x = vec.copy()
    for j, i in enumerate(unknown):
        x[i] = (mask >> j) & 1
    p = rf.predict_proba([x])[0, 1]
    if p > 0.9:
        best.append((p, x.copy()))

best.sort(key=lambda t: t[0], reverse=True)
print('candidates >0.9', len(best))
if best:
    print('top p', best[0][0])
    print('top vec', ''.join(str(int(z)) for z in best[0][1]))
    print('top5', [round(b[0], 5) for b in best[:5]])
