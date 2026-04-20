import sys as _sys

# Register vendor subpackages under their original bare names so that:
#  1) pickle.Unpickler.find_class() resolves types stored as "torch_utils.*",
#     "training.*", or "dnnlib.*" in legacy checkpoint files.
#  2) exec()'d source code inside torch_utils.persistence._src_to_module()
#     can successfully import from the original module paths.
_ALIASES = {
    'torch_utils':  'vendor.torch_utils',
    'training':     'vendor.training',
    'dnnlib':       'vendor.dnnlib',
}

def _install_aliases():
    from vendor import torch_utils, training, dnnlib  # noqa: F401
    for bare, vendored in _ALIASES.items():
        if bare not in _sys.modules:
            _sys.modules[bare] = _sys.modules[vendored]
        # Also alias submodules that are already loaded
        for key, mod in list(_sys.modules.items()):
            if key.startswith(vendored + '.'):
                bare_key = bare + key[len(vendored):]
                if bare_key not in _sys.modules:
                    _sys.modules[bare_key] = mod

_install_aliases()
