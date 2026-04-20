from pathlib import Path

from pypdf import PdfReader


reader = PdfReader("starting_files/linux.pdf")
page = reader.pages[0]

main_js = str(page["/AA"]["/O"]["/JS"])
Path("artifacts/main.js").write_text(main_js)

annots = page["/Annots"]
Path("artifacts/annot_index.txt").write_text("")

with Path("artifacts/annot_index.txt").open("w") as handle:
    for index, annot_ref in enumerate(annots):
        annot = annot_ref.get_object()
        field_name = annot.get("/T", f"annot_{index}")
        handle.write(f"[{index}] {field_name}\n")
        if "/AA" not in annot:
            continue
        for action_name, action in annot["/AA"].items():
            if "/JS" not in action:
                continue
            js = str(action["/JS"])
            safe_field_name = str(field_name).replace("/", "_")
            out = Path(f"artifacts/annot_{index}_{safe_field_name}_{action_name[1:]}.js")
            out.write_text(js)
            handle.write(f"  {action_name} -> {out.name} len={len(js)}\n")