#! /bin/bash -eu

BASENAME=$(basename "$0")

eval "$(docopts -A args -V - -h - : "$@" <<EOF
Usage:
  $BASENAME [options] <number_of_lines>

  $BASENAME --help

Arguments:
  <number_of_lines> Number of points

Options:
  --upper-bound=<upper-bound>          Maximum coordinate point value [default: 100]
  --output-location=<output-location>  Output directory [default: ./]
  --filename=<filename>                Resulting filename [default: points.txt]
  --help                               Show this message

----
$BASENAME
EOF
)"

LINES_NUMBER=${args[<number_of_lines>]}
validate_int() {
  if ! [[ $2 =~ ^-?[0-9]+$ ]]
    then
      echo "$1 must be a number" >&2
      exit 1
  fi
}

validate_numeric_input() {
# validate that all numeric user input is valid int
  for i in "number_of_lines $LINES_NUMBER" "upper-bound ${args[--upper-bound]}"
  do
      set -- $i
      validate_int "$1" "$2"
  done
}

# define full file name
define_full_filename() {
  # write the name of the full path to stdout
  # the caller of the function will assign this value
  echo "${args[--output-location]}/${args[--filename]}"
  return 0
}


validate_directory_input() {
  if ! [[ -d ${args[--output-location]} ]]
    then
      echo "Directory ${args[--output-location]} doesn't exist" >&2
      exit 1
  fi
}

# do a for loop until $number_of_lines
# in each iteration:
# generate three stringified decimal numbers
# separate them by a comma
# append thm to $output-location
write_points_to_file() {
  if [[ $# -eq 0 ]]
    then
      echo "Full file path unknown" >&2
      exit 1
  fi

  local file="$1"

  for (( i=0; i<LINES_NUMBER; i+=1 ))
  do
    local line
    for (( j=1; j<=3; j+=1 ))
    do
      local decimal_one=$(generate_decimal_number)
      line+="$decimal_one"
      if ! [[ j -eq 3 ]]
        then
          line+=","
      fi
    done
    echo $line >> "$file"
    line=""
  done
}

generate_random_int() {
  if [[ $# -gt 0 ]]
    then
      validate_int "parameter to generate_random_int" "$1"
      echo -n $(( RANDOM % $1 ))
      exit 0
  fi
  echo -n $(( RANDOM ))
  exit 0
}

# will generate decimal part of a decimal number
# it is hardcoded to be two digits only
generate_random_decimal_part() {
  echo -n $(generate_random_int 100)
  exit 0
}

# will generate whole part of a decimal number
# bounded by the bound
generate_random_whole_part() {
  let "REAL_BOUND=${args[--upper-bound]} - 1"
  echo -n $(generate_random_int "$REAL_BOUND")
  exit 0
}

generate_decimal_number() {
  local whole_part=$(generate_random_whole_part)
  local decimal_part=$(generate_random_decimal_part)
  local sign
  if [[ $(generate_random_int 2) -eq 1 ]]
    then
      sign="-"
  else
      sign=""
  fi
  echo -n "$sign$whole_part.$decimal_part"
  exit 0
}

main() {
  validate_numeric_input
  validate_directory_input
  full_file_name=$(define_full_filename)
  write_points_to_file $full_file_name
}

main
