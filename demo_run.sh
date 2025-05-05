#!/bin/sh

set -e

# 0. Create a temporary directory for the demo
TMPDIR=$(mktemp -d)
echo "Using temp dir: $TMPDIR"
cleanup() {
    echo "Cleaning up $TMPDIR"
    rm -rf "$TMPDIR"
}
trap cleanup EXIT

cd "$TMPDIR"

# 1. Clone Repositories
git clone https://github.com/nickrallison/chiselware_lints.git
git clone https://github.com/The-Chiselers/uart.git

# 2. Publish chiselware_lints locally
cd chiselware_lints
sbt clean publishLocal
cd ..

# 4. Compile with scalafix rules
cd uart
git fetch origin scalafix
git checkout scalafix
sbt clean compile