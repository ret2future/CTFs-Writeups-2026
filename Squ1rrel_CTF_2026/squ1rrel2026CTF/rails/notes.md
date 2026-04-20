# rails

## Challenge Summary
- Given: A live web service at `https://rails.squ1rrel.dev` and a source archive `gooner.zip`.
- Goal: Retrieve the hidden flag in the Rails app.
- Constraints: Flag format is `squ1rrel{...}`.

## Initial Recon / Triage
- Observations: The app exposes a public `GET /show/:resource` route and an admin-only `GET /admin/posts` route.
- File identification: The archive contains a Rails app with custom middleware and a small dynamic module loader.
- Entry points: `app/controllers/show_controller.rb`, `lib/middleware/admin_auth.rb`, `config/jwt.rb`, `config/initializers/misc.rb`, and `app/controllers/admin/posts_controller.rb`.

## Hypotheses & Approach
- Hypothesis 1: The `show/:resource` route can instantiate unintended classes because it does `constantize` on attacker-controlled input.
- Hypothesis 2: If the JWT secret used by the admin middleware can be disclosed, any valid cookie will satisfy admin auth because the middleware only verifies signature validity.

## Execution Steps (Reproducible)

### Stage 1
Commands:
```bash
cd /root/squ1rrel2026CTF/rails/artifacts/rails-ctf-dist
sed -n '1,120p' app/controllers/show_controller.rb
sed -n '1,120p' config/jwt.rb
sed -n '1,120p' config/initializers/misc.rb
sed -n '1,160p' lib/middleware/admin_auth.rb
sed -n '1,120p' app/controllers/admin/posts_controller.rb
```

Results:
- `ShowController#index` builds `resource_name = params[:resource] + "Module"` and calls `resource_name.constantize.new.show`.
- `config/initializers/misc.rb` defines `class JWTModule < JWTSecret; end`.
- `JWTSecret#show` returns the signing secret.
- The admin middleware decodes the `auth` cookie with that secret but never checks claims.
- `Admin::PostsController#index` blocks only the exact string `"1"`, then runs `Post.where(id: params[:id]).first`.

### Stage 2
Commands:
```bash
python3 artifacts/exploit.py
```

Results:
- The script fetches `https://rails.squ1rrel.dev/show/JWT` to leak the signing secret.
- It forges a valid HS256 JWT for the `auth` cookie.
- It requests `https://rails.squ1rrel.dev/admin/posts?id[]=1` so the guard `id == "1"` is bypassed while ActiveRecord still resolves post `1`.
- The response returns the reserved post containing the flag.

## Artifacts Produced
- `artifacts/rails-ctf-dist/` - extracted source archive.
- `artifacts/exploit.py` - end-to-end exploit script.

## Flag
```
squ1rrel{rails?_in_my_ctf???}
```
