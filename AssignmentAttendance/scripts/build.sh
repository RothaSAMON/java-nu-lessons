#!/usr/bin/env bash
set -euo pipefail

PROJECT_ROOT="$(cd "$(dirname "$0")/.." && pwd)"
LIB="$PROJECT_ROOT/lib"
SRC="$PROJECT_ROOT/src"
OUT="$PROJECT_ROOT/out"

mkdir -p "$OUT"

javac -version || true

find "$SRC" -name "*.java" -exec javac -cp "$LIB/sqlite-jdbc-3.46.0.0.jar" -d "$OUT" {} +

echo "Build complete: $OUT"

