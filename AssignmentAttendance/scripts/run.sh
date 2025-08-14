#!/usr/bin/env bash
set -euo pipefail

PROJECT_ROOT="$(cd "$(dirname "$0")/.." && pwd)"
LIB="$PROJECT_ROOT/lib"
OUT="$PROJECT_ROOT/out"

CLASSPATH="$OUT:$LIB/sqlite-jdbc-3.46.0.0.jar:$LIB/slf4j-api-2.0.13.jar:$LIB/slf4j-simple-2.0.13.jar"

java -cp "$CLASSPATH" attendance.ui.MainFrame

