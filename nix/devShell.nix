{
  mkShell,
  alejandra,
  bash,
  jdk23,
  just
}:
mkShell rec {
  name = "network-infra-lab-06";

  packages = [
    # Our Makefile requires a modern bash. If the developer computer is
    # running macOS then it ships with an old broken version of bash.
    # This ensures that the Makefile works. Alternately, we can just
    # fix the Makefile.
    bash
    jdk23

    just

    # Required for CI for format checking.
    alejandra
  ];
}
