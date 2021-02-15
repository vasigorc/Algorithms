#!/bin/bash
set -euo pipefail
BASENAME=$(basename "$0")

eval "$(docopts -A args -V - -h - : "$@" <<EOF
Usage:
  $BASENAME (--input-file=<input_file>)

  $BASENAME --help

Options:
  --input-file=<input_file>  File containing coordinate points
  --help                     Show this message

----
$BASENAME
EOF
)"

# shellcheck disable=SC2154
FILE="${args[--input-file]}"

validate_sbt_is_installed() {
  if ! command -v sbt &> /dev/null
  then
    echo "sbt must be installed in order to run this program"
    exit 1
  fi
}

validate_input_file_is_present() {
  if ! [[ -f $FILE ]]
    then
      echo "File $FILE doesn't exist" >&2
      exit 1
  fi
}

main() {
  validate_sbt_is_installed
  validate_input_file_is_present
  time sbt "runMain ca.vgorcinschi.algorithms2_4.selectionfilter.SelectionFilter $FILE"
}

main
