from pypdf import PdfReader
from pypdf.generic import ArrayObject, DictionaryObject, IndirectObject


reader = PdfReader("starting_files/linux.pdf")
root = reader.trailer["/Root"]
seen = set()
results = []


def walk(obj, path="root"):
    if isinstance(obj, IndirectObject):
        key = (obj.idnum, obj.generation)
        if key in seen:
            return
        seen.add(key)
        try:
            obj = obj.get_object()
        except Exception:
            return

    ref = getattr(obj, "indirect_reference", None)
    if ref is not None:
        key = (ref.idnum, ref.generation)
        if key in seen:
            return
        seen.add(key)

    if isinstance(obj, DictionaryObject):
        if obj.get("/S") == "/JavaScript" or "/JS" in obj:
            js = obj.get("/JS")
            try:
                data = js.get_object() if hasattr(js, "get_object") else js
            except Exception:
                data = js
            text = str(data)
            results.append((len(text), path, text[:400]))
        for key, value in obj.items():
            walk(value, f"{path}/{key}")
        return

    if isinstance(obj, ArrayObject):
        for index, value in enumerate(obj):
            walk(value, f"{path}[{index}]")


walk(root)

for index, (length, path, snippet) in enumerate(sorted(results, reverse=True), 1):
    print(f"#{index} len={length} path={path}")
    print(repr(snippet))
    print("---")